package com.kiranam.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kiranam.android.databinding.EmptyDataItemBinding;


public class EmptyDataAdapter extends RecyclerView.Adapter<EmptyDataAdapter.EmptyViewHolder> {

    private Context context;
    private String content;
    private int imageId;
    private int type;


    public EmptyDataAdapter(Context context, String content, int imageId, int type) {
        this.context = context;
        this.content = content;
        this.imageId = imageId;
        this.type = type;
    }

    @Override
    public EmptyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        EmptyDataItemBinding emptyDataBinding;
        emptyDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.empty_data_item, parent, false);
        return new EmptyViewHolder(emptyDataBinding);
    }

    @Override
    public void onBindViewHolder(EmptyViewHolder holder, int position) {

        holder.bindData();

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        private EmptyDataItemBinding emptyDataBinding;

        public EmptyViewHolder(EmptyDataItemBinding emptyDataBinding) {
            super(emptyDataBinding.getRoot());
            this.emptyDataBinding = emptyDataBinding;
        }

        public void bindData() {
            emptyDataBinding.tvNote.setVisibility(type == 0 ? View.VISIBLE : View.GONE);
            emptyDataBinding.tvError.setText(content);
            emptyDataBinding.imgError.setImageResource(imageId);
        }
    }
}
