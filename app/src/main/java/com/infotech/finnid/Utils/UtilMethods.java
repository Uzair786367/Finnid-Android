package com.infotech.finnid.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.infotech.finnid.Activity.CartListActivity;
import com.infotech.finnid.Activity.MemberRegistrationActivity;
import com.infotech.finnid.Activity.PurchaseVoucherActivity;
import com.infotech.finnid.ApiResponse.CountryListResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.StateListResponse;
import com.infotech.finnid.LoginActivity;
import com.infotech.finnid.Objects.StateListData;
import com.infotech.finnid.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum UtilMethods {

    INSTANCE;

    public static String imageFilePath = "";


    public static File createImageFile(Context context) throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix  */
                storageDir      /* directory*/
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static boolean isJPEGorPNG(String url) {
        try {
            String type = getMimeType(url);
            String ext = type.substring(type.lastIndexOf("/") + 1);
            if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

        return false;
    }


    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    PreferencesManager preferencesManager;
    LoginResponse mLoginResponse;
      PreferencesManager tokenManager;

    public static String convertFileToBase64(String filePath) {
        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] byteArray = new byte[(int) file.length()];
            fileInputStream.read(byteArray);
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void SaleCartAdd(Context context , String itemId , int qty , Double amount   ){


        ApiUtilMethods.INSTANCE.showloader(context);

        tokenManager = new PreferencesManager(context);

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(context);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);


        JsonObject postParam = new JsonObject();
        postParam.addProperty("itemId",  itemId);
        postParam.addProperty("qty", qty );
        postParam.addProperty("amount", amount );

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.SaleCartAdd("Bearer " + tokenManager.getAccessToken(),postParam);
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(context);
                if (response.isSuccessful()) {

                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    handleTokenExpiration(response ,  context);
                }
            }

            @Override
            public void onFailure(Call<CountryListResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(context);

            }
        });
    }
    public void Delete_Cart(Context context , String itemId , int qty){


        ApiUtilMethods.INSTANCE.showloader(context);

        tokenManager = new PreferencesManager(context);

        preferencesManager = ApiUtilMethods.INSTANCE.getAppPreferences(context);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(preferencesManager);


        JsonObject postParam = new JsonObject();
        postParam.addProperty("itemId", ""+ itemId);

        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<CountryListResponse> call = git.Delete_Cart("Bearer " + tokenManager.getAccessToken(),postParam);
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(context);
                if (response.isSuccessful()) {

                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    ((CartListActivity) context).ItemClickRemove();



                } else {

                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    handleTokenExpiration(response ,  context);
                }
            }

            @Override
            public void onFailure(Call<CountryListResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(context);

            }
        });
    }

    private void handleTokenExpiration(Response<?> response  , Context context) {
        if (response.code() == 401) {
            tokenManager.clear();
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
           // finish();
        }
    }

}
