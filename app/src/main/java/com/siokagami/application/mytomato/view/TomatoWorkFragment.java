package com.siokagami.application.mytomato.view;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.bean.UpdateStatQuery;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.DateParseUtil;
import com.siokagami.application.mytomato.utils.PrefUtils;
import com.siokagami.application.mytomato.widget.TomatoCountdownTimer;
import com.siokagami.application.mytomato.widget.CustomAlertDialog;
import com.siokagami.application.mytomato.utils.DialogUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TomatoWorkFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private TextView tvTomatoWorkCount;
    private TextView tvTomatoWorkType;
    private ImageView ivTomatoWorkStop;
    private static PowerManager.WakeLock wakeLock;
    private boolean isFirst = true;
    private float fX;
    private float fY;


    private TomatoCountdownTimer tomatoCountdownTimer;
    private CheckBox cbTomatoWorkControl;
    private String mTag;
    private boolean workStatus = true;
    private int workCount = 0;
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
        mTag = PrefUtils.getUserWorkTag(getContext());
        initView(view);
        initCountDown();
        initSensor();
        return view;
    }


    private void initView(View view) {
        Typeface fontFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/siv_text_font.ttf");
        tvTomatoWorkCount = (TextView) view.findViewById(R.id.tv_tomato_work_count);
        cbTomatoWorkControl = (CheckBox) view.findViewById(R.id.cb_tomato_work_control);
        ivTomatoWorkStop = (ImageView) view.findViewById(R.id.iv_tomato_work_stop);
        tvTomatoWorkType = (TextView) view.findViewById(R.id.tv_tomato_work_type);
        tvX = (TextView) view.findViewById(R.id.tv_x);
        tvY = (TextView) view.findViewById(R.id.tv_y);
        tvZ = (TextView) view.findViewById(R.id.tv_z);
        tvTomatoWorkCount.setTypeface(fontFace);
        tvTomatoWorkType.setText(mTag + "中。。。");
        ivTomatoWorkStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTomatoWork();
                tvTomatoWorkCount.setText(DateParseUtil.millSec2MinSec(PrefUtils.getMyTomatoWorkTime(getActivity())));
                tomatoCountdownTimer.setmRemainTime(PrefUtils.getMyTomatoWorkTime(getActivity()));
                if(workCount>0) {
                    updateWork();
                }
            }
        });
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
        if(isFirst)
        {
            fX = x;
            fY = y;
            isFirst =false;
        }

        if (x < -3.0 +fX|| x > 3.0+fX) {
            onUserMove();
        } else if (y < -3.0+fY || y > 3.0+fY) {
            onUserMove();
        }
    }

    private void onUserMove() {
        stopTomatoWork();
        CustomAlertDialog dialog = DialogUtil.createAlertDialog(getContext(), "NO!!!\n\n工作中请不要操作手机", "是否重新开始?", new CustomAlertDialog.OnBtnClickListener() {
            @Override
            public void onConfirmClicked(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
                if (!customAlertDialog.isShowing())
                    restartTomatoWork();
            }

            @Override
            public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
                stopTomatoWork();
                tvTomatoWorkCount.setText(DateParseUtil.millSec2MinSec(PrefUtils.getMyTomatoWorkTime(getActivity())));
                tomatoCountdownTimer.setmRemainTime(PrefUtils.getMyTomatoWorkTime(getActivity()));
                if (workCount != 0) {
                    updateWork();
                }
            }
        });
        dialog.setConfirmText("是");
        dialog.show();
    }

    private void updateWork() {
        CustomAlertDialog dialog = DialogUtil.createAlertDialog(getContext(), "上传成绩", "您一共完整进行了" + workCount + "次番茄工作\n是否上传成绩?", new CustomAlertDialog.OnBtnClickListener() {
            @Override
            public void onConfirmClicked(final CustomAlertDialog customAlertDialog) {
                Observable<Void> postTomatoWork = MyTomatoAPI.myTomatoService.updateStat(new UpdateStatQuery(mTag, workCount, PrefUtils.getUserAccessToken(getContext())));
                postTomatoWork.subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Void>() {
                                       @Override
                                       public void call(Void aVoid) {
                                           customAlertDialog.dismiss();
                                           Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                                       }

                                   }
                                , new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        throwable.printStackTrace();
                                        customAlertDialog.dismiss();
                                        Toast.makeText(getContext(), "上传失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

            }

            @Override
            public void onCancelClicked(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
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
                tomatoCountdownTimer.pause();
                workStatus = !workStatus;
                if (workStatus) {
                    changeWorkMode();
                } else {
                    changeRestMode();
                }
                tomatoCountdownTimer.next();
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
        isFirst = true;
        tomatoCountdownTimer.cancel();
        ivTomatoWorkStop.setVisibility(View.GONE);
        keepScreenOn(getContext(),false);


    }

    private void startTomatoWork() {
        cbTomatoWorkControl.setChecked(true);
        keepScreenOn(getContext(),true);
        startSensor();
        tomatoCountdownTimer.start();
        ivTomatoWorkStop.setVisibility(View.VISIBLE);
    }

    private void restartTomatoWork() {

        cbTomatoWorkControl.setChecked(true);
        startSensor();
        tomatoCountdownTimer.restart();

    }

    private void resumeTomatoWork() {
        cbTomatoWorkControl.setChecked(true);
        startSensor();
        tomatoCountdownTimer.resume();
    }

    private void pauseTomatoWork() {
        cbTomatoWorkControl.setChecked(false);
        stopSensor();
        tomatoCountdownTimer.pause();
    }

    private void changeRestMode() {
        tvTomatoWorkType.setText("休息中~~~");
        stopSensor();
        tomatoCountdownTimer.changeTime2RestMode();

    }

    private void changeWorkMode() {
        workCount += 1;
        tvTomatoWorkType.setText(mTag + "中。。。");
        startSensor();
        Log.d("siokagami", "changeWorkMode: " + workCount);
        tomatoCountdownTimer.changeTime2WorkMode();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("sioo", "onPause: ");
        stopTomatoWork();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("sioo", "onResume: ");
        mTag = PrefUtils.getUserWorkTag(getContext());
        tvTomatoWorkType.setText(mTag + "中。。。");
        tvTomatoWorkCount.setText(DateParseUtil.millSec2MinSec(PrefUtils.getMyTomatoWorkTime(getActivity())));
    }

    public static void keepScreenOn(Context context, boolean on) {
        if (on) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "==KeepScreenOn==");
            wakeLock.acquire();
        } else {
            if (wakeLock != null) {
                wakeLock.release();
                wakeLock = null;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        keepScreenOn(getContext(), false);

    }

}
