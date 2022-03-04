package com.geektech.newsapp40.utils.app;

import android.app.Application;

import androidx.room.Room;

import com.geektech.newsapp40.data.room.database.Database;

public class App extends Application {
    public static Database database;
    static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        database = Room.databaseBuilder(getApplicationContext(), Database.class, "database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
}
