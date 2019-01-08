package com.itschoolhackaton.mathglass.equation;

public class LinearEquation {
    private int k;
    private int b;
    private int y;
    private final static String X ="x";

    public void generate(){
        k = (int) (Math.random() * 100);
        if(k == 0)
            k = 5;
        b = (int) (Math.random() * 100);
        y = (int) (Math.random() * 100);
    }

    public double solve(){
        double x = (y - b)/k;
        return x;
    }
}
