package cn.kevinwu.zhihudaily_android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * author: KevinWu
 * date:   On 2018/11/8.
 */

public class DailyModel {
    private String date;
    private List<NewsModel> news;
    private List<NewsModel> top_stories;

    public String getDate() {
        return date;
    }

    public List<NewsModel> getNews() {
        List tempList = new ArrayList();
        tempList.addAll(top_stories);
        if(top_stories != null && news != null){
            for(NewsModel newsModel : news){
                if(!tempList.contains(newsModel)){
                    tempList.add(newsModel);
                }
            }
        }
        return tempList;
    }
}

