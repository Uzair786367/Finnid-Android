package com.infotech.finnid.Recharge;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infotech.finnid.Activity.PurchaseVoucherActivity;
import com.infotech.finnid.Adapter.CirCleAdapter;
import com.infotech.finnid.Adapter.ItemizedAdapter;
import com.infotech.finnid.Adapter.OperatorAdapter;
import com.infotech.finnid.ApiResponse.CountryListResponse;
import com.infotech.finnid.ApiResponse.GetSalesVoucherDatum;
import com.infotech.finnid.ApiResponse.GetSalesVoucherResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.CountryNameData;
import com.infotech.finnid.R;
import com.infotech.finnid.Recharge.ApiRespose.OPDatumRes;
import com.infotech.finnid.Recharge.ApiRespose.OperatorRespose;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrepaidRechageActivity extends AppCompatActivity implements View.OnClickListener {
EditText number;
    TextView toolbar_text;
    EditText amount;
    ImageView toolbar_im;
    String type;
    String serviceName;
    String serviceId;
    TextView bt_recharge ;
    String type_id;
    TextView operator;
    String response_api;
    int paymentModeId=0;
    int Bank_Id=0;
    String response_api_CircleCode;

    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;

    OperatorAdapter mAdapter;
    ArrayList<OPDatumRes> transactionsObjects = new ArrayList<>();
    OperatorRespose transactions = new OperatorRespose();
    CirCleAdapter mAdapter_CirCle;
    ArrayList<OPDatumRes> transactionsObjects_CirCle = new ArrayList<>();
    OperatorRespose transactions_CirCle = new OperatorRespose();


    LinearLayout li_circle;
    TextView tv_circle;
    String op_SpKey="0" , op_circleCode="";
    Spinner spin_PaymentMode , spin_bank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prepaid_rechage);

        getid();

    }

    private void getid() {

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        tokenManager = new PreferencesManager(this);



        type = getIntent().getStringExtra("type");
        serviceName = getIntent().getStringExtra("serviceName");
        serviceId = getIntent().getStringExtra("serviceId");

        toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_im = findViewById(R.id.toolbar_im);
        bt_recharge =  findViewById(R.id.bt_recharge);
        operator =  findViewById(R.id.operator);
        tv_circle =  findViewById(R.id.tv_circle);
        li_circle =  findViewById(R.id.li_circle);
        amount =  findViewById(R.id.amount);
        spin_bank =  findViewById(R.id.spin_bank);
        spin_PaymentMode =  findViewById(R.id.spin_PaymentMode);
        number =  findViewById(R.id.number);


        li_circle.setVisibility(View.GONE);

        toolbar_text.setText(type+" Recharge");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        if(type.toString().trim().equalsIgnoreCase("Mobile")){

            type_id="1";


        }else  if(type.toString().trim().equalsIgnoreCase("Dth")){

            type_id="0";


        }else  if(type.toString().trim().equalsIgnoreCase("Electricity")){

            type_id="0";


        }else  if(type.toString().trim().equalsIgnoreCase("Gas")){

            type_id="0";


        }else  if(type.toString().trim().equalsIgnoreCase("Water")){

            type_id="0";


        }else  if(type.toString().trim().equalsIgnoreCase("Broadband")){

            type_id="0";


        }else  if(type.toString().trim().equalsIgnoreCase("Insurance")){

            type_id="0";


        }else  if(type.toString().trim().equalsIgnoreCase("Landline")){

            type_id="0";


        } else  if(type.toString().trim().equalsIgnoreCase("Postpaid")){

            type_id="2";


        } else  if(type.toString().trim().equalsIgnoreCase("Water")){

            type_id="0";


        } else  if(type.toString().trim().equalsIgnoreCase("FASTag")){

            type_id="0";


        }


        bt_recharge.setOnClickListener(this);
        operator.setOnClickListener(this);
        tv_circle.setOnClickListener(this);

        hit_operator(type_id);
        hit_GetCircleCode(type_id);
        Get_Payment_mode();
        Get_BankList();



    }

    ArrayList<String> payment_mode_arealist = new ArrayList<String>();
    ArrayList<String> bankList_arealist = new ArrayList<String>();

    private void Get_Payment_mode() {

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.PAymentMode("Bearer " + tokenManager.getAccessToken() );
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        payment_mode_arealist.clear();
                        payment_mode_arealist.add("Select");

                        List<CountryNameData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    payment_mode_arealist.add(stateListData.get(i).getName());

                                }
                            }

                            spin_PaymentMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            paymentModeId = 0;

                                        } else {

                                            paymentModeId = stateListData.get(position - 1).getId();

                                        }
                                    }
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PrepaidRechageActivity.this, R.layout.spinner_item, payment_mode_arealist);
                                spin_PaymentMode.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }

                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<CountryListResponse> call, Throwable t) {

            }
        });

    }

    private void Get_BankList() {

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.GetSubPaymentMode("Bearer " + tokenManager.getAccessToken() );
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        bankList_arealist.clear();
                        bankList_arealist.add("Select");

                        List<CountryNameData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    bankList_arealist.add(stateListData.get(i).getBankInfo());

                                }
                            }

                            spin_bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub

                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            Bank_Id = 0;

                                        } else {

                                            Bank_Id = stateListData.get(position - 1).getId();

                                        }
                                    }
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PrepaidRechageActivity.this, R.layout.spinner_item, bankList_arealist);
                                spin_bank.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }

                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<CountryListResponse> call, Throwable t) {

            }
        });

    }

    private void hit_operator(String typeId) {

        JsonObject postParam = new JsonObject();
        postParam.addProperty("type",  typeId);

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<OperatorRespose> call = git.GetOperator("Bearer " + tokenManager.getAccessToken() , postParam);
        call.enqueue(new Callback<OperatorRespose>() {
            @Override
            public void onResponse(Call<OperatorRespose> call, Response<OperatorRespose> response) {
                if (response.isSuccessful()) {

                    response_api =""+ new Gson().toJson(response.body()).toString();

                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<OperatorRespose> call, Throwable t) {

            }
        });

    }
    private void hit_GetCircleCode(String typeId) {

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<OperatorRespose> call = git.GetCircleCode("Bearer " + tokenManager.getAccessToken() );
        call.enqueue(new Callback<OperatorRespose>() {
            @Override
            public void onResponse(Call<OperatorRespose> call, Response<OperatorRespose> response) {
                if (response.isSuccessful()) {

                    response_api_CircleCode =""+ new Gson().toJson(response.body()).toString();

                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<OperatorRespose> call, Throwable t) {

            }
        });

    }
    private void HitApi_DoRechage(Integer paymentModeId ,Integer bankId ,String serviceName ,String accountNo ,
                                  String serviceId ,String outletId ,String amount ,  String spKey ,String pincode,String latlong  ) {

        JsonObject postParam = new JsonObject();
        postParam.addProperty("paymentModeId",  paymentModeId);
        postParam.addProperty("bankId",  bankId);
        postParam.addProperty("serviceName",  serviceName);
        postParam.addProperty("accountNo",  accountNo);
        postParam.addProperty("serviceId",  serviceId);
        postParam.addProperty("outletId",  outletId);
        postParam.addProperty("amount",  amount);
        postParam.addProperty("spKey",  spKey);
        postParam.addProperty("pincode",  pincode);
        postParam.addProperty("latlong",  latlong);

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<OperatorRespose> call = git.DoRechage("Bearer " + tokenManager.getAccessToken() , postParam );
        call.enqueue(new Callback<OperatorRespose>() {
            @Override
            public void onResponse(Call<OperatorRespose> call, Response<OperatorRespose> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(PrepaidRechageActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    //  response_api_CircleCode =""+ new Gson().toJson(response.body()).toString();

                } else {

                    Toast.makeText(PrepaidRechageActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<OperatorRespose> call, Throwable t) {

            }
        });

    }

    private void handleTokenExpiration(Response<?> response) {
        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onClick(View view) {

        if(view==operator){

            Operator_Dialog();

        }

        if(view==tv_circle){

            circle_Dialog();

        }

        if(view==bt_recharge){
            
            if(number.getText().toString().trim().isEmpty()){

                Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                
            }else if(op_SpKey.toString().trim().equalsIgnoreCase("0")){
                Toast.makeText(this, "Select Operator", Toast.LENGTH_SHORT).show();

            } else if(paymentModeId==0){
                Toast.makeText(this, "Select Payment Mode", Toast.LENGTH_SHORT).show();

            } else if(Bank_Id==0){
                Toast.makeText(this, "Select Bank", Toast.LENGTH_SHORT).show();

            } else {

                HitApi_DoRechage( paymentModeId , Bank_Id , serviceName , number.getText().toString().trim()+"" , serviceId , "0" , amount.getText().toString().trim() , op_SpKey , "229001", "26.865730" );
            }
        }

    }

    private void circle_Dialog() {

        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewa = inflater.inflate(R.layout.operator_dialog, null);

        RecyclerView recycler_op = (RecyclerView) viewa.findViewById(R.id.recycler_op);
        ImageView  im_cancel = (ImageView) viewa.findViewById(R.id.im_cancel);
        TextView  tttt = (TextView) viewa.findViewById(R.id.tttt);
        tttt.setText("Select Circle" );


        final BottomSheetDialog dialog = new BottomSheetDialog(this);

        dialog.setCancelable(false);
        dialog.setContentView(viewa);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        transactions_CirCle = new Gson().fromJson(response_api_CircleCode, OperatorRespose.class);
        transactionsObjects_CirCle = transactions_CirCle.getData();

        if (transactionsObjects_CirCle.size() > 0) {

            mAdapter_CirCle = new CirCleAdapter(transactionsObjects_CirCle, this , dialog , tv_circle );
            recycler_op.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
            recycler_op.setItemAnimator(new DefaultItemAnimator());
            recycler_op.setAdapter( mAdapter_CirCle);
            recycler_op.setVisibility(View.VISIBLE);
            //  nodatafound.setVisibility(View.GONE);

        } else {

            //   nodatafound.setVisibility(View.VISIBLE);
            recycler_op.setVisibility(View.GONE);

        }


        im_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }


    private void Operator_Dialog() {

        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewa = inflater.inflate(R.layout.operator_dialog, null);

        RecyclerView recycler_op = (RecyclerView) viewa.findViewById(R.id.recycler_op);
        ImageView  im_cancel = (ImageView) viewa.findViewById(R.id.im_cancel);


        final BottomSheetDialog dialog = new BottomSheetDialog(this);

        dialog.setCancelable(false);
        dialog.setContentView(viewa);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        transactions = new Gson().fromJson(response_api, OperatorRespose.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {

            mAdapter = new OperatorAdapter(transactionsObjects, this , dialog , operator );
            recycler_op.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
            recycler_op.setItemAnimator(new DefaultItemAnimator());
            recycler_op.setAdapter( mAdapter);
            recycler_op.setVisibility(View.VISIBLE);
            //  nodatafound.setVisibility(View.GONE);

        } else {

            //   nodatafound.setVisibility(View.VISIBLE);
            recycler_op.setVisibility(View.GONE);

        }


        im_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    public void ItemClick_op(String SpKey) {

        op_SpKey = SpKey;

        li_circle.setVisibility(View.VISIBLE);

    }

    public void ItemClick_CirCle(String circleCode) {

        op_circleCode = circleCode;



    }
}