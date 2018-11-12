package cn.kevinwu.zhihudaily_android.repository;

import cn.kevinwu.zhihudaily_android.model.DailyModel;

/**
 * author: KevinWu
 * date:   On 2018/11/8.
 */

public interface DailyRepository {
    interface DailyCallback {
        void onSuccess(DailyModel dailyModel);
        void onFail();
    }
    void loadData(DailyCallback dailyCallback);
}
