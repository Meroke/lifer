package com.example.lifer.Data;

public class WeatherCard {

    private String date;
    private String textDay;
    private String textNight;
    private String highTemp;
    private String lowTemp;
    private String rainfall;
    private String windDirection;
    private String windSpeed;
    private String humidity;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }


    public WeatherCard(String date, String textDay, String textNight, String highTemp, String lowTemp, String rainfall, String windDirection, String windSpeed, String humidity) {
        this.date = date;
        this.textDay = textDay;
        this.textNight = textNight;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.rainfall = rainfall;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
    }


}
