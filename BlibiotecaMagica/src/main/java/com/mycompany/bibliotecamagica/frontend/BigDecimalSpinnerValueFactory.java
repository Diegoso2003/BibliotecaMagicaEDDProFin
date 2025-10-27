/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.frontend;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

/**
 *
 * @author rafael-cayax
 */
public class BigDecimalSpinnerValueFactory extends SpinnerValueFactory<BigDecimal> {
private final BigDecimal step;
    private final BigDecimal min;
    private final BigDecimal max;

    public BigDecimalSpinnerValueFactory(BigDecimal min, BigDecimal max, BigDecimal initialValue, BigDecimal step) {
        this.min = min;
        this.max = max;
        this.step = step;
         setConverter(new StringConverter<BigDecimal>() {
            @Override
            public String toString(BigDecimal value) {
                if(value.compareTo(min) < 0){
                    setValue(min);
                    value = min;
                } else if(value.compareTo(max) > 0){
                    setValue(max);
                    value = max;
                }
                return value == null ? "" : value.toPlainString();
            }

            @Override
            public BigDecimal fromString(String string) {
                try {
                    string = string.trim();
                    BigDecimal value = new BigDecimal(string).setScale(2, RoundingMode.HALF_UP);
                    return value;
                } catch (NumberFormatException e) {
                    return new BigDecimal("-1");
                }
            }
        });
        setValue(initialValue);
    }

    @Override
    public void decrement(int steps) {
        BigDecimal newValue = getValue().subtract(step.multiply(BigDecimal.valueOf(steps)));
        if (newValue.compareTo(min) < 0)
            newValue = min;
        setValue(newValue);
    }

    @Override
    public void increment(int steps) {
        BigDecimal newValue = getValue().add(step.multiply(BigDecimal.valueOf(steps)));
        if (newValue.compareTo(max) > 0)
            newValue = max;
        setValue(newValue);
    }
    
}
