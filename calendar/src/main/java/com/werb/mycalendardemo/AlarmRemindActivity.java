package com.werb.mycalendardemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by acer-pc on 2016/4/21.
 */
public class AlarmRemindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        AlarmBean alarmBean = (AlarmBean) bundle.getSerializable("alarm");

        Toast.makeText(this,"闹钟响了！"+alarmBean.getTitle(),Toast.LENGTH_SHORT).show();
    }

}
