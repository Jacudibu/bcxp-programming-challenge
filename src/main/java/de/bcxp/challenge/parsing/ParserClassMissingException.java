package de.bcxp.challenge.parsing;

public class ParserClassMissingException extends RuntimeException {
    public ParserClassMissingException() {
        super("The Parser Class was not set! Use .withClass to provide it.");
    }
}
