package com.geektech.newsapp40.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geektech.newsapp40.NewsModel.NewsModel;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(NewsModel newsModel);

    @Query("SELECT * FROM name_news")
    List<NewsModel> getAllNews();

    @Delete
    void deleteNews(NewsModel newsModel);
}
