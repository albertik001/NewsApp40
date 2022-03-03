package com.geektech.newsapp40.data;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.geektech.newsapp40.data.AppDataBase;

public class App extends Application {
    public static AppDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "newsAppDataBase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
}
