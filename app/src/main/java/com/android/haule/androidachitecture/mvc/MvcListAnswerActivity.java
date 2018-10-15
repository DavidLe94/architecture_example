package com.android.haule.androidachitecture.mvc;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.android.haule.androidachitecture.R;
import com.android.haule.androidachitecture.adapter.AnswerAdapter;
import com.android.haule.androidachitecture.api.ApiServices;
import com.android.haule.androidachitecture.api.ApiUtils;
import com.android.haule.androidachitecture.base.BaseActivity;
import com.android.haule.androidachitecture.callback.OnItemClickListener;
import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.models.ResAnswer;
import java.util.ArrayList;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.android.haule.androidachitecture.utils.Reference.SUCCESS;

public class MvcListAnswerActivity extends BaseActivity {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private ApiServices apiServices;
    private AnswerAdapter adapter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        dialog = showLoadingDialog(this, "Loading...");
    }

    private void initData() {
        apiServices = ApiUtils.getApiService();
        ArrayList<Item> list = new ArrayList<>();
        adapter = new AnswerAdapter(this, list, new OnItemClickListener() {
            @Override
            public void callback(View view, int position) {
                //TODO:data callback
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callApi();
    }

    private void callApi() {
        dialog.show();
        apiServices.getListAnswer().enqueue(new Callback<ResAnswer>() {
            @Override
            public void onResponse(Call<ResAnswer> call, Response<ResAnswer> response) {
                if(response.code() == SUCCESS){
                    adapter.notifyData((ArrayList<Item>) response.body().getItems());
                }else{
                    Toast.makeText(MvcListAnswerActivity.this, "Can't get data", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResAnswer> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MvcListAnswerActivity.this, "Can't connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutResoureId() {
        return R.layout.activity_mvc_list_answer;
    }
}
