package com.geektech.newsapp40;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private final SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveUsernameProfile(String username) {
        preferences.edit().putString("username", username).apply();
    }

    public String isUsernameProfile(String s) {
        return preferences.getString("username", "null");
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("boardState", true).apply();
    }

    public boolean isBoardShow() {
        return preferences.getBoolean("boardState", false);
    }

    public void saveImageUsers(Uri uri) {
        preferences.edit().putString("imageUsers", String.valueOf(uri)).apply();
    }

    public String isImageUsers() {
        return preferences.getString("imageUsers", "null");
    }
}
