import java.util.ArrayList;
import java.util.HashMap;

public class Q7BuildOrderV1 {
    /* Find a correct build order */
    public static Project[] findBuildOrder(String[] projects, String[][] dependencies){
        Q7Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    /* Build the graph, adding the edge (a, b) if b is dependent on a. Assumes a pair
    * is listed in "build order". The pair (a, b) in dependencies indicates that b
    * depends on a and a must be built before b. */
    public static Q7Graph buildGraph(String[] projects, String[][] dependencies){
        Q7Graph graph = new Q7Graph();
        for (String project : projects)
            graph.getOrCreateNode(project);

        for (String[] dependency : dependencies){
            String first = dependency[0];
            String second = dependency[1];
            graph.addEdge(first, second);
        }
        return graph;
    }

    /* Return a list of the projects a correct build order. */
    public static Project[] orderProjects(ArrayList<Project> projects){
        Project[] order = new Project[projects.size()];

        /* Add roots to the build order first. */
        int endOfList = addNonDependent(order, projects, 0);

        int toBeProcessed = 0;
        while (toBeProcessed < order.length){
            Project current = order[toBeProcessed];

            /* We have a circular dependency since there are no remaining projects with
            * zero dependencies. */
            if (current == null)
                return null;

            /* Remove my self as a dependency. */
            ArrayList<Project> children = current.getChildren();
            for (Project child : children)
                child.decrementDependencies();

            /* Add children that have no one depending on them. */
            endOfList = addNonDependent(order, children, endOfList);
            toBeProcessed++;
        }
        return order;
    }

    /* A helper function to insert projects with zero dependencies into the order
    * array, starting at index offset. */
    public static int addNonDependent(Project[] order, ArrayList<Project> projects, int offset){
        for (Project project : projects){
            if (project.getNumberDependencies() == 0){
                order[offset] = project;
                offset++;
            }
        }
        return offset;
    }

    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[][] dependencies = {
                {"a", "b"},
                {"b", "c"},
                {"a", "c"},
                {"d", "e"},
                {"b", "d"},
                {"e", "f"},
                {"a", "f"},
                {"h", "i"},
                {"h", "j"},
                {"i", "j"},
                {"g", "j"}};
        Project[] buildOrder = findBuildOrder(projects, dependencies);
        String[] buildOrderString = new String[buildOrder.length];
        for (int i = 0; i < buildOrder.length; i++) {
            buildOrderString[i] = buildOrder[i].getName();
        }
        if (buildOrder == null) {
            System.out.println("Circular Dependency.");
        } else {
            for (String s : buildOrderString) {
                System.out.println(s);
            }
        }
    }
}

class Q7Graph{
    private ArrayList<Project> nodes = new ArrayList<>();
    private HashMap<String, Project> map = new HashMap<>();

    public Project getOrCreateNode(String name){
        if (!map.containsKey(name)){
            Project node = new Project(name);
            nodes.add(node);
            map.put(name, node);
        }
        return map.get(name);
    }

    public void addEdge(String startName, String endName){
        Project start = getOrCreateNode(startName);
        Project end = getOrCreateNode(endName);
        start.addNeighbor(end);
    }

    public ArrayList<Project> getNodes(){
        return nodes;
    }
}


class Project{
    private ArrayList<Project> children = new ArrayList<>();
    private HashMap<String, Project> map = new HashMap<>();
    private String name;
    private int dependency = 0;

    public Project(String n){
        this.name = n;
    }

    public void addNeighbor(Project node){
        if (!map.containsKey(node.getName())){
            children.add(node);
            map.put(node.getName(), node);
            node.incrementDependencies();
        }
    }

    public void incrementDependencies(){
        this.dependency++;
    }

    public void decrementDependencies(){
        this.dependency--;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Project> getChildren(){
        return children;
    }

    public int getNumberDependencies(){
        return dependency;
    }
}