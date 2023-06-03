package com.werb.mycalendardemo.alarmsetactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.werb.mycalendardemo.R;
import com.werb.mycalendardemo.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by acer-pc on 2016/4/17.
 */
public class SetAlarmTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox no_remind,min10_remind,hour1_remind,day1_remind;

    @OnClick(R2.id.left_alarm_back) void finishClose(){
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_time);
        ButterKnife.bind(this);
        no_remind = (CheckBox) findViewById(R.id.no_remind);
        min10_remind = (CheckBox) findViewById(R.id.min10_remind);
        hour1_remind = (CheckBox) findViewById(R.id.hour1_remind);
        day1_remind = (CheckBox) findViewById(R.id.day1_remind);

        no_remind.setChecked(true);

        no_remind.setOnClickListener(this);
        min10_remind.setOnClickListener(this);
        hour1_remind.setOnClickListener(this);
        day1_remind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        int id = v.getId();
        if (id == R.id.no_remind) {
            no_remind.setChecked(true);
            min10_remind.setChecked(false);
            hour1_remind.setChecked(false);
            day1_remind.setChecked(false);
            intent.putExtra("remind", "无");
            setResult(1, intent);
            finish();
        } else if (id == R.id.min10_remind) {
            min10_remind.setChecked(true);
            no_remind.setChecked(false);
            hour1_remind.setChecked(false);
            day1_remind.setChecked(false);
            intent.putExtra("remind", "提前十分钟提醒");
            setResult(1, intent);
            finish();
        } else if (id == R.id.hour1_remind) {
            hour1_remind.setChecked(true);
            no_remind.setChecked(false);
            min10_remind.setChecked(false);
            day1_remind.setChecked(false);
            intent.putExtra("remind", "提前一个小时通知");
            setResult(1, intent);
            finish();
        } else if (id == R.id.day1_remind) {
            day1_remind.setChecked(true);
            no_remind.setChecked(false);
            min10_remind.setChecked(false);
            hour1_remind.setChecked(false);
            intent.putExtra("remind", "提前一天提醒");
            setResult(1, intent);
            finish();
        }
    }
}
