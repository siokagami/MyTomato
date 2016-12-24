package com.siokagami.application.mytomato.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.utils.StringUtils;


/**
 * Created by huangxu on 15/10/19.
 */
public class CustomAlertDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView tv_dialog_confirm, tv_dialog_cancel, tv_dialog_title, tv_dialog_message;

    private ImageView iv_dialogIcon, iv_closeIcon;
    private TextView tv_dialogDes;

    private String title, message, confirmText, cancelText;
    private String des;
    private SpannableString spanMessage;

    private Object data;

    private Integer iconResId = null;
    private Integer closeIconResId = null;

    private boolean showConfirmBtn = true;

    private boolean showCancelBtn = true;

    private OnBtnClickListener onBtnClickListener;
    private OnDesClickListener onDesClickListener;

    public interface OnBtnClickListener {
        void onConfirmClicked(CustomAlertDialog customAlertDialog);

        void onCancelClicked(CustomAlertDialog customAlertDialog);
    }

    public interface OnDesClickListener {
        void onDesClick(CustomAlertDialog customAlertDialog);
    }

    public void setConfirmText(String confirmText) {
        this.confirmText = confirmText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public CustomAlertDialog(Context context, int customDialogTheme) {
        super(context, customDialogTheme);
        initView();
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }

    public void setOnDesClickListener(OnDesClickListener onDesClickListener) {
        this.onDesClickListener = onDesClickListener;
    }

    public void setIconRes(Integer iconResId) {
        this.iconResId = iconResId;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isShowConfirmBtn() {
        return showConfirmBtn;
    }

    public void setShowConfirmBtn(boolean showConfirmBtn) {
        this.showConfirmBtn = showConfirmBtn;
    }

    public boolean isShowCancelBtn() {
        return showCancelBtn;
    }

    public void setShowCancelBtn(boolean showCancelBtn) {
        this.showCancelBtn = showCancelBtn;
    }

    public void setCloseIconRes(@DrawableRes int closeIconRes){
        this.closeIconResId = closeIconRes;
    }
    public CustomAlertDialog(Context context) {
        super(context);
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
        if (tv_dialog_message != null) {
            tv_dialog_message.setText(message);
        }
    }

    public void setSpanMessage(SpannableString spanMessage) {
        this.spanMessage = spanMessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.custom_alert_dailog);
        tv_dialog_cancel = (TextView) findViewById(R.id.tv_dialog_cancel);
        tv_dialog_confirm = (TextView) findViewById(R.id.tv_dialog_confirm);
        tv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        tv_dialog_message = (TextView) findViewById(R.id.tv_dialog_message);

        iv_dialogIcon = (ImageView) findViewById(R.id.iv_dialogIcon);
        iv_closeIcon = (ImageView) findViewById(R.id.iv_close_icon);
        tv_dialogDes = (TextView) findViewById(R.id.tv_dialogDes);

        tv_dialog_title.setText(title);
        if (spanMessage == null) {
            tv_dialog_message.setText(message);
        } else {
            tv_dialog_message.setText(spanMessage);
        }
        if (iconResId != null) {
            iv_dialogIcon.setVisibility(View.VISIBLE);
            iv_dialogIcon.setImageResource(iconResId);
        } else {
            iv_dialogIcon.setVisibility(View.GONE);
        }
        if(closeIconResId != null){
            iv_closeIcon.setVisibility(View.VISIBLE);
            iv_closeIcon.setImageResource(closeIconResId);
        }else{
            iv_closeIcon.setVisibility(View.GONE);
        }
        if (StringUtils.isNotEmpty(des)) {
            tv_dialogDes.setVisibility(View.VISIBLE);
            tv_dialogDes.setText(des);
        } else {
            tv_dialogDes.setVisibility(View.GONE);
        }

        if (!showCancelBtn) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv_dialog_confirm.getLayoutParams();
            lp.addRule(RelativeLayout.RIGHT_OF, 0);
            tv_dialog_cancel.setVisibility(View.GONE);
        }

        if (!showConfirmBtn) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv_dialog_cancel.getLayoutParams();
            lp.addRule(RelativeLayout.LEFT_OF, 0);
            tv_dialog_confirm.setVisibility(View.GONE);
        }

        if (StringUtils.isNotEmpty(confirmText)) {
            tv_dialog_confirm.setText(confirmText);
        }
        if (StringUtils.isNotEmpty(cancelText)) {
            tv_dialog_cancel.setText(cancelText);
        }


        tv_dialog_confirm.setOnClickListener(this);
        tv_dialog_cancel.setOnClickListener(this);
        tv_dialogDes.setOnClickListener(this);
        iv_closeIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_confirm:
                if (onBtnClickListener != null) {
                    onBtnClickListener.onConfirmClicked(CustomAlertDialog.this);
                    return;
                }
                break;
            case R.id.tv_dialog_cancel:
                if (onBtnClickListener != null) {
                    onBtnClickListener.onCancelClicked(CustomAlertDialog.this);
                    return;
                }
                break;
            case R.id.tv_dialogDes:
                if (onDesClickListener != null) {
                    onDesClickListener.onDesClick(CustomAlertDialog.this);
                    return;
                }
                break;
            case R.id.iv_close_icon:
                if(onBtnClickListener != null){
                    onBtnClickListener.onCancelClicked(CustomAlertDialog.this);
                    return;
                }
        }
        dismiss();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TextView getTv_dialog_confirm() {
        return tv_dialog_confirm;
    }

    public TextView getTv_dialog_cancel() {
        return tv_dialog_cancel;
    }

}
