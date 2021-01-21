//package com.goodeggs.android.ui.dailog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageButton;
//
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.goodeggs.android.R;
//import com.goodeggs.android.model.ReturnReasonData;
//import com.goodeggs.android.ui.EmptyDataAdapter;
//import com.goodeggs.android.utils.Constants;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class ReturnReasonDialog extends Dialog implements IReturnReasonDialog {
//    @BindView(R.id.rvList)
//    RecyclerView rvListt;
//    @BindView(R.id.ivClose)
//    ImageButton ivClose;
//    @BindView(R.id.btnSubmit)
//    Button btnSubmit;
//
//    Context context;
//    List<ReturnReasonData> ReturnReasonDataList;
//    CommentsReportOptionsAdapter adapter;
//    ICommentsAdapter iCommentsAdapter;
//    IReturnReasonDialog iCommentsReportDialog;
//
//    public ReturnReasonDialog() {
//    }
//
//    public void setData(List<ReturnReasonData> ReturnReasonDataList) {
//        this.ReturnReasonDataList = ReturnReasonDataList;
////        this.iCommentsAdapter = iCommentsAdapter;
////        this.comments = comments;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.return_reason_pop_up, container, false);
//        ButterKnife.bind(this, view);
//        context = getContext();
//        rvListt = (RecyclerView)view.findViewById(R.id.rvList);
//        iCommentsReportDialog = this;
//        //
//        return view;
//    }
//
//    private void setup() {
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
////        adapter = new CarTypeRVAdapter(new CarCallBack(), new CarVHFactory());
////        rvListt.setNestedScrollingEnabled(true);
//        rvListt.setLayoutManager(mLayoutManager);
////        rvListt.setItemViewCacheSize(50);
//
//
////        etRemarks.setVisibility(View.GONE);
////        rvListt.setLayoutManager(new LinearLayoutManager(getContext()));
//        //
//        /*btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (adapter == null || adapter.getSelectedIndex() <= -1) {
////                        Common.getInstance().cusToast(context, "Please select your option");
//                    } else {
//                        dismiss();
//                        iCommentsAdapter.reportSubmit(comments, ReturnReasonDataList.get(adapter.getSelectedIndex()),
//                                etRemarks.getText().toString().trim());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });*/
//    }
//
//    private void setAdapter() {
//        try {
//            if (ReturnReasonDataList != null && ReturnReasonDataList.size() > 0) {
//                adapter = new CommentsReportOptionsAdapter(ReturnReasonDataList, context, iCommentsReportDialog);
//                rvListt.setAdapter(adapter);
//            } else {
//                rvListt.setAdapter(new EmptyDataAdapter(context, Constants.NO_DATA_AVAILABLE, R.drawable.ic_list_interface_symbol, 2));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        setup();
//        setAdapter();
//    }
//
//    @Override
//    public void enableRemarkEditText(Boolean enable) {
////        if (enable) {
////            etRemarks.setText("");
////            etRemarks.setVisibility(View.VISIBLE);
////        } else {
////            etRemarks.setVisibility(View.GONE);
////        }
//    }
//}
