package com.example.lifer.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifer.R;
import com.example.lifer.Data.WeatherCard;

import java.util.List;

public class WeatherCardAdapter extends RecyclerView.Adapter<WeatherCardAdapter.WeatherCardViewHolder> {

    private List<WeatherCard> weatherCards;

    public WeatherCardAdapter(List<WeatherCard> weatherCards) {
        this.weatherCards = weatherCards;
    }

    @NonNull
    @Override
    public WeatherCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_card_item, parent, false);
        return new WeatherCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCardViewHolder holder, int position) {
        WeatherCard weatherCard = weatherCards.get(position);

        holder.dateTextView.setText(weatherCard.getDate());
        holder.textDayTextView.setText("白天:" + weatherCard.getTextDay());
        holder.textNightTextView.setText("夜晚:" +weatherCard.getTextNight());
        holder.highTempTextView.setText(weatherCard.getHighTemp() + "°C");
        holder.lowTempTextView.setText(weatherCard.getLowTemp() + "°C");
        holder.rainfallTextView.setText("降水量" + weatherCard.getRainfall() + "mm");
        holder.windDirectionTextView.setText(weatherCard.getWindDirection() + "风");
        holder.windSpeedTextView.setText("风速"+weatherCard.getWindSpeed() + "km/h");
        holder.humidityTextView.setText("湿度:"+weatherCard.getHumidity() + "%");
        setImageView(holder.dayWeatherImageView,weatherCard.getTextDay());
        setImageView(holder.nightWeatherImageView,weatherCard.getTextNight());
    }

    public void setImageView(ImageView imageView,String weather){
        if( weather.equals("多云") )
            imageView.setImageResource(R.drawable.duoyun_icon);
        else if( weather.equals( "阵雨"))
            imageView.setImageResource(R.drawable.zhenyu_icon);
        else if(weather.equals( "小雨"))
            imageView.setImageResource(R.drawable.xiaoyu_icon);
        else if(weather.equals( "阴"))
            imageView.setImageResource(R.drawable.yin_icon);
        else if(weather.equals( "晴"))
            imageView.setImageResource(R.drawable.qingtian_icon);
        else if(weather.equals("中雨"))
            imageView.setImageResource(R.drawable.zhongyu_icon);
        else if(weather.equals("雷阵雨"))
            imageView.setImageResource(R.drawable.leizhenyu_icon);
    }

    @Override
    public int getItemCount() {
        return weatherCards.size();
    }

    public class WeatherCardViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView textDayTextView;
        public TextView textNightTextView;
        public TextView highTempTextView;
        public TextView lowTempTextView;
        public TextView rainfallTextView;
        public TextView windDirectionTextView;
        public TextView windSpeedTextView;
        public TextView humidityTextView;
        public ImageView dayWeatherImageView;
        public ImageView nightWeatherImageView;

        public WeatherCardViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            textDayTextView = itemView.findViewById(R.id.textDayTextView);
            textNightTextView = itemView.findViewById(R.id.textNightTextView);
            highTempTextView = itemView.findViewById(R.id.highTempTextView);
            lowTempTextView = itemView.findViewById(R.id.lowTempTextView);
            rainfallTextView = itemView.findViewById(R.id.rainfallTextView);
            windDirectionTextView = itemView.findViewById(R.id.windDirectionTextView);
            windSpeedTextView = itemView.findViewById(R.id.windSpeedTextView);
            humidityTextView = itemView.findViewById(R.id.humidityTextView);

            dayWeatherImageView = itemView.findViewById(R.id.dayWeather);
            nightWeatherImageView = itemView.findViewById(R.id.nightWeather);
        }
        public void bind(WeatherCard weatherCard) {
            dateTextView.setText(weatherCard.getDate());
            textDayTextView.setText(weatherCard.getTextDay());
            textNightTextView.setText(weatherCard.getTextNight());
            highTempTextView.setText(weatherCard.getHighTemp() + "°C");
            lowTempTextView.setText(weatherCard.getLowTemp() + "°C");
            rainfallTextView.setText(weatherCard.getRainfall() + "mm");
            windDirectionTextView.setText(weatherCard.getWindDirection());
            windSpeedTextView.setText(weatherCard.getWindSpeed() + "km/h");
            humidityTextView.setText(weatherCard.getHumidity() + "%");
            dayWeatherImageView.setImageResource(R.drawable.duoyun);
            nightWeatherImageView.setImageResource(R.drawable.duoyun);
        }

    }

}
