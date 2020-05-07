package com.example.reciever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.pm.PackageManager;


public class  MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permission();

        Button start = findViewById(R.id.button);
        Button stop = findViewById(R.id.button3);
        Button update_selection = findViewById(R.id.button4);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        update_selection.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        final LinearLayout orders = findViewById(R.id.orderlist);


        switch(v.getId()){

            case R.id.button:

                for (int i = 0; i < SMSreciever.orderlist.size(); i++){

                    TextView textview =  new TextView(this);
                    textview.setText(SMSreciever.orderlist.get(i));
                    orders.addView(textview);
                }

                SMSreciever.orderlist.clear();
                break;


            case R.id.button3:

                orders.removeAllViews();
                break;

            case R.id.button4:

                Intent intent = new Intent(this, update_menu.class);
                startActivity(intent);
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }



    }


    private void permission(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        1);

            }

        }




    }


}










