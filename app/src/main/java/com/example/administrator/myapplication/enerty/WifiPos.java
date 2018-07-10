package com.example.administrator.myapplication.enerty;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Administrator on 2018/7/10.
 */

public class WifiPos extends DataSupport {

    private int id;
    private String classId;
    private String wifiResult;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getWifiResult() {
        return wifiResult;
    }

    public void setWifiResult(String wifiResult) {
        this.wifiResult = wifiResult;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
