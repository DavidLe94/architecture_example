package com.android.haule.androidachitecture.viper.views;

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
import com.android.haule.androidachitecture.viper.Contracts;
import com.android.haule.androidachitecture.viper.presenters.ViperAnswerListPresenter;
import java.util.ArrayList;
import butterknife.BindView;

public class ViperAnswerListActivity extends BaseActivity implements Contracts.View {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ArrayList<Item> list;
    private AnswerAdapter adapter;
    private Dialog dialog;
    private ViperAnswerListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataView();
    }

    private void initDataView() {
        list = new ArrayList<>();
        adapter = new AnswerAdapter(this, list, new OnItemClickListener() {
            @Override
            public void callback(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
        presenter = new ViperAnswerListPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.actionLoadListAnswer();
    }

    @Override
    protected int getLayoutResoureId() {
        return R.layout.activity_viper_answer_list;
    }

    @Override
    public void getListAnswerFailed(String message) {
        Toast.makeText(this, "Can't get list answer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getListAnswerSuccess(ArrayList<Item> list) {
        adapter.notifyData(list);
        this.list = list;
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
