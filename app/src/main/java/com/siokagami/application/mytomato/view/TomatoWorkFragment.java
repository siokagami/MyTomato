package com.siokagami.application.mytomato.view;


import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.DateParseUtil;
import com.siokagami.application.mytomato.utils.PrefUtils;
import com.siokagami.application.mytomato.widget.TomatoCountdownTimer;
import com.siokagami.application.mytomato.widget.CustomAlertDialog;
import com.siokagami.application.mytomato.utils.DialogUtil;

import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TomatoWorkFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private TextView tvTomatoWorkCount;
    private TomatoCountdownTimer tomatoCountdownTimer;
    private SensorEventListener sensorListener;
    private CheckBox cbTomatoWorkControl;


    private Sensor sensor;
    private TextView tvX;
    private TextView tvY;
    private TextView tvZ;


    public TomatoWorkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tomato_work, container, false);
        initView(view);
        initCountDown();
        initSensor();
        return view;
    }


    private void initView(View view) {
        Typeface fontFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/siv_text_font.ttf");
        tvTomatoWorkCount = (TextView) view.findViewById(R.id.tv_tomato_work_count);
        cbTomatoWorkControl = (CheckBox) view.findViewById(R.id.cb_tomato_work_control);
        tvX = (TextView) view.findViewById(R.id.tv_x);
        tvY = (TextView) view.findViewById(R.id.tv_y);
        tvZ = (TextView) view.findViewById(R.id.tv_z);
        tvTomatoWorkCount.setTypeface(fontFace);
        tvTomatoWorkCount.setText(DateParseUtil.millSec2MinSec(PrefUtils.getMyTomatoWorkTime(getActivity())));
        cbTomatoWorkControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    startTomatoWork();
                } else {
                    pauseTomatoWork();
                }
            }
        });
    }

    private void initSensor() {
        //初始化SensorManager
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        //获取重力加速度传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        //设定传感器精度,忽略小数点后的数值
        //x轴
        float x = Math.round(values[0]);
        tvX.setText("x轴" + x);
        //y轴
        float y = Math.round(values[1]);
        tvY.setText("y轴" + y);

        //z轴
        float z = Math.round(values[2]);
        tvZ.setText("y轴" + z);

        if (x < -3.0 || x > 3.0) {
            onUserMove();
        } else if (y < -3.0 || y > 3.0) {
            onUserMove();
        }
    }

    private void onUserMove() {
        stopTomatoWork();
        CustomAlertDialog dialog = DialogUtil.createAlertDialog(getContext(), null, "恭喜!\n\n您写代码共完成了2个番茄时间", "是否上传成绩?", "", "是", new CustomAlertDialog.OnBtnClickListener() {
            @Override
            public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
            }

            @Override
            public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
            }
        }, new CustomAlertDialog.OnDesClickListener() {
            @Override
            public void onDesClick(CustomAlertDialog customAlertDialog) {

            }
        });
        dialog.show();
    }

    private void initCountDown() {
        tomatoCountdownTimer = new TomatoCountdownTimer(PrefUtils.getMyTomatoWorkTime(getActivity()), 1000) {
            @Override
            public void onTick(long millisUntilFinished, int percent) {
                tvTomatoWorkCount.setText(DateParseUtil.millSec2MinSec(millisUntilFinished));
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void startSensor() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    private void stopSensor() {
        sensorManager.unregisterListener(this);
    }

    private void stopTomatoWork() {
        cbTomatoWorkControl.setChecked(false);
        stopSensor();
        tomatoCountdownTimer.cancel();
    }

    private void startTomatoWork() {
        cbTomatoWorkControl.setChecked(true);
        startSensor();
        tomatoCountdownTimer.start();
    }

    private void resumeTomatoWork() {
        cbTomatoWorkControl.setChecked(true);
        startSensor();
        tomatoCountdownTimer.start();
    }

    private void pauseTomatoWork() {
        cbTomatoWorkControl.setChecked(false);
        stopSensor();
        tomatoCountdownTimer.pause();
    }


}
