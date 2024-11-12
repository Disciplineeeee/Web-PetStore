package csu.web.petstore.domain;

import java.io.Serializable;

public class Record implements Serializable {
    private String userid;
    private String records;

    int isItem;

    public int getIsItem() {
        return isItem;
    }

    public void setIsItem(int isItem) {
        this.isItem = isItem;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }
}

