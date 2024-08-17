package de.bcxp.challenge.evaluator;

/* Interface for wrappers around our POJOs.
 */
public interface IEvaluable<TData, TValue> {
    TData getData();
    TValue getValue();
}
