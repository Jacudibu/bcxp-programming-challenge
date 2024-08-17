package de.bcxp.challenge.parsing;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

public class CSVFileParser<TDataBean, TValue extends Comparable<TValue>> implements IFileParser<TDataBean, TValue> {
    private Path path;
    private Optional<Class<? extends TDataBean>> type = Optional.empty();
    private Optional<Function<TDataBean, TValue>> evaluator = Optional.empty();
    private char separator = ',';
    private Ordering ordering = Ordering.Smallest;

    public CSVFileParser(Path path) {
        this.path = path;
    }

    public CSVFileParser<TDataBean, TValue> withClass(Class<? extends TDataBean> type) {
        this.type = Optional.of(type);
        return this;
    }

    public CSVFileParser<TDataBean, TValue> withSeparator(char separator) {
        this.separator = separator;
        return this;
    }

    public CSVFileParser<TDataBean, TValue> withOrdering(Ordering ordering) {
        this.ordering = ordering;
        return this;
    }

    public CSVFileParser<TDataBean, TValue> withEvaluationFunction(Function<TDataBean, TValue> function) {
        this.evaluator = Optional.of(function);
        return this;
    }

    @Override
    public FileParserResult<TDataBean, TValue> parse() throws IOException, ParserClassMissingException, ParserEvaluatorMissingException {
        Optional<FileParserResult<TDataBean, TValue>> best = Optional.empty();

        Class<? extends TDataBean> type = this.type.orElseThrow(ParserClassMissingException::new);
        Function<TDataBean, TValue> evaluator = this.evaluator.orElseThrow(ParserEvaluatorMissingException::new);

        try (Reader reader = Files.newBufferedReader(this.path)) {
            for (final TDataBean data : new CsvToBeanBuilder<TDataBean>(reader)
                    .withType(type)
                    .withSeparator(this.separator)
                    .build()) {

                TValue evaluated_data = evaluator.apply(data);

                if (best.isEmpty() || best.get().isOtherValueBetter(evaluated_data, this.ordering)) {
                    best = Optional.of(new FileParserResult<>(data, evaluated_data));
                }
            }
        }

        return best.orElseThrow(UnableToParseFileContentException::new);
    }
}
