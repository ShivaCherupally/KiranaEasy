package com.goodeggs.android.ui.dailog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.goodeggs.android.R;
import com.goodeggs.android.model.ReturnReasonData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsReportOptionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ReturnReasonData> ReturnReasonDataArrayList;
    private Context context;
    private Integer selectedIndex;
    IReturnReasonDialog iCommentsReportDialog;

    public CommentsReportOptionsAdapter(List<ReturnReasonData> returnReasonDataArrayList, Context contextLocal,
                                        IReturnReasonDialog iCommentsReportDialog) {
        this.ReturnReasonDataArrayList = returnReasonDataArrayList;
        this.context = contextLocal;
        this.iCommentsReportDialog = iCommentsReportDialog;
        selectedIndex = -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_comments_report_option;
        return new AdapterHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof AdapterHolder) {
            AdapterHolder viewHolder = (AdapterHolder) holder;
//            if (!Common.getInstance().IsNull(ReturnReasonDataArrayList.get(position).feedbackItem)) {
//                viewHolder.tvUserName.setText(ReturnReasonDataArrayList.get(position).feedbackItem);
//            }
            viewHolder.tvUserName.setText(ReturnReasonDataArrayList.get(position).getRt_title());
//            if (selectedIndex > -1 && selectedIndex == position) {
//                viewHolder.ivRadioBtn.setImageResource(R.drawable.ic_radio_btn_on);
//            } else {
//                viewHolder.ivRadioBtn.setImageResource(R.drawable.ic_radio_btn_off);
//            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeSelection(position);
                }
            });
        }
    }

    private void changeSelection(Integer position) {
        selectedIndex = position;
        /*if (ReturnReasonDataArrayList.get(position).feedbackItem.equalsIgnoreCase("Others")) {
            iCommentsReportDialog.enableRemarkEditText(true);
        } else {
            iCommentsReportDialog.enableRemarkEditText(false);
        }*/
        notifyDataSetChanged();
    }

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public int getItemCount() {
        return ReturnReasonDataArrayList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvUserName)
        TextView tvUserName;

        @BindView(R.id.ivRadioBtn)
        ImageView ivRadioBtn;

        public AdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
