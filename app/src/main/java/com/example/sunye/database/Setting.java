package com.example.sunye.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import static com.example.sunye.database.R.id.et_target;

/**
 * Created by sunYe on 2017-06-21.
 */

public class Setting extends Activity implements OnClickListener{

    EditText nameText;
    //EditText ageText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        nameText = (EditText)findViewById(R.id.et_target);
        //ageText = (EditText)findViewById(R.id.ageText);
        Button sendBtn = (Button)findViewById(R.id.bt_target);
        sendBtn.setOnClickListener(this);

     /*   final EditText edittext = (EditText) findViewById(R.id.et_target);
        Button button = (Button) findViewById(R.id.bt_target);
        final TextView textView = (TextView) findViewById(R.id.tv_target);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(edittext.getText());
            }
        });*/
/*
        Intent intent = getIntent();

        String message = intent.getStringExtra(".MESSAGE");

        TextView textView = (TextView)findViewById(R.id.tv_target);

        textView.setText(message);*/

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        //데이터를 Intent에 넣는다
        //intent.putExtra(String name, value)
        //"키"의 역할을 하는 name과 그에 해당하는 값인 value가 짝을 이루어 저장됨.
        intent.putExtra("name", nameText.getText().toString());
        //intent.putExtra("age", ageText.getText().toString());
        startActivity(intent);
    }


    /*public void sendMessage(View view) {


        EditText editText = (EditText) findViewById(R.id.et_target);

        String message = editText.getText().toString();


        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(".MESSAGE", message);

        startActivity(intent);

    }*/


}