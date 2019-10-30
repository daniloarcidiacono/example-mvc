package com.objectway.stage.examples.mvc.service;

import com.objectway.stage.examples.mvc.exception.UnitMismatchException;
import com.objectway.stage.examples.mvc.model.Unit;

/**
 * Service for converting values between different units.
 */
public interface ConversionService {
    /**
     * Applies a conversion.
     *
     * @param value the value to convert
     * @param from the value unit (cannot be null)
     * @param to the target unit (cannot be null)
     * @return the converted value
     * @throws IllegalArgumentException if from or to are null
     * @throws UnitMismatchException if the units are not compatible
     */
    float convert(final float value, final Unit from, final Unit to) throws UnitMismatchException;
}
