package com.werb.mycalendardemo.alarmsetactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

import com.werb.mycalendardemo.R;
import com.werb.mycalendardemo.R2;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by acer-pc on 2016/4/17.
 */
public class SetLocalActivity extends AppCompatActivity {

    @BindView(R2.id.ed_local)
    EditText ed_local;
    @OnClick(R2.id.tv_save) void saveAndClose(){
        Intent intent=new Intent();
        if(ed_local.getText().toString().equals("")){
            intent.putExtra("local", "无");
            setResult(2, intent);
            finish();
        }else {
            intent.putExtra("local", ed_local.getText().toString());
            setResult(2, intent);
            finish();
        }

    }

    @OnClick(R2.id.left_local_back) void finishClose(){
        finish();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_local);
        ButterKnife.bind(this);


    }
}
