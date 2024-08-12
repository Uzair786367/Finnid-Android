package com.infotech.finnid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infotech.finnid.Adapter.CartListAdapter;
import com.infotech.finnid.Adapter.ItemizedAdapter;
import com.infotech.finnid.ApiResponse.CountryListResponse;
import com.infotech.finnid.ApiResponse.GetSalesVoucherDatum;
import com.infotech.finnid.ApiResponse.GetSalesVoucherResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.CountryNameData;
import com.infotech.finnid.R;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spin_Customer , spin_Ship;
    LinearLayout li_cashSale , li_other;
    RadioButton rd_cssh_sale , rd_other ;
    TextView toolbar_text;
    ImageView toolbar_im;
    int Customer_id;
    int ship_id;
    ArrayList<String> Customer_arealist = new ArrayList<String>();
    ArrayList<String> ship_arealist = new ArrayList<String>();
    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;
    RecyclerView list_sela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        GEtid();

        getCustomer();

    }
    private void getCustomer() {



        JsonObject postParam = new JsonObject();
        postParam.addProperty("customerId", "0");
        postParam.addProperty("isActive", "1");

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.CustomerDataById("Bearer " + tokenManager.getAccessToken() , postParam);
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        Customer_arealist.clear();
                        Customer_arealist.add("Select");

                        List<CountryNameData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    Customer_arealist.add(stateListData.get(i).getCustomerName());

                                }
                            }


                            spin_Customer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            Customer_id = 0;

                                        } else {

                                            Customer_id = stateListData.get(position - 1).getId();


                                          //  Get_Uom_List(Customer_id);

                                        }


                                    }

                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(CartListActivity.this, R.layout.spinner_item, Customer_arealist);
                                spin_Customer.setAdapter(countryAdapter_job);
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
    private void handleTokenExpiration(Response<?> response) {

        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
    private void GEtid() {

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        tokenManager = new PreferencesManager(this);

        list_sela = findViewById(R.id.list_sela);
        spin_Customer = findViewById(R.id.spin_Customer);
        spin_Ship = findViewById(R.id.spin_Ship);
        li_other = findViewById(R.id.li_other);
        li_cashSale = findViewById(R.id.li_cashSale);
        rd_cssh_sale = findViewById(R.id.rd_cssh_sale);
        rd_other = findViewById(R.id.rd_other);

        rd_cssh_sale.setOnClickListener(this);
        rd_other.setOnClickListener(this);

        li_cashSale.setVisibility(View.VISIBLE);
        li_other.setVisibility(View.GONE);

        toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_im = findViewById(R.id.toolbar_im);

        toolbar_text.setText("List");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Get_list();

    }
    private void Get_list() {

        JsonObject postParam = new JsonObject();
        postParam.addProperty("itemId",  0);


        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<GetSalesVoucherResponse> call = git.GetCartList("Bearer " + tokenManager.getAccessToken() , postParam);
        call.enqueue(new Callback<GetSalesVoucherResponse>() {
            @Override
            public void onResponse(Call<GetSalesVoucherResponse> call, Response<GetSalesVoucherResponse> response) {
                if (response.isSuccessful()) {

                    dataParse(new Gson().toJson(response.body()).toString());

                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<GetSalesVoucherResponse> call, Throwable t) {

            }
        });




    }
    CartListAdapter mAdapter;
    ArrayList<GetSalesVoucherDatum> transactionsObjects = new ArrayList<>();
    GetSalesVoucherResponse transactions = new GetSalesVoucherResponse();
    private void dataParse(String response) {

        transactions = new Gson().fromJson(response, GetSalesVoucherResponse.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {

            mAdapter = new CartListAdapter(transactionsObjects, this  );
            list_sela.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
            list_sela.setItemAnimator(new DefaultItemAnimator());
            list_sela.setAdapter( mAdapter);
            list_sela.setVisibility(View.VISIBLE);
           // nodatafound.setVisibility(View.GONE);

        } else {
            // nodatafound.setVisibility(View.VISIBLE);
            list_sela.setVisibility(View.GONE);

        }

    }

    @Override
    public void onClick(View view) {

        if(view==rd_cssh_sale){

            li_cashSale.setVisibility(View.VISIBLE);
            li_other.setVisibility(View.GONE);

        }

        if(view==rd_other){

            li_cashSale.setVisibility(View.GONE);
            li_other.setVisibility(View.VISIBLE);

        }

    }

    public void ItemClickRemove() {

        Get_list();
    }


}