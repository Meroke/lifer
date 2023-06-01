package com.example.lifer.Data;

public class Alarm {
    private int hour;
    private int minute;
    private String remark;

    public Alarm(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String getTimeText() {
        // 将时间格式化为字符串
        return String.format("%02d:%02d", hour, minute);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

