package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.data.UndoData;
import com.airwallex.rpncalculator.exception.InsufficientParametersException;
import com.airwallex.rpncalculator.exception.RPNCalculatorException;
import com.airwallex.rpncalculator.operators.Operator;
import com.airwallex.rpncalculator.operators.Operators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Stack;

/**
 * Singleton RPNCalculator which encapsulate internal states like operand stack
 */
public class RPNCalculator {

    private Stack<BigDecimal> operands = new Stack<>();
    private Stack<UndoData> undoOperands = new Stack<>();
    private DecimalFormat df = new DecimalFormat("0.##########");

    private RPNCalculator() {
        df.setRoundingMode(RoundingMode.DOWN);
    }

    private static class RPNCalculatorHolder {
        private static final RPNCalculator calculator = new RPNCalculator();
    }

    public static RPNCalculator getInstance() {
        return RPNCalculatorHolder.calculator;
    }

    /**
     * Calculate from the command-line.
     * @param rpnExpression command-line RPN expression
     */
    public void calculate(String rpnExpression) throws InsufficientParametersException, RPNCalculatorException {
        if (rpnExpression == null || rpnExpression.trim().equals("")) return;

        String[] steps = rpnExpression.split(" ");

        int position = 1;

        for (String step: steps) {
            try {
                calculateStep(position, step);
            }
            catch (RPNCalculatorException | InsufficientParametersException ex) {
                throw ex;
            }
            position = position + step.length() + 1;
        }
    }

    /**
     * Calculate one step.
     * @param stepExpression
     */
    private void calculateStep(int position, String stepExpression) throws RPNCalculatorException, InsufficientParametersException {
        Operator operator = Operators.getOperator(stepExpression);
        if (operator == null) {
            try {
                BigDecimal operand = new BigDecimal(stepExpression);
                operands.push(operand);
                UndoData undoData = UndoData.of();
                undoOperands.push(undoData);
            }
            catch (Exception ex) {
                String errorMsg = stepExpression + " is not a valid operand";
                throw new RPNCalculatorException(errorMsg);
            }
        }
        else {
            boolean result = operator.execute(operands, undoOperands);
            if (!result) {
                throw new InsufficientParametersException(position, stepExpression);
            }
        }
    }

    /**
     * For testing purpose
     * @return
     */
    BigDecimal[] getOperands() {
        return operands.toArray(new BigDecimal[0]);
    }

    /**
     * For testing purpose
     * @return
     */
    UndoData[] getUndoData() {
        return undoOperands.toArray(new UndoData[0]);
    }

    /**
     * Prints stack content after each command-line RPN expression.
     */
    public void printStack() {
        System.out.print("stack: ");
        operands.forEach(operand -> {
            System.out.print(df.format(operand) + " ");
        });
        System.out.println();
    }

    /**
     * Testing
     */
    String getPrintStackMessage() {
        StringBuilder result = new StringBuilder();
        result.append("stack: ");
        operands.forEach(operand -> {
            result.append(df.format(operand) + " ");
        });

        return result.toString();
    }

    void clean() {
        operands.clear();
        undoOperands.clear();
    }

    BigDecimal operandPeek() {
        return operands.peek();
    }
}
