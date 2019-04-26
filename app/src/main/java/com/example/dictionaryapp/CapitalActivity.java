package com.example.dictionaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CapitalActivity extends AppCompatActivity {

    private TextView tvMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            String meaning = bundle.getString("capital");
            tvMeaning = findViewById(R.id.tvMeaning);
            tvMeaning.setText(meaning);

        }
    }
}
