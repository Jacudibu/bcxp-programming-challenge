package de.bcxp.challenge.deserializer;

import com.opencsv.bean.AbstractBeanField;

public class PopulationDeserializer extends AbstractBeanField<String, Integer> {
    @Override
    protected Integer convert(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        // In our case, the offender is "4.036.355,00" - There will never be ,50 people or so.
        if (input.endsWith(",00")) {
            input = input.substring(0, input.length() - 3);
            input = input.replace(".", "");
        }

        return Integer.parseInt(input);
    }
}
