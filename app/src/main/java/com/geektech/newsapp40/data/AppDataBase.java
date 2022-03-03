package com.geektech.newsapp40.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.newsapp40.NewsModel.NewsModel;

@Database(entities = {NewsModel.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
