package com.android.haule.androidachitecture.api;

import com.android.haule.androidachitecture.models.ResAnswer;


import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Hau Le on 2018-10-09.
 */
public interface ApiServices {
    @GET("2.2/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<ResAnswer> getListAnswerObs();

    @GET("2.2/answers?order=desc&sort=activity&site=stackoverflow")
    Call<ResAnswer> getListAnswer();
}
