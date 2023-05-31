package com.example.lifer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifer.R;


public class MainActivity extends AppCompatActivity {

    private Button calculatorButton;
    private Button ContactsButton;
    private Button musicButton;
    private Button weatherButton;
    private Button newsBUtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorButton = findViewById(R.id.btn_calculator);
        ContactsButton = findViewById(R.id.btn_contacts);
        musicButton = findViewById(R.id.btn_music);
        weatherButton = findViewById(R.id.btn_weather);
        newsBUtton = findViewById(R.id.btn_news);
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCalculatorButtonClick();
            }
        });

        ContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle phonebook button click
                onContactsButtonClick();
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle music button click
                onMusicButtonClick();
            }
        });
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle music button click
                onWeatherButtonClick();
            }
        });
        newsBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle music button click
                onNewsButtonClick();
            }
        });
    }

    private void onCalculatorButtonClick() {
        Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(intent);
    }

    private void onContactsButtonClick() {
        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
        startActivity(intent);
    }

    private void onMusicButtonClick() {
        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        startActivity(intent);
    }
    private void onWeatherButtonClick() {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        startActivity(intent);
    }

    private void onNewsButtonClick() {
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        startActivity(intent);
    }
}
