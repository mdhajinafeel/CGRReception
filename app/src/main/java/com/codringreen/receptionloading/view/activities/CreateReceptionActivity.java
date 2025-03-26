package com.codringreen.receptionloading.view.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.codringreen.receptionloading.R;

public class CreateReceptionActivity extends BaseActivity{
    @Override
    protected void initVariable(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_farm);
        initComponents();
    }

    private void initComponents() {
        try {
            hideKeyboard(this);

            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);

            txtTitle.setText(getString(R.string.create_farm));
            imgBack.setOnClickListener(v -> finish());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error) + " : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}