package com.infotech.finnid.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    public String LoginPref = "LOGIN_PREF";
    private static final String PREF_NAME = "MyPrefs";
    private static final String ACCESS_TOKEN_KEY = "accessToken";
    private static final String REFRESH_TOKEN_KEY = "refreshToken";
    private static final String REFRESH_TOKEN_EXPIRY_KEY = "refreshTokenExpiryTime";

    private static final String PREF_NAME_GENERAL = "finnidDataPref";
    private static final String PREF_NAME_NON_REMOVAL = "finnidNonRemovalPref";

    private final SharedPreferences generalPreferences;
    private final SharedPreferences nonRemovalPreferences;

    public PreferencesManager(Context context) {
        generalPreferences = context.getSharedPreferences(PREF_NAME_GENERAL, Context.MODE_PRIVATE);
        nonRemovalPreferences = context.getSharedPreferences(PREF_NAME_NON_REMOVAL, Context.MODE_PRIVATE);
    }

    // General Preferences Methods
    public void set(String key, String value) {
        SharedPreferences.Editor editor = generalPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return generalPreferences.getString(key, "");
    }

    // Token-related Preferences Methods
    public void saveTokens(String accessToken, String refreshToken, String refreshTokenExpiryTime) {
        SharedPreferences.Editor editor = generalPreferences.edit();
        editor.putString(ACCESS_TOKEN_KEY, accessToken);
        editor.putString(REFRESH_TOKEN_KEY, refreshToken);
        editor.putString(REFRESH_TOKEN_EXPIRY_KEY, refreshTokenExpiryTime);
        editor.apply();
    }


    public String getAccessToken() {
        return generalPreferences.getString(ACCESS_TOKEN_KEY, "");
    }

    public String getRefreshToken() {
        return generalPreferences.getString(REFRESH_TOKEN_KEY, "");
    }

    public String getRefreshTokenExpiryTime() {
        return generalPreferences.getString(REFRESH_TOKEN_EXPIRY_KEY, "");
    }

    // Non-Removal Preferences Methods
    public void setNonRemoval(String key, String value) {
        SharedPreferences.Editor editor = nonRemovalPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getNonRemovalString(String key) {
        return nonRemovalPreferences.getString(key, "");
    }

    // Other General Preferences Methods
    public void clear() {
        SharedPreferences.Editor editor = generalPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
