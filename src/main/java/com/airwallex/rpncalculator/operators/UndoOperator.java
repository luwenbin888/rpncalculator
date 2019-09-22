package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.data.UndoData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class UndoOperator implements Operator {

    @Override
    public boolean execute(Stack<BigDecimal> operands, Stack<UndoData> undoOperands) {
        if (operands.size() > 0) {
            operands.pop();
            UndoData undoData = undoOperands.pop();
            List<BigDecimal> previousOperands = undoData.get();

            for (int i = previousOperands.size() - 1; i >= 0; i--) {
                operands.push(previousOperands.get(i));
            }
        }

        return true;
    }
}
