package de.bcxp.challenge.data;

public class WeatherBean {
    private final int day;
    private final int minimumTemperature;
    private final int maximumTemperature;

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
