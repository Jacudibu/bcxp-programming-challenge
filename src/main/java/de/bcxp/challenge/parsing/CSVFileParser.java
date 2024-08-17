package de.bcxp.challenge.parsing;

import de.bcxp.challenge.evaluator.IEvaluable;

import java.nio.file.Path;
import java.util.Optional;

public class CSVFileParser<TData, TValue, TEvaluator extends IEvaluable<TData, TValue>> implements IFileParser<TData, TValue, TEvaluator> {
    public TEvaluator parse(Path path) {
        Optional<TEvaluator> best = Optional.empty();

        if (best.isPresent()) {
            return best.get();
        } else {
            throw new EmptyFileException();
        }
    }
}
