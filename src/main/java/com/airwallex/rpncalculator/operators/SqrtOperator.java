package com.airwallex.rpncalculator.operators;

import java.math.BigDecimal;

public class SqrtOperator extends AbstractOperator {

    /**
     * Below code is modified from Internet
     * @param operands
     * @return
     */
    @Override
    public BigDecimal execute(BigDecimal... operands) {
        BigDecimal op = operands[0];

        int scale = 50;

        BigDecimal x = BigDecimal.valueOf(Math.sqrt(op.doubleValue()));
        if(scale < 17){
            x = x.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        } else {

            BigDecimal b2 = new BigDecimal(2);
            for(int tempScale = 16; tempScale < scale; tempScale *= 2){
                x = x.subtract(
                        x.multiply(x).subtract(op).divide(
                                x.multiply(b2), scale, BigDecimal.ROUND_HALF_EVEN));
            }
        }

        return x;
    }

    @Override
    public int operandCount() {
        return 1;
    }
}
