package com.example.lifer.clock;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lifer.R;

public class ClockMainActivity extends AppCompatActivity {

    private ImageView clock,sandglass,stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();//隐藏ActionBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明化通知栏

        setContentView(R.layout.activity_clock_main);

        clock=findViewById(R.id.clock);
        sandglass=findViewById(R.id.sandglass);
        stopwatch=findViewById(R.id.stopwatch);

        if (ServiceUtil.isRunning(getApplication(),"com.example.lifer.clock.ClockService")){
        }else {
            startService(new Intent(ClockMainActivity.this,ClockService.class).putExtra("flag","ClockActivity"));
            Toast.makeText(getApplicationContext(),"服务开启成功",Toast.LENGTH_SHORT).show();
        }
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClockMainActivity.this, ClockActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sandglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClockMainActivity.this, SandglassActivity.class);
                startActivity(intent);
                finish();
            }
        });

        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClockMainActivity.this, StopwatchActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
