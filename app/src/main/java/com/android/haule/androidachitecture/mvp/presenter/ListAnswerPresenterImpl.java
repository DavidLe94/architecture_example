package com.android.haule.androidachitecture.mvp.presenter;

import com.android.haule.androidachitecture.utils.Reference;
import com.android.haule.androidachitecture.api.ApiServices;
import com.android.haule.androidachitecture.api.ApiUtils;
import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.models.ResAnswer;
import com.android.haule.androidachitecture.mvp.views.ListAnswerView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hau Le on 2018-10-11.
 */
public class ListAnswerPresenterImpl implements ListAnswerPresenter {
    private ListAnswerView view;
    private ApiServices apiServices;

    public ListAnswerPresenterImpl(ListAnswerView view) {
        this.view = view;
        apiServices = ApiUtils.getApiService();
    }

    @Override
    public void actionGetListMovie() {
        view.showLoading();
        apiServices.getListAnswer().enqueue(new Callback<ResAnswer>() {
            @Override
            public void onResponse(Call<ResAnswer> call, Response<ResAnswer> response) {
                if(response.code() == Reference.SUCCESS){
                    view.getListMovieSuccess((ArrayList<Item>) response.body().getItems());
                }else{
                    view.getListMovieFailed(response.message());
                }
                view.hiddenLoading();
            }

            @Override
            public void onFailure(Call<ResAnswer> call, Throwable t) {
                view.hiddenLoading();
                view.getListMovieFailed(t.getMessage());
            }
        });
    }
}
