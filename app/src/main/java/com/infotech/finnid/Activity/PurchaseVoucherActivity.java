package com.infotech.finnid.Activity;

import static com.infotech.finnid.SqliteDatabase.DatabaseHelper.TABLE_NAME;
import static com.infotech.finnid.SqliteDatabase.DatabaseHelper.cess_amt;
import static com.infotech.finnid.SqliteDatabase.DatabaseHelper.gst_amt;
import static com.infotech.finnid.Utils.UtilMethods.rotateImage;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.infotech.finnid.ApiRequest.ExpensesSummary;
import com.infotech.finnid.ApiRequest.InventoryPurchaseRequest;
import com.infotech.finnid.ApiRequest.ItemDetail;
import com.infotech.finnid.ApiRequest.ItemOfferDetail;
import com.infotech.finnid.ApiRequest.MemberListRequest;
import com.infotech.finnid.ApiRequest.PaymentDetail;
import com.infotech.finnid.ApiRequest.PurchasedPrice;
import com.infotech.finnid.ApiResponse.CityListResponse;
import com.infotech.finnid.ApiResponse.CountryListResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.MemberListResponse;
import com.infotech.finnid.ApiResponse.StateListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.CityListData;
import com.infotech.finnid.Objects.CountryNameData;
import com.infotech.finnid.Objects.StateListData;
import com.infotech.finnid.R;
import com.infotech.finnid.SqliteDatabase.DBManager;
import com.infotech.finnid.SqliteDatabase.DBManager_PaymentMode;
import com.infotech.finnid.SqliteDatabase.DatabaseHelper;
import com.infotech.finnid.SqliteDatabase.DatabaseHelper_PaymentMode;
import com.infotech.finnid.SqliteDatabase.ModifyCountryActivity;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;
import com.infotech.finnid.Utils.UtilMethods;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseVoucherActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_mobile_no  ,permPinCodeEt, ed_supplier_bill_no , ed_eway_bill_no  , document_upload  , ed_posting_date  , ed_document_date;

    ImageView toolbar_im;
    TextView tv_bill_date , toolbar_text;
    Spinner spin_countryType ,spin_uom, spin_StateType , spin_CityType , ed_is_dedicated_purchase   ;
    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;
    int countryid;
    int stateid;
    int permCity_id;
    int supplier_id;

    Button submitBtn;
     CheckBox cb_unregistered , cb_is_rcm;
     LinearLayout li_mobile_no , li_suppiler_number ;
     TextView tv_add , tv_remove , tv_payment_remove, tv_PM_add;

    private SimpleCursorAdapter adapter;
    private SimpleCursorAdapter adapter_payment;

      Spinner item_sr_no;
      EditText  ed_qty , ed_Rate ,ed_Disc , ed_transaction_value , ed_gst, ed_gst_amt ,ed_Cess , ed_cess_amt , ed_Amount
               , ed_payment_Amount ,ed_Balance, ed_Paid ;
      ListView   lst_Item , lst_Item_payment_mode ;

    ArrayList<String> list_item = new ArrayList<String>();
    ArrayList<String> list_UOM = new ArrayList<String>();
    ArrayList<String> list_qty = new ArrayList<String>();
    ArrayList<String> list_Rate = new ArrayList<String>();
    ArrayList<String> list_Disc = new ArrayList<String>();
    ArrayList<String> list_Transaction = new ArrayList<String>();
    ArrayList<String> list_Cess_amount = new ArrayList<String>();
    ArrayList<String> list_Amount = new ArrayList<String>();
    ArrayList<String> list_Cess = new ArrayList<String>();
    ArrayList<String> list_Gst_amount = new ArrayList<String>();
    ArrayList<String> list_Gst = new ArrayList<String>();
    private DBManager dbManager;
    private DBManager_PaymentMode dbManager_pm;
    Spinner spin_supplier , spin_payment_mode;
    final String[] from = new String[] { DatabaseHelper._ID, DatabaseHelper.item,DatabaseHelper.UOM,  DatabaseHelper.qty ,
            DatabaseHelper.Rate , DatabaseHelper.Disc ,DatabaseHelper.transaction_value , DatabaseHelper.gst,
            DatabaseHelper.gst_amt ,DatabaseHelper.Cess ,
            DatabaseHelper.cess_amt ,DatabaseHelper.Amount,DatabaseHelper.item_HsnCode,
            DatabaseHelper.item_BarCode,DatabaseHelper.itemGroupCode ,DatabaseHelper.item_id_id  };
    final int[] to = new int[] { R.id.id, R.id.title , R.id.UOM ,R.id.qty ,R.id.Rate ,R.id.Disc ,
            R.id.transaction_value ,R.id.gst ,R.id.gst_amt , R.id.Cess ,R.id.cess_amt ,R.id.Amount
            ,R.id.item_HsnCode ,R.id.item_BarCode ,R.id.itemGroupCode ,R.id.item_id_id  };

     final String[] from_pm = new String[] { DatabaseHelper_PaymentMode._ID, DatabaseHelper_PaymentMode.payment_mode,
             DatabaseHelper_PaymentMode.Paid,  DatabaseHelper_PaymentMode.Balance , DatabaseHelper_PaymentMode.payment_Amount ,
             DatabaseHelper_PaymentMode.payment_mode_id ,DatabaseHelper_PaymentMode.Sub_payment_mode_id   };
    final int[] to_pm = new int[] { R.id.id,  R.id.payment_mode ,R.id.Paid ,R.id.Balance ,R.id.payment_Amount,R.id.payment_mode_id,
            R.id.Sub_payment_mode_id  };

    String date_type;
    String base64_image="1";
    Double sun_at=0.0 ;
    Double Taxable_total=0.0 ;
    Double Cess_total=0.0 ;
    int Expenses_total=0 ;
    int Round_off_total=0 ;
    Double Total_total=0.0 ;
    Double IGST_total=0.0 ;
     int payment_mode_id;

    ArrayList<ItemDetail> itemDetails  = new ArrayList<>();
    ArrayList<ItemOfferDetail> item_offer_Details  = new ArrayList<>();
    ArrayList<PurchasedPrice> purchased_Price_detail  = new ArrayList<>();
    ArrayList<ExpensesSummary> expensesSummaryList_details  = new ArrayList<>();
    ArrayList<PaymentDetail> Payment_Details  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_voucher);


        lst_Item = findViewById(R.id.lst_Item);
        lst_Item_payment_mode = findViewById(R.id.lst_Item_payment_mode);
        spin_supplier = findViewById(R.id.spin_supplier);
        spin_payment_mode = findViewById(R.id.spin_payment_mode);


        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        tokenManager = new PreferencesManager(this);

        tv_remove = findViewById(R.id.tv_remove);
        tv_payment_remove = findViewById(R.id.tv_payment_remove);
        tv_add = findViewById(R.id.tv_add);
        cb_unregistered = findViewById(R.id.cb_unregistered);
        cb_is_rcm = findViewById(R.id.cb_is_rcm);
        toolbar_im = findViewById(R.id.toolbar_im);
        li_suppiler_number = findViewById(R.id.li_suppiler_number);
         li_mobile_no = findViewById(R.id.li_mobile_no);
        document_upload = findViewById(R.id.document_upload);
        ed_posting_date = findViewById(R.id.ed_posting_date);
        ed_document_date = findViewById(R.id.ed_document_date);

         ed_qty = findViewById(R.id.ed_qty);
        ed_Rate = findViewById(R.id.ed_Rate);
        ed_Disc = findViewById(R.id.ed_Disc);
        ed_transaction_value = findViewById(R.id.ed_transaction_value);
        ed_gst = findViewById(R.id.ed_gst);
        ed_gst_amt = findViewById(R.id.ed_gst_amt);
        ed_Cess = findViewById(R.id.ed_Cess);
        ed_cess_amt = findViewById(R.id.ed_cess_amt);
        ed_Amount = findViewById(R.id.ed_Amount);
        ed_Paid = findViewById(R.id.ed_Paid);
        ed_Balance = findViewById(R.id.ed_Balance);
        ed_payment_Amount = findViewById(R.id.ed_payment_Amount);
        tv_PM_add = findViewById(R.id.tv_PM_add);

        ed_mobile_no = findViewById(R.id.ed_mobile_no);
         permPinCodeEt = findViewById(R.id.permPinCodeEt);
        ed_supplier_bill_no = findViewById(R.id.ed_supplier_bill_no);
        ed_eway_bill_no = findViewById(R.id.ed_eway_bill_no);

        tv_bill_date = findViewById(R.id.tv_bill_date);
        toolbar_text = findViewById(R.id.toolbar_text);
        spin_countryType = findViewById(R.id.spin_countryType);
        spin_uom = findViewById(R.id.spin_uom);
        item_sr_no = findViewById(R.id.item_sr_no);
        spin_StateType = findViewById(R.id.spin_StateType);
        spin_CityType = findViewById(R.id.spin_CityType);

        ed_is_dedicated_purchase = findViewById(R.id.ed_is_dedicated_purchase);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        document_upload.setOnClickListener(this);
        ed_posting_date.setOnClickListener(this);
        ed_document_date.setOnClickListener(this);

        tv_remove.setOnClickListener(this);
        tv_payment_remove.setOnClickListener(this);
        tv_add.setOnClickListener(this);


        countryType();

        String[] nationalityType = new String[]{"No","Yes"};
        ArrayAdapter<String> NationalityAdapter = new ArrayAdapter<>(this,  R.layout.spinner_item, nationalityType);
        ed_is_dedicated_purchase.setAdapter(NationalityAdapter);

      /*  String[] item_sr = new String[]{"Toor Dal","Potato","Tomato","Garlic","Mango","SUGAR","abcd","FAT MILK","TULSE LEAVES","MATAR","HEMANT RETURN CHECK","1206 NEW ITEM"};
        ArrayAdapter<String> item_sr_Adapter = new ArrayAdapter<>(this,  R.layout.spinner_item, item_sr);
        item_sr_no.setAdapter(item_sr_Adapter);*/

        ////////////////////////////////////////////////

       /* String[] item_sr_pm = new String[]{"CHEQUE", "IMPS" ,"NEFT", "RTGS", "UPI"};
        ArrayAdapter<String> item_pm_Adapter = new ArrayAdapter<>(this,  R.layout.spinner_item, item_sr_pm);
        spin_payment_mode.setAdapter(item_pm_Adapter);
*/


        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd MMM yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                if(date_type.toString().trim().equalsIgnoreCase("bill")){

                    tv_bill_date.setText(sdf.format(myCalendar.getTime()));

                }else if(date_type.toString().trim().equalsIgnoreCase("posting")){

                    ed_posting_date.setText(sdf.format(myCalendar.getTime()));

                }else if(date_type.toString().trim().equalsIgnoreCase("document")){

                    ed_document_date.setText(sdf.format(myCalendar.getTime()));

                }
            }
        };

        ed_posting_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date_type = "posting";

                new DatePickerDialog(PurchaseVoucherActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tv_bill_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date_type = "bill";

                new DatePickerDialog(PurchaseVoucherActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_document_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date_type = "document";

                new DatePickerDialog(PurchaseVoucherActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_Cess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.e("change","val : "+ s);

                if(!ed_Cess.getText().toString().trim().isEmpty()){

                    Double cess_int = Double.valueOf(Integer.parseInt(s.toString()));
                    Double div_cess_int = cess_int / 100;
                    Double transaction_int = Double.valueOf(Integer.parseInt(ed_transaction_value.getText().toString().trim()));
                    Double cess_amt_int =   div_cess_int * transaction_int;

                    Log.e("numbertotllll","div_cess_int :  "+ div_cess_int +"  ,  cess_int  :  "+ cess_int +" ,    transaction_int  :  "+ transaction_int +" :   cess_amt_int  :  "+ cess_amt_int  );

                    ed_cess_amt.setText(""+ cess_amt_int);

                    Double to_gst = Double.valueOf(ed_gst_amt.getText().toString().trim());
                    Double to_cess = Double.valueOf(ed_cess_amt.getText().toString().trim());
                    Double to_transaction = Double.valueOf(ed_transaction_value.getText().toString().trim());

                    Double total_sum =  to_gst + to_cess +   to_transaction ;
                    ed_Amount.setText(""+ total_sum);

                }

                //ed_transaction_value.setText(""+total_rate);

            }
        });

        ed_gst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.e("change","val : "+ s);

                 if(!ed_gst.getText().toString().trim().isEmpty()){

                     Double gst_int = Double.valueOf(Integer.parseInt(s.toString()));
                     Double div_gst_int = gst_int / 100;
                     Double transaction_int = Double.valueOf(Integer.parseInt(ed_transaction_value.getText().toString().trim()));
                     Double gst_amt_int =   div_gst_int * transaction_int;

                     Log.e("numbertotllll","div_gst_int :  "+ div_gst_int +"  ,  gst_int  :  "+ gst_int +" ,    transaction_int  :  "+ transaction_int +" :   gst_amt_int  :  "+ gst_amt_int  );

                     ed_gst_amt.setText(""+ gst_amt_int);

                }




                //ed_transaction_value.setText(""+total_rate);

            }
        });

        ed_Rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!ed_Rate.getText().toString().trim().isEmpty()){

                    Log.e("change","val : "+ s);

                    int rate_int = Integer.parseInt(s.toString());
                    int qty_int = 1;

                    if(ed_qty.getText().toString().trim().isEmpty()){

                        qty_int =1;

                    }else {

                        qty_int = Integer.parseInt(ed_qty.getText().toString());

                    }

                    int total_rate = rate_int * qty_int;

                    ed_transaction_value.setText(""+total_rate);

                }



            }
        });

        ed_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!ed_qty.getText().toString().trim().isEmpty()){


                    Log.e("changeqyyyyy","Qtyyyyy : "+ s);

                    int qty_int = Integer.parseInt(s.toString());

                    int rate_int =1;


                    if(ed_Rate.getText().toString().trim().isEmpty()){

                        rate_int =1;

                    }else {

                        rate_int = Integer.parseInt(ed_Rate.getText().toString());

                    }

                    int total_rate = rate_int * qty_int;
                    ed_transaction_value.setText(""+total_rate);

                }

            }
        });

        toolbar_text.setText("Purchase Voucher");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        li_mobile_no.setVisibility(View.GONE);
        li_suppiler_number.setVisibility(View.VISIBLE);


        cb_unregistered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    li_mobile_no.setVisibility(View.VISIBLE);
                    li_suppiler_number.setVisibility(View.GONE);


                } else {

                    li_mobile_no.setVisibility(View.GONE);
                    li_suppiler_number.setVisibility(View.VISIBLE);

                }
            }
        });


        dbManager = new DBManager(this);
        dbManager.open();


        dbManager_pm = new DBManager_PaymentMode(this);
        dbManager_pm.open();


        SetDat_List();
        SetDat_List_payment_mode();

        Delete_All_Item();
        Delete_All_Item_Payment_mode();

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*ed_qty.setText("");
                        ed_Rate.setText("");
                        ed_Disc.setText("");
                        ed_transaction_value.setText("");
                        ed_gst.setText("");
                        ed_gst_amt.setText("");
                        ed_Cess.setText("");
                        ed_cess_amt.setText("");
                        ed_Amount.setText("");*/

                if(ed_qty.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Qty", Toast.LENGTH_SHORT).show();
                }else if(ed_Rate.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Rate", Toast.LENGTH_SHORT).show();
                }else if(ed_Disc.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Discount", Toast.LENGTH_SHORT).show();
                }else if(ed_transaction_value.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Transaction Value", Toast.LENGTH_SHORT).show();
                }else if(ed_gst.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter GST", Toast.LENGTH_SHORT).show();
                }else if(ed_gst_amt.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter GST Amount", Toast.LENGTH_SHORT).show();
                }else if(ed_Cess.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Cess", Toast.LENGTH_SHORT).show();
                }else if(ed_cess_amt.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Cess Amount", Toast.LENGTH_SHORT).show();
                }else if(ed_Amount.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(PurchaseVoucherActivity.this, "item Added", Toast.LENGTH_SHORT).show();

                    dbManager.insert(""+item_sr_no.getSelectedItem().toString().trim(), ""+spin_uom.getSelectedItem().toString().trim(),
                            ""+ed_qty.getText().toString().trim() ,""+ed_Rate.getText().toString().trim() ,
                            ""+ed_Disc.getText().toString().trim() , ""+ed_transaction_value.getText().toString().trim()
                            ,""+ed_gst.getText().toString().trim() , ""+ed_gst_amt.getText().toString().trim() ,
                            ""+ed_Cess.getText().toString().trim() , ""+ed_cess_amt.getText().toString().trim() ,
                            ""+ed_Amount.getText().toString().trim()  ,""+ item_HsnCode ,
                            ""+item_BarCode, ""+itemGroupCode , item_id_id+"" );


                    Get_ItemList();

                    ed_qty.setText("");
                    ed_Rate.setText("");
                    ed_Disc.setText("");
                    ed_transaction_value.setText("");
                    ed_gst.setText("");
                    ed_gst_amt.setText("");
                    ed_Cess.setText("");
                    ed_cess_amt.setText("");
                    ed_Amount.setText("");

                    SetDat_List();

                }


            }
        });

        tv_PM_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(ed_Paid.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Paid", Toast.LENGTH_SHORT).show();

                }else  if(ed_Balance.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Balance", Toast.LENGTH_SHORT).show();

                }else  if(ed_payment_Amount.getText().toString().trim().isEmpty()){
                    Toast.makeText(PurchaseVoucherActivity.this, "Enter Payment Amount", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(PurchaseVoucherActivity.this, "item Added", Toast.LENGTH_SHORT).show();

                    dbManager_pm.insert_payment_mode(""+spin_payment_mode.getSelectedItem().toString().trim(),
                            ""+ed_Paid.getText().toString().trim(),
                            ""+ed_Balance.getText().toString().trim() ,
                            ""+ed_payment_Amount.getText().toString().trim() , payment_mode_id, payment_mode_id);

                    ed_Paid.setText("");
                    ed_Balance.setText("");
                    ed_payment_Amount.setText("");

                    SetDat_List_payment_mode();

                }

            }
        });




        lst_Item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);

                String id = idTextView.getText().toString();

                long _id = Long.parseLong(id);

                dbManager.delete(_id);

                SetDat_List();

                Toast.makeText(PurchaseVoucherActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        lst_Item_payment_mode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {


                TextView idTextView = (TextView) view.findViewById(R.id.id);

                String id = idTextView.getText().toString();

                long _id = Long.parseLong(id);

                dbManager_pm.delete(_id);

                 SetDat_List_payment_mode();

                Toast.makeText(PurchaseVoucherActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        GEt_Support();
        GetParmition();
        Get_ItemList();
        Get_PAymentMode();

        setBlankValue();

    }

    private void setBlankValue() {

        purchased_Price_detail.add(new PurchasedPrice( 0,0,"0",0,0, 0,0  ));
        expensesSummaryList_details.add(new ExpensesSummary( 0,0,0,"0",0  ));
        item_offer_Details.add(new ItemOfferDetail( 0,0,0,0,"0" ,"0" ));


    }

    private void Get_PAymentMode() {

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


                            spin_payment_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            payment_mode_id = 0;

                                        } else {

                                            payment_mode_id = stateListData.get(position - 1).getId();

                                        }


                                    }

                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, payment_mode_arealist);
                                spin_payment_mode.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }


                        /*UomGroup_id = stateListData.get(1).getUomGroup();

                        Get_Uom_List(UomGroup_id);*/

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


    private void Get_ItemList() {

        JsonObject postParam = new JsonObject();
        postParam.addProperty("id", "0");
        postParam.addProperty("itemGroupId", "0");

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.GetMasterItemDetails("Bearer " + tokenManager.getAccessToken() , postParam);
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        Item_List_arealist.clear();
                        Item_List_arealist.add("Select");

                        List<CountryNameData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    Item_List_arealist.add(stateListData.get(i).getItemName());

                                }
                            }


                            item_sr_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            item_id = 0;

                                        } else {

                                            item_id = stateListData.get(position - 1).getUomGroup();
                                            item_id_id = stateListData.get(position - 1).getId();
                                            item_HsnCode = stateListData.get(position - 1).getHsnCode();
                                            item_BarCode = stateListData.get(position - 1).getBarCode();
                                            itemGroupCode = stateListData.get(position - 1).getItemGroupCode();

                                            Get_Uom_List(item_id);

                                        }


                                    }

                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, Item_List_arealist);
                                item_sr_no.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }


                        /*UomGroup_id = stateListData.get(1).getUomGroup();

                        Get_Uom_List(UomGroup_id);*/

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
    private void Get_Uom_List(int item_id) {

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.GetUOM("Bearer " + tokenManager.getAccessToken() , item_id);
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                if (response.isSuccessful()) {
                    CountryListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        Uom_List_arealist.clear();
                        Uom_List_arealist.add("Select");

                        List<CountryNameData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    Uom_List_arealist.add(stateListData.get(i).getUnit());

                                }
                            }

                            spin_uom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            UomGroup_id = 0;

                                        } else {

                                            UomGroup_id = stateListData.get(position - 1).getId();

                                        }
                                    }
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, Uom_List_arealist);
                                spin_uom.setAdapter(countryAdapter_job);
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

    private void GEt_Support(){

        JsonObject postParam = new JsonObject();
        postParam.addProperty("id", "0");

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<StateListResponse> call = git.GetSupplierList("Bearer " + tokenManager.getAccessToken(),postParam);
        call.enqueue(new Callback<StateListResponse>() {
            @Override
            public void onResponse(Call<StateListResponse> call, Response<StateListResponse> response) {
                if (response.isSuccessful()) {
                    StateListResponse stateListResponse = response.body();
                    if (stateListResponse != null) {

                        supplier_arealist.clear();
                        supplier_arealist.add("Select");

                        List<StateListData> stateListData = stateListResponse.getData();

                        if (stateListData.size() > 0) {

                            if (stateListData != null && stateListData.size() > 0) {

                                for (int i = 0; i < stateListData.size(); i++) {

                                    supplier_arealist.add(stateListData.get(i).getSupplierName());

                                }
                            }


                            spin_supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub


                                    Log.e("positionff"," position :  "+position);

                                    if(position != 0){

                                        if (parent.getItemAtPosition(position).toString().equals("Select")) {

                                            supplier_id = 0;

                                        } else {

                                            supplier_id = stateListData.get(position - 1).getId();

                                        }

                                     }


                                }


                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });


                            try {
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, supplier_arealist);
                                spin_supplier.setAdapter(countryAdapter_job);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {

                        }


                       /* stateid = stateListData.get(0).getId();
                        cityType();*/
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

    private void Hit_Submit() {


        ApiUtilMethods.INSTANCE.showloader(PurchaseVoucherActivity.this);

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<MemberListResponse> call = git.GetInventoryPurchase("Bearer " + tokenManager.getAccessToken() ,
                new InventoryPurchaseRequest( "",""+spin_supplier.getSelectedItem().toString().trim() ,
                        ""+supplier_id,
                        ""+stateid,
                        ""+ed_mobile_no.getText().toString().trim() ,
                        "" ,
                        ""+ed_supplier_bill_no.getText().toString().trim(),
                        "true" ,
                        ""+stateid,
                        ""+ed_eway_bill_no.getText().toString().trim(),
                        ""+tv_bill_date.getText().toString().trim() ,
                        "true",
                        ""+Taxable_total.toString().trim(),
                        ""+Cess_total ,
                        "0",
                        ""+Total_total,
                        ""+ed_posting_date.getText().toString().trim() ,
                        ""+ed_document_date.getText().toString().trim(),
                        ""+IGST_total,
                        ""+IGST_total ,
                        ""+IGST_total,
                        itemDetails,
                        Payment_Details ,
                        item_offer_Details
                        ,"",
                        "" ,
                        ""+base64_image,
                        "",
                        ""
                        ,true,
                        cb_unregistered.isChecked(),
                        0,
                        purchased_Price_detail,
                        expensesSummaryList_details));
        call.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(PurchaseVoucherActivity.this);

                if (response.isSuccessful()) {

                    ApiUtilMethods.INSTANCE.SussesDialog(PurchaseVoucherActivity.this, response.body().getMessage(),PurchaseVoucherActivity.this);

                } else {

                    Toast.makeText(PurchaseVoucherActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    handleTokenExpiration(response);
                }
            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(PurchaseVoucherActivity.this);

            }
        });
    }


    private void getListItem() {

        sun_at=0.0 ;
        Taxable_total=0.0 ;
        Cess_total=0.0 ;
        Expenses_total=0 ;
        Round_off_total=0 ;
        Total_total=0.0 ;
        IGST_total=0.0 ;
        ed_Paid.setText("");
        ed_Balance.setText("");
        ed_payment_Amount.setText("");

        list_item.clear();
        list_UOM.clear();
        list_qty.clear();
        list_Rate.clear();
        list_Disc.clear();
        list_Transaction.clear();
        list_Gst.clear();
        list_Gst_amount.clear();
        list_Cess.clear();
        list_Cess_amount.clear();
        list_Amount.clear();
        itemDetails.clear();

        Cursor cursor = dbManager.getListItem();

        Log.e("countivall", " Count " + cursor.getCount());

        if(cursor.getCount()!=0){

            if (cursor != null) {
                cursor.moveToNext();

                do {



                    list_item.add(""+cursor.getString(1).toString().trim());
                    list_UOM.add(""+cursor.getString(2).toString().trim());
                    list_qty.add(""+cursor.getString(3).toString().trim());
                    list_Rate.add(""+cursor.getString(4).toString().trim());
                    list_Disc.add(""+cursor.getString(5).toString().trim());
                    list_Transaction.add(""+cursor.getString(6).toString().trim());
                    list_Gst.add(""+cursor.getString(7).toString().trim());
                    list_Gst_amount.add(""+cursor.getString(8).toString().trim());
                    list_Cess.add(""+cursor.getString(9).toString().trim());
                    list_Cess_amount.add(""+cursor.getString(10).toString().trim());
                    list_Amount.add(""+cursor.getString(11).toString().trim());

                    int Taxable_amt = Integer.parseInt(cursor.getString(6).toString().trim());
                    Double Cess_amt = Double.valueOf(cursor.getString(10).toString().trim());
                    Double GST_amt = Double.valueOf(cursor.getString(8).toString().trim());

                    Double final_amt = Double.valueOf(cursor.getString(11).toString().trim());
                    sun_at = final_amt + sun_at;
                    ed_Balance.setText(sun_at.toString());

                   Taxable_total = Taxable_amt + Taxable_total;
                   Cess_total = Cess_amt + Cess_total;

                    Expenses_total = 0;
                    Total_total = Double.valueOf(sun_at);
                    IGST_total = GST_amt + IGST_total;

                    Log.e("purchasevoucher","Total_total : "+ Total_total +"  ,  IGST_total  :  "+ IGST_total +"   ,  Cess_total  :  "+ Cess_total+"   ,  Taxable_total  :  "+ Taxable_total  );


                    int paid_qty = Integer.parseInt(cursor.getString(3).toString().trim());
                    int cgst_per_unit = Integer.parseInt(cursor.getString(7).toString().trim());
                    int c_cess_per_unit = Integer.parseInt(cursor.getString(9).toString().trim());

                    int hsnCode = Integer.parseInt(cursor.getString(12).toString().trim());
                    int BarCode = Integer.parseInt(cursor.getString(13).toString().trim());
                    int Item_grp_Code = Integer.parseInt(cursor.getString(14).toString().trim());
                    int Rate_amt = Integer.parseInt(cursor.getString(4).toString().trim());
                    String Item_id_code =  cursor.getString(15).toString().trim();


                    Double  cess_rate = Double.valueOf(cursor.getString(10).toString().trim());
                    Double gst_rate =  Double.valueOf(cursor.getString(8).toString().trim());
                    Double transaction_value =  Double.valueOf(cursor.getString(6).toString().trim());

                    itemDetails.add(new ItemDetail(  0,Item_id_code+"","","",UomGroup_id,UomGroup_id,
                            paid_qty,paid_qty,paid_qty,cgst_per_unit,cgst_per_unit,cgst_per_unit,c_cess_per_unit,
                            true, 0,0,hsnCode+"",Rate_amt,cess_rate,gst_rate,
                            transaction_value,sun_at));

                    Log.e("itemdetailvalueee","aaaaaa   :  "+ cursor.getString(12).toString().trim() +" ,  bbbbb    :  "+ cursor.getString(13).toString().trim()+" ,  ccccccc    :  "+ cursor.getString(14).toString().trim() );



                } while (cursor.moveToNext());
            }

        }
    }
    private void getListItem_Payment_mode() {



        Cursor cursor = dbManager_pm.getListItem();

        Log.e("countivall", " Count " + cursor.getCount());

        if(cursor.getCount()!=0){

            if (cursor != null) {
                cursor.moveToNext();

                do {

                    /*""+spin_payment_mode.getSelectedItem().toString().trim(),
                            ""+ed_Paid.getText().toString().trim(),
                            ""+ed_Balance.getText().toString().trim() ,
                            ""+ed_payment_Amount.getText().toString().trim()*/

                    //                 String[] item_sr_pm = new String[]{"CHEQUE", "IMPS" ,"NEFT", "RTGS", "UPI"};

                    String PMode = cursor.getString(1).toString().trim();

                    int Paid_at = Integer.parseInt(cursor.getString(2).toString().trim());
                    Double Balance_amt = Double.valueOf(cursor.getString(3).toString().trim());
                    String utr = cursor.getString(4).toString().trim();
                    int Pay_mode_id = Integer.parseInt(cursor.getString(5).toString().trim());

                    Payment_Details.add(new PaymentDetail(
                            0,
                            Pay_mode_id,
                            Pay_mode_id,
                            Paid_at,
                            Balance_amt,
                            utr ));

                } while (cursor.moveToNext());
            }

        }
    }

    private void Delete_All_Item() {

        Cursor cursor = dbManager.getListItem();

        Log.e("countivall", " Count : " + cursor.getCount());

        if(cursor.getCount()!=0){

            if (cursor != null) {
                cursor.moveToNext();

                do {

                     String iddd = ""+cursor.getString(0).toString().trim();

                    Log.e("numbercallll", " iddd :  " + iddd);


                long _id = Long.parseLong(iddd);

                dbManager.delete(_id);

                    sun_at=0.0 ;
                    Taxable_total=0.0 ;
                    Cess_total=0.0 ;
                    Expenses_total=0 ;
                    Round_off_total=0 ;
                    Total_total=0.0 ;
                    IGST_total=0.0 ;
                    ed_Paid.setText("");
                    ed_Balance.setText("");
                    ed_payment_Amount.setText("");
                    itemDetails.clear();
                    item_offer_Details.clear();
                    purchased_Price_detail.clear();
                    expensesSummaryList_details.clear();
                    Payment_Details.clear();


                } while (cursor.moveToNext());
                SetDat_List();

                Log.e("numbercallll", " out :  " + "outtt");


            }

        }

    }

    private void Delete_All_Item_Payment_mode() {

        Cursor cursor = dbManager_pm.getListItem();

        Log.e("countivall", " Count : " + cursor.getCount());

        if(cursor.getCount()!=0){

            if (cursor != null) {
                cursor.moveToNext();

                do {

                     String iddd = ""+cursor.getString(0).toString().trim();

                    Log.e("numbercallll", " iddd :  " + iddd);


                long _id = Long.parseLong(iddd);

                dbManager_pm.delete(_id);
                    sun_at=0.0 ;
                    Taxable_total=0.0 ;
                    Cess_total=0.0 ;
                    Expenses_total=0 ;
                    Round_off_total=0 ;
                    Total_total=0.0 ;
                    IGST_total=0.0 ;
                    ed_Paid.setText("");
                    ed_Balance.setText("");
                    ed_payment_Amount.setText("");

                } while (cursor.moveToNext());
                SetDat_List_payment_mode();

                Log.e("numbercallll", " out :  " + "outtt");


            }

        }

    }

    private void SetDat_List() {

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

         adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        lst_Item.setAdapter(adapter);

         getListItem();

    }


    private void SetDat_List_payment_mode() {

       dbManager_pm = new DBManager_PaymentMode(this);
        dbManager_pm.open();
        Cursor cursor = dbManager_pm.fetch();

        adapter_payment = new SimpleCursorAdapter(this, R.layout.activity_view_record_payment_mode, cursor, from_pm, to_pm, 0);
        adapter_payment.notifyDataSetChanged();

        lst_Item_payment_mode.setAdapter(adapter_payment);

        getListItem_Payment_mode();

    }

    ArrayList<String> country_arealist = new ArrayList<String>();
     ArrayList<String> state_arealist = new ArrayList<String>();
     ArrayList<String> Item_List_arealist = new ArrayList<String>();
     ArrayList<String> payment_mode_arealist = new ArrayList<String>();
     ArrayList<String> Uom_List_arealist = new ArrayList<String>();
     ArrayList<String> supplier_arealist = new ArrayList<String>();
     ArrayList<String> city_arealist = new ArrayList<String>();
     int UomGroup_id=0;
     int item_id=0;
     int item_id_id=0;
    String item_HsnCode;
     String item_BarCode;
    int itemGroupCode;

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
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, country_arealist);
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
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, state_arealist);
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
                                ArrayAdapter<String> countryAdapter_job = new ArrayAdapter<String>(PurchaseVoucherActivity.this, R.layout.spinner_item, city_arealist);
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

    private void GetParmition() {

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 256);


        }

    }

    private void selectGallery() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent,
                    "Select"), REQ_PICK_IMAGE);

        } catch (Exception ex) {
            showToast("Please install a File Manager.",
                    Toast.LENGTH_SHORT);
        }

    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int REQ_PICK_IMAGE = 101;

    Toast toast;
    File fileUri1;


    public void showToast(final String text, final int duration) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_PICK_IMAGE) {

            if (resultCode == Activity.RESULT_OK) {

                Uri tmp_fileUri = data.getData();

                String selectedImagePath = UriHelper.getPath( this, tmp_fileUri);
                try {
                    fileUri1 = new File(selectedImagePath);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                String document_absolutePath = fileUri1.getAbsolutePath();
                document_upload.setText(fileUri1.getName());

           //     Toast.makeText(this, ""+document_absolutePath, Toast.LENGTH_SHORT).show();

                byte[] data_im = new byte[0];
                try {
                    data_im = document_absolutePath.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                  base64_image = Base64.encodeToString(data_im, Base64.DEFAULT);

             //   Toast.makeText(this, ""+base64_image, Toast.LENGTH_SHORT).show();


            }
        }
    }




    @Override
    public void onClick(View view) {

        if(view==document_upload){



            selectGallery();



        }


        if(view==tv_remove){


             ed_qty.setText("0");
            ed_Rate.setText("");
            ed_Disc.setText("");
            ed_transaction_value.setText("");
            ed_gst.setText("");
            ed_gst_amt.setText("");
            ed_Cess.setText("");
            ed_cess_amt.setText("");
            ed_Amount.setText("");



        }


        if(view==tv_payment_remove){




            ed_Paid.setText("");
            ed_Balance.setText("");
            ed_payment_Amount.setText("");



        }

        if(view==submitBtn){


            if(ed_mobile_no.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            }else  if(ed_eway_bill_no.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Eway Bill Number", Toast.LENGTH_SHORT).show();
            }else  if(tv_bill_date.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Bill Date", Toast.LENGTH_SHORT).show();
            }else  if(ed_posting_date.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Posting Date", Toast.LENGTH_SHORT).show();
            }else  if(ed_document_date.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter Document Date", Toast.LENGTH_SHORT).show();
            }else  if(base64_image.toString().trim().equalsIgnoreCase("1")){
                Toast.makeText(this, "Select Image", Toast.LENGTH_SHORT).show();
            }else {

                Hit_Submit();
            }




        }

    }


}