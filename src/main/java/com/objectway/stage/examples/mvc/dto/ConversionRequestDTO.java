package com.objectway.stage.examples.mvc.dto;

import com.objectway.stage.examples.mvc.model.Unit;
import com.objectway.stage.examples.mvc.validation.Domain;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ConversionRequestDTO {
    @NotNull
    private float value;

    @Domain(enumClass = Unit.class, message = "must be a valid unit")
    private String unit;

    @Domain(enumClass = Unit.class, message = "must be a valid unit")
    private String to;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionRequestDTO that = (ConversionRequestDTO) o;
        return Float.compare(that.value, value) == 0 &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit, to);
    }

    @Override
    public String toString() {
        return "ConversionRequestDTO{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
