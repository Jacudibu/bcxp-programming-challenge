package de.bcxp.challenge.parsing;

public class ParserEvaluatorMissingException extends RuntimeException {
    public ParserEvaluatorMissingException() {
        super("The Parser Evaluator was not set! Use .withEvaluator to provide it.");
    }
}
