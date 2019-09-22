package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.data.UndoData;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Top interface for an operator
 */
@FunctionalInterface
public interface Operator {
    boolean execute(Stack<BigDecimal> operands, Stack<UndoData> undoOperands);
}
