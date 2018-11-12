package cn.kevinwu.zhihudaily_android.daily;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.kevinwu.zhihudaily_android.App;
import cn.kevinwu.zhihudaily_android.R;
import cn.kevinwu.zhihudaily_android.model.DailyModel;
import cn.kevinwu.zhihudaily_android.model.NewsModel;
import cn.kevinwu.zhihudaily_android.repository.DailyRepository;
import cn.kevinwu.zhihudaily_android.repository.DailyRepositoryImpl;

public class DailyPageActivity extends AppCompatActivity {
    private RecyclerView rcvDailyList;
    private DailyAdapter dailyAdapter;
    private List<NewsModel> newsModelList;
    private DailyRepositoryImpl dailyRepositoryImpl;
    private FloatingActionButton btnTest;
    private int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvDailyList = findViewById(R.id.rcv_dailyList);
        btnTest = findViewById(R.id.fab_floatingActionButton);
        rcvDailyList.setLayoutManager(new LinearLayoutManager(this));
        newsModelList = new ArrayList<>();
        dailyAdapter = new DailyAdapter(this, newsModelList);
        rcvDailyList.setAdapter(dailyAdapter);
        dailyRepositoryImpl = new DailyRepositoryImpl();
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData(++a);
            }
        });
        refreshData(1);
    }

    private void refreshData(final int times) {
        newsModelList.clear();
        dailyRepositoryImpl.loadData(new DailyRepository.DailyCallback() {
            @Override
            public void onSuccess(DailyModel dailyModel) {
                List<NewsModel> tempList = new ArrayList<>();
                for(int i = times; i>0 ;i--) {
                    tempList.addAll(dailyModel.getNews());
                }
                newsModelList = tempList;
                dailyAdapter.setListData(newsModelList);
                dailyAdapter.notifyDataSetChanged();
                getSupportActionBar().setTitle(getApplicationContext().getString(R.string.app_name) + "   重复：" + times);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
