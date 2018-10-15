package com.android.haule.androidachitecture.mvvm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.haule.androidachitecture.R;
import com.android.haule.androidachitecture.databinding.ItemBindingAnswerBinding;
import com.android.haule.androidachitecture.models.Item;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hau Le on 2018-10-15.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    private List<Item> list;

    public AnswerAdapter() {
        this.list = Collections.emptyList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemBindingAnswerBinding view = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_binding_answer, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mViewDataBinding.setItem(list.get(i));
    }

    public void setItemList(List<Item> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBindingAnswerBinding mViewDataBinding;

        ViewHolder(@NonNull ItemBindingAnswerBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.mViewDataBinding = viewDataBinding;
        }
    }
}
