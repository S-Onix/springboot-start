package org.example;

public class Calculator {
    private ICalculate calculate;

    public Calculator(ICalculate calculate) {
        this.calculate = calculate;
    }

    public int sum(int x, int y) {
        return this.calculate.sum(x,y);
    }

    public int sub(int x, int y) {
        return this.calculate.sub(x, y);
    }
}
