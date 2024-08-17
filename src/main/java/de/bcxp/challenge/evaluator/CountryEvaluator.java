package de.bcxp.challenge.evaluator;

import de.bcxp.challenge.data.CountryBean;

public class CountryEvaluator implements IEvaluable<CountryBean, Float> {
    private final CountryBean data;
    private final float value;

    public CountryEvaluator(CountryBean data) {
        this.data = data;
        this.value = (float) data.getPopulation() / data.getArea();
    }

    @Override
    public CountryBean getData() {
        return data;
    }

    @Override
    public Float getValue() {
        return value;
    }
}
