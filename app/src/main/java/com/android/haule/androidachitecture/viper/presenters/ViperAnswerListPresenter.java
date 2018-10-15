package com.android.haule.androidachitecture.viper.presenters;

import android.content.Context;

import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.viper.AnswerListContracts;
import com.android.haule.androidachitecture.viper.interactors.ViperAnswerListInteractor;
import com.android.haule.androidachitecture.viper.routers.ViperAnswerListRouter;
import java.util.ArrayList;

/**
 * Created by Hau Le on 2018-10-15.
 */
public class ViperAnswerListPresenter implements AnswerListContracts.Presenter, AnswerListContracts.InteractorOutput {
    private AnswerListContracts.View view;
    private AnswerListContracts.Router router;
    private AnswerListContracts.Interactor interactor = new ViperAnswerListInteractor(this);

    public ViperAnswerListPresenter(AnswerListContracts.View view) {
        this.view = view;
        router = new  ViperAnswerListRouter((Context) view);
    }

    @Override
    public void actionLoadListAnswer() {
        view.showLoading();
        interactor.doLoadListAnswer();
    }

    @Override
    public void onGetAnswerListSuccess(ArrayList<Item> list) {
        view.hiddenLoading();
        view.getListAnswerSuccess(list);
    }

    @Override
    public void onGetAnswerListFailed(String message) {
        view.hiddenLoading();
        view.getListAnswerFailed(message);
    }
}
