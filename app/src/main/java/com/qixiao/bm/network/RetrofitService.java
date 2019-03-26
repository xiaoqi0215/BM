package com.qixiao.bm.network;



import com.qixiao.bm.bean.reponse.Test;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("volley/person_object.json")
    Observable<Response<Test>> test();
}
