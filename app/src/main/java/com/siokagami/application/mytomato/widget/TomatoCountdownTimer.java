package com.siokagami.application.mytomato.widget;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public abstract class TomatoCountdownTimer {

    private static final int TOMATO_START = 1;

    private static final int TOMATO_PAUSE = 2;

    private final long mCountdownInterval;

    private long totalTime;

    private long mRemainTime;

    /**
     * @param totalTime 表示以毫秒为单位 倒计时的总数
     *                  <p>
     *                  例如 totalTime=1000 表示1秒
     * @param tickTime  表示 间隔 多少微秒 调用一次 onTick 方法
     *                  <p>
     *                  例如: tickTime =1000 ; 表示每1000毫秒调用一次onTick()
     */
    public TomatoCountdownTimer(long totalTime, long tickTime) {
        this.totalTime = totalTime;
        mCountdownInterval = tickTime;
        mRemainTime = totalTime;
    }

    public final void seek(int value) {
        synchronized (TomatoCountdownTimer.this) {
            mRemainTime = ((100 - value) * totalTime) / 100;
        }
    }

    public final void cancel() {
        tHandler.removeMessages(TOMATO_START);
        tHandler.removeMessages(TOMATO_PAUSE);
    }

    public final void resume() {
        tHandler.removeMessages(TOMATO_PAUSE);
        tHandler.sendMessageAtFrontOfQueue(tHandler.obtainMessage(TOMATO_START));
    }

    public final void restart() {
        mRemainTime = totalTime;
        pause();
        resume();
    }

    public final void next() {
        pause();
        resume();
    }

    public final void changeTime2RestMode() {
        mRemainTime = 6000;
    }

    public final void changeTime2WorkMode() {
        mRemainTime = 10000;
    }

    public long getmRemainTime() {
        return mRemainTime;
    }

    public void setmRemainTime(long mRemainTime) {
        this.mRemainTime = mRemainTime;
    }

    public final void pause() {
        tHandler.removeMessages(TOMATO_START);
        tHandler.sendMessageAtFrontOfQueue(tHandler.obtainMessage(TOMATO_PAUSE));
    }


    public synchronized final TomatoCountdownTimer start() {
        if (mRemainTime <= 0) {
            onFinish();
            return this;
        }
        tHandler.sendMessageDelayed(tHandler.obtainMessage(TOMATO_START),
                mCountdownInterval);
        return this;
    }

    public abstract void onTick(long millisUntilFinished, int percent);

    public abstract void onFinish();


    private Handler tHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            synchronized (TomatoCountdownTimer.this) {
                switch (msg.what) {
                    case TOMATO_START:
                        mRemainTime = mRemainTime - mCountdownInterval;
                        if (mRemainTime <= 0) {
                            onFinish();
                        } else if (mRemainTime < mCountdownInterval) {
                            sendMessageDelayed(obtainMessage(TOMATO_START), mRemainTime);
                        } else {
                            onTick(mRemainTime, new Long(100 * (totalTime - mRemainTime) / totalTime)
                                    .intValue());
                            sendMessageDelayed(obtainMessage(TOMATO_START),
                                    mCountdownInterval);
                        }
                        break;
                    case TOMATO_PAUSE:
                        break;
                }
            }
        }
    };


}