package com.kiranam.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kiranam.android.BulkDataActivity;
import com.kiranam.android.ElectricMeterReaderActivity;
import com.kiranam.android.R;
import com.kiranam.android.StudentListActivity;

public class Home extends AppCompatActivity {
    Button queone, quetwo, quethree, quefour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        queone = (Button) findViewById(R.id.queone);
        quetwo = (Button) findViewById(R.id.quetwo);
        quethree = (Button) findViewById(R.id.quethree);
        quefour = (Button) findViewById(R.id.quefour);


        queone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, BulkDataActivity.class);
                startActivity(i);
            }
        });

        quefour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, StudentListActivity.class);
                startActivity(i);
            }
        });


        quethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, ElectricMeterReaderActivity.class);
                startActivity(i);
            }
        });

        //ElectricMeterReaderActivity
    }


}