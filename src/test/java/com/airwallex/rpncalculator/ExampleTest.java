package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.exception.InsufficientParametersException;
import com.airwallex.rpncalculator.exception.RPNCalculatorException;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    RPNCalculator calculator = RPNCalculator.getInstance();

    @After
    public void cleanCalculator() {
        calculator.clean();
    }

    @Test
    public void testExample1() {

        try {
            calculator.calculate("5 2");
            BigDecimal[] operands = calculator.getOperands();
            assertEquals(operands.length, 2);
            assertEquals(operands[0], new BigDecimal("5"));
            assertEquals(operands[1], new BigDecimal("2"));

            String stackMsg = calculator.getPrintStackMessage();
            assertEquals(stackMsg.trim(), "stack: 5 2");
        } catch (Exception ex) {
            fail("");
        }
    }

    @Test
    public void testExample2() {

        try {
            String[] rpnExpressions = {"2 sqrt", "clear 9 sqrt"};
            calculator.calculate(rpnExpressions[0]);
            String msg1 = calculator.getPrintStackMessage();
            assertEquals(msg1.trim(), "stack: 1.4142135623");

            calculator.calculate(rpnExpressions[1]);
            String msg2 = calculator.getPrintStackMessage();
            assertEquals(msg2.trim(), "stack: 3");
        } catch (Exception ex) {
            fail("");
        }
    }

    @Test
    public void testExample3() {

        try {
            calculator.calculate("5 2 -");
            assertEquals(calculator.getPrintStackMessage().trim(), "stack: 3");

            calculator.calculate("3 -");
            assertEquals(calculator.getPrintStackMessage().trim(), "stack: 0");

            calculator.calculate("clear");
            assertEquals(calculator.getPrintStackMessage().trim(), "stack:");
        } catch (Exception ex) {
            fail("");
        }
    }

    @Test
    public void testExample4() throws InsufficientParametersException, RPNCalculatorException {
        calculator.calculate("5 4 3 2");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 5 4 3 2");

        calculator.calculate("undo undo *");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 20");

        calculator.calculate("5 *");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 100");

        calculator.calculate("undo");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 20 5");
    }

    @Test
    public void testExample5() throws InsufficientParametersException, RPNCalculatorException {
        calculator.calculate("7 12 2 /");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 7 6");

        calculator.calculate("*");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 42");

        calculator.calculate("4 /");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 10.5");
    }

    @Test
    public void testExample6() throws InsufficientParametersException, RPNCalculatorException {
        calculator.calculate("1 2 3 4 5");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 1 2 3 4 5");

        calculator.calculate("*");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 1 2 3 20");

        calculator.calculate("clear 3 4 -");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: -1");
    }

    @Test
    public void testExample7() throws InsufficientParametersException, RPNCalculatorException {
        calculator.calculate("1 2 3 4 5");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 1 2 3 4 5");

        calculator.calculate("* * * *");
        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 120");
    }

    @Test
    public void testExample8() throws InsufficientParametersException, RPNCalculatorException {
        try {
            calculator.calculate("1 2 3 * 5 + * * 6 5");
        } catch (InsufficientParametersException ex) {
            assertEquals(ex.getMessage().trim(), "operator * (position: 15): insufficient parameters");
        }

        assertEquals(calculator.getPrintStackMessage().trim(), "stack: 11");
    }
}
