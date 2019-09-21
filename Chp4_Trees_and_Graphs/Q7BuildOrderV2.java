import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Q7BuildOrderV2 {
    public static Stack<ProjectV2> findBuildOrder(String[] projects, String[][] dependencies){
        Q7GraphV2 graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    public static Q7GraphV2 buildGraph(String[] projects, String[][] dependencies){
        Q7GraphV2 graph = new Q7GraphV2();
        for (String project : projects)
            graph.getOrCreateNode(project);

        for (String[] dependency : dependencies){
            String first = dependency[0];
            String second = dependency[1];
            graph.addEdge(first, second);
        }
        return graph;
    }

    public static Stack<ProjectV2> orderProjects(ArrayList<ProjectV2> projects){
        Stack<ProjectV2> stack = new Stack<>();
        for (ProjectV2 project : projects){
            if (project.getState() == ProjectV2.State.BLANK){
                if (!doDFS(project, stack))
                    return null;
            }
        }
        return stack;
    }

    public static boolean doDFS(ProjectV2 project, Stack<ProjectV2> stack){
        if (project.getState() == ProjectV2.State.PARTIAL) // error found, pass error up
            return false;

        if (project.getState() == ProjectV2.State.BLANK){
            project.setState(ProjectV2.State.PARTIAL);
            ArrayList<ProjectV2> children = project.getChildren();
            for (ProjectV2 child : children)
                if (!doDFS(child, stack)) // receive error passed from children
                    return false;
            project.setState(ProjectV2.State.COMPLETE);
            stack.push(project);
        }
        return true;
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
        Stack<ProjectV2> buildOrder = findBuildOrder(projects, dependencies);
        String[] buildOrderString = new String[buildOrder.size()];
        for (int i = 0; i < buildOrderString.length; i++) {
            buildOrderString[i] = buildOrder.pop().getName();
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

class Q7GraphV2{
    private ArrayList<ProjectV2> nodes = new ArrayList<>();
    private HashMap<String, ProjectV2> map = new HashMap<>();

    public ProjectV2 getOrCreateNode(String name){
        if (!map.containsKey(name)){
            ProjectV2 node = new ProjectV2(name);
            nodes.add(node);
            map.put(name, node);
        }
        return map.get(name);
    }

    public void addEdge(String startName, String endName){
        ProjectV2 start = getOrCreateNode(startName);
        ProjectV2 end = getOrCreateNode(endName);
        start.addNeighbor(end);
    }

    public ArrayList<ProjectV2> getNodes(){
        return nodes;
    }
}


class ProjectV2{
    public enum State {COMPLETE, PARTIAL, BLANK};

    private ArrayList<ProjectV2> children = new ArrayList<>();
    private HashMap<String, ProjectV2> map = new HashMap<>();
    private State state = State.BLANK;
    private String name;


    public ProjectV2(String n){
        this.name = n;
    }

    public void addNeighbor(ProjectV2 node){
        if (!map.containsKey(node.getName())){
            children.add(node);
            map.put(node.getName(), node);
        }
    }

    public String getName(){
        return name;
    }

    public ArrayList<ProjectV2> getChildren(){
        return children;
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }
}
