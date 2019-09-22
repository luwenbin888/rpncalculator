package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.exception.InsufficientParametersException;
import com.airwallex.rpncalculator.exception.RPNCalculatorException;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class SmokeTest {

    @Test
    public void someTest() throws InsufficientParametersException, RPNCalculatorException {

        RPNCalculator calculator = RPNCalculator.getInstance();

        String[] steps = {"3 5 +", "50 30 + 9 16 + +"};
        BigDecimal[] results = {new BigDecimal(8.0), new BigDecimal(105.0)};

        for (int i = 0; i < steps.length; i++) {
            calculator.calculate(steps[i]);
            assertEquals("Value should be same",
                    calculator.operandPeek(), results[i]);
        }

        calculator.clean();
    }

    @Test
    public void undoTest() throws InsufficientParametersException, RPNCalculatorException {

        RPNCalculator calculator = RPNCalculator.getInstance();

        calculator.calculate("5 6 7 8 undo undo");
        assertEquals(calculator.operandPeek(), new BigDecimal(6.0));

        calculator.calculate("+ undo");
        assertEquals(calculator.operandPeek(), new BigDecimal(6.0));

        calculator.calculate("7 8 *");
        assertEquals(calculator.operandPeek(), new BigDecimal(56.0));
    }
}
