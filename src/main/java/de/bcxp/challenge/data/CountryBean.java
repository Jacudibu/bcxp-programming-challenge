package de.bcxp.challenge.data;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import de.bcxp.challenge.deserializer.PopulationDeserializer;

public class CountryBean {
    @CsvBindByName
    private String name;

    @CsvBindByName
    @CsvCustomBindByName(converter = PopulationDeserializer.class)
    private int population;

    @CsvBindByName(column = "Area (km²)")
    private int area;

    public CountryBean() {
    }

    public CountryBean(String name, int population, int area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }
}
