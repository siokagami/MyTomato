package com.siokagami.application.mytomato.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.text.SpannableString;
import android.view.View;
import android.view.Window;


import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.base.TomatoApplication;
import com.siokagami.application.mytomato.widget.CustomAlertDialog;

import java.io.File;

/**
 * dialog样式
 * Created by sunshine on 15/9/21.
 */
public class DialogUtil {

    public static CustomAlertDialog createAlertDialog(Context context, Integer iconResId, String title, String message, String des, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener, CustomAlertDialog.OnDesClickListener onDesClickListener) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, R.style.customDialogTheme);
        customAlertDialog.setContentView(R.layout.custom_alert_dailog);
        customAlertDialog.setCanceledOnTouchOutside(false);
        customAlertDialog.setTitle(title);
        customAlertDialog.setMessage(message);

        customAlertDialog.setDes(des);
        customAlertDialog.setConfirmText(confirmText);
        customAlertDialog.setIconRes(iconResId);
        customAlertDialog.setOnDesClickListener(onDesClickListener);
        if (onBtnClickListener == null) {
            onBtnClickListener = new CustomAlertDialog.OnBtnClickListener() {
                @Override
                public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }

                @Override
                public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }
            };
        }
        customAlertDialog.setOnBtnClickListener(onBtnClickListener);
        return customAlertDialog;
    }

    public static CustomAlertDialog createAlertDialog(Context context, Integer iconResId, String title, SpannableString spanMessage, String des, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener, CustomAlertDialog.OnDesClickListener onDesClickListener) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, R.style.customDialogTheme);
//        customAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customAlertDialog.setContentView(R.layout.custom_alert_dailog);
        customAlertDialog.setCanceledOnTouchOutside(false);
        customAlertDialog.setTitle(title);
        customAlertDialog.setSpanMessage(spanMessage);

        customAlertDialog.setDes(des);
        customAlertDialog.setConfirmText(confirmText);
        customAlertDialog.setIconRes(iconResId);
        customAlertDialog.setOnDesClickListener(onDesClickListener);
        if (onBtnClickListener == null) {
            onBtnClickListener = new CustomAlertDialog.OnBtnClickListener() {
                @Override
                public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }

                @Override
                public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }
            };
        }
        customAlertDialog.setOnBtnClickListener(onBtnClickListener);
        return customAlertDialog;
    }

    public static CustomAlertDialog createAlertDialogWithNoCancel(Context context, String title, String message, String des, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, R.style.customDialogTheme);
        customAlertDialog.setContentView(R.layout.custom_alert_dailog);
        customAlertDialog.setShowCancelBtn(false);

        customAlertDialog.setCanceledOnTouchOutside(false);

        customAlertDialog.setTitle(title);
        customAlertDialog.setMessage(message);

        customAlertDialog.setDes(des);
        customAlertDialog.setConfirmText(confirmText);

        if (onBtnClickListener == null) {
            onBtnClickListener = new CustomAlertDialog.OnBtnClickListener() {
                @Override
                public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }

                @Override
                public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }
            };
        }
        customAlertDialog.setOnBtnClickListener(onBtnClickListener);
        return customAlertDialog;

    }

    public static CustomAlertDialog createCloseAbleDialog(Context context, @DrawableRes int iconResId, @DrawableRes int closeIconResId, String title, String message, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, R.style.customDialogTheme);
//        customAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customAlertDialog.setContentView(R.layout.custom_alert_dailog);
        customAlertDialog.setCanceledOnTouchOutside(false);
        customAlertDialog.setTitle(title);
        customAlertDialog.setMessage(message);

        customAlertDialog.setConfirmText(confirmText);
        customAlertDialog.setIconRes(iconResId);
        customAlertDialog.setCloseIconRes(closeIconResId);
        customAlertDialog.setShowCancelBtn(false);
        if (onBtnClickListener == null) {
            onBtnClickListener = new CustomAlertDialog.OnBtnClickListener() {
                @Override
                public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }

                @Override
                public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }
            };
        }
        customAlertDialog.setOnBtnClickListener(onBtnClickListener);
        return customAlertDialog;
    }

    public static CustomAlertDialog createAlertDialogWithoutIcon(Context context, String message, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, R.style.customDialogTheme);
//        customAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customAlertDialog.setContentView(R.layout.custom_alert_dailog);
        customAlertDialog.setCanceledOnTouchOutside(false);
        customAlertDialog.setMessage(message);

        customAlertDialog.setConfirmText(confirmText);
        customAlertDialog.setShowCancelBtn(false);
        if (onBtnClickListener == null) {
            onBtnClickListener = new CustomAlertDialog.OnBtnClickListener() {
                @Override
                public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }

                @Override
                public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                    customAlertDialog.dismiss();
                }
            };
        }
        customAlertDialog.setOnBtnClickListener(onBtnClickListener);
        return customAlertDialog;
    }


    public static CustomAlertDialog createAlertDialogWithIcon(Context context, Integer iconResId, SpannableString spanMessage, String des, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener, CustomAlertDialog.OnDesClickListener onDesClickListener) {
        return createAlertDialog(context, iconResId, TomatoApplication.instance.getString(R.string.dialogPromptTitle), spanMessage, des, confirmText, onBtnClickListener, onDesClickListener);
    }

    public static CustomAlertDialog createAlertDialogWithIcon(Context context, Integer iconResId, String message, String des, String confirmText, CustomAlertDialog.OnBtnClickListener onBtnClickListener, CustomAlertDialog.OnDesClickListener onDesClickListener) {
        return createAlertDialog(context, iconResId, TomatoApplication.instance.getString(R.string.dialogPromptTitle), message, des, confirmText, onBtnClickListener, onDesClickListener);
    }

    public static CustomAlertDialog createAlertDialog(Context context, int titleResId, int messageResId, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, null, context.getString(titleResId), TomatoApplication.instance.getString(messageResId), null, null, onBtnClickListener, null);
    }

    public static CustomAlertDialog createAlertDialog(Context context, int titleResId, String message, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, null, context.getString(titleResId), message, null, null, onBtnClickListener, null);
    }

    public static CustomAlertDialog createAlertDialog(Context context, String message, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, R.string.dialogPromptTitle, message, onBtnClickListener);
    }

    public static CustomAlertDialog createAlertDialog(Context context, int messageResId, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, R.string.dialogPromptTitle, messageResId, onBtnClickListener);
    }

    public static CustomAlertDialog createAlertDialogWithConfirm(Context context, String message, String confirm, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, null, null, message, null, confirm, onBtnClickListener, null);
    }

    public static CustomAlertDialog createAlertDialogWithConfirmAndCancel(Context context, String message, String confirm, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, null, TomatoApplication.instance.getString(R.string.dialogPromptTitle), message, null, confirm, onBtnClickListener, null);
    }

    public static CustomAlertDialog createAlertDialog(Context context, String title, String message, CustomAlertDialog.OnBtnClickListener onBtnClickListener) {
        return createAlertDialog(context, null, title, message, null, null, onBtnClickListener, null);
    }

    public static CustomAlertDialog createAlertDialog(Context context, String message) {
        return createAlertDialog(context, message, true);
    }

    public static CustomAlertDialog createAlertDialog(Context context, String message, boolean showCancelBtn) {
        CustomAlertDialog dialog = createAlertDialog(context, message, null);
        dialog.setShowCancelBtn(showCancelBtn);
        return dialog;
    }


}
