package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.CountryBean;
import de.bcxp.challenge.data.WeatherBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryEvaluatorTest {
    @Test
    public void populationDensity() {
        CountryBean bean = new CountryBean("MyIsland", 20, 10);
        assertEquals(2, CountryEvaluator.populationDensity(bean));
    }
}
