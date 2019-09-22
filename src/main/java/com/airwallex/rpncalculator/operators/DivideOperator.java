package com.airwallex.rpncalculator.operators;

import java.math.BigDecimal;

public class DivideOperator extends AbstractOperator {
    @Override
    public BigDecimal execute(BigDecimal... operands) {
        BigDecimal op1 = operands[0];
        BigDecimal op2 = operands[1];
        return op2.divide(op1);
    }

    @Override
    public int operandCount() {
        return 2;
    }
}
