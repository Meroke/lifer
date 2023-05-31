package com.example.lifer.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifer.Data.NewsItem;
import com.example.lifer.R;
import com.example.lifer.adapters.NewsAdapter;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    NewsAPI: https://newsapi.org/docs/get-started
 */
public class NewsActivity extends AppCompatActivity {

    private static final String API_KEY = "9b69fae2ecd644c49a63ba382dd75e83";
    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsItem> newsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsItemList = new ArrayList<>();
        newsAdapter = new NewsAdapter(this, newsItemList);
        recyclerView.setAdapter(newsAdapter);

        fetchNews();
    }

    private void fetchNews() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(NEWS_API_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(NewsActivity.this, "Failed to fetch news", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject json = new JSONObject(responseBody);
                        JSONArray articles = json.getJSONArray("articles");

                        for (int i = 0; i < articles.length(); i++) {
                            JSONObject article = articles.getJSONObject(i);
                            String author = article.getString("author");
                            if(author.equals("null"))
                                continue;
                            String title = article.getString("title");
                            String imageUrl = article.getString("urlToImage");
                            String description = article.getString("description");
                            String publishedAt = article.getString("publishedAt");

                            NewsItem newsItem = new NewsItem(imageUrl,title , description, publishedAt);
                            newsItemList.add(newsItem);
                        }

                        runOnUiThread(() -> {
                            newsAdapter.notifyDataSetChanged();
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
