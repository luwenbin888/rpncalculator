package com.airwallex.rpncalculator.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Used to hold the RPNCalculator operators in a single calculation.
 */
public class UndoData {
    private List<BigDecimal> data = new ArrayList<>(3);

    private UndoData() {}

    public static UndoData of(BigDecimal... operands) {

        UndoData undoData = new UndoData();
        for (BigDecimal operand: operands) undoData.data.add(operand);

        return undoData;
    }

    public List<BigDecimal> get() {
        return Collections.unmodifiableList(data);
    }
}
