package de.bcxp.challenge.parsing;

import jdk.jshell.spi.ExecutionControl;
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

    @Test
    void singleElement_shouldWork() throws ExecutionControl.NotImplementedException {
        Path path = getPath("singe_weather.csv");
        int result = CSVFileParser.parse(path);
        assertEquals(1, result);
    }

    @Test
    void weatherFile_shouldWork() throws ExecutionControl.NotImplementedException {
        Path path = getPath("single_weather.csv");
        int result = CSVFileParser.parse(path);
        assertEquals(14, result);
    }

    @Test
    void invalidPath_shouldThrow() {
        Path path = getPath("very_invalid.csv");
        assertThrows(IOException.class, () -> CSVFileParser.parse(path));
    }

    @Test
    void emptyFile_shouldThrow() {
        Path path = getPath("empty.csv");
        assertThrows(EmptyFileException.class, () -> CSVFileParser.parse(path));
    }

    @Test
    void invalidData_shouldThrow() {
        Path path = getPath("invalid.csv");
        assertThrows(InvalidFileContentException.class, () -> CSVFileParser.parse(path));
    }
}
