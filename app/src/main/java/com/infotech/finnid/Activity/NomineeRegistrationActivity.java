package com.infotech.finnid.Activity;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infotech.finnid.ApiRequest.MemberRegistrationReq;
import com.infotech.finnid.ApiResponse.AddressProofResponse;
import com.infotech.finnid.ApiResponse.CityListResponse;
import com.infotech.finnid.ApiResponse.CountryListResponse;
import com.infotech.finnid.ApiResponse.FieldUOMResponse;
import com.infotech.finnid.ApiResponse.IdentityProofResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.ShareTypeResponse;
import com.infotech.finnid.ApiResponse.StateListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.AddressProofData;
import com.infotech.finnid.Objects.CityListData;
import com.infotech.finnid.Objects.CountryNameData;
import com.infotech.finnid.Objects.FieldUOMData;
import com.infotech.finnid.Objects.IdentityProofData;
import com.infotech.finnid.Objects.ShareTpeData;
import com.infotech.finnid.Objects.StateListData;
import com.infotech.finnid.R;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NomineeRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_nominee_name , et_fathername , ed_mother_name , ed_spouse_name , ed_email , ed_mobile , permPinCodeEt
            ,   addressEt  , ed_relationship , ed_effective_date_nomination , ed_date_attaining_majorit ;
    ImageView toolbar_im;
    TextView tv_dob , toolbar_text;
    Spinner spin_countryType , spin_StateType , spin_CityType , spin_NationalityType  , occupationEt ;
    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;
    int countryid;
    int stateid;
    int permCity_id;
    Button submitBtn;
    String memberId;
    int date_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominee_registration);

        memberId = getIntent().getStringExtra("memberId");

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        tokenManager = new PreferencesManager(this);

        toolbar_im = findViewById(R.id.toolbar_im);
        ed_nominee_name = findViewById(R.id.ed_nominee_name);
        ed_nominee_name = findViewById(R.id.ed_nominee_name);
        et_fathername = findViewById(R.id.et_fathername);
        ed_mother_name = findViewById(R.id.ed_mother_name);
        ed_spouse_name = findViewById(R.id.ed_spouse_name);
        ed_email = findViewById(R.id.ed_email);
        ed_mobile = findViewById(R.id.ed_mobile);
        tv_dob = findViewById(R.id.tv_dob);
        toolbar_text = findViewById(R.id.toolbar_text);
        addressEt = findViewById(R.id.addressEt);
        spin_countryType = findViewById(R.id.spin_countryType);
        spin_StateType = findViewById(R.id.spin_StateType);
        spin_CityType = findViewById(R.id.spin_CityType);
        permPinCodeEt = findViewById(R.id.permPinCodeEt);
        occupationEt = findViewById(R.id.occupationEt);
        ed_relationship = findViewById(R.id.ed_relationship);
        ed_date_attaining_majorit = findViewById(R.id.ed_date_attaining_majorit);
        ed_effective_date_nomination = findViewById(R.id.ed_effective_date_nomination);
        spin_NationalityType = findViewById(R.id.spin_NationalityType);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);


        countryType();

        String[] nationalityType = new String[]{"Indian"};
        ArrayAdapter<String> NationalityAdapter = new ArrayAdapter<>(this,  R.layout.spinner_item, nationalityType);
        spin_NationalityType.setAdapter(NationalityAdapter);


        String[] occupationType = new String[]{"Self Employed","Professional","Home Maker","Student","Service Man"};
        ArrayAdapter<String> OccupationAdapter = new ArrayAdapter<>(this,  R.layout.spinner_item, occupationType);
        occupationEt.setAdapter(OccupationAdapter);



