package cn.kevinwu.zhihudaily_android;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * author: KevinWu
 * date:   On 2018/11/9.
 */

public class App extends Application {
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://news.at.zhihu.com")
                .client(okHttpClient)
                .build();
    }
}
