package com.android.haule.androidachitecture.mvp.views;

import com.android.haule.androidachitecture.models.Item;
import java.util.ArrayList;

/**
 * Created by Hau Le on 2018-10-11.
 */
public interface ListAnswerView {
    void getListMovieSuccess(ArrayList<Item> list);
    void getListMovieFailed(String message);
    void showLoading();
    void hiddenLoading();
}
