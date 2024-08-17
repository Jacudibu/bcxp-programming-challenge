package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.WeatherBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherEvaluatorTest {
    @Test
    public void evaluating() {
        WeatherBean bean = new WeatherBean(1, 5, 10);
        WeatherEvaluator evaluator = new WeatherEvaluator(bean);

        assertEquals(1, evaluator.getData().getDay());
        assertEquals(5, evaluator.getValue());
    }
}
