package com.itschoolhackaton.mathglass.equation;

public class SquareEquation {
    private int a;
    private int b;
    private int c;

    public void generate() {
        a = (int) ((Math.random() + 1) * 10);
        b = (int) (Math.random() * 10);
        c = (int) (Math.random() * 10);
    }

    @Override
    public String toString() {
        if (b != 0 && c != 0) {
            return a + "x^2+" + b + "x+" + c + " = 0";
        } else if (b != 0) {
            return a + "x^2+" + b + "x=0";
        } else if (c != 0) {
            return a + "x^2" + c + "=0";
        } else {
            return a + "x^2=0";
        }
    }

    public double[] decision() {
        double x1 = 0, x2 = 0;
        if (b != 0 && c != 0) {
            x1 = -b + Math.pow((Math.pow(b, 2) - 4 * a * c), 0.5);
            x2 = -b - Math.pow((Math.pow(b, 2) - 4 * a * c), 0.5);
        } else if (b != 0) {
            x1 = 0;
            x2 = -(b / a);
        } else if (c != 0) {
            x1 = Math.pow((-c / a), 0.5);
            x2 = -Math.pow((-c / a), 0.5);
        } else {
            x1 = 0;
            x2 = 0;
        }
        return new double[] {x1, x2};
    }
}
