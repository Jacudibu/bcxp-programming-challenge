package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.WeatherBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherEvaluatorTest {
    @Test
    public void temperatureSpread() {
        WeatherBean bean = new WeatherBean(1, 5, 10);
        assertEquals(5, WeatherEvaluator.temperatureSpread(bean));
    }
}
