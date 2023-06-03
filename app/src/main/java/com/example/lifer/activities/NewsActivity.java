package com.example.lifer.activities;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifer.R;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private static final String API_KEY = "4db5e79fc03e58f5";
    private static final String API_URL = "https://api.jisuapi.com/news/get";
    private static final int NUM_NEWS_PER_REQUEST = 10;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsItems = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsItems);
        recyclerView.setAdapter(newsAdapter);

        // TODO: Implement the navigation bar with channels
    }

    private void fetchNews(String channel) {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?channel=%s&start=0&num=%d&appkey=%s",
                API_URL, channel, NUM_NEWS_PER_REQUEST, API_KEY);
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                // TODO: Handle network request failure
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    NewsResponse newsResponse = gson.fromJson(responseBody, NewsResponse.class);

                    if (newsResponse != null && newsResponse.status == 0 && newsResponse.result != null) {
                        List<NewsItem> fetchedNewsItems = newsResponse.result.list;

                        runOnUiThread(() -> {
                            newsItems.clear();
                            newsItems.addAll(fetchedNewsItems);
                            newsAdapter.notifyDataSetChanged();
                        });
                    } else {
                        // TODO: Handle invalid response
                    }
                } else {
                    // TODO: Handle unsuccessful response
                }
            }
        });
    }

    private static class NewsResponse {
        @SerializedName("status")
        int status;

        @SerializedName("msg")
        String message;

        @SerializedName("result")
        NewsResult result;
    }

    private static class NewsResult {
        @SerializedName("channel")
        String channel;

        @SerializedName("num")
        int num;

        @SerializedName("list")
        List<NewsItem> list;
    }

    private static class NewsItem {
        @SerializedName("title")
        String title;

        @SerializedName("time")
        String time;

        @SerializedName("src")
        String source;

        @SerializedName("pic")
        String imageUrl;

        @SerializedName("url")
        String newsUrl;

        @SerializedName("content")
        String content;
    }

    private static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

        private List<NewsItem> newsItems;

        public NewsAdapter(List<NewsItem> newsItems) {
            this.newsItems = newsItems;
        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
            NewsItem newsItem = newsItems.get(position);
            holder.bind(newsItem);
        }

        @Override
        public int getItemCount() {
            return newsItems.size();
        }

        public static class NewsViewHolder extends RecyclerView.ViewHolder {

            private TextView titleTextView;
            private TextView timeTextView;
            private TextView sourceTextView;

            public NewsViewHolder(@NonNull View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                timeTextView = itemView.findViewById(R.id.timeTextView);
                sourceTextView = itemView.findViewById(R.id.sourceTextView);
            }

            public void bind(NewsItem newsItem) {
                titleTextView.setText(newsItem.title);
                timeTextView.setText(newsItem.time);
                sourceTextView.setText(newsItem.source);

                // TODO: Load and display the image using an image loading library like Glide
            }
        }
    }
}
