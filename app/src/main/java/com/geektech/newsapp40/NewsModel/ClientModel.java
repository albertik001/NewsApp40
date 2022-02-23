package com.geektech.newsapp40.NewsModel;

import com.geektech.newsapp40.R;

import java.util.ArrayList;

public class ClientModel {

    public static ArrayList<NewsModel> newsList = new ArrayList<>();
    public static String[] titleDescription = new String[]{"Шаганэ ты моя, Шаганэ!\n" +
            "Потому, что я с севера, что ли,\n" +
            "Я готов рассказать тебе поле,\n" +
            "Про волнистую рожь при луне.\n" +
            "Шаганэ ты моя, Шаганэ.", "Потому, что я с севера, что ли,\n" +
            "Что луна там огромней в сто раз,\n" +
            "Как бы ни был красив Шираз,\n" +
            "Он не лучше рязанских раздолий.\n" +
            "Потому, что я с севера, что ли.", "Я готов рассказать тебе поле,\n" +
            "Эти волосы взял я у ржи,\n" +
            "Если хочешь, на палец вяжи —\n" +
            "Я нисколько не чувствую боли.\n" +
            "Я готов рассказать тебе поле."};

    public static ArrayList<NewsModel> getNewsList() {
        newsList.add(new NewsModel("Asd", "243523454325"));
        newsList.add(new NewsModel("Asd", "243523454325"));
        newsList.add(new NewsModel("Asd", "243523454325"));
        newsList.add(new NewsModel("Asd", "243523454325"));
        newsList.add(new NewsModel("Asd", "243523454325"));
        newsList.add(new NewsModel("Asd", "243523454325"));
        return newsList;
    }


}
