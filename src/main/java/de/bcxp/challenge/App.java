package de.bcxp.challenge;

import de.bcxp.challenge.data.CountryBean;
import de.bcxp.challenge.data.WeatherBean;
import de.bcxp.challenge.evaluator.CountryEvaluator;
import de.bcxp.challenge.evaluator.WeatherEvaluator;
import de.bcxp.challenge.parsing.CSVFileParser;
import de.bcxp.challenge.parsing.FileParserResult;
import de.bcxp.challenge.parsing.Ordering;
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
            String dayWithSmallestTempSpread = parseWeather();
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

            String countryWithHighestPopulationDensity = parseCountry();
            System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }

    private static String parseWeather() {
        Path weatherPath = PathParser.getPathToResourceFile("main", "weather.csv");

        try {
            FileParserResult<WeatherBean, Integer> result = new CSVFileParser<WeatherBean, Integer>(weatherPath)
                    .withClass(WeatherBean.class)
                    .withEvaluationFunction(WeatherEvaluator::temperatureDifference)
                    .withOrdering(Ordering.Smallest)
                    .parse();

            return String.valueOf(result.data.getDay());
        } catch (IOException e) {
            System.out.printf("Weather: Was unable to read file: %s%n", e.getMessage());
            return "<Error>";
        }
    }

    private static String parseCountry() {
        Path countryPath = PathParser.getPathToResourceFile("main", "countries.csv");
        try {
            FileParserResult<CountryBean, Float> result = new CSVFileParser<CountryBean, Float>(countryPath)
                    .withClass(CountryBean.class)
                    .withEvaluationFunction(CountryEvaluator::populationDensity)
                    .withOrdering(Ordering.Biggest)
                    .withSeparator(';')
                    .parse();

            return result.data.getName();
        } catch (IOException e) {
            System.out.printf("Country: Was unable to read file: %s%n", e.getMessage());
            return "<Error>";
        }
    }
}
