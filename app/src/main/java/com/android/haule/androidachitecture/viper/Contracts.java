package com.android.haule.androidachitecture.viper;

import com.android.haule.androidachitecture.models.Item;

import java.util.ArrayList;

/**
 * Created by Hau Le on 2018-10-15.
 */
public interface Contracts {
    interface View{
        void getListAnswerFailed(String message);
        void getListAnswerSuccess(ArrayList<Item> list);
        void showLoading();
        void hiddenLoading();
    }

    interface Presenter{
        void actionLoadListAnswer();
    }

    interface Interactor{
        void doLoadListAnswer();
    }

    interface InteractorOutput{
        void onGetAnswerListSuccess(ArrayList<Item> list);
        void onGetAnswerListFailed(String message);
    }

    interface Router{

    }
}
