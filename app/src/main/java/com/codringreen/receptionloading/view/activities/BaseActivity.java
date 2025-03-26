package com.codringreen.receptionloading.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.utils.CommonUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.Objects;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected abstract void initVariable(Bundle savedInstanceState);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initVariable(savedInstanceState);
    }

    public void hideKeyboard(Context ctx) {
        InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = ((Activity) ctx).getCurrentFocus();
        if (currentFocus == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void showDialog(String message, String title, DialogInterface.OnClickListener onPositiveButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_semibold), title));
        builder.setMessage(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_medium), message));
        builder.setCancelable(false);
        builder.setPositiveButton(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_bold), "Ok"), Objects.requireNonNullElseGet(onPositiveButtonClickListener, () -> (dialogInterface, i) -> dialogInterface.dismiss()));
        AlertDialog create = builder.create();
        if (!create.isShowing()) {
            create.show();
        }
        create.getButton(-1).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButton));
    }

    public void showDialogWithCancel(String message, String title, DialogInterface.OnClickListener onPositiveButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_semibold), title));
        builder.setMessage(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_medium), message));
        builder.setCancelable(false);
        builder.setPositiveButton(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_bold), getResources().getString(R.string.text_ok)), onPositiveButtonClickListener);
        builder.setNegativeButton(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_bold), getResources().getString(R.string.text_cancel)), new DialogInterface.OnClickListener() { // from class: com.codringreen.receptionloading.view.activities.-$$Lambda$BaseActivity$L82vj_MQE_vwFKdXXnADmyYBqoA
            @Override
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog create = builder.create();
        if (!create.isShowing()) {
            create.show();
        }
        create.getButton(-1).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButton));
        create.getButton(-2).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonNegative));
    }

    public void showDialogWithCancelAction(String message, String title, String positiveBtnText, String negativeBntText, DialogInterface.OnClickListener onPositiveButtonClickListener, DialogInterface.OnClickListener onNegativeButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_semibold), title));
        builder.setMessage(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_medium), message));
        builder.setCancelable(false);
        builder.setPositiveButton(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_bold), positiveBtnText), onPositiveButtonClickListener);
        builder.setNegativeButton(CommonUtils.customFontTypeFace(ResourcesCompat.getFont(getApplicationContext(), R.font.montserrat_bold), negativeBntText), onNegativeButtonClickListener);
        AlertDialog create = builder.create();
        if (!create.isShowing()) {
            create.show();
        }
        create.getButton(-1).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButton));
        create.getButton(-2).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonNegative));
    }
}