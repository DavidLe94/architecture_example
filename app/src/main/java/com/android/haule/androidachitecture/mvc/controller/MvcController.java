package com.android.haule.androidachitecture.mvc.controller;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.android.haule.androidachitecture.adapter.AnswerAdapter;
import com.android.haule.androidachitecture.api.ApiServices;
import com.android.haule.androidachitecture.api.ApiUtils;
import com.android.haule.androidachitecture.callback.OnItemClickListener;
import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.models.ResAnswer;
import java.util.ArrayList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hau Le on 2018-10-17.
 */
public class MvcController {
    private ApiServices apiServices;
    private Context context;

    public MvcController(Context context) {
        this.context = context;
        apiServices = ApiUtils.getApiService();
    }

    public void callApi(final RecyclerView recyclerView, final Dialog dialog){
        dialog.show();
        apiServices.getListAnswerObs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResAnswer>() {
                @Override
                public void onCompleted() {
                    dialog.dismiss();
                    Log.d("TAG", "onCompleted: ");
                }

                @Override
                public void onError(Throwable e) {
                    dialog.dismiss();
                    Log.d("TAG", "onError: ");
                }

                @Override
                public void onNext(ResAnswer resAnswer) {
                    Log.d("TAG", "onNext: ");
                    AnswerAdapter adapter = new AnswerAdapter(context,
                            (ArrayList<Item>) resAnswer.getItems(),
                            new OnItemClickListener() {
                                @Override
                                public void callback(View view, int position) {
                                    //TODO:data callback
                                }
                            });
                    recyclerView.setAdapter(adapter);
                }
            });
    }
}
