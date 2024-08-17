package de.bcxp.challenge.parsing;

import java.io.IOException;

public interface IFileParser<TDataBean, TValue extends Comparable<TValue>> {
    FileParserResult<TDataBean, TValue> parse() throws IOException, ParserClassMissingException, ParserEvaluatorMissingException;
}
