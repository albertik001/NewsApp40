package com.geektech.newsapp40.NewsModel;

import com.geektech.newsapp40.R;

import java.util.ArrayList;

public class ClientModel {

    //База данных OnBoard
    public static int[] imageList = new int[]{R.drawable.pngwing_com_2_, R.drawable.pngwing_com_3_, R.drawable.pngwing_com_1_};
    public static String[] titles = new String[]{"Fast Food", "Fast Delivery", "Easy Payment"};
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
    //Лист
    public static ArrayList<NewsModel> newsList = new ArrayList<>();

    public static ArrayList<NewsModel> getNewsList() {
        return newsList;
    }


}
