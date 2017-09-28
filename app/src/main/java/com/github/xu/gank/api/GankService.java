package com.github.xu.gank.api;

import com.github.xu.gank.bean.BoonBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xukankan on 2017/9/22.
 */

public interface GankService {
    String BASE_URL = "http://www.gank.io/api/";

    @GET("data/%E7%A6%8F%E5%88%A9/10/{pageNumber}")
    Observable<BoonBean> getBoon(@Path("pageNumber") int pageNumber);
}
