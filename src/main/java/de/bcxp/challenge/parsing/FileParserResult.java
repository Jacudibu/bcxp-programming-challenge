package de.bcxp.challenge.parsing;

public class FileParserResult<TDataBean, TValue extends Comparable<TValue>> {
    public final TDataBean data;
    public final TValue value;

    public FileParserResult(TDataBean data, TValue value) {
        this.data = data;
        this.value = value;
    }

    public boolean isOtherValueBetter(TValue other, Ordering ordering) {
        if (ordering == Ordering.Smallest) {
            return this.value.compareTo(other) > 0;
        } else {
            return this.value.compareTo(other) < 0;
        }
    }
}
