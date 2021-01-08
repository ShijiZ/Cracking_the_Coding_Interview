package Chp8_Recursion_and_Dynamic_Programming;

import CtCILibrary.AssortedMethods;

import java.util.ArrayList;

public class Q2RobotInAGridV1 {
    public static ArrayList<PointV1> getPath(boolean[][] maze){
        if (maze == null || maze.length == 0)
            return null;
        ArrayList<PointV1> path = new ArrayList<>();
        if (getPath(maze, maze.length-1, maze[0].length-1, path))
            return path;
        return null;
    }

    public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<PointV1> path){
        /* If out of bounds or not available, return.*/
        if (col < 0 || row < 0 || !maze[row][col])
            return false;

        /* If there's a path from the start to there, add my location. */
        if ((row == 0)&&(col == 0) ||  // is at origin
                getPath(maze, row, col-1, path) ||  // path exist to (row, col-1)
                getPath(maze, row-1, col, path)){  // path exist to (row-1, col)
            PointV1 p = new PointV1(row, col);
            path.add(p);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int size = 5;
        boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 80);

        AssortedMethods.printMatrix(maze);

        ArrayList<PointV1> path = getPath(maze);
        if (path != null) {
            System.out.println(path.toString());
        } else {
            System.out.println("No path found.");
        }
    }
}

class PointV1{
    public int row, column;

    public PointV1(int row, int column){
        this.row = row;
        this.column = column;
    }

    public String toString(){
        return "(" + row + ", " + column + ")";
    }
}
