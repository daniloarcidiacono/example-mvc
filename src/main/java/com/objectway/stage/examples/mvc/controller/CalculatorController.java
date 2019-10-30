package com.objectway.stage.examples.mvc.controller;

import com.objectway.stage.examples.mvc.dto.ConversionRequestDTO;
import com.objectway.stage.examples.mvc.dto.ConversionResultDTO;
import com.objectway.stage.examples.mvc.exception.UnitMismatchException;
import com.objectway.stage.examples.mvc.model.Unit;
import com.objectway.stage.examples.mvc.service.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
public class CalculatorController {
    /**
     * Instances of java.util.Random are threadsafe. However, the concurrent use of the same java.util.Random
     * instance across threads may encounter contention and consequent poor performance.
     */
    private static final Random random = new Random();

    private final ConversionService conversionService;

    // Constructor-based injection
    public CalculatorController(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/sum/{opA}/{opB}")
    public int computeSum(@PathVariable final int opA, @PathVariable final int opB) {
        return opA + opB;
    }

    @GetMapping("/sub/{opA}/{opB}")
    public int computeSub(@PathVariable final int opA, @PathVariable final int opB) {
        return opA - opB;
    }

    @GetMapping("/mul/{opA}/{opB}")
    public int computeMul(@PathVariable final int opA, @PathVariable final int opB) {
        return opA * opB;
    }

    @GetMapping("/div/{opA}/{opB}")
    public float computeDiv(@PathVariable final int opA, @PathVariable final int opB) {
        return opA / (float)opB;
    }

    @GetMapping("/random")
    public int computeRandom(@RequestParam(defaultValue = "1", required = false) final int min,
                             @RequestParam(defaultValue = "10", required = false) final int max) {
        return min + random.nextInt(max - min + 1);
    }

    @PostMapping("/convert")
    public ConversionResultDTO convert(@RequestBody @Valid final ConversionRequestDTO conversion) throws UnitMismatchException {
        final Unit sourceUnit = Unit.valueOf(conversion.getUnit());
        final Unit targetUnit = Unit.valueOf(conversion.getTo());
        final float converted = conversionService.convert(
            conversion.getValue(),
            sourceUnit,
            targetUnit
        );

        return new ConversionResultDTO(converted, targetUnit);
    }
}
