package de.bcxp.challenge.parsing;

public class UnableToParseFileContentException extends RuntimeException {
    public UnableToParseFileContentException() {
        super("The provided file was empty or didn't contain valid CSV!");
    }
}
