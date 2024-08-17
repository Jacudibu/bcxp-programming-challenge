package de.bcxp.challenge.parsing;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException() {
        super("The provided file was empty!");
    }
}
