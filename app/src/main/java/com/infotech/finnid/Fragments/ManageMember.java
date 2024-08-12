package com.infotech.finnid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infotech.finnid.Adapter.FieldUOMDataAdapter;
import com.infotech.finnid.Adapter.MemberListAdapter;
import com.infotech.finnid.ApiRequest.MemberListRequest;
import com.infotech.finnid.ApiResponse.FieldUOMResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.MemberListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.FieldUOMData;
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
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageMember extends Fragment {
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


    public ManageMember() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_manage_member, container, false);
        tokenManager = new PreferencesManager(requireContext());
        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(getContext());
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);
        noDataView = rootView.findViewById(R.id.noDataView);
        noNetworkView = rootView.findViewById(R.id.noNetworkView);
        mCustomFilterDialog = new CustomAlertDialog(getActivity());
        search = rootView.findViewById(R.id.search);
        recycler_view = rootView.findViewById(R.id.recycler_view);
        mAdapter = new MemberListAdapter(members, getContext());
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setAdapter(mAdapter);
        startDateView= rootView.findViewById(R.id.startDateView);
        endDateView= rootView.findViewById(R.id.endDateView);
        startDateView.setOnClickListener(v -> mCustomFilterDialog.setDCFromDate(startDate));
        endDateView.setOnClickListener(v -> mCustomFilterDialog.setDCToDate( endDate));
        startDate = rootView.findViewById(R.id.startDate);
        endDate = rootView.findViewById(R.id.endDate);

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

        return rootView;
    }
    private void memberList() {

        ApiUtilMethods.INSTANCE.showloader(getActivity());


        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<MemberListResponse> call = git.GetMemberList("Bearer " + tokenManager.getAccessToken(),new MemberListRequest(fromDate,toDate,mLoginResponse.getData().getUserName()));
        call.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(getActivity());

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

                ApiUtilMethods.INSTANCE.hideLoader(getActivity());


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
            ApiUtilMethods.INSTANCE.Error(getContext(), "No Record Found ! between \n " + fromDate + " to " + toDate);
        }*/
    }

    private void handleTokenExpiration(Response<?> response) {
        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        }
    }
}