package com.example.lab51;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcastReceiver();
    }

    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_SHORT).show();

        TextView tvContents = findViewById(R.id.tv_content);
        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
            if (messages != null) {
                String sms = "";

                for (int i = 0; i < messages.length; i++) {
                    SmsMessage smsMsg;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = intent.getStringExtra("format");
                        smsMsg = SmsMessage.createFromPdu((byte[]) messages[i], format);
                    } else {
                        smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);
                    }

                    String msgBody = smsMsg.getMessageBody();
                    String address = smsMsg.getDisplayOriginatingAddress();

                    sms += address + ":\n" + msgBody + "\n";
                }

                tvContents.setText(sms);
            }
        }
    }

    private void initBroadcastReceiver() {
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) initBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
