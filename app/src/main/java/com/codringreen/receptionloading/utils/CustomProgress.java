package com.codringreen.receptionloading.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.codringreen.receptionloading.R;

public class CustomProgress {

    private static CustomProgress instance;
    private static Dialog sDialog;
    private static Context scontext;

    private CustomProgress() {}  // Private constructor to prevent direct instantiation.

    public static synchronized CustomProgress getInstance(Context context) {
        if (instance == null) {
            instance = new CustomProgress();
        }
        scontext = context;  // Ensure context is updated.
        return instance;
    }

//    private static CustomProgress instace;
//    private static Dialog sDialog;
//    private static Context scontext;
//
//    public static CustomProgress getInstance(Context context) {
//        CustomProgress customProgress = new CustomProgress();
//        instace = customProgress;
//        scontext = context;
//        return customProgress;
//    }
//
//    private static Dialog GetDialog(Context context) {
//        Dialog dialog;
//        try {
//            if (context != scontext && (dialog = sDialog) != null) {
//                dialog.dismiss();
//                sDialog = null;
//            }
//            if (sDialog == null) {
//                scontext = context;
//                Dialog dialog2 = new Dialog(context, R.style.error_dialog);
//                sDialog = dialog2;
//                dialog2.requestWindowFeature(1);
//                sDialog.setContentView(R.layout.custom_progressbar);
//                sDialog.setCancelable(false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sDialog;
//    }

    private static Dialog GetDialog(Context context) {
        Dialog dialog;
        try {
            if (!(context instanceof Activity)) {
                return null; // Prevent issues with non-Activity context.
            }

            if (context != scontext && (dialog = sDialog) != null) {
                dialog.dismiss();
                sDialog = null;
            }
            if (sDialog == null) {
                scontext = context;
                sDialog = new Dialog(context, R.style.error_dialog);
                sDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                sDialog.setContentView(R.layout.custom_progressbar);
                sDialog.setCancelable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sDialog;
    }

//    public void showProgress(Context context) {
//        try {
//            Dialog GetDialog = GetDialog(context);
//            if (GetDialog == null || GetDialog.isShowing()) {
//                return;
//            }
//            GetDialog.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void showProgress(Context context) {
        try {
            if (!(context instanceof Activity)) return; // Prevent using non-Activity context.

            Activity activity = (Activity) context;
            activity.runOnUiThread(() -> {
                Dialog GetDialog = GetDialog(context);
                if (GetDialog == null || GetDialog.isShowing()) {
                    return;
                }
                GetDialog.show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgress() {
        try {
            Dialog dialog = sDialog;
            if (dialog == null || !dialog.isShowing()) {
                return;
            }
            sDialog.dismiss();
            sDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}