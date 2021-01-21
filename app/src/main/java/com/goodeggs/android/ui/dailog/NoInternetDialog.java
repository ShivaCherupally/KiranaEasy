package com.goodeggs.android.ui.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.goodeggs.android.R;


/**
 * Created by User on 17-07-2018.
 **/

public class NoInternetDialog extends Dialog {
    Context context;
    Button btRetry, btCancel;
    IInternetAppDialog iUpdateAppDialog;
    boolean isForce;

    public NoInternetDialog(@NonNull Context context, IInternetAppDialog iUpdateAppDialog, boolean isForce) {
        super(context);
        this.context = context;
        this.iUpdateAppDialog = iUpdateAppDialog;
        this.isForce = isForce;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nointernet);

        initialize();
        setUp();
    }

    private void initialize() {
        btRetry = findViewById(R.id.btRetry);
    }

    private void setUp() {
//        btCancel.setVisibility(isForce ? View.GONE : View.VISIBLE);

        btRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iUpdateAppDialog.retryClick();
            }
        });
       /* btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                iUpdateAppDialog.doLater();

            }
        });*/
    }

}
