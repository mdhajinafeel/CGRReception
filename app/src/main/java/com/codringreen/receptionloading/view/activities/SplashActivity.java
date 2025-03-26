package com.codringreen.receptionloading.view.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.helper.PreferenceManager;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            lockPortraitMode();
        }

        setContentView(R.layout.activity_splash);
        initComponents();
    }

    private void initComponents() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        new Handler().postDelayed(() -> {
            if (PreferenceManager.INSTANCE.isLoggedIn()) {
                startActivity(new Intent(SplashActivity.this, DashboardActivity.class)
                        .putExtra("DownloadMaster", "No")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            } else {
                PreferenceManager.INSTANCE.clearLoginDetails();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        }, 2500);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void lockPortraitMode() {
        getWindow().setAttributes(new WindowManager.LayoutParams() {{
            preferredDisplayModeId = 1;  // Lock to Portrait
        }});
    }
}