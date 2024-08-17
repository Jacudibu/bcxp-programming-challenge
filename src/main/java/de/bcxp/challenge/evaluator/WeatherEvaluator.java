package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.WeatherBean;

public abstract class WeatherEvaluator {
    public static int temperatureDifference(WeatherBean weather) {
        return weather.getMaximumTemperature() - weather.getMinimumTemperature();
    }
}
