package Chp8_Recursion_and_Dynamic_Programming;

import CtCILibrary.AssortedMethods;

import java.util.ArrayList;
import java.util.HashSet;

public class Q2RobotInAGridV2 {
    public static ArrayList<Point> getPath(boolean[][] maze){
        if (maze == null || maze.length == 0)
            return null;
        ArrayList<Point> path = new ArrayList<>();
        HashSet<Point> failedPoints = new HashSet<>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints))
            return path;
        return null;
    }

    public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path,
                                  HashSet<Point> failedPoints){
        /* If out of bounds or not available, return. */
        if (col < 0 || row < 0 || !maze[row][col])
            return false;

        Point p = new Point(row, col);

        /* If we've already visited this cell, return. */
        if (failedPoints.contains(p))
            return false;

        /* If there's a path from the start to there, add my location. */
        if ((row == 0)&&(col == 0) ||  // is at origin
                getPath(maze, row, col-1, path, failedPoints) ||  // path exist to (row, col-1)
                getPath(maze, row-1, col, path, failedPoints)){  // path exist to (row-1, col)
            path.add(p);
            return true;
        }

        failedPoints.add(p); // Cache result
        return false;
    }

    public static void main(String[] args) {
        int size = 5;
        boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 80);

        AssortedMethods.printMatrix(maze);

        ArrayList<Point> path = getPath(maze);
        if (path != null) {
            System.out.println(path.toString());
        } else {
            System.out.println("No path found.");
        }
    }
}


class Point {
    public int row, column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
