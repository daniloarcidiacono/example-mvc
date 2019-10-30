package com.objectway.stage.examples.mvc.exception;

import com.objectway.stage.examples.mvc.model.Unit;

/**
 * Exception thrown when trying to convert between two incompatible units
 * (e.g. meters to kilograms).
 */
public class UnitMismatchException extends Exception {
    private Unit from, to;

    public UnitMismatchException(final Unit from, final Unit to) {
        super(String.format("Cannot convert from %s to %s", from, to));
        this.from = from;
        this.to = to;
    }
}
