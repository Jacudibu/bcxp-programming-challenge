package de.bcxp.challenge.data;

import com.opencsv.bean.CsvBindByName;

public class WeatherBean {
    @CsvBindByName
    private int day;

    @CsvBindByName(column = "MnT")
    private int minimumTemperature;

    @CsvBindByName(column = "MxT")
    private int maximumTemperature;

    public WeatherBean() {
    }

    public WeatherBean(int day, int minimumTemperature, int maximumTemperature) {
        this.day = day;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
    }

    public int getDay() {
        return day;
    }

    public int getMinimumTemperature() {
        return minimumTemperature;
    }

    public int getMaximumTemperature() {
        return maximumTemperature;
    }
}
