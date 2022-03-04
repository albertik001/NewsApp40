package com.geektech.newsapp40.data.room.database;

import androidx.room.RoomDatabase;

import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.data.room.dao.NewsDao;

@androidx.room.Database(entities = {NewsModel.class}, version = 4, exportSchema = false)
public abstract class Database extends RoomDatabase {
    static Database database;

    public static Database getDatabase() {
        return database;
    }

    public abstract NewsDao newsDao();
}