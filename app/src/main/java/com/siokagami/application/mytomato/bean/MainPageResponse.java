package com.siokagami.application.mytomato.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.siokagami.application.mytomato.utils.DateParseUtil;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by siokagami on 16/12/26.
 */
public class MainPageResponse extends BaseObservable {
    private String common;
    private int count;
    private int ranking;
    private Latest latest;


    @Bindable
    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
        notifyPropertyChanged(BR.common);

    }

    @Bindable
    public int getCount() {
        return count;

    }

    public void setCount(int count) {
        this.count = count;
        notifyPropertyChanged(BR.count);

    }

    @Bindable
    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
        notifyPropertyChanged(BR.ranking);

    }

    @Bindable
    public Latest getLatest() {
        return latest;
    }

    public void setLatest(Latest latest) {
//        this.latest.updatedAt = );
        this.latest = latest;
        notifyPropertyChanged(BR.latest);
    }

    public class Latest extends BaseObservable {
        private String updatedAt;

        @Bindable
        public String getUpdatedAt() {
            return DateParseUtil.dateFormatStringmd(DateParseUtil.stringToDate(updatedAt, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            notifyPropertyChanged(BR.updatedAt);
        }
    }

}
