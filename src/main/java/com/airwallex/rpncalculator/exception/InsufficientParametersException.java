package com.airwallex.rpncalculator.exception;

public class InsufficientParametersException extends Exception {
    private int position;
    private String operator;
    private static String exceptionMsgFormat = "operator %s (position: %s): insufficient parameters";

    public InsufficientParametersException(int position, String operator) {
        super(String.format(exceptionMsgFormat, operator, position));
    }

    public int getPosition() {
        return position;
    }

    public String getOperator() {
        return operator;
    }
}
