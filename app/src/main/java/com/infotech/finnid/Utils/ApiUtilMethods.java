package com.infotech.finnid.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.infotech.finnid.Activity.MemberRegistrationActivity;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.Fragments.ManageMember;
import com.infotech.finnid.R;

public enum  ApiUtilMethods {
    INSTANCE;
    public int ERROR_OTHER = 1;
    public LoginResponse mTempLoginDataResponse;
    private PreferencesManager mAppPreferences;
    Gson gson;
    Loader loader;

    public LoginResponse getLoginResponse(PreferencesManager mAppPreferences) {
        if (mTempLoginDataResponse != null && mTempLoginDataResponse.getData() != null) {
            return mTempLoginDataResponse;
        } else {
            if (gson == null) {
                gson = new Gson();
            }
            mTempLoginDataResponse = gson.fromJson(mAppPreferences.getString(mAppPreferences.LoginPref), LoginResponse.class);
            return mTempLoginDataResponse;
        }
    }
    public PreferencesManager getAppPreferences(Context mContext) {
        if (mAppPreferences != null) {
            return mAppPreferences;
        } else {
            mAppPreferences = new PreferencesManager(mContext);
            return mAppPreferences;
        }

    }

    public void Error(final Activity context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context);
        customAlertDialog.Error(message);
    }

    public void SussesDialog(final Activity context, final String message , Activity activity) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_show_susses_msg, null);

        TextView msg = (TextView) view.findViewById(R.id.msg);
        TextView ok = (TextView) view.findViewById(R.id.ok);

        final Dialog dialog = new Dialog(context);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        msg.setText(""+ message);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.finish();

                dialog.dismiss();
            }
        });

        dialog.show();

    }




    public void showloader(Context context) {

         loader = new Loader(context, android.R.style.Theme_Translucent_NoTitleBar);

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);
    }
    public void hideLoader(Context context) {

        if (loader != null) {
            if (loader.isShowing())
                loader.dismiss();
        }
    }





}
