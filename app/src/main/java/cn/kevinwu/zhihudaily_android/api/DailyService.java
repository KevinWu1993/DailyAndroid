package cn.kevinwu.zhihudaily_android.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * author: KevinWu
 * date:   On 2018/11/9.
 */

public interface DailyService {

    @GET(DailyApi.LATEST_DAILY)
    Call<ResponseBody> getNewest();

}
