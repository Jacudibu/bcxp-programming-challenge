package de.bcxp.challenge.parsing;

import com.opencsv.bean.CsvToBeanBuilder;
import de.bcxp.challenge.data.CountryBean;
import de.bcxp.challenge.data.WeatherBean;
import de.bcxp.challenge.evaluator.CountryEvaluator;
import de.bcxp.challenge.evaluator.WeatherEvaluator;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class CSVFileParser {
    public WeatherEvaluator parse(Path path) throws IOException, UnableToParseFileContentException {
        Optional<WeatherEvaluator> best = Optional.empty();

        try (Reader reader = Files.newBufferedReader(path)) {
            for (final WeatherBean data : new CsvToBeanBuilder<WeatherBean>(reader)
                    .withType(WeatherBean.class)
                    .build()) {
                WeatherEvaluator evaluated_data = new WeatherEvaluator(data);

                if (best.isEmpty() || best.get().getValue().compareTo(evaluated_data.getValue()) > 0) {
                    best = Optional.of(evaluated_data);
                }
            }
        }

        return best.orElseThrow(UnableToParseFileContentException::new);
    }

    public CountryEvaluator parse_country(Path path) throws IOException, UnableToParseFileContentException {
        Optional<CountryEvaluator> best = Optional.empty();

        try (Reader reader = Files.newBufferedReader(path)) {
            for (final CountryBean data : new CsvToBeanBuilder<CountryBean>(reader)
                    .withType(CountryBean.class)
                    .withSeparator(';')
                    .build()) {
                CountryEvaluator evaluated_data = new CountryEvaluator(data);

                if (best.isEmpty() || best.get().getValue().compareTo(evaluated_data.getValue()) < 0) {
                    best = Optional.of(evaluated_data);
                }
            }
        }

        return best.orElseThrow(UnableToParseFileContentException::new);
    }
}
