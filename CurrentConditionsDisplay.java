package com.test;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements DisplayElement, Observer {
    private float temp, humidity;
    private Observable weatherData;

    public CurrentConditionsDisplay(Observable weatherD) {
        this.weatherData = weatherD;
        weatherD.addObserver(this);

    }

    @Override
    public void display() {
        System.out.println("humid=" + humidity + ":temp=" + temp);

    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof WeatherData) {
            WeatherData wd = (WeatherData) arg;
            this.temp = wd.getTemp();
            this.humidity = wd.getHumidity();
            display();

        }


    }

}
