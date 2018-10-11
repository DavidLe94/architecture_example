package com.android.haule.androidachitecture.mvp.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.android.haule.androidachitecture.R;
import com.android.haule.androidachitecture.adapter.AnswerAdapter;
import com.android.haule.androidachitecture.base.BaseActivity;
import com.android.haule.androidachitecture.callback.OnItemClickListener;
import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.mvp.presenter.ListAnswerPresenter;
import com.android.haule.androidachitecture.mvp.presenter.ListAnswerPresenterImpl;
import java.util.ArrayList;
import butterknife.BindView;

public class MvpListAnswerActivity extends BaseActivity implements ListAnswerView {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private Dialog dialog;
    private AnswerAdapter adapter;
    private ArrayList<Item> list;
    private ListAnswerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new ListAnswerPresenterImpl(this);
        list = new ArrayList<>();
        adapter = new AnswerAdapter(this, list, new OnItemClickListener() {
            @Override
            public void callback(View view, int position) {
                //TODO: callback
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.actionGetListMovie();
    }

    @Override
    protected int getLayoutResoureId() {
        return R.layout.activity_mvp_list_answer;
    }

    @Override
    public void getListMovieSuccess(ArrayList<Item> list) {
        this.list = list;
        adapter.notifyData(list);
    }

    @Override
    public void getListMovieFailed(String message) {
        Toast.makeText(this, "Can't get list movie", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        hiddenLoading();
        dialog = showLoadingDialog(this, "Loading...");
        dialog.show();
    }

    @Override
    public void hiddenLoading() {
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
