package de.bcxp.challenge.parsing;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractFileParser<TDataBean, TValue extends Comparable<TValue>> {
    private final Path path;
    private Optional<Class<? extends TDataBean>> type = Optional.empty();
    private Optional<Function<TDataBean, TValue>> evaluator = Optional.empty();
    private Ordering ordering = Ordering.Smallest;

    protected AbstractFileParser(Path path) {
        this.path = path;
    }

    public AbstractFileParser<TDataBean, TValue> withClass(Class<? extends TDataBean> type) {
        this.type = Optional.of(type);
        return this;
    }

    public AbstractFileParser<TDataBean, TValue> withOrdering(Ordering ordering) {
        this.ordering = ordering;
        return this;
    }

    public AbstractFileParser<TDataBean, TValue> withEvaluationFunction(Function<TDataBean, TValue> function) {
        this.evaluator = Optional.of(function);
        return this;
    }

    abstract Iterator<TDataBean> getIterator(Reader reader, Class<? extends TDataBean> type);

    public FileParserResult<TDataBean, TValue> parse() throws IOException, ParserClassMissingException, ParserEvaluatorMissingException {
        Optional<FileParserResult<TDataBean, TValue>> best = Optional.empty();

        Class<? extends TDataBean> type = this.type.orElseThrow(ParserClassMissingException::new);
        Function<TDataBean, TValue> evaluator = this.evaluator.orElseThrow(ParserEvaluatorMissingException::new);

        try (Reader reader = Files.newBufferedReader(this.path)) {
            Iterator<TDataBean> iterator = getIterator(reader, type);
            while (iterator.hasNext()) {
                TDataBean data = iterator.next();
                TValue evaluated_data = evaluator.apply(data);

                if (best.isEmpty() || best.get().isOtherValueBetter(evaluated_data, this.ordering)) {
                    best = Optional.of(new FileParserResult<>(data, evaluated_data));
                }
            }
        }

        return best.orElseThrow(UnableToParseFileContentException::new);
    }
}
