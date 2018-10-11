package com.android.haule.androidachitecture.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.haule.androidachitecture.R;
import com.android.haule.androidachitecture.models.Item;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.haule.androidachitecture.callback.OnItemClickListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hau Le on 2018-10-09.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.SimpleViewHolder> {
    private Context context;
    private ArrayList<Item> list;
    private OnItemClickListener listener;

    public AnswerAdapter(Context context, ArrayList<Item> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnswerAdapter.SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_answer, viewGroup, false);
        final SimpleViewHolder mViewHolder = new SimpleViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.callback(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.SimpleViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getOwner().getProfileImage())
                .apply(
                    new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                )
                .into(holder.imgProfileImage);
        holder.tvDisplayName.setText(list.get(position).getOwner().getDisplayName());
        holder.tvUserType.setText(list.get(position).getOwner().getUserType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_profile_image) CircleImageView imgProfileImage;
        @BindView(R.id.tv_display_name) TextView tvDisplayName;
        @BindView(R.id.tv_user_type) TextView tvUserType;

        SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void notifyData(ArrayList<Item> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
