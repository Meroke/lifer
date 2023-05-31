package com.example.lifer.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lifer.R;
import com.example.lifer.Data.WeatherCard;
import com.example.lifer.adapters.WeatherCardAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
    心知天气API: https://seniverse.yuque.com/hyper_data/datasets/start?
 */
public class WeatherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherCardAdapter adapter;
    private List<WeatherCard> weatherCards;

    private static final String API_KEY = "S3ajJ9HXNXvzV5SzW";
    private static final String LOCATION = "ningbo";
    private static final String LANGUAGE = "zh-Hans";
    private static final String UNIT = "c";
    private static final int DAYS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        weatherCards = new ArrayList<>();
        adapter = new WeatherCardAdapter(weatherCards);
        recyclerView.setAdapter(adapter);

        fetchWeatherData();
    }

    private void fetchWeatherData() {
        String url = "https://api.seniverse.com/v3/weather/daily.json?key=" + API_KEY +
                "&location=" + LOCATION +
                "&language=" + LANGUAGE +
                "&unit=" + UNIT +
                "&start=0" +
                "&days=" + DAYS;

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");
                            JSONObject resultsObject = resultsArray.getJSONObject(0);

                            JSONObject locationObject = resultsObject.getJSONObject("location");
                            JSONArray dailyArray = resultsObject.getJSONArray("daily");

                            for (int i = 0; i < dailyArray.length(); i++) {
                                JSONObject dailyObject = dailyArray.getJSONObject(i);

                                String date = dailyObject.getString("date");
                                String textDay = dailyObject.getString("text_day");
                                String textNight = dailyObject.getString("text_night");
                                String highTemp = dailyObject.getString("high");
                                String lowTemp = dailyObject.getString("low");
                                String rainfall = dailyObject.getString("rainfall");
                                String windDirection = dailyObject.getString("wind_direction");
                                String windSpeed = dailyObject.getString("wind_speed");
                                String humidity = dailyObject.getString("humidity");

                                WeatherCard weatherCard = new WeatherCard(date, textDay, textNight, highTemp, lowTemp, rainfall, windDirection, windSpeed, humidity);
                                weatherCards.add(weatherCard);
                            }

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(request);
    }

}
