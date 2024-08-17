package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.CountryBean;

public abstract class CountryEvaluator {
    public static float populationDensity(CountryBean country) {
        return (float) country.getPopulation() / country.getArea();
    }
}
