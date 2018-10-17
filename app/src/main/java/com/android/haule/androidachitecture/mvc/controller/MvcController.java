package com.android.haule.androidachitecture.mvc.controller;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.android.haule.androidachitecture.adapter.AnswerAdapter;
import com.android.haule.androidachitecture.api.ApiServices;
import com.android.haule.androidachitecture.api.ApiUtils;
import com.android.haule.androidachitecture.callback.OnItemClickListener;
import com.android.haule.androidachitecture.models.Item;
import com.android.haule.androidachitecture.models.ResAnswer;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;
import static com.android.haule.androidachitecture.utils.Reference.SUCCESS;

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
        apiServices.getListAnswer().enqueue(new retrofit2.Callback<ResAnswer>() {
            @Override
            public void onResponse(Call<ResAnswer> call, Response<ResAnswer> response) {
                if(response.code() == SUCCESS){
                    AnswerAdapter adapter = new AnswerAdapter(context,
                            (ArrayList<Item>) response.body().getItems(),
                            new OnItemClickListener() {
                        @Override
                        public void callback(View view, int position) {
                            //TODO:data callback
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Can't get data", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResAnswer> call, Throwable t) {
                Toast.makeText(context, "Can't connect to server", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
