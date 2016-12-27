package com.siokagami.application.mytomato.bean;

/**
 * Created by siokagami on 16/12/24.
 */

public class UpdateStatQuery {
    private String category;
    private int workCount;
    private String token;

    public UpdateStatQuery(String category, int workCount, String token) {
        this.category = category;
        this.workCount = workCount;
        this.token = token;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
