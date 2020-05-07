package com.example.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SMSreciever extends BroadcastReceiver

{
    public static TextView textView;
    static int count = 0;
    static ArrayList<String> orderlist = new ArrayList();


    public void settextView(TextView textView) {

        SMSreciever.textView = textView;

    }



    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        try {

            for (SmsMessage sms : messages)
            {
                String message = sms.getMessageBody();
                String address = sms.getOriginatingAddress();
                if (message.contains("Order")) {

                    orderlist.add("From: "+ address + "\n" + "Order: " + message + "\n");
                }

            }

        }

        catch (Exception e){

            Toast.makeText(context, "message" + e, Toast.LENGTH_LONG).show();

        }

    }



}

