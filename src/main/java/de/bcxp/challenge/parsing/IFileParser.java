package de.bcxp.challenge.parsing;

import de.bcxp.challenge.evaluator.IEvaluable;
import de.bcxp.challenge.evaluator.WeatherEvaluator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;

public interface IFileParser<TData, TValue, TEvaluator extends IEvaluable<TData, TValue>> {
    /**
     * Parses the file at the given path and returns the smallest evaluated value inside it.
     */
    TEvaluator parse(Path path);
}
