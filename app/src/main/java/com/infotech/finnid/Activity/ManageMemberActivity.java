package com.infotech.finnid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infotech.finnid.Adapter.MemberListAdapter;
import com.infotech.finnid.ApiRequest.MemberListRequest;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.MemberListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.MemberData;
import com.infotech.finnid.R;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.CustomAlertDialog;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageMemberActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    MemberListAdapter mAdapter;
    ArrayList<MemberData> members = new ArrayList<>();
    private PreferencesManager tokenManager;
    AppCompatTextView startDate,endDate;
    private String fromDate = "",toDate = "";
    LoginResponse mLoginResponse;
    PreferencesManager preferencesManager;
    private CustomAlertDialog mCustomFilterDialog;
    LinearLayout startDateView,endDateView;
    TextView search;
    View noDataView, noNetworkView;
    ImageView toolbar_im;
    TextView toolbar_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_member);

        tokenManager = new PreferencesManager(this);
        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(ManageMemberActivity.this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);
        noDataView = findViewById(R.id.noDataView);
        noNetworkView = findViewById(R.id.noNetworkView);
        mCustomFilterDialog = new CustomAlertDialog(ManageMemberActivity.this);
        search = findViewById(R.id.search);
        recycler_view = findViewById(R.id.recycler_view);
        mAdapter = new MemberListAdapter(members, ManageMemberActivity.this);
        recycler_view.setLayoutManager(new LinearLayoutManager(ManageMemberActivity.this));
        recycler_view.setAdapter(mAdapter);
        startDateView= findViewById(R.id.startDateView);
        endDateView= findViewById(R.id.endDateView);
        startDateView.setOnClickListener(v -> mCustomFilterDialog.setDCFromDate(startDate));
        endDateView.setOnClickListener(v -> mCustomFilterDialog.setDCToDate( endDate));
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);

        toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_im = findViewById(R.id.toolbar_im);



        toolbar_text.setText("Manage Member");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fromDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date());
        toDate = fromDate;
        startDate.setText(fromDate);
        endDate.setText(toDate);

        memberList();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDate = startDate.getText().toString();
                toDate = endDate.getText().toString();
                memberList();
            }
        });


    }
    private void memberList() {

        ApiUtilMethods.INSTANCE.showloader(ManageMemberActivity.this);


        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<MemberListResponse> call = git.GetMemberList("Bearer " + tokenManager.getAccessToken(),new MemberListRequest(fromDate,toDate,mLoginResponse.getData().getUserName()));
        call.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(ManageMemberActivity.this);

                if (response.isSuccessful()) {
                    MemberListResponse memberListResponse = response.body();
                    if (memberListResponse != null) {
                        dataParse(memberListResponse);
                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(ManageMemberActivity.this);

            }
        });
    }


    public void dataParse(MemberListResponse memberListResponse) {


        if (memberListResponse != null && memberListResponse.getData() != null) {
            members.clear();
            members.addAll(memberListResponse.getData());
        }
        if (members.size() > 0) {
            noDataView.setVisibility(View.GONE);
            noNetworkView.setVisibility(View.GONE);
            recycler_view.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();

        }/* else {
            setInfoHideShow(ApiUtilMethods.INSTANCE.ERROR_OTHER);
            ApiUtilMethods.INSTANCE.Error(ManageMemberActivity.this, "No Record Found ! between \n " + fromDate + " to " + toDate);
        }*/
    }


    private void handleTokenExpiration(Response<?> response) {
        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(ManageMemberActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}