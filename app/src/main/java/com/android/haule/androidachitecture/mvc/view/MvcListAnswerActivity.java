package com.android.haule.androidachitecture.mvc.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.android.haule.androidachitecture.R;
import com.android.haule.androidachitecture.base.BaseActivity;
import com.android.haule.androidachitecture.mvc.controller.MvcController;
import butterknife.BindView;

public class MvcListAnswerActivity extends BaseActivity{
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private Dialog dialog;
    private MvcController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new MvcController(this);
        dialog = showLoadingDialog(this, "Loading...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.callApi(recyclerView, dialog);
    }

    @Override
    protected int getLayoutResoureId() {
        return R.layout.activity_mvc_list_answer;
    }
}
