package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.data.UndoData;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Abstract class for all numeric operators.
 *
 * Any concrete operator class should override:
 * 1) operandCount to indicate how many operands involved
 * 2) execute to do real operation
 */
public abstract class AbstractOperator implements Operator{
    abstract BigDecimal execute(BigDecimal... operands);
    abstract int operandCount();

    @Override
    public boolean execute(Stack<BigDecimal> operands, Stack<UndoData> undoOperands) {
        int operandCount = operandCount();
        if (operands.size() < operandCount) return false;

        BigDecimal[] stepOperands = new BigDecimal[operandCount];
        for (int i = 0; i < operandCount; i++) stepOperands[i] = operands.pop();

        UndoData undoData = UndoData.of(stepOperands);
        undoOperands.push(undoData);

        BigDecimal result = execute(stepOperands);
        if (result != null) operands.push(result);

        return true;
    }
}
