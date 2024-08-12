package com.infotech.finnid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.PreferencesManager;

public class SplashActivity extends AppCompatActivity {
private LoginResponse mLoginResponse;
private PreferencesManager mAppPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAppPreferences = ApiUtilMethods.INSTANCE.getAppPreferences(this);
        mLoginResponse = ApiUtilMethods.INSTANCE.getLoginResponse(mAppPreferences);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {


            @Override
            public void run() {
                goToNextScreen();
            }
        },3000);

    }
    void goToNextScreen() {

        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            startDashboard();
        } else {
            startLogin();
        }

    }
    void startLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    void startDashboard() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivityNew.class);
        startActivity(intent);
        finish();
    }
}