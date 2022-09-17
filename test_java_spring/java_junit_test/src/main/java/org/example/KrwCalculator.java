package org.example;

public class KrwCalculator implements ICalculate{

    private int price = 1;
    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x+y;
    }

    @Override
    public int sub(int x, int y) {
        x *= price;
        y *= price;
        return x-y;
    }
}
