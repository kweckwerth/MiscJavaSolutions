package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WeatherData extends Observable {
    List<Observer> list = new ArrayList<Observer>();
    private float temp, humidity, pressure;

    public float getTemp() {
        return temp;
    }


    public float getHumidity() {
        return humidity;
    }


    public float getPressure() {
        return pressure;
    }


    public WeatherData() {

    }

    @Override
    public synchronized void addObserver(Observer arg0) {
        // TODO Auto-generated method stub
        super.addObserver(arg0);
    }

    @Override
    protected synchronized void clearChanged() {
        // TODO Auto-generated method stub
        super.clearChanged();
    }

    @Override
    public synchronized void deleteObserver(Observer arg0) {
        // TODO Auto-generated method stub
        super.deleteObserver(arg0);
    }

    @Override
    public synchronized void deleteObservers() {
        // TODO Auto-generated method stub
        super.deleteObservers();
    }

    @Override
    public void notifyObservers(Object arg0) {
        // TODO Auto-generated method stub
        super.notifyObservers(arg0);
    }

    @Override
    protected synchronized void setChanged() {
        // TODO Auto-generated method stub
        super.setChanged();
    }


    public void setMeasurements(float temp, float hum, float pres) {
        this.temp = temp;
        this.humidity = hum;
        this.pressure = pres;
        measurementsChanged();
    }

    private void measurementsChanged() {
        setChanged();
        notifyObservers(this);
        clearChanged();

        notifyObservers(this);
    }

}
