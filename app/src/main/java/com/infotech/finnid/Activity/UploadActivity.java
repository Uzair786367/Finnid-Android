package com.infotech.finnid.Activity;

import static com.infotech.finnid.Utils.UtilMethods.rotateImage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.R;
import com.infotech.finnid.Recharge.ApiRespose.OperatorRespose;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;
import com.infotech.finnid.Utils.UtilMethods;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    String operator;
    TextView signature_choose_file , Identity_choose_file , address_choose_file , Profile_choose_file ;
    TextView signature_choose_file_tv , IdentityTv , addressTv ,ProfileTv , upload_tv , cancel_tv ;

    ImageView toolbar_im;
    TextView toolbar_text;
    ImageView Browser;
     String type_im;
     String memberId;

    String  signature_imagepath="0" , identity_imagepath="0" , address_imagepath="0" , Profile_imagepath="0";


    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
    private PreferencesManager tokenManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload);

        GEtid();

    }

    private void GEtid() {

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);

        tokenManager = new PreferencesManager(this);


        operator = getIntent().getStringExtra("operator");
        memberId = getIntent().getStringExtra("memberId");

        Identity_choose_file= findViewById(R.id.Identity_choose_file);
        signature_choose_file= findViewById(R.id.signature_choose_file);
        address_choose_file= findViewById(R.id.address_choose_file);
        Profile_choose_file= findViewById(R.id.Profile_choose_file);
        Browser= findViewById(R.id.Browser);


        signature_choose_file_tv= findViewById(R.id.signature_choose_file_tv);
        IdentityTv= findViewById(R.id.IdentityTv);
        addressTv= findViewById(R.id.addressTv);
        ProfileTv= findViewById(R.id.ProfileTv);
        upload_tv= findViewById(R.id.upload_tv);
        cancel_tv= findViewById(R.id.cancel_tv);

        toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_im = findViewById(R.id.toolbar_im);

        Identity_choose_file.setOnClickListener(this);
        signature_choose_file.setOnClickListener(this);
        address_choose_file.setOnClickListener(this);
        Profile_choose_file.setOnClickListener(this);
        toolbar_im.setOnClickListener(this);
        toolbar_text.setOnClickListener(this);
        upload_tv.setOnClickListener(this);



        toolbar_text.setText("Upload");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        GetParmition();

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

    @Override
    public void onClick(View view) {


        if(view==signature_choose_file){

            type_im = "signature";
            SelectImage();

        }

         if(view==Identity_choose_file){

             type_im = "identity";
             SelectImage();

        }

         if(view==address_choose_file){

             type_im = "address";

             SelectImage();

        }

  if(view==Profile_choose_file){

             type_im = "profile";

             SelectImage();

        }
  if(view==upload_tv){

      if(signature_imagepath.toString().trim().equalsIgnoreCase("0")){

          Toast.makeText(this, "Select Signature Photo", Toast.LENGTH_SHORT).show();
          
      }else  if(identity_imagepath.toString().trim().equalsIgnoreCase("0")){
          Toast.makeText(this, "Select identity Photo", Toast.LENGTH_SHORT).show();

      }else  if(address_imagepath.toString().trim().equalsIgnoreCase("0")){
          Toast.makeText(this, "Select Address Proof Photo", Toast.LENGTH_SHORT).show();

      }else  if(Profile_imagepath.toString().trim().equalsIgnoreCase("0")){
          Toast.makeText(this, "Select Profile Photo ", Toast.LENGTH_SHORT).show();

      }else {
          UploadDocumentAdd();

      } 
      
        }
    }

    private void UploadDocumentAdd( ) {

        JsonObject postParam = new JsonObject();
        postParam.addProperty("memberId",  memberId);
        postParam.addProperty("profileImageBase64",  Profile_imagepath);
        postParam.addProperty("signatureImageBase64",  signature_imagepath);
        postParam.addProperty("identityImageBase64",  identity_imagepath);
        postParam.addProperty("addressImageBase64",  address_imagepath);


        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<OperatorRespose> call = git.UploadDocumentAdd("Bearer " + tokenManager.getAccessToken() , postParam );
        call.enqueue(new Callback<OperatorRespose>() {
            @Override
            public void onResponse(Call<OperatorRespose> call, Response<OperatorRespose> response) {

                Toast.makeText(UploadActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {

                    finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<OperatorRespose> call, Throwable t) {

            }
        });

    }


    private void SelectImage() {

        LayoutInflater inflater = (LayoutInflater)  getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.select_type_popup, null);

        ImageView im_cross = view.findViewById(R.id.im_cross);

        LinearLayout li_camera = view.findViewById(R.id.li_camera);
        LinearLayout li_gallery = view.findViewById(R.id.li_gallery);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);

        //  // dialog.setCancelable(false);
        dialog.setContentView(view);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        li_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectCammera();

                dialog.dismiss();

            }
        });


        li_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectGallery();


                dialog.dismiss();
            }
        });


        im_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });


        dialog.show();




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

    String base64String ="0";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

             if (requestCode == REQ_PICK_IMAGE) {

            if (resultCode == Activity.RESULT_OK) {

                Uri tmp_fileUri = data.getData();

                String selectedImagePath = UriHelper.getPath( this, tmp_fileUri);
                fileUri1 = new File(selectedImagePath);

               /* imagepath = fileUri1.getAbsolutePath();
                 signature_choose_file_tv.setText(fileUri1.getName());*/

                base64String = UtilMethods.convertFileToBase64(fileUri1.getAbsolutePath());

                if(type_im.toString().trim().equalsIgnoreCase("signature")){

                    signature_imagepath = base64String;
                    signature_choose_file_tv.setText(fileUri1.getName());

                }else  if(type_im.toString().trim().equalsIgnoreCase("identity")){


                    identity_imagepath =  base64String;

                    IdentityTv.setText(fileUri1.getName());

                }else  if(type_im.toString().trim().equalsIgnoreCase("address")){


                    address_imagepath =  base64String;

                    addressTv.setText(fileUri1.getName());

                }else  if(type_im.toString().trim().equalsIgnoreCase("profile")){


                    Profile_imagepath =  base64String;

                    ProfileTv.setText(fileUri1.getName());

                }

            }
        }
    }




    private void selectCammera() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCameraIntent("Browser");
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }


    }

    private void startCameraIntent(String type) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (intent.resolveActivity(getPackageManager()) != null) {
                //Create a file to store the image
                File photoFile = null;
                try {
                    photoFile = UtilMethods.createImageFile(UploadActivity.this);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(
                            UploadActivity.this, getPackageName() + ".provider", photoFile
                    );
                    intent.putExtra(
                            MediaStore.EXTRA_OUTPUT, photoURI
                    );
                }
            }
        }

        if (!TextUtils.isEmpty(type)) {
            if (type.equalsIgnoreCase("Browser")) {
                cameraResultLauncher.launch(intent);
            }
        }
    }



    ActivityResultLauncher<Intent> cameraResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result != null) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            String imgUrl = UtilMethods.imageFilePath;
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            Bitmap bitmap = BitmapFactory.decodeFile(imgUrl, bmOptions);
                            Browser.setImageBitmap(bitmap);
                            if (!TextUtils.isEmpty(imgUrl)) {
                                File file = new File(imgUrl);
                                Bitmap rotatedBitmap = bitmap;
                                try {
                                    if (!TextUtils.isEmpty(file.getAbsolutePath())) {
                                        ExifInterface ei = new ExifInterface(file.getAbsolutePath());
                                        int orientation = ei.getAttributeInt(
                                                ExifInterface.TAG_ORIENTATION,
                                                ExifInterface.ORIENTATION_UNDEFINED
                                        );
                                        switch (orientation) {
                                            case ExifInterface.ORIENTATION_ROTATE_90:
                                                rotatedBitmap = rotateImage(
                                                        bitmap,
                                                        90f
                                                );
                                                break;
                                            case ExifInterface.ORIENTATION_ROTATE_180:
                                                rotatedBitmap = rotateImage(
                                                        bitmap,
                                                        180f
                                                );
                                                break;
                                            case ExifInterface.ORIENTATION_ROTATE_270:
                                                rotatedBitmap = rotateImage(
                                                        bitmap,
                                                        270f
                                                );
                                                break;
                                            default:
                                                rotatedBitmap = bitmap;
                                                break;

                                        }
                                    }
                                } catch (IOException ee) {
                                    ee.printStackTrace();
                                }
                                Browser.setImageBitmap(rotatedBitmap);
                                if (file.exists()) {

                                    base64String = UtilMethods.convertFileToBase64(file.getAbsolutePath());



                                    if(type_im.toString().trim().equalsIgnoreCase("signature")){

                                        signature_imagepath =  base64String;
                                        signature_choose_file_tv.setText(file.getName());

                                    }else  if(type_im.toString().trim().equalsIgnoreCase("identity")){


                                        identity_imagepath =  base64String;

                                        IdentityTv.setText(file.getName());

                                    }else  if(type_im.toString().trim().equalsIgnoreCase("address")){


                                        address_imagepath =  base64String;

                                        addressTv.setText(file.getName());

                                    }else if(type_im.toString().trim().equalsIgnoreCase("profile")){


                                        Profile_imagepath =  base64String;

                                        ProfileTv.setText(file.getName());


                                    }


                                }
                            }
                            //uploadImageDocVideoFile(imageURI, Constants.images)
                        } else {
                            if (result.getData() != null) {
                                onCaptureImageResult(result.getData());
                            }
                        }
                    }
                }
            });

    private void onCaptureImageResult(Intent data) {
        if (data.getExtras() != null && data.getExtras().get("data") != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            if (thumbnail != null) {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File destination = new File(
                        this.getExternalFilesDir("").toString(), System.currentTimeMillis() + ".jpg"
                );
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Browser.setImageBitmap(thumbnail);

                Uri tempUri = getImageUri(thumbnail);

                if (tempUri != null) {
                    File file = FileUtils.getFile(UploadActivity.this, tempUri);

                  /*  imagepath = file.getAbsolutePath();
                    signature_choose_file_tv.setText(file.getName());*/

                    base64String = UtilMethods.convertFileToBase64(file.getAbsolutePath());


                    if(type_im.toString().trim().equalsIgnoreCase("signature")){

                        signature_imagepath = base64String;
                        signature_choose_file_tv.setText(file.getName());

                    }else  if(type_im.toString().trim().equalsIgnoreCase("identity")){


                        identity_imagepath = base64String;

                        IdentityTv.setText(file.getName());

                    }else  if(type_im.toString().trim().equalsIgnoreCase("address")){


                        address_imagepath = base64String;

                        addressTv.setText(file.getName());

                    }else  if(type_im.toString().trim().equalsIgnoreCase("profile")){


                        Profile_imagepath = base64String;

                        ProfileTv.setText(file.getName());



                    }

                }
            }
        }
    }


    private Uri getImageUri(Bitmap inImage) {
        String path = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            path = MediaStore.Images.Media.insertImage(
                    getContentResolver(), inImage, "Title", null
            );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getIImageUri->", "ERROR  : " + e.getMessage());
        }
        return Uri.parse(path);
    }


  /*  public void startCropActivity(Uri tmp_fileUri) {
      *//*  Intent intent = CropImage.activity(tmp_fileUri).setAllowRotation(true).getIntent(this);
        startActivityForResult(intent, REQ_CROP_IMAGE_FIRST);*//*
    }*/

}