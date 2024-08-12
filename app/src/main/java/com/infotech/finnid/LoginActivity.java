package com.infotech.finnid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.infotech.finnid.ApiRequest.ChangePassReq;
import com.infotech.finnid.ApiRequest.LoginRequest;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.Utils.ApiClients;
import com.infotech.finnid.Utils.ApiUtilMethods;
import com.infotech.finnid.Utils.ApplicationConstant;
import com.infotech.finnid.Utils.EndPoint;
import com.infotech.finnid.Utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tv_signup,tvForgotpass;
    private LinearLayout llLogin;
    private LinearLayout llFwdPass;
    private AutoCompleteTextView et_mobile,et_pass,et_mobile_fwp,et_old_pass,et_new_pass;
    private CardView cancelButton,okButton;
    CardView btLogin;
    private TextView tilMobile,tilEmail,tilOldPass,tilNewPass;
    private TextView tilPass;
    private PreferencesManager tokenManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tokenManager = new PreferencesManager(getApplicationContext());

        tv_signup = (TextView) findViewById(R.id.tv_signup);
        tvForgotpass = findViewById(R.id.tv_forgotpass);
        llLogin = findViewById(R.id.ll_login);
        llFwdPass = findViewById(R.id.ll_fwd_pass);
        cancelButton = findViewById(R.id.cancelButton);
        okButton = findViewById(R.id.okButton);
        et_mobile = findViewById(R.id.et_mobile);
        et_mobile_fwp = findViewById(R.id.et_mobile_fwp);
        et_old_pass = findViewById(R.id.et_old_pass);
        et_new_pass = findViewById(R.id.et_new_pass);
        et_pass = findViewById(R.id.et_pass);
        btLogin = findViewById(R.id.bt_login);
        tilMobile = findViewById(R.id.til_mobile_num);
        tilEmail = findViewById(R.id.til_email_num);
        tilOldPass = findViewById(R.id.til_old_num);
        tilNewPass = findViewById(R.id.til_new_num);
        tilPass = findViewById(R.id.til_password);
        et_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateMobile()) {
                    return;
                }
            }
        });
        et_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validatePass()) {
                    return;
                }
            }
        });
        et_mobile_fwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateEmail()) {
                    return;
                }
            }
        });
        et_old_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateOldPass()) {
                    return;
                }
            }
        });
        et_new_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateNewPass()) {
                    return;
                }
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                login();
            }
        });


        tv_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SinUpActivity.class);
                startActivity(intent);
            }
        });
        tvForgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLogin.setVisibility(View.GONE);
                llFwdPass.setVisibility(View.VISIBLE);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLogin.setVisibility(View.VISIBLE);
                llFwdPass.setVisibility(View.GONE);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail()) {
                    return;
                }
                if (!validateOldPass()) {
                    return;
                }
                if (!validateNewPass()) {
                    return;
                }
                changePassword();
            }
        });


    }

    private boolean validateNewPass() {
        {
            if (et_new_pass.getText().toString().trim().isEmpty()) {
                tilNewPass.setText(getString(R.string.err_new_pass));
                requestFocus(et_new_pass);
                return false;
            } else {
                tilNewPass.setText("");
            }
            return true;
        }
    }

    private boolean validateOldPass() {
        {
            if (et_old_pass.getText().toString().trim().isEmpty()) {
                tilOldPass.setText(getString(R.string.err_old_pass));
                requestFocus(et_old_pass);
                return false;
            } else {
                tilOldPass.setText("");
            }
            return true;
        }
    }

    private boolean validateEmail() {
        if (et_mobile_fwp.getText().toString().trim().isEmpty()) {
            tilEmail.setText(getString(R.string.err_empty_field));
            requestFocus(et_mobile_fwp);
            return false;
        } else {
            tilEmail.setText("");
        }

        return true;
    }


    private boolean validateMobile() {
        if (et_mobile.getText().toString().trim().isEmpty()) {
            tilMobile.setText(getString(R.string.err_empty_field));
            requestFocus(et_mobile);
            return false;
        } else {
            tilMobile.setText("");
        }

        return true;
    }

    private boolean validatePass() {
        if (et_pass.getText().toString().trim().isEmpty()) {
            tilPass.setText(getString(R.string.err_msg_pass));
            requestFocus(et_pass);
            return false;
        } else {
            tilPass.setText("");
        }
        return true;
    }
    private void login() {

        if (!validateMobile()) {
            return;
        }
        if (!validatePass()) {
            return;
        }

        secureLogin();
    }

    private void secureLogin() {

        ApiUtilMethods.INSTANCE.showloader(this);


        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<LoginResponse> call = git.secureLogin(new LoginRequest(et_mobile.getText().toString().trim(), et_pass.getText().toString().trim()));
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                ApiUtilMethods.INSTANCE.hideLoader(LoginActivity.this);

                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse loginResponse = response.body();

                    if (loginResponse.isSuccess()) {
                        // Save tokens using TokenManager
                        tokenManager.saveTokens(
                                loginResponse.getData().getToken(),
                                loginResponse.getData().getRefreshToken(),
                                loginResponse.getData().getRefreshTokenExpiryTime()
                        );
                        tokenManager.set(tokenManager.LoginPref, new Gson().toJson(response.body()));
                        Intent browseIntent = new Intent(LoginActivity.this, DashboardActivityNew.class);
                        startActivity(browseIntent);
                        finish();
                    } else {
                        showErrorDialog(loginResponse.getMessage());

                     //   Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                ApiUtilMethods.INSTANCE.hideLoader(LoginActivity.this);

                showErrorDialog("Network error. Please check your connection and try again.");
            }
        });

    }
    private void changePassword() {
        EndPoint git = ApiClients.getClient().create(EndPoint.class);
        Call<LoginResponse> call = git.changePassword(new ChangePassReq(et_mobile_fwp.getText().toString().trim(), et_old_pass.getText().toString().trim(), et_new_pass.getText().toString().trim()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()){
                        showSuccessDialog("Password changed successfully!");
                    } else {
                        // API call was not successful, show error dialog with the error message
                        showErrorDialog("Error changing password: " + loginResponse.getMessage());
                    }
                } else {
                    // Handle unsuccessful response (e.g., HTTP error)
                    showErrorDialog("Error changing password. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showErrorDialog("Network error. Please check your connection and try again.");
            }
        });
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Error")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Success")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}