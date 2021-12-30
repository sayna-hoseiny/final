package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private TextView yourEdt,resultEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().hide();
        yourEdt=findViewById(R.id.YourEdt);
        resultEdt=findViewById(R.id.ResultEdt);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        yourEdt.setText(extras.getString("word"));
        resultEdt.setText(extras.getString("result"));
    }
}