package com.example.reciever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permission();

        Button start = findViewById(R.id.button);
        Button stop = findViewById(R.id.button3);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        final LinearLayout orders = findViewById(R.id.orderlist);


        switch(v.getId()){

            case R.id.button:

                for (int i = 0; i < SMSreciever.orderlist.size(); i ++){


                    TextView textview =  new TextView(this);
                    textview.setText(SMSreciever.orderlist.get(i));
                    orders.addView(textview);

                }

                SMSreciever.orderlist.clear();
                break;


            case R.id.button3:

                orders.removeAllViews();
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





