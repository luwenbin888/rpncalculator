package com.airwallex.rpncalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RPNCalculatorApplication {

    public static void main(String[] args) throws IOException {
        RPNCalculator rpnCalculator = RPNCalculator.getInstance();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        try {
            while (!line.equals("")) {
                rpnCalculator.calculate(line);
                rpnCalculator.printStack();

                line = br.readLine();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            rpnCalculator.printStack();
        }
    }
}
