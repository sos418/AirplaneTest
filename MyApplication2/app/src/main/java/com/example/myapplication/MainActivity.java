package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AirplaneModeActivity";
    ToggleButton toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleBtn = (ToggleButton) findViewById(R.id.toggle_btn);
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAirplaneMode(MainActivity.this);
            }

            public void setAirplaneMode(Context context) {
                    // API 16 and earlier.
                    boolean enabled = isAirplaneModeEnabled(context);
                    Log.i(TAG, "API 16 : " + enabled);
                    Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, enabled ? 0 : 1);
                    Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                    intent.putExtra("state", !enabled);
                    sendBroadcast(intent);
            }

            @SuppressLint("NewApi")
            @SuppressWarnings("deprecation")
            private boolean isAirplaneModeEnabled(Context context) {
                boolean mode = false;
                mode = Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;
                return mode;
            }

        });
    }
}