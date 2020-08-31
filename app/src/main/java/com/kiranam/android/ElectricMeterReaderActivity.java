package com.kiranam.android;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kiranam.android.RoomDatabase.LoginTable;
import com.kiranam.android.ViewModel.LoginViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ElectricMeterReaderActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginViewModel loginViewModel;

    EditText txtEmailAddress, txtPassword;
    TextView lblEmailAnswer, lblPasswordAnswer;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electric_meter_act);

        txtEmailAddress = (EditText) findViewById(R.id.txtEmailAddress);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        lblPasswordAnswer = (TextView) findViewById(R.id.lblPasswordAnswer);
        lblEmailAnswer = (TextView) findViewById(R.id.lblEmailAnswer);

//        binding = DataBindingUtil.setContentView(ElectricMeterReaderActivity.this, R.layout.electric_meter_act);

//        binding.setClickListener((ElectricMeterReaderActivity) this);
//        binding.se

        btnLogin.setOnClickListener(this::onClick);


        loginViewModel = ViewModelProviders.of(ElectricMeterReaderActivity.this).get(LoginViewModel.class);


        loginViewModel.getGetAllData().observe(this, new androidx.lifecycle.Observer<List<LoginTable>>() {
            @Override
            public void onChanged(List<LoginTable> loginTables) {
                try {
                    lblEmailAnswer.setText((Objects.requireNonNull(loginTables).get(0).getEmail()));
                    lblPasswordAnswer.setText((Objects.requireNonNull(loginTables.get(0).getPassword())) + "/Units");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        String strEmail = txtEmailAddress.getText().toString().trim();
        String strPassword = txtPassword.getText().toString().trim();

        LoginTable data = new LoginTable();

        if (TextUtils.isEmpty(strEmail)) {
//            txtEmailAddress.setError("Please Enter Your E-mail Address");
            Toast.makeText(getApplicationContext(), "Please enter service no", Toast.LENGTH_SHORT).show();
        } else if (strEmail.length() != 10) {
            Toast.makeText(getApplicationContext(), "Please enter valid service no", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(strPassword)) {
//            txtPassword.setError("Please Enter Your Password");
            Toast.makeText(getApplicationContext(), "Please enter meter reading", Toast.LENGTH_SHORT).show();
        } else {
            data.setEmail(setCurrentDate());
            data.setPassword(strPassword);
            loginViewModel.insert(data);

            Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
            txtEmailAddress.setText("");
            txtPassword.setText("");
        }

    }

    private String setCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String date = df.format(c.getTime());
        return date;
    }
}
