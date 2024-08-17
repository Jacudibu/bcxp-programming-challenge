package de.bcxp.challenge.parsing;

import de.bcxp.challenge.data.CountryBean;
import de.bcxp.challenge.data.WeatherBean;
import de.bcxp.challenge.evaluator.CountryEvaluator;
import de.bcxp.challenge.evaluator.WeatherEvaluator;
import de.bcxp.challenge.utils.PathParser;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVFileParserTest {
    @NotNull
    private Path getPath(String fileName) {
        return PathParser.getPathToResourceFile("test", fileName);
    }

    @NotNull
    private FileParserResult<WeatherBean, Integer> parseWeather(String fileName) throws IOException {
        Path path = getPath(fileName);

        return new CSVFileParser<WeatherBean, Integer>(path)
                .withClass(WeatherBean.class)
                .withEvaluationFunction(WeatherEvaluator::temperatureDifference)
                .withOrdering(Ordering.Smallest)
                .parse();
    }

    @NotNull
    private FileParserResult<CountryBean, Float> parseCountry(String fileName) throws IOException {
        Path path = getPath(fileName);

        return new CSVFileParser<CountryBean, Float>(path)
                .withClass(CountryBean.class)
                .withEvaluationFunction(CountryEvaluator::populationDensity)
                .withSeparator(';')
                .withOrdering(Ordering.Biggest)
                .parse();
    }

    @Test
    void singleElement_shouldWork() throws Exception {
        FileParserResult<WeatherBean, Integer> result = parseWeather("single_weather.csv");
        assertEquals(1, result.data.getDay());
    }

    @Test
    void weatherFile_shouldWork() throws Exception {
        FileParserResult<WeatherBean, Integer> result = parseWeather("weather.csv");
        assertEquals(14, result.data.getDay());
    }

    @Test
    void countryFile_shouldWork() throws Exception {
        FileParserResult<CountryBean, Float> result = parseCountry("countries.csv");
        assertEquals(516100, result.data.getPopulation());
        assertEquals("Malta", result.data.getName());
    }

    @Test
    void countryFile_withWeirdPopulationFormatting_shouldWork() throws Exception {
        FileParserResult<CountryBean, Float> result = parseCountry("countries_croatia.csv");
        assertEquals(4036355, result.data.getPopulation());
        assertEquals("Croatia", result.data.getName());
    }

    @Test
    void invalidPath_shouldThrow() {
        assertThrows(IOException.class, () -> parseWeather("invalid_path.csv"));
    }

    @Test
    void emptyFile_shouldThrow() {
        assertThrows(UnableToParseFileContentException.class, () -> parseWeather("empty.csv"));
    }

    @Test
    void invalidData_shouldThrow() {
        assertThrows(UnableToParseFileContentException.class, () -> parseWeather("invalid.csv"));
    }
}
