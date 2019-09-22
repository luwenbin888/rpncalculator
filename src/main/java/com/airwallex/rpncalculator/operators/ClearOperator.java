package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.data.UndoData;

import java.math.BigDecimal;
import java.util.Stack;

public class ClearOperator implements Operator {

    @Override
    public boolean execute(Stack<BigDecimal> operands, Stack<UndoData> undoOperands) {
        operands.clear();
        undoOperands.clear();
        return true;
    }
}
