package com.example.sunye.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    Button button1;
    Button button2;
    Button button3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.bt_weight);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);

            }
        });

        button2 = (Button) findViewById(R.id.bt_diary);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TextActivity.class);
                startActivity(intent);

            }
        });

        button3 = (Button) findViewById(R.id.bt_setting);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);

            }
        });
/*
        Intent intent=getIntent();

        String name = intent.getExtras().get("name").toString();
       // String age = intent.getExtras().get("age").toString();
        TextView tv = (TextView) findViewById(R.id.tv_target);
        tv.setText(name);
       // Button sendBtn = (Button) findViewById(R.id.ok);
       // sendBtn.setOnClickListener(this);*/
    }


    }
