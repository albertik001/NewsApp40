package com.geektech.newsapp40.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.newsapp40.data.room.model.NewsModel;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    void insert(NewsModel newsModel);

    @Query("SELECT * FROM newsmodel ORDER by id DESC")
    LiveData<List<NewsModel>> getAll();
}
