package de.bcxp.challenge.parsing;

import de.bcxp.challenge.data.WeatherBean;
import de.bcxp.challenge.evaluator.WeatherEvaluator;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVFileParserTest {
    @NotNull
    private Path getPath(String fileName) {
        return Paths.get("src/test/resources/" + fileName);
    }

    @NotNull
    private WeatherEvaluator parseWeather(String fileName) {
        Path path = getPath(fileName);
        CSVFileParser<WeatherBean, Integer, WeatherEvaluator> parser = new CSVFileParser<>();
        return parser.parse(path);
    }

    @Test
    void singleElement_shouldWork() {
        WeatherEvaluator result = parseWeather("single_weather.csv");
        assertEquals(1, result.getData().getDay());
    }

    @Test
    void weatherFile_shouldWork() {
        WeatherEvaluator result = parseWeather("weather.csv");
        assertEquals(14, result.getData().getDay());
    }

    @Test
    void invalidPath_shouldThrow() {
        assertThrows(IOException.class, () -> parseWeather("invalid_path.csv"));
    }

    @Test
    void emptyFile_shouldThrow() {
        assertThrows(EmptyFileException.class, () -> parseWeather("empty.csv"));
    }

    @Test
    void invalidData_shouldThrow() {
        assertThrows(InvalidFileContentException.class, () ->  parseWeather("invalid.csv"));
    }
}
