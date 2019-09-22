package com.airwallex.rpncalculator.operators;

import java.util.HashMap;
import java.util.Map;

public class Operators {

    public static final String ADD = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String SQRT = "sqrt";
    public static final String CLEAR = "clear";
    public static final String UNDO = "undo";

    private static Map<String, Operator> validOperators = new HashMap<>();

    static {
        validOperators.put(ADD, new AddOperator());
        validOperators.put(MINUS, new MinusOperator());
        validOperators.put(MULTIPLY, new MultiplyOperator());
        validOperators.put(DIVIDE, new DivideOperator());
        validOperators.put(SQRT, new SqrtOperator());
        validOperators.put(CLEAR, new ClearOperator());
        validOperators.put(UNDO, new UndoOperator());
    }

    /**
     * Factory strategy method to get Operator from a operator str
     * @param token
     * @return
     */
    public static Operator getOperator(String token) {
        return validOperators.get(token);
    }
}
