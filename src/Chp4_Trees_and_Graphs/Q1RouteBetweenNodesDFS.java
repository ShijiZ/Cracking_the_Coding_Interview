package Chp4_Trees_and_Graphs;

import java.util.ArrayList;

public class Q1RouteBetweenNodesDFS {
    public static boolean search(Node start, Node end){
        boolean found = false;
        start.visited = true;

        for (Node u : start.getAdjacent()) {
            if (!u.visited) {
                if (u == end){
                    return true;
                } else {
                    found = search(u, end);
                }
            }
        }
        return found;
    }

    public static void main(String[] args){
        Graph g = new Graph();
        Node[] temp = new Node[6];

        temp[0] = new Node("a");
        temp[1] = new Node("b");
        temp[2] = new Node("c");
        temp[3] = new Node("d");
        temp[4] = new Node("e");
        temp[5] = new Node("f");

        temp[0].addAdjacent(temp[1]);
        temp[0].addAdjacent(temp[2]);
        temp[0].addAdjacent(temp[3]);
        temp[3].addAdjacent(temp[4]);
        temp[4].addAdjacent(temp[5]);
        for (int i = 0; i < 6; i++) {
            g.addNode(temp[i]);
        }

        ArrayList<Node> n = g.getNodes();
        Node start = n.get(3);
        Node end = n.get(5);
        if (search(start, end)) {
            System.out.println("Route exists from " + start.getValue() + " to " + end.getValue());
        }
        else {
            System.out.println("Route doesn't exist from " + start.getValue() + " to " + end.getValue());
        }
    }
}