package com.infotech.finnid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infotech.finnid.Activity.ManageMemberActivity;
import com.infotech.finnid.Adapter.ItemizedAdapter;
import com.infotech.finnid.ApiRequest.MemberListRequest;
import com.infotech.finnid.ApiResponse.GetSalesVoucherDatum;
import com.infotech.finnid.ApiResponse.GetSalesVoucherResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.MemberListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.MemberData;
import com.infotech.finnid.R;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemizedFragment extends Fragment implements View.OnClickListener {

    RecyclerView recycler_view;
    private PreferencesManager tokenManager;
    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;

    ItemizedAdapter mAdapter;
    ArrayList<GetSalesVoucherDatum> transactionsObjects = new ArrayList<>();
    GetSalesVoucherResponse transactions = new GetSalesVoucherResponse();
    RadioButton Combo , Product ,Loose ,  Service ,  Stock ;
    ImageView nodatafound;
    EditText ed_search;
    TextView tv_search;
    String type_search="WarehouseStock";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_itemized, container, false);

        find_by_id(v);

        return v;

    }

    private void find_by_id(View v) {

        tokenManager = new PreferencesManager(getActivity());
        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(getActivity());
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        recycler_view = v.findViewById(R.id.recycler_view);
        Combo = v.findViewById(R.id.Combo);
        Product = v.findViewById(R.id.Product);
        Loose = v.findViewById(R.id.Loose);
        Service = v.findViewById(R.id.Service);
        Stock = v.findViewById(R.id.Stock);
        nodatafound = v.findViewById(R.id.nodatafound);
        ed_search = v.findViewById(R.id.ed_search);
        tv_search = v.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);

        Combo.setOnClickListener(this);
        Product.setOnClickListener(this);
        Loose.setOnClickListener(this);
        Service.setOnClickListener(this);
        Stock.setOnClickListener(this);

        HitApi("WarehouseStock","");

    }

    private void HitApi(String type , String itemFilter) {

        ApiUtilMethods.INSTANCE.showloader(getActivity());

        JsonObject postParam = new JsonObject();
        postParam.addProperty("id", "0");
        postParam.addProperty("type", type);
        postParam.addProperty("itemFilter", itemFilter);

        Log.e("salesvoucher","hi postParam :  "+postParam);


        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<GetSalesVoucherResponse> call = git.GetSalesVoucherItemDetails("Bearer " + tokenManager.getAccessToken(), postParam);
        call.enqueue(new Callback<GetSalesVoucherResponse>() {
            @Override
            public void onResponse(Call<GetSalesVoucherResponse> call, Response<GetSalesVoucherResponse> response) {
                Log.e("salesvoucher","hi  :  "+ response.isSuccessful());


                ApiUtilMethods.INSTANCE.hideLoader(getActivity());

                Log.e("salesvoucher","hi  :  "+ response.isSuccessful());

                if (response.isSuccessful()) {

                    nodatafound.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);

                    dataParse(new Gson().toJson(response.body()).toString());


                } else {
                    nodatafound.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);

                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<GetSalesVoucherResponse> call, Throwable t) {

                Log.e("salesvoucher","hi Failure :  "+ t.getMessage());
                Toast.makeText(getContext(), "Exception", Toast.LENGTH_SHORT).show();


                ApiUtilMethods.INSTANCE.hideLoader(getActivity());

            }
        });
    }

    private void dataParse(String response) {

        transactions = new Gson().fromJson(response, GetSalesVoucherResponse.class);
        transactionsObjects = transactions.getData();

        Log.e("salesvoucher","hi size  :  "+ transactionsObjects.size());


        if (transactionsObjects.size() > 0) {

            mAdapter = new ItemizedAdapter(transactionsObjects, getActivity()  );
            recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter( mAdapter);
            recycler_view.setVisibility(View.VISIBLE);
            nodatafound.setVisibility(View.GONE);

        } else {

            nodatafound.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);

        }

    }

    private void handleTokenExpiration(Response<?> response) {
        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }


    @Override
    public void onClick(View view) {

        if(view==tv_search){

            HitApi(""+type_search,""+ed_search.getText().toString().toString());

        }

        if(view==Combo){

            type_search = "Combo";

            HitApi("Combo","");
        }

        if(view==Product){

            type_search = "Product";

            HitApi("Product","");
        }

        if(view==Loose){


            type_search = "LooseSale";
            HitApi("LooseSale","");
        }

        if(view==Service){

            type_search = "Service";
            HitApi("Service","");
        }

        if(view==Stock){

            type_search = "WarehouseStock";

            HitApi("WarehouseStock","");
        }
    }
}