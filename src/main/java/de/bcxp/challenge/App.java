package de.bcxp.challenge;

import de.bcxp.challenge.evaluator.CountryEvaluator;
import de.bcxp.challenge.evaluator.WeatherEvaluator;
import de.bcxp.challenge.parsing.CSVFileParser;
import de.bcxp.challenge.parsing.UnableToParseFileContentException;
import de.bcxp.challenge.utils.PathParser;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {
    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        CSVFileParser csvFileParser = new CSVFileParser();

        try {
            Path weatherPath = PathParser.getPathToResourceFile("main", "weather.csv");
            WeatherEvaluator dayWithSmallestTempSpread = csvFileParser.parse(weatherPath);
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread.getData().getDay());
        } catch (IOException e) {
            System.out.printf("Weather: Was unable to read file: %s%n", e.getMessage());
        } catch (UnableToParseFileContentException e) {
            System.out.printf("Weather: Something went wrong when trying to parse file content: %s%n", e.getMessage());
        }

        try {
            Path countryPath = PathParser.getPathToResourceFile("main", "countries.csv");
            CountryEvaluator countryWithHighestPopulationDensity = csvFileParser.parse_country(countryPath);
            System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity.getData().getName());
        } catch (IOException e) {
            System.out.printf("Country: Was unable to read file: %s%n", e.getMessage());
        } catch (UnableToParseFileContentException e) {
            System.out.printf("Country: Something went wrong when trying to parse file content: %s%n", e.getMessage());
        }
    }
}
