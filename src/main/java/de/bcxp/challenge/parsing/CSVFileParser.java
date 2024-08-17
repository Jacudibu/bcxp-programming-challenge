package de.bcxp.challenge.parsing;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Path;
import java.util.Iterator;

public class CSVFileParser<TDataBean, TValue extends Comparable<TValue>> extends AbstractFileParser<TDataBean, TValue> {
    private char separator = ',';

    public CSVFileParser(Path path) {
        super(path);
    }

    public CSVFileParser<TDataBean, TValue> withSeparator(char separator) {
        this.separator = separator;
        return this;
    }

    @Override
    Iterator<TDataBean> getIterator(Reader reader, Class<? extends TDataBean> type) {
        return new CsvToBeanBuilder<TDataBean>(reader)
                .withType(type)
                .withSeparator(this.separator)
                .build()
                .iterator();
    }
}
