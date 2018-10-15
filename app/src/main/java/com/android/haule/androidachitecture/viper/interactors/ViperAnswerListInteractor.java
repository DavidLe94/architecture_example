package com.android.haule.androidachitecture.viper.interactors;

import com.android.haule.androidachitecture.api.ApiServices;
import com.android.haule.androidachitecture.api.ApiUtils;
import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.models.ResAnswer;
import com.android.haule.androidachitecture.viper.Contracts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hau Le on 2018-10-15.
 */
public class ViperAnswerListInteractor implements Contracts.Interactor {
    private Contracts.InteractorOutput output;
    private ApiServices apiServices;

    public ViperAnswerListInteractor(Contracts.InteractorOutput output) {
        this.output = output;
        apiServices = ApiUtils.getApiService();
    }

    @Override
    public void doLoadListAnswer() {
        apiServices.getListAnswer().enqueue(new Callback<ResAnswer>() {
            @Override
            public void onResponse(Call<ResAnswer> call, Response<ResAnswer> response) {
                output.onGetAnswerListSuccess((ArrayList<Item>) response.body().getItems());
            }

            @Override
            public void onFailure(Call<ResAnswer> call, Throwable t) {
                output.onGetAnswerListFailed(t.getMessage());
            }
        });
    }
}
