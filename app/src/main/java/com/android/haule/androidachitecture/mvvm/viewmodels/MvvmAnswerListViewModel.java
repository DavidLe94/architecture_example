package com.android.haule.androidachitecture.mvvm.viewmodels;

import android.app.Dialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.widget.Toast;
import com.android.haule.androidachitecture.api.ApiServices;
import com.android.haule.androidachitecture.api.ApiUtils;
import com.android.haule.androidachitecture.databinding.ActivityMvvmAnswerListBinding;
import com.android.haule.androidachitecture.models.ResAnswer;
import com.android.haule.androidachitecture.mvvm.adapters.AnswerAdapter;
import com.android.haule.androidachitecture.mvvm.dialogs.Loading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hau Le on 2018-10-15.
 */
public class MvvmAnswerListViewModel extends BaseObservable{
    private ApiServices apiServices;
    private Dialog dialog;
    private AnswerAdapter adapter;

    public MvvmAnswerListViewModel(final Context context, ActivityMvvmAnswerListBinding binding) {
        initLoading(context);
        apiServices = ApiUtils.getApiService();
        adapter = new AnswerAdapter();
        binding.listAnswer.setAdapter(adapter);
        //show loading...
        showLoading();
        //call api
        apiServices.getListAnswer().enqueue(new Callback<ResAnswer>() {
            @Override
            public void onResponse(Call<ResAnswer> call, Response<ResAnswer> response) {
                adapter.setItemList(response.body().getItems());
                dismissLoading();
            }

            @Override
            public void onFailure(Call<ResAnswer> call, Throwable t) {
                dismissLoading();
                Toast.makeText(context, "Ca't get answer list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initLoading(Context context){
        dialog = Loading.showLoadingDialog(context, "Loading...");
    }

    private void showLoading(){
        dialog.show();
    }

    private void dismissLoading(){
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
