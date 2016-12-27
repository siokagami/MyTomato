package com.siokagami.application.mytomato.bean;

import com.siokagami.application.mytomato.utils.DateParseUtil;

import java.text.SimpleDateFormat;

/**
 * Created by siokagami on 16/12/26.
 */

public class MainPageResponse {
    private String common;
    private int count;
    private int ranking;
    private Latest latest;

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRanking() {
        return "第"+ranking+"名";
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Latest getLatest() {
        return latest;
    }

    public void setLatest(Latest latest) {
        this.latest = latest;
    }

    public class Latest {
        private String updatedAt;

        public String getUpdatedAt() {
            return DateParseUtil.dateFormateYmdHm(DateParseUtil.stringToDate(updatedAt,"yyyy-MM-dd HH:mm:ss"));
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

}
