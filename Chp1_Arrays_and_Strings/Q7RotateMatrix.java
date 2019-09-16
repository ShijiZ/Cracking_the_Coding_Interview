import CtCILibrary.AssortedMethods;

public class Q7RotateMatrix {
    public static boolean rotate(int[][] matrix){
        if (matrix.length == 0 || matrix.length != matrix[0].length)
            return false;
        int n = matrix.length;
        for (int layer = 0; layer < n/2; layer++){
            int first = layer;
            int last = n-1-layer;
            for (int i=first; i<last; i++){
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[n-1-i][first];

                // bottom -> left
                matrix[n-1-i][first] = matrix[last][n-1-i];

                // right -> bottom
                matrix[last][n-1-i] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
        AssortedMethods.printMatrix(matrix);
        rotate(matrix);
        System.out.println();
        AssortedMethods.printMatrix(matrix);
    }
}
