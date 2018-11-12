package cn.kevinwu.zhihudaily_android.repository;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;

import cn.kevinwu.zhihudaily_android.App;
import cn.kevinwu.zhihudaily_android.api.DailyService;
import cn.kevinwu.zhihudaily_android.model.DailyModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: KevinWu
 * date:   On 2018/11/8.
 */

public class DailyRepositoryImpl implements DailyRepository {

    @Override
    public void loadData(final DailyCallback dailyCallback) {
        DailyService dailyService = App.retrofit.create(DailyService.class);
        Call<ResponseBody> stringCall = dailyService.getNewest();
        stringCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String content = response.body().string();
                if(!TextUtils.isEmpty(content)) {
                    if(dailyCallback != null) {
                            dailyCallback.onSuccess(new Gson().fromJson(content, DailyModel.class));
                    }
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(dailyCallback != null) {
                    dailyCallback.onFail();
                }
            }
        });
    }
}
