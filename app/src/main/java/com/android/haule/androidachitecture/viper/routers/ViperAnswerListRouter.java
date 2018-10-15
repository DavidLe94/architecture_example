package com.android.haule.androidachitecture.viper.routers;

import android.content.Context;

import com.android.haule.androidachitecture.viper.Contracts;

/**
 * Created by Hau Le on 2018-10-15.
 */
public class ViperAnswerListRouter implements Contracts.Router {
    private Context context;

    public ViperAnswerListRouter(Context context) {
        this.context = context;
    }
}
