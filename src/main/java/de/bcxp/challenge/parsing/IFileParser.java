package de.bcxp.challenge.parsing;

import de.bcxp.challenge.evaluator.IEvaluable;

import java.nio.file.Path;

public interface IFileParser<TData, TValue, TEvaluator extends IEvaluable<TData, TValue>> {
    /**
     * Parses the file at the given path and returns the smallest evaluated value inside it.
     */
    TEvaluator parse(Path path);
}
