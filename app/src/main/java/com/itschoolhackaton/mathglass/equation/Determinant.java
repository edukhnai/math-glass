package com.itschoolhackaton.mathglass.equation;

public class Determinant {
    private int[][] matrix = new int[3][3];

    void randomFillMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (10 * Math.random());
            }
        }
    }

    int solveDeterminant() {
        return (matrix[1][1] * (matrix[2][2] * matrix[3][3] - matrix[2][3] * matrix[3][2]) - matrix[2][1] * (matrix[3][2] * matrix[1][3] - matrix[3][3] * matrix[1][2]) - matrix[3][1] * (matrix[1][2] * matrix[2][3] - matrix[2][2] * matrix[1][3]));
    }

    boolean comparison(int answer, int answerin) {
        return (answer == answerin);
    }
}
