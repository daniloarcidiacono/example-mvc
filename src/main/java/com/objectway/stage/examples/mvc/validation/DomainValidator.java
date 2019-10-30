package com.objectway.stage.examples.mvc.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DomainValidator implements ConstraintValidator<Domain, String> {
    private List<String> acceptedValues;
    private boolean nullsAllowed;
 
    @Override
    public void initialize(Domain annotation) {
        nullsAllowed = annotation.nullable();
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toList());
    }
 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return nullsAllowed;
        }
 
        return acceptedValues.contains(value);
    }
}
