package Chp1_Arrays_and_Strings;

import CtCILibrary.AssortedMethods;

public class Q8ZeroMatrixV1 {
    public static void setZeros(int[][] matrix){
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        // Store the row and column index with value 0
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        // Nullify rows
        for (int i=0; i<row.length; i++)
            if (row[i])
                nullifyRow(matrix, i);

        // Nullify columns
        for (int j=0; j<column.length; j++)
            if (column[j])
                nullifyColumn(matrix, j);
    }

    private static void nullifyRow(int[][] matrix, int row){
        for (int j=0; j<matrix[0].length; j++)
            matrix[row][j] = 0;
    }

    private static void nullifyColumn(int[][] matrix, int col){
        for (int i=0; i<matrix.length; i++)
            matrix[i][col] = 0;
    }

    public static void main(String[] args) {
        int nrows = 10;
        int ncols = 15;
        int[][] matrix = AssortedMethods.randomMatrix(nrows, ncols, -10, 10);
        AssortedMethods.printMatrix(matrix);
        setZeros(matrix);
        System.out.println();
        AssortedMethods.printMatrix(matrix);
    }
}