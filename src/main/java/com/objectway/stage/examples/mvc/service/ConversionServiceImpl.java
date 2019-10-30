package com.objectway.stage.examples.mvc.service;

import com.objectway.stage.examples.mvc.exception.UnitMismatchException;
import com.objectway.stage.examples.mvc.model.Unit;
import com.objectway.stage.examples.mvc.utils.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConversionServiceImpl implements ConversionService {
    private static final Map<Pair<Unit, Unit>, Float> conversionFactors = new HashMap<>();
    static {
        // Length units
        conversionFactors.put(Pair.of(Unit.KILOMETERS, Unit.METERS), 1e3f);
        conversionFactors.put(Pair.of(Unit.KILOMETERS, Unit.CENTIMETERS), 1e5f);
        conversionFactors.put(Pair.of(Unit.KILOMETERS, Unit.MILLIMETERS), 1e6f);
        conversionFactors.put(Pair.of(Unit.KILOMETERS, Unit.INCHES), 39370.1f);
        conversionFactors.put(Pair.of(Unit.KILOMETERS, Unit.FEET), 3280.84f);
        conversionFactors.put(Pair.of(Unit.METERS, Unit.CENTIMETERS), 1e2f);
        conversionFactors.put(Pair.of(Unit.METERS, Unit.MILLIMETERS), 1e3f);
        conversionFactors.put(Pair.of(Unit.METERS, Unit.INCHES), 39.3701f);
        conversionFactors.put(Pair.of(Unit.METERS, Unit.FEET), 3.28084f);
        conversionFactors.put(Pair.of(Unit.CENTIMETERS, Unit.MILLIMETERS), 1e1f);
        conversionFactors.put(Pair.of(Unit.CENTIMETERS, Unit.INCHES), 0.393701f);
        conversionFactors.put(Pair.of(Unit.CENTIMETERS, Unit.FEET), 0.0328084f);
        conversionFactors.put(Pair.of(Unit.MILLIMETERS, Unit.INCHES), 0.0393701f);
        conversionFactors.put(Pair.of(Unit.MILLIMETERS, Unit.FEET), 0.00328084f);
        conversionFactors.put(Pair.of(Unit.INCHES, Unit.FEET), 0.0833333f);

        // Mass units
        conversionFactors.put(Pair.of(Unit.KILOGRAMS, Unit.GRAMS), 1e3f);
        conversionFactors.put(Pair.of(Unit.KILOGRAMS, Unit.MILLIGRAMS), 1e6f);
        conversionFactors.put(Pair.of(Unit.KILOGRAMS, Unit.POUNDS), 2.20462f);
        conversionFactors.put(Pair.of(Unit.KILOGRAMS, Unit.OUNCES), 35.274f);
        conversionFactors.put(Pair.of(Unit.GRAMS, Unit.MILLIGRAMS), 1e3f);
        conversionFactors.put(Pair.of(Unit.GRAMS, Unit.POUNDS), 0.0022046f);
        conversionFactors.put(Pair.of(Unit.GRAMS, Unit.OUNCES), 0.035274f);
        conversionFactors.put(Pair.of(Unit.MILLIGRAMS, Unit.POUNDS), 2.20462e6f);
        conversionFactors.put(Pair.of(Unit.MILLIGRAMS, Unit.OUNCES), 3.5274e5f);
        conversionFactors.put(Pair.of(Unit.POUNDS, Unit.OUNCES), 16f);

        // Inverse
        final Map<Pair<Unit, Unit>, Float> inverse = new HashMap<>();
        for (Map.Entry<Pair<Unit, Unit>, Float> entry : conversionFactors.entrySet()) {
            final Pair<Unit, Unit> pair = entry.getKey();
            inverse.put(
                Pair.of(
                    pair.getSecond(),
                    pair.getFirst()
                ),
                1 / entry.getValue()
            );
        }

        conversionFactors.putAll(inverse);
    }

    @Override
    public float convert(final float value, final Unit from, final Unit to) throws UnitMismatchException {
        // Sanity checks
        if (from == null) {
            throw new IllegalArgumentException("from parameter cannot be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("to parameter cannot be null");
        }

        // Same unit
        if (from.equals(to)) {
            // No conversion needed
            return value;
        }

        // Lookup the conversion factor
        final Pair<Unit, Unit> pair = Pair.of(from, to);
        final Float factor = conversionFactors.get(pair);

        // Unit not convertible
        if (factor == null) {
            throw new UnitMismatchException(from, to);
        }

        // Convert
        return value * factor;
    }
}
