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
import com.infotech.finnid.Objects.MemberData;
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

public class ViewProfileRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;
    Button submitBtn;

    String operator;

    Spinner spin_fieldUomType,spin_shareType,spin_landOwnershipType,spin_countryType,spin_StateType,spin_CityType,
            spin_proofIdentityType,spin_proofAddressType,spin_GenderType,spin_NationalityType,spin_categotyType,
            spin_preCountryType,spin_perStateType,spin_perCityType;
    private int countryid=0;
    private int shareType_id=0;
    private int countryid_present=0;
    private int proofIdentityType_id=0;
    private int proofAddress_id=0;
      private int permCity_id=0;
    private int stateid=0;
    private int stateid_present=0;
    private int categoty_id=1;
    private int presCity_id=0;
    TextView tv_dob,landOwnershipTv;
    CheckBox sameAsPermanentAddressCheckbox;
    LinearLayout landOwnershipView;
    LinearLayout relationWithMemberLayout,ketauniLayout,landlordKhetauniLayout,landlordNameLayout,relativeNameLayout;
    EditText relationWithMemberEt,presentAddressEt,presPinCodeEt,presLandmarkEt,permLandmarkEt,permPinCodeEt,addressEt, durationOfStayPAddressEt ,relativeNameEt, ketauniNoEt;
    PreferencesManager preferencesManager;

    int fieldUomType_id=0;
    EditText areaEt, nameEt, lastNameEt, fatherFirstNameEt , fatherLastNameEt , birthPlaceEt , occupationEt,
            panEt , aadharEt , mobileEt , landlineEt , emailEt , educationalQualificationEt, membershipTypeEt;
    ImageView toolbar_im;
    TextView toolbar_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member_registration);

        operator= getIntent().getStringExtra("operator");



        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);


        tokenManager = new PreferencesManager(this);
        presentAddressEt = findViewById(R.id.presentAddressEt);
        relationWithMemberEt = findViewById(R.id.relationWithMemberEt);
        addressEt = findViewById(R.id.addressEt);
        durationOfStayPAddressEt = findViewById(R.id.durationOfStayPAddressEt);
        relativeNameEt = findViewById(R.id.relativeNameEt);
        ketauniNoEt = findViewById(R.id.ketauniNoEt);
        toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_im = findViewById(R.id.toolbar_im);



        areaEt = findViewById(R.id.areaEt);
        nameEt = findViewById(R.id.nameEt);
        lastNameEt = findViewById(R.id.lastNameEt);
        fatherFirstNameEt = findViewById(R.id.fatherFirstNameEt);
        fatherLastNameEt = findViewById(R.id.fatherLastNameEt);
        birthPlaceEt = findViewById(R.id.birthPlaceEt);
        occupationEt = findViewById(R.id.occupationEt);
        panEt = findViewById(R.id.panEt);
        aadharEt = findViewById(R.id.aadharEt);
        mobileEt = findViewById(R.id.mobileEt);
        landlineEt = findViewById(R.id.landlineEt);
        emailEt = findViewById(R.id.emailEt);
        educationalQualificationEt = findViewById(R.id.educationalQualificationEt);
        membershipTypeEt = findViewById(R.id.membershipTypeEt);


        spin_preCountryType = findViewById(R.id.spin_preCountryType);
        spin_perStateType = findViewById(R.id.spin_perStateType);
        spin_perCityType = findViewById(R.id.spin_perCityType);
        presPinCodeEt = findViewById(R.id.presPinCodeEt);
        presLandmarkEt = findViewById(R.id.presLandmarkEt);
        permLandmarkEt = findViewById(R.id.permLandmarkEt);
        permPinCodeEt = findViewById(R.id.permPinCodeEt);
        spin_fieldUomType = findViewById(R.id.spin_fieldUomType);
        relationWithMemberLayout = findViewById(R.id.relationWithMemberLayout);
        ketauniLayout = findViewById(R.id.ketauniLayout);
        landlordKhetauniLayout = findViewById(R.id.landlordKhetauniLayout);
        landlordNameLayout = findViewById(R.id.landlordNameLayout);
        relativeNameLayout = findViewById(R.id.relativeNameLayout);
        spin_countryType = findViewById(R.id.spin_countryType);
        spin_StateType = findViewById(R.id.spin_StateType);
        tv_dob = findViewById(R.id.tv_dob);
        spin_CityType = findViewById(R.id.spin_CityType);
        landOwnershipTv = findViewById(R.id.landOwnershipTv);
        spin_GenderType = findViewById(R.id.spin_GenderType);
        spin_categotyType = findViewById(R.id.spin_categotyType);
        spin_NationalityType = findViewById(R.id.spin_NationalityType);
        spin_proofIdentityType = findViewById(R.id.spin_proofIdentityType);
        spin_proofAddressType = findViewById(R.id.spin_proofAddressType);
        sameAsPermanentAddressCheckbox = findViewById(R.id.sameAsPermanentAddressCheckbox);
        spin_shareType = findViewById(R.id.spin_shareType);
        submitBtn = findViewById(R.id.submitBtn);
        landOwnershipView = findViewById(R.id.landOwnershipView); // Replace with your CardView ID

        toolbar_text.setText("View Member Register");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        SetValue_Auto();

    }

    private void SetValue_Auto() {


        MemberData gohome = new Gson().fromJson(operator, MemberData.class);

        landOwnershipTv.setText(""+ gohome.getLandOwnershipType());
        relationWithMemberEt.setText(""+ gohome.getRelationWithFamilyMember());
        relativeNameEt.setText(""+ gohome.getRelativeName());
        ketauniNoEt.setText(""+ gohome.getKhatauniNo());
        areaEt.setText(""+ gohome.getArea());
        nameEt.setText(""+ gohome.getfFirstName());
        lastNameEt.setText(""+ gohome.getMemLastName());
        fatherFirstNameEt.setText(""+ gohome.getfFirstName());
        fatherLastNameEt.setText(""+ gohome.getfLastName());
        tv_dob.setText(""+ gohome.getDob());
        birthPlaceEt.setText(""+ gohome.getBirthPlace());
        occupationEt.setText(""+ gohome.getOccupationType());
        panEt.setText(""+ gohome.getPan());
        aadharEt.setText(""+ gohome.getAdhar());
        mobileEt.setText(""+ gohome.getMobile());
        landlineEt.setText(""+ gohome.getLandline());
        emailEt.setText(""+ gohome.getEmail());
        presPinCodeEt.setText(""+ gohome.getPresPinCode());
        permLandmarkEt.setText(""+ gohome.getPermLandmark());
        presentAddressEt.setText(""+ gohome.getPresentAddress());
        permPinCodeEt.setText(""+ gohome.getPermPinCode());
        presLandmarkEt.setText(""+ gohome.getPresLandmark());
        educationalQualificationEt.setText(""+ gohome.getEducationalQualification());
        addressEt.setText(""+ gohome.getPresentAddress());


        String[] genderTypes = new String[]{gohome.getGender()};
        ArrayAdapter<String> GenderAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, genderTypes);
        spin_GenderType.setAdapter(GenderAdapter);

        ////////////////////////////////////////////

        String[] NationalityTypes = new String[]{gohome.getNationality()};
        ArrayAdapter<String> NationalityAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, NationalityTypes);
        spin_NationalityType.setAdapter(NationalityAdapter);



    }


    @Override
    public void onClick(View view) {

        if(view==submitBtn){

        }

    }

}