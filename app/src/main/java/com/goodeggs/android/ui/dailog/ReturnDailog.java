package com.goodeggs.android.ui.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goodeggs.android.R;
import com.goodeggs.android.model.ReturnReasonData;
import com.goodeggs.android.ui.EmptyDataAdapter;
import com.goodeggs.android.ui.myorders.ReturnReasonAdapter;
import com.goodeggs.android.utils.Constants;

import java.util.List;


/**
 * Created by User on 17-07-2018.
 **/

public class ReturnDailog extends Dialog {
    Context context;
    Button btUpdate, btCancel;
    IUpdateAppDialog iUpdateAppDialog;
    boolean isForce;
    RecyclerView rvList;
    IReturnReasonDialog iCommentsReportDialog;
    List<ReturnReasonData> ReturnReasonDataList;
    ReturnReasonAdapter adapter;
    int position;

    public ReturnDailog(Context context, IUpdateAppDialog iUpdateAppDialog,
                        List<ReturnReasonData> returnReasonDataList, int positionLocal) {
        super(context);
        this.context = context;
        this.iUpdateAppDialog = iUpdateAppDialog;
        this.isForce = isForce;
        this.ReturnReasonDataList = returnReasonDataList;
        this.position = positionLocal;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_reason_pop_up);


        initialize();

        setUp();
    }

    private void initialize() {
        rvList = findViewById(R.id.rvList);
        btUpdate = findViewById(R.id.btnSubmit);
        btCancel = findViewById(R.id.ivClose);
    }

    private void setUp() {
        setAdapter();
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iUpdateAppDialog.updateNow();
                try {
                    if (adapter == null || adapter.getSelectedIndex() <= -1) {
//                        Common.getInstance().cusToast(context, "Please select your option");
                        Toast.makeText(context, "Please select reason", Toast.LENGTH_LONG).show();
                    } else {
                        dismiss();
                        iUpdateAppDialog.reasonReturn(ReturnReasonDataList.get(adapter.getSelectedIndex()).getRt_title(), position);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
//                iUpdateAppDialog.doLater();
            }
        });
    }

    private void setAdapter() {
        try {
            rvList.setLayoutManager(new LinearLayoutManager(context));
            if (ReturnReasonDataList != null && ReturnReasonDataList.size() > 0) {
                adapter = new ReturnReasonAdapter(ReturnReasonDataList, -1);
                rvList.setAdapter(adapter);
            } else {
                rvList.setAdapter(new EmptyDataAdapter(context, Constants.NO_DATA_AVAILABLE, R.drawable.ic_list_interface_symbol, 2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
