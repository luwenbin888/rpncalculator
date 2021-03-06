package com.airwallex.rpncalculator.operators;

import java.math.BigDecimal;

public class MultiplyOperator extends AbstractOperator {

    @Override
    public BigDecimal execute(BigDecimal... operands) {
        BigDecimal op1 = operands[0];
        BigDecimal op2 = operands[1];
        return op1.multiply(op2);
    }

    @Override
    public int operandCount() {
        return 2;
    }
}
