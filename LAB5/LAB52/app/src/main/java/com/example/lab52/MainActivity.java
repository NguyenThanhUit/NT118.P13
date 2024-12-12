package com.example.lab52;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PowerStateChangeReceiver powerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        powerReceiver = new PowerStateChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (powerReceiver != null) {
            unregisterReceiver(powerReceiver);
        }
    }

    public static class PowerStateChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) return;

            String action = intent.getAction();
            if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                Log.d("PowerStateReceiver", "Power connected");
                Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                Log.d("PowerStateReceiver", "Power disconnected");
                Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
