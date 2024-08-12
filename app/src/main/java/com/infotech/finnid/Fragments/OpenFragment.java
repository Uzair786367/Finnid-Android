package com.infotech.finnid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.infotech.finnid.Activity.PurchaseVoucherActivity;
import com.infotech.finnid.ApiRequest.InventoryPurchaseRequest;
import com.infotech.finnid.ApiRequest.PaymentDetail;
import com.infotech.finnid.ApiResponse.FieldUOMResponse;
import com.infotech.finnid.ApiResponse.InventorySaleRequest.CompanyVoucherPaymentDetail;
import com.infotech.finnid.ApiResponse.InventorySaleRequest.DenominationIn;
import com.infotech.finnid.ApiResponse.InventorySaleRequest.DenominationOut;
import com.infotech.finnid.ApiResponse.InventorySaleRequest.InventorySaleRequest;
import com.infotech.finnid.ApiResponse.InventorySaleRequest.SaleVoucherItemDetail;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.MemberListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.FieldUOMData;
import com.infotech.finnid.R;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenFragment extends Fragment implements View.OnClickListener {

    EditText Particulars , HSN,  Qty ,   Rate  , Discount , GSTRate , CESSRate ;
    TextView tv_submit;

    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;

    int fieldUomType_id=0;
    Spinner spin_fieldUomType;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_open, container, false);

        getid(v);

        return v;
    }

    private void getid(View v) {

        Particulars =v.findViewById(R.id.Particulars);
        HSN =v.findViewById(R.id.HSN);
        Qty =v.findViewById(R.id.Qty);
        Rate =v.findViewById(R.id.Rate);
         Discount =v.findViewById(R.id.Discount);
        GSTRate =v.findViewById(R.id.GSTRate);
        CESSRate =v.findViewById(R.id.CESSRate);
        tv_submit =v.findViewById(R.id.tv_submit);
        spin_fieldUomType =v.findViewById(R.id.spin_fieldUomType);

        tv_submit.setOnClickListener(this);

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(getActivity());
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        tokenManager = new PreferencesManager(getActivity());
        fieldUOMType();

    }

    ArrayList<String> fieldUom_arealist = new ArrayList<String>();


    private void fieldUOMType() {
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<FieldUOMResponse> call = git.GetFieldUOM("Bearer " + tokenManager.getAccessToken());
        call.enqueue(new Callback<FieldUOMResponse>() {
            @Override
            public void onResponse(Call<FieldUOMResponse> call, Response<FieldUOMResponse> response) {
                if (response.isSuccessful()) {
                    FieldUOMResponse fieldUOMResponse = response.body();
                    if (fieldUOMResponse != null) {

                        Set_fieldUomType(new Gson().toJson(response.body()));


                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<FieldUOMResponse> call, Throwable t) {

            }
        });

    }

    FieldUOMResponse transactions2 = new FieldUOMResponse();
    ArrayList<FieldUOMData> operator2 = new ArrayList<>();


    public void Set_fieldUomType(String response) {

        fieldUom_arealist.clear();

        fieldUom_arealist.add("Select");

        Gson gson = new Gson();
        transactions2 = gson.fromJson(response, FieldUOMResponse.class);
        operator2 = transactions2.getData();

        if (operator2.size() > 0) {

            if (operator2 != null && operator2.size() > 0) {

                for (int i = 0; i < operator2.size(); i++) {

                    fieldUom_arealist.add(operator2.get(i).getUnit());

                }
            }


            spin_fieldUomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub

                    if (parent.getItemAtPosition(position).toString().equals("Select")) {
                        fieldUomType_id = 0;
                    } else {

                        fieldUomType_id = operator2.get(position - 1).getId();


                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });

            try {
                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, fieldUom_arealist);
                spin_fieldUomType.setAdapter(countryAdapter_job);
            } catch (Exception e) {

            }

        } else {

        }
    }

    @Override
    public void onClick(View view) {



        /*    EditText Particulars , HSN,  Qty ,   Rate , UOM , Discount , GSTRate , CESSRate ;
         */

        if(view==tv_submit){

            if(Particulars.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter Particulars", Toast.LENGTH_SHORT).show();
            }else if(HSN.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter HSN", Toast.LENGTH_SHORT).show();
            }else if(Qty.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter Qty", Toast.LENGTH_SHORT).show();
            }else if(Rate.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter Rate", Toast.LENGTH_SHORT).show();
            }else if(fieldUomType_id==0){
                Toast.makeText(getActivity(), "Select UOM", Toast.LENGTH_SHORT).show();
            }else if(Discount.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter Discount", Toast.LENGTH_SHORT).show();
            }else if(GSTRate.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter GST Rate", Toast.LENGTH_SHORT).show();
            }else if(CESSRate.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Enter CESS Rate", Toast.LENGTH_SHORT).show();
            }else {

                Hit_Submit();

            }


        }


    }

    ArrayList<CompanyVoucherPaymentDetail> companyVoucherPaymentDetails  = new ArrayList<>();
    ArrayList<SaleVoucherItemDetail> saleVoucherItemDetails  = new ArrayList<>();
    ArrayList<DenominationIn> denominationIn  = new ArrayList<>();
    ArrayList<DenominationOut> denominationOut  = new ArrayList<>();


    private void  Hit_Submit() {
        // Particulars , HSN,  Qty ,   Rate , UOM , Discount , GSTRate , CESSRate

        int rate_int = Integer.parseInt(Rate.getText().toString().trim());
        int gst_int = Integer.parseInt(GSTRate.getText().toString().trim());
        int cess_int = Integer.parseInt(CESSRate.getText().toString().trim());

        companyVoucherPaymentDetails.add(new CompanyVoucherPaymentDetail(0, 0,0,0,0,"" ));
        denominationIn.add(new DenominationIn(0, 0));
        denominationOut.add(new DenominationOut(0, 0  ));

        saleVoucherItemDetails.add(new SaleVoucherItemDetail("0",
                Particulars.getText().toString().trim()+"",
                1,"0","0",
                rate_int,gst_int,rate_int,0,0,0,"0",0,"0",
                rate_int,
                0,
                0,
                0,
                rate_int,
                "0",
                "0",
                "0","0","0",
                HSN.getText().toString().trim()+"",
                "0",
                "0"  ));



        ApiUtilMethods.INSTANCE.showloader(getActivity());

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<MemberListResponse> call = git.InventorySale("Bearer " + tokenManager.getAccessToken() ,
                new InventorySaleRequest(  0,
                        "",
                        "",
                        true,
                         "",
                        "",
                        "",
                        0,
                         "",
                        "",
                        "",
                         "",
                          companyVoucherPaymentDetails,
                          saleVoucherItemDetails,
                          denominationIn,
                          denominationOut));
        call.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(getActivity());

                if (response.isSuccessful()) {

                    ApiUtilMethods.INSTANCE.SussesDialog(getActivity(), response.body().getMessage(),getActivity());

                } else {

                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(getActivity());

            }
        });
    }

    private void handleTokenExpiration(Response<?> response) {
        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }


}