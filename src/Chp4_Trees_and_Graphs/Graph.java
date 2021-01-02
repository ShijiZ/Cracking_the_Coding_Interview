package Chp4_Trees_and_Graphs;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> nodes;

    public Graph(){
        nodes = new ArrayList<>();
    }

    public void addNode(Node x){
        nodes.add(x);
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }
}

class Node {
    private String value;
    private ArrayList<Node> adjacent;
    public boolean visited;

    public Node(String value){
        this.value = value;
        this.adjacent = new ArrayList<>();
    }

    public void addAdjacent(Node x){
        adjacent.add(x);

        // Uncomment if undirected graph
        //x.adjacent.add(this);
    }

    public String getValue(){
        return value;
    }

    public ArrayList<Node> getAdjacent(){
        return adjacent;
    }
}