package com.objectway.stage.examples.mvc.dto;

import com.objectway.stage.examples.mvc.model.Unit;

import java.util.Objects;

public class ConversionResultDTO {
    private float result;
    private Unit unit;

    public ConversionResultDTO() {
    }

    public ConversionResultDTO(float result, Unit unit) {
        this.result = result;
        this.unit = unit;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionResultDTO that = (ConversionResultDTO) o;
        return Float.compare(that.result, result) == 0 &&
                unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, unit);
    }

    @Override
    public String toString() {
        return "ConversionResultDTO{" +
                "result=" + result +
                ", unit=" + unit +
                '}';
    }
}