/////////////////////////////////////////////////////



        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


              //  String myFormat = "dd MMM yyyy"; //In which you need put here
                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


                if(date_count==1){

                    tv_dob.setText(sdf.format(myCalendar.getTime()));

                }else if(date_count==2){

                    ed_effective_date_nomination.setText(sdf.format(myCalendar.getTime()));

                }else if(date_count==3){

                    ed_date_attaining_majorit.setText(sdf.format(myCalendar.getTime()));

                }

            }
        };

        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date_count = 1;
                new DatePickerDialog(NomineeRegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_effective_date_nomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date_count = 2;
                new DatePickerDialog(NomineeRegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_date_attaining_majorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date_count = 3;
                new DatePickerDialog(NomineeRegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });







        toolbar_text.setText("Nominee Register");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

     ArrayList<String> country_arealist = new ArrayList<String>();
     ArrayList<String> state_arealist = new ArrayList<String>();
     ArrayList<String> city_arealist = new ArrayList<String>();

    private void countryType(){

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.CountryList("Bearer " + tokenManager.getAccessToken());
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse countryListResponse = response.body();
                    if (countryListResponse != null) {

                        List<CountryNameData> countryNameData = countryListResponse.getData();

                        country_arealist.clear();
                        country_arealist.add("Select");

                        if (countryNameData.size() > 0) {

                            if (countryNameData != null && countryNameData.size() > 0) {

                                for (int i = 0; i < countryNameData.size(); i++) {

                                    country_arealist.add(countryNameData.get(i).getName());


                                }
                            }


                            spin_countryType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    // TODO Auto-generated method stub

                                    if (parent.getItemAtPosition(position).toString().equals("Select")) {
                                        countryid = 0;
                                    } else {

                                        countryid = countryNameData.get(position - 1).getId();

                                    }
                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(NomineeRegistrationActivity.this, R.layout.spinner_item, country_arealist);
                                spin_countryType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }


                        countryid = countryNameData.get(0).getId();

                        stateType();
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

    private void stateType(){
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<StateListResponse> call = git.StateList("Bearer " + tokenManager.getAccessToken(),countryid);
        call.enqueue(new Callback<StateListResponse>() {
            @Override
            public void onResponse(Call<StateListResponse> call, Response<StateListResponse> response) {
                if (response.isSuccessful()) {
                    StateListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        state_arealist.clear();
                        state_arealist.add("Select");

                        List<StateListData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    state_arealist.add(stateListData.get(i).getName());

                                }
                            }


                            spin_StateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            stateid = 0;

                                        } else {

                                            stateid = stateListData.get(position - 1).getId();

                                        }

                                        cityType();
                                    }




                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(NomineeRegistrationActivity.this, R.layout.spinner_item, state_arealist);
                                spin_StateType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }


                       /* StateNameAdapter adapter = new StateNameAdapter(MemberRegistrationActivity.this, stateListData);
                        spin_StateType.setAdapter(adapter);*/


                        stateid = stateListData.get(0).getId();
                        cityType();
                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<StateListResponse> call, Throwable t) {

            }
        });
    }
     private void cityType(){
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CityListResponse> call = git.GetCity("Bearer " + tokenManager.getAccessToken(),stateid);
        call.enqueue(new Callback<CityListResponse>() {
            @Override
            public void onResponse(Call<CityListResponse> call, Response<CityListResponse> response) {
                if (response.isSuccessful()) {
                    CityListResponse cityListResponse = response.body();
                    if (cityListResponse != null) {
                        List<CityListData> cityListData = cityListResponse.getData();

                        city_arealist.clear();
                        city_arealist.add("Select");


                        if (cityListData.size() > 0) {

                            if (cityListData != null && cityListData.size() > 0) {

                                for (int i = 0; i < cityListData.size(); i++) {

                                    city_arealist.add(cityListData.get(i).getName());

                                }
                            }

                            spin_CityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub

                                  //  permCity_id = cityListData.get(position - 1).getId();

                                    if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                        permCity_id = 0;

                                    } else {

                                        permCity_id = cityListData.get(position - 1).getId();

                                    }


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(NomineeRegistrationActivity.this, R.layout.spinner_item, city_arealist);
                                spin_CityType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {

                            }

                        } else {

                        }


                       /* CityNameAdapter adapter = new CityNameAdapter(MemberRegistrationActivity.this, cityListData);
                        spin_CityType.setAdapter(adapter);*/


                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<CityListResponse> call, Throwable t) {

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

        if(view==submitBtn){

            if(ed_nominee_name.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Nominee Name", Toast.LENGTH_SHORT).show();
            }else if(et_fathername.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Father Name", Toast.LENGTH_SHORT).show();
            }else if(ed_mother_name.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Mother Name", Toast.LENGTH_SHORT).show();
            }else if(ed_spouse_name.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Spouse Name", Toast.LENGTH_SHORT).show();
            }else if(tv_dob.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter DOB", Toast.LENGTH_SHORT).show();
            }else if(ed_email.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            }else if(ed_mobile.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            }else if(addressEt.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            } else {

                Hit_Submit();

            }

        }

    }

    private void Hit_Submit() {

        JsonObject postParam = new JsonObject();
        postParam.addProperty("memberId", memberId.toString().trim());
        postParam.addProperty("nomineeName", ed_nominee_name.getText().toString().trim());
        postParam.addProperty("fatherName", et_fathername.getText().toString().trim());
        postParam.addProperty("motherName", ed_mother_name.getText().toString().trim());
        postParam.addProperty("spouseName", ed_spouse_name.getText().toString().trim());
        postParam.addProperty("dob", tv_dob.getText().toString().trim());
        postParam.addProperty("email", ed_email.getText().toString().trim());
        postParam.addProperty("mobile", ed_mobile.getText().toString().trim());
        postParam.addProperty("nomineeAddress", addressEt.getText().toString().trim());
        postParam.addProperty("nomineeCityId",  String.valueOf(permCity_id).toString()+"");
        postParam.addProperty("nomineeStateId",   String.valueOf(stateid).toString());
        postParam.addProperty("nomineeCountryId",  String.valueOf(countryid).toString());
        postParam.addProperty("nomineePinCode", permPinCodeEt.getText().toString().trim());
        postParam.addProperty("nationality", spin_NationalityType.getSelectedItem().toString().trim());
        postParam.addProperty("occupation", occupationEt.getSelectedItem().toString().trim());
        postParam.addProperty("relationShip", ed_relationship.getText().toString().trim());
        postParam.addProperty("edom", ed_effective_date_nomination.getText().toString().trim());
        postParam.addProperty("doam", ed_date_attaining_majorit.getText().toString().trim());




        ApiUtilMethods.INSTANCE.showloader(this);
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<LoginResponse> call = git.RegisterNominee(
                "Bearer " + tokenManager.getAccessToken(), postParam);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(NomineeRegistrationActivity.this);

                try {
                    if (response.isSuccessful()) {

                        ApiUtilMethods.INSTANCE.SussesDialog(NomineeRegistrationActivity.this , response.body().getMessage() , NomineeRegistrationActivity.this);

                    } else {

                        Toast.makeText(NomineeRegistrationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        handleTokenExpiration(response);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(NomineeRegistrationActivity.this);


            }
        });




    }


}