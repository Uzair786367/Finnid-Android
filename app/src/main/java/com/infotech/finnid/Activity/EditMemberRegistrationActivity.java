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

public class EditMemberRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

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

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_member_registration, container, false);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member_registration);

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
        submitBtn.setOnClickListener(this);



        landOwnershipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLandOwnershipDialog();
            }
        });

        relationWithMemberEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showrelationWithMemberEtDialog();
            }
        });


        countryType();

        countryType_present();


        shareType();
        spin_proofIdentityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdentityProofData identityProofData = (IdentityProofData) parent.getItemAtPosition(position);
                if (identityProofData != null) {
                    String selectedUnit = identityProofData.getIdentityName();
                    int selectedId = identityProofData.getId();

                    ArrayAdapter<IdentityProofData> adapter = (ArrayAdapter<IdentityProofData>) spin_proofIdentityType.getAdapter();
                    spin_proofIdentityType.setSelection(adapter.getPosition(identityProofData));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        identifyType();



       addressType();
         fieldUOMType();

        String[] genderTypes = new String[]{"Male", "Female", "Transgender"};
        ArrayAdapter<String> GenderAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, genderTypes);
      //  GenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_GenderType.setAdapter(GenderAdapter);




        String[] categoryType = new String[]{"General", "OBC", "SC", "ST"};
        ArrayAdapter<String> CategoryAdapter = new ArrayAdapter<>(this,  R.layout.spinner_item, categoryType);
         spin_categotyType.setAdapter(CategoryAdapter);


        String[] nationalityType = new String[]{"Indian"};
        ArrayAdapter<String> NationalityAdapter = new ArrayAdapter<>(this,  R.layout.spinner_item, nationalityType);
      //  NationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_NationalityType.setAdapter(NationalityAdapter);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "dd MMM yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tv_dob.setText(sdf.format(myCalendar.getTime()));
            }
        };
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditMemberRegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        sameAsPermanentAddressCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Log.e("check" , "11111");

                    setPresentAddressSameAsPermanent();
                } else {

                    Log.e("check" , "22222");

                    clearPresentAddressFields();
                }
            }
        });

        toolbar_text.setText("Edit Member Register");
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


       /* String[] genderTypes = new String[]{gohome.getGender()};
        ArrayAdapter<String> GenderAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, genderTypes);
        spin_GenderType.setAdapter(GenderAdapter);

        ////////////////////////////////////////////

        String[] NationalityTypes = new String[]{gohome.getNationality()};
        ArrayAdapter<String> NationalityAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, NationalityTypes);
        spin_NationalityType.setAdapter(NationalityAdapter);*/



    }



    private void setPresentAddressSameAsPermanent() {
        presLandmarkEt.setText(permLandmarkEt.getText());
        presPinCodeEt.setText(permPinCodeEt.getText());
        presentAddressEt.setText(addressEt.getText());
        String SelectedCountryPosition = spin_countryType.getSelectedItem().toString();
        String SelectedStatePosition = spin_StateType.getSelectedItem().toString();
        String SelectedCityPosition = spin_CityType.getSelectedItem().toString();


        Log.e("selectedCountryPosition ","selectedCountryPosition :  "+ SelectedCountryPosition);

        ///////////////////////////////////////////////

        countryid_present = countryid;
        stateid_present = stateid;

        country_arealist_present.clear();
        country_arealist_present.add(SelectedCountryPosition);
        ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, country_arealist_present);
        spin_preCountryType.setAdapter(countryAdapter_job);

        ///////////////////////////////////////////////
        state_arealist_present.clear();
        state_arealist_present.add(SelectedStatePosition);
        ArrayAdapter<String> state_list = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, state_arealist_present);
        spin_perStateType.setAdapter(state_list);

        ///////////////////////////////////////////////

        city_arealist_present.clear();
        city_arealist_present.add(SelectedCityPosition);
        ArrayAdapter<String> city_list_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, city_arealist_present);
        spin_perCityType.setAdapter(city_list_job);

        /////////////////////////////////////////////////


        /* spin_preCountryType.setSelection(selectedCountryPosition);
        int selectedStatePosition = spin_StateType.getSelectedItemPosition();
        spin_perStateType.setSelection(selectedStatePosition);
        int selectedCityPosition = spin_CityType.getSelectedItemPosition();
        spin_perCityType.setSelection(selectedCityPosition);*/


    }
    private void clearPresentAddressFields() {
        presentAddressEt.setText("");
       // presCountryIdEt.setText("");
     //   presStateIdEt.setText("");
      //  presCityIdEt.setText("");
        presPinCodeEt.setText("");
        presLandmarkEt.setText("");

        countryType_present();
    }
    private void showLandOwnershipDialog() {

        LayoutInflater inflater = (LayoutInflater) EditMemberRegistrationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_land_owership, null);


        TextView clear = (TextView) view.findViewById(R.id.clear);
        TextView Self = (TextView) view.findViewById(R.id.Self);
        TextView Family = (TextView) view.findViewById(R.id.Family);
        TextView Leased = (TextView) view.findViewById(R.id.Leased);

        final Dialog dialog = new Dialog(EditMemberRegistrationActivity.this);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                landOwnershipTv.setText("Family");

                relationWithMemberLayout.setVisibility(View.VISIBLE);
                relativeNameLayout.setVisibility(View.VISIBLE);
                ketauniLayout.setVisibility(View.VISIBLE);
                landlordKhetauniLayout.setVisibility(View.GONE);
                landlordNameLayout.setVisibility(View.GONE);

                dialog.dismiss();
            }
        });

        Self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                landOwnershipTv.setText("Self");

                ketauniLayout.setVisibility(View.VISIBLE);
                relationWithMemberLayout.setVisibility(View.GONE);
                relativeNameLayout.setVisibility(View.GONE);
                landlordKhetauniLayout.setVisibility(View.GONE);
                landlordNameLayout.setVisibility(View.GONE);

                dialog.dismiss();
            }
        });

         clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                landOwnershipTv.setText("Select Land Ownership");

                relationWithMemberLayout.setVisibility(View.GONE);
                relativeNameLayout.setVisibility(View.GONE);
                ketauniLayout.setVisibility(View.GONE);
                landlordKhetauniLayout.setVisibility(View.GONE);
                landlordNameLayout.setVisibility(View.GONE);

                dialog.dismiss();
            }
        });

        Leased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                landOwnershipTv.setText("Leased");

                landlordKhetauniLayout.setVisibility(View.VISIBLE);
                landlordNameLayout.setVisibility(View.VISIBLE);
                ketauniLayout.setVisibility(View.GONE);
                relationWithMemberLayout.setVisibility(View.GONE);
                relativeNameLayout.setVisibility(View.GONE);

                dialog.dismiss();
            }
        });





        dialog.show();

       /*
        AlertDialog.Builder builder = new AlertDialog.Builder(MemberRegistrationActivity.this);
        builder.setTitle("Select Land Ownership");
        final String[] landOwnershipTypes = new String[]{"Self", "Family", "Leased"};
        builder.setItems(landOwnershipTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedLandOwnership = landOwnershipTypes[which];
                landOwnershipTv.setText(selectedLandOwnership);
                if (selectedLandOwnership.equals("Select Land Ownership")) {
                    relationWithMemberLayout.setVisibility(View.GONE);
                    relativeNameLayout.setVisibility(View.GONE);
                    ketauniLayout.setVisibility(View.GONE);
                    landlordKhetauniLayout.setVisibility(View.GONE);
                    landlordNameLayout.setVisibility(View.GONE);
                } else if (selectedLandOwnership.equals("Self")) {
                    ketauniLayout.setVisibility(View.VISIBLE);
                    relationWithMemberLayout.setVisibility(View.GONE);
                    relativeNameLayout.setVisibility(View.GONE);
                    landlordKhetauniLayout.setVisibility(View.GONE);
                    landlordNameLayout.setVisibility(View.GONE);
                } else if (selectedLandOwnership.equals("Family")) {
                    relationWithMemberLayout.setVisibility(View.VISIBLE);
                    relativeNameLayout.setVisibility(View.VISIBLE);
                    ketauniLayout.setVisibility(View.VISIBLE);
                    landlordKhetauniLayout.setVisibility(View.GONE);
                    landlordNameLayout.setVisibility(View.GONE);
                } else if (selectedLandOwnership.equals("Leased")) {
                    landlordKhetauniLayout.setVisibility(View.VISIBLE);
                    landlordNameLayout.setVisibility(View.VISIBLE);
                    ketauniLayout.setVisibility(View.GONE);
                    relationWithMemberLayout.setVisibility(View.GONE);
                    relativeNameLayout.setVisibility(View.GONE);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();*/
    }

    private void showrelationWithMemberEtDialog() {

        LayoutInflater inflater = (LayoutInflater) EditMemberRegistrationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_show_relative_with_member, null);


        TextView clear = (TextView) view.findViewById(R.id.clear);
        TextView mother = (TextView) view.findViewById(R.id.mother);
        TextView father = (TextView) view.findViewById(R.id.father);
        TextView grandmother = (TextView) view.findViewById(R.id.grandmother);

        final Dialog dialog = new Dialog(EditMemberRegistrationActivity.this);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relationWithMemberEt.setText("Mother");


                dialog.dismiss();
            }
        });

        father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relationWithMemberEt.setText("father");



                dialog.dismiss();
            }
        });

         clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relationWithMemberEt.setText("Select Land Ownership");



                dialog.dismiss();
            }
        });

        grandmother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                landOwnershipTv.setText("Grand Mother");


                dialog.dismiss();
            }
        });



        dialog.show();

     }



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
                        /*List<FieldUOMData> fieldUOMDataList = fieldUOMResponse.getData();
                        FieldUOMDataAdapter adapter = new FieldUOMDataAdapter(MemberRegistrationActivity.this, fieldUOMDataList);
                        spin_fieldUomType.setAdapter(adapter);*/

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



    private void shareType(){
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<ShareTypeResponse> call = git.GetShareType("Bearer " + tokenManager.getAccessToken());
        call.enqueue(new Callback<ShareTypeResponse>() {
            @Override
            public void onResponse(Call<ShareTypeResponse> call, Response<ShareTypeResponse> response) {
                if (response.isSuccessful()) {
                    ShareTypeResponse shareTypeResponse = response.body();
                    if (shareTypeResponse != null) {

                        Set_shareType(new Gson().toJson(response.body()));



                    }
                } else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<ShareTypeResponse> call, Throwable t) {

            }
        });
    }
    private void identifyType(){
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<IdentityProofResponse> call = git.GetProofOfIdentity("Bearer " + tokenManager.getAccessToken());
        call.enqueue(new Callback<IdentityProofResponse>() {
            @Override
            public void onResponse(Call<IdentityProofResponse> call, Response<IdentityProofResponse> response) {
                if (response.isSuccessful()) {
                    IdentityProofResponse identityProofResponse = response.body();
                    if (identityProofResponse != null) {
                        List<IdentityProofData> identityProofData = identityProofResponse.getData();

                        identityProof_arealist.clear();

                        if (identityProofData.size() > 0) {

                            if (identityProofData != null && identityProofData.size() > 0) {

                                for (int i = 0; i < identityProofData.size(); i++) {

                                    identityProof_arealist.add(identityProofData.get(i).getIdentityName());

                                }
                            }


                            spin_proofIdentityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub

                                    proofIdentityType_id  = identityProofData.get(position).getId();

                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, identityProof_arealist);
                                spin_proofIdentityType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {

                            }

                        } else {

                        }


                       /* IdentityProofAdapter adapter = new IdentityProofAdapter(MemberRegistrationActivity.this, identityProofData);
                        spin_proofIdentityType.setAdapter(adapter);*/
                    }
                }else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<IdentityProofResponse> call, Throwable t) {

            }
        });
    }
    private void addressType(){
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<AddressProofResponse> call = git.GetProofOfAddress("Bearer " + tokenManager.getAccessToken());
        call.enqueue(new Callback<AddressProofResponse>() {
            @Override
            public void onResponse(Call<AddressProofResponse> call, Response<AddressProofResponse> response) {
                if (response.isSuccessful()) {
                    AddressProofResponse addressProofResponse = response.body();
                    if (addressProofResponse != null) {

                        set_Address_Proof(new Gson().toJson(response.body()));


                     /*   List<AddressProofData> addressProofData = addressProofResponse.getData();
                        AddressProofAdapter adapter = new AddressProofAdapter(MemberRegistrationActivity.this, addressProofData);
                        spin_proofAddressType.setAdapter(adapter);*/


                    }
                }else {
                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<AddressProofResponse> call, Throwable t) {

            }
        });
    }

    ArrayList<String> address_arealist = new ArrayList<String>();
    ArrayList<String> fieldUom_arealist = new ArrayList<String>();
    ArrayList<String> shareType_arealist = new ArrayList<String>();
    ArrayList<String> identityProof_arealist = new ArrayList<String>();
    ArrayList<String> country_arealist = new ArrayList<String>();
    ArrayList<String> country_arealist_present = new ArrayList<String>();
    ArrayList<String> state_arealist = new ArrayList<String>();
    ArrayList<String> state_arealist_present = new ArrayList<String>();
    ArrayList<String> city_arealist = new ArrayList<String>();
    ArrayList<String> city_arealist_present = new ArrayList<String>();
    AddressProofResponse transactions1 = new AddressProofResponse();
    ArrayList<AddressProofData> operator1 = new ArrayList<>();
    FieldUOMResponse transactions2 = new FieldUOMResponse();
    ArrayList<FieldUOMData> operator2 = new ArrayList<>();

    ShareTypeResponse transactions3 = new ShareTypeResponse();
    ArrayList<ShareTpeData> operator3 = new ArrayList<>();

    public void set_Address_Proof(String response) {

        address_arealist.clear();

        address_arealist.add("Select");

        Gson gson = new Gson();
        transactions1 = gson.fromJson(response, AddressProofResponse.class);
        operator1 = transactions1.getData();

        if (operator1.size() > 0) {

            if (operator1 != null && operator1.size() > 0) {

                for (int i = 0; i < operator1.size(); i++) {

                    address_arealist.add(operator1.get(i).getAddresProofName());

                }
            }


            spin_proofAddressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub

                    if (parent.getItemAtPosition(position).toString().equals("Select")) {
                         proofAddress_id = 0;
                    } else {
                        proofAddress_id = operator1.get(position - 1).getId();
                      //  spin_State_id_name = operator1.get(position - 1).getState();




                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });


            try {
                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, address_arealist);
                spin_proofAddressType.setAdapter(countryAdapter_job);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {

        }
    }
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

                        fieldUomType_id = operator1.get(position - 1).getId();


                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });

            try {
                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, fieldUom_arealist);
                spin_fieldUomType.setAdapter(countryAdapter_job);
            } catch (Exception e) {

            }

        } else {

        }
    }
    public void Set_shareType(String response) {

        shareType_arealist.clear();

        shareType_arealist.add("Select");

        Gson gson = new Gson();
        transactions3 = gson.fromJson(response, ShareTypeResponse.class);
        operator3 = transactions3.getData();

        if (operator3.size() > 0) {

            if (operator3 != null && operator3.size() > 0) {

                for (int i = 0; i < operator3.size(); i++) {

                    shareType_arealist.add(operator3.get(i).getShareDetail());

                }
            }


            spin_shareType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub

                    if (parent.getItemAtPosition(position).toString().equals("Select")) {
                       shareType_id = 0;
                    } else {
                         shareType_id = operator3.get(position - 1).getId();
                      //  spin_State_id_name = operator1.get(position - 1).getState();




                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });


            try {
                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, shareType_arealist);
                spin_shareType.setAdapter(countryAdapter_job);
            } catch (Exception e) {

            }

        } else {

        }
    }

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
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, country_arealist);
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

    private void countryType_present(){

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.CountryList("Bearer " + tokenManager.getAccessToken());
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse countryListResponse = response.body();
                    if (countryListResponse != null) {


                        List<CountryNameData> countryNameData = countryListResponse.getData();

                        country_arealist_present.clear();
                        country_arealist_present.add("Select");

                        if (countryNameData.size() > 0) {

                            if (countryNameData != null && countryNameData.size() > 0) {

                                for (int i = 0; i < countryNameData.size(); i++) {

                                    country_arealist_present.add(countryNameData.get(i).getName());

                                }
                            }


                            spin_preCountryType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    if(sameAsPermanentAddressCheckbox.isChecked()){


                                    }else {


                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            countryid_present = 0;

                                        } else {

                                            countryid_present = countryNameData.get(position - 1).getId();

                                        }
                                    }




                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, country_arealist_present);
                                spin_preCountryType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {

                            }

                        } else {

                        }


                        countryid_present = countryNameData.get(0).getId();

                        stateType_present();
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
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, state_arealist);
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
    private void stateType_present(){
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<StateListResponse> call = git.StateList("Bearer " + tokenManager.getAccessToken(),countryid_present);
        call.enqueue(new Callback<StateListResponse>() {
            @Override
            public void onResponse(Call<StateListResponse> call, Response<StateListResponse> response) {
                if (response.isSuccessful()) {
                    StateListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        state_arealist_present.clear();
                        state_arealist_present.add("Select");

                        List<StateListData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    state_arealist_present.add(stateListData.get(i).getName());

                                }
                            }

                            spin_perStateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub

                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){


                                        if(sameAsPermanentAddressCheckbox.isChecked()){


                                        }else {


                                            if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                                stateid_present = 0;

                                            } else {

                                                stateid_present = stateListData.get(position - 1).getId();


                                            }

                                            cityType_present();
                                        }




                                    }




                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, state_arealist_present);
                                spin_perStateType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {

                            }

                        } else {

                        }


                        stateid_present = stateListData.get(0).getId();
                        cityType_present();
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
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, city_arealist);
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

    private void cityType_present(){

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CityListResponse> call = git.GetCity("Bearer " + tokenManager.getAccessToken(),stateid_present);
        call.enqueue(new Callback<CityListResponse>() {
            @Override
            public void onResponse(Call<CityListResponse> call, Response<CityListResponse> response) {
                if (response.isSuccessful()) {
                    CityListResponse cityListResponse = response.body();
                    if (cityListResponse != null) {
                        List<CityListData> cityListData = cityListResponse.getData();

                        city_arealist_present.clear();
                        city_arealist_present.add("Select");


                        if (cityListData.size() > 0) {

                            if (cityListData != null && cityListData.size() > 0) {

                                for (int i = 0; i < cityListData.size(); i++) {

                                    city_arealist_present.add(cityListData.get(i).getName());

                                }
                            }

                            spin_perCityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub




                                    if(sameAsPermanentAddressCheckbox.isChecked()){


                                    }else {


                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            presCity_id = 0;

                                        } else {

                                            presCity_id = cityListData.get(position - 1).getId();

                                        }

                                    }


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });

                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(EditMemberRegistrationActivity.this, R.layout.spinner_item, city_arealist_present);
                                spin_perCityType.setAdapter(countryAdapter_job);
                            } catch (Exception e) {

                            }

                        } else {

                        }
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


            SetValue();

            ApiUtilMethods.INSTANCE.showloader(this);
            EndPoint git = ApiClients.getClient().create(EndPoint.class);
            Call<LoginResponse> call = git.MemberRegistration(
                    "Bearer " + tokenManager.getAccessToken(),new MemberRegistrationReq(0,
                            ""+landOwnershipTv.getText().toString().trim()+"",""+relationWithMemberEt.getText().toString().trim() ,
                    ""+relativeNameEt.getText().toString().trim() ,""+ketauniNoEt.getText().toString().trim(),
                            fieldUomType_id  ,""+areaEt.getText().toString().trim(),
                            categoty_id ,
                            "" ,"","",""+nameEt.getText().toString().trim(),
                    "" ,""+lastNameEt.getText().toString().trim() ,
                            ""+fatherFirstNameEt.getText().toString().trim(),"",
                            ""+fatherLastNameEt.getText().toString().trim(),
                            ""+spin_GenderType.getSelectedItem().toString().trim() ,
                            ""+tv_dob.getText().toString().trim() ,
                            ""+spin_NationalityType.getSelectedItem().toString().trim(),
                            ""+birthPlaceEt.getText().toString().trim(),
                    ""+occupationEt.getText().toString().trim(),
                            ""+panEt.getText().toString().trim() ,
                            ""+aadharEt.getText().toString().trim() ,
                            ""+mobileEt.getText().toString().trim(),
                            ""+landlineEt.getText().toString().trim(),
                            ""+emailEt.getText().toString().trim(),
                            proofIdentityType_id ,proofAddress_id ,
                            ""+addressEt.getText().toString().trim(),
                             countryid, stateid, permCity_id ,
                            ""+presPinCodeEt.getText().toString().trim() ,
                            ""+permLandmarkEt.getText().toString().trim(),
                            ""+presentAddressEt.getText().toString().trim(),
                            countryid_present, stateid_present , presCity_id ,
                            ""+permPinCodeEt.getText().toString().trim(),
                            ""+presLandmarkEt.getText().toString().trim(),
                            2,
                            ""+educationalQualificationEt.getText().toString().trim() ,
                            0 , shareType_id,0));
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    ApiUtilMethods.INSTANCE.hideLoader(EditMemberRegistrationActivity.this);

                    if (response.isSuccessful()) {

                        ApiUtilMethods.INSTANCE.SussesDialog(EditMemberRegistrationActivity.this , response.body().getMessage() , EditMemberRegistrationActivity.this);

                    } else {

                        Toast.makeText(EditMemberRegistrationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        handleTokenExpiration(response);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    ApiUtilMethods.INSTANCE.hideLoader(EditMemberRegistrationActivity.this);


                }
            });


        }

    }

    private void SetValue() {


        if(spin_categotyType.getSelectedItem().toString().trim().equalsIgnoreCase("General")){

            categoty_id =1;
        }else  if(spin_categotyType.getSelectedItem().toString().trim().equalsIgnoreCase("OBC")){

            categoty_id =2;
        }else  if(spin_categotyType.getSelectedItem().toString().trim().equalsIgnoreCase("SC")){

            categoty_id =3;
        }else  if(spin_categotyType.getSelectedItem().toString().trim().equalsIgnoreCase("ST")){

            categoty_id =4;
        }


      //  String[] categoryType = new String[]{"General", "OBC", "SC", "ST"};

    }


    
}