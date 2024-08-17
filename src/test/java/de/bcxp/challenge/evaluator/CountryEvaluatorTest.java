package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.CountryBean;
import de.bcxp.challenge.data.WeatherBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryEvaluatorTest {
    @Test
    public void evaluating() {
        CountryBean bean = new CountryBean("MyIsland", 20, 10);
        CountryEvaluator evaluator = new CountryEvaluator(bean);

        assertEquals("MyIsland", evaluator.getData().getName());
        assertEquals(2, evaluator.getValue());
    }
}
