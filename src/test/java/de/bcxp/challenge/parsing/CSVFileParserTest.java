package de.bcxp.challenge.parsing;

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
    private WeatherEvaluator parseWeather(String fileName) throws IOException {
        Path path = getPath(fileName);
        CSVFileParser parser = new CSVFileParser();
        return parser.parse(path);
    }

    @NotNull
    private CountryEvaluator parseCountry(String fileName) throws IOException {
        Path path = getPath(fileName);
        CSVFileParser parser = new CSVFileParser();
        return parser.parse_country(path);
    }

    @Test
    void singleElement_shouldWork() throws Exception {
        WeatherEvaluator result = parseWeather("single_weather.csv");
        assertEquals(1, result.getData().getDay());
    }

    @Test
    void weatherFile_shouldWork() throws Exception {
        WeatherEvaluator result = parseWeather("weather.csv");
        assertEquals(14, result.getData().getDay());
    }

    @Test
    void countryFile_shouldWork() throws Exception {
        CountryEvaluator result = parseCountry("countries.csv");
        assertEquals(516100, result.getData().getPopulation());
        assertEquals("Malta", result.getData().getName());
    }

    @Test
    void countryFile_withWeirdPopulationFormatting_shouldWork() throws Exception {
        CountryEvaluator result = parseCountry("countries_croatia.csv");
        assertEquals(4036355, result.getData().getPopulation());
        assertEquals("Croatia", result.getData().getName());
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
