package com.itschoolhackaton.mathglass.equation;

public class LogarithmicEquation {
        private int a;
        private int b;
        private int c;

        void generate() {
            a = (int)(2 + Math.random() * 100);
            b = (int)(2 + Math.random() * 100);
            c = (int)(Math.random() * 100);
        }

        double solve(){
            double x = Math.pow(a, b) -  c;
            return(x);
        }
}
