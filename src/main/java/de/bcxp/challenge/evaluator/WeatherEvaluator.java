package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.WeatherBean;

public class WeatherEvaluator implements IEvaluable<WeatherBean, Integer> {
    private final WeatherBean data;
    private final int value;

    public WeatherEvaluator(WeatherBean data) {
        this.data = data;
        this.value = data.getMaximumTemperature() - data.getMinimumTemperature();
    }

    @Override
    public WeatherBean getData() {
        return data;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
