package com.geektech.newsapp40.data.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private final SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveUsernameProfile(String name) {
        preferences.edit().putString("username", name).apply();
    }

    public String isUsernameProfile() {
        return preferences.getString("username", "");
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
        return preferences.getString("imageUsers", null);
    }

    public void saveRegisterFragment() {
        preferences.edit().putBoolean("register", false).apply();
    }

    public boolean isRegisterShow() {
        return preferences.getBoolean("register", true);
    }

    public void prefsCash() {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        editor.remove("imageUsers").apply();
        editor.remove("username").apply();
    }

}
