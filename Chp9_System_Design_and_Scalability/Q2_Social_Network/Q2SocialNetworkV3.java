package Q2_Social_Network;

import java.util.*;

public class Q2SocialNetworkV3 {
    public static LinkedList<Person> findPathBFS(Server s, int source, int destination){
        Queue<PathNode> toVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<Integer>();
        toVisit.add(new PathNode(s.getPersonWithID(source), null));
        visited.add(source);

        while (!toVisit.isEmpty()){
            PathNode node = toVisit.poll();
            Person person = node.getPerson();

            // node contains destination, return the path from source node to node
            if (person.getID() == destination)
                return node.collapse(false);

            /* Search friends. */
            ArrayList<Integer> friends = person.getFriends();
            for (int friendId : friends){
                if (!visited.contains(friendId)){
                    visited.add(friendId);
                    Person friend = s.getPersonWithID(friendId);
                    toVisit.add(new PathNode(friend, node));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int nPeople = 11;
        int nMachine = 2;
        Server server = new Server();

        for (int i=0; i<nMachine; i++){
            Machine m = new Machine(i);
            server.addMachine(i, m);
        }

        for (int i=0; i<6; i++){
            Person p = new Person(i);
            server.addPersonToMachine(i, p, 0);
        }

        for (int i=6; i<nPeople; i++){
            Person p = new Person(i);
            server.addPersonToMachine(i, p, 1);
        }

        int[][] edges = {{1, 4}, {1, 2}, {1, 3}, {3, 2}, {4, 6}, {3, 7}, {6, 9}, {9, 10}, {5, 10}, {2, 5}, {3, 7}};

        for (int[] edge : edges) {
            Person source = server.getPersonWithID(edge[0]);
            source.addFriend(edge[1]);

            Person destination = server.getPersonWithID(edge[1]);
            destination.addFriend(edge[0]);
        }

        int i = 1;
        int j = 10;
        LinkedList<Person> path = findPathBFS(server, i, j);
        if (path == null) {
            System.out.println("No path");
        } else {
            for (Person p : path) {
                System.out.println(p.getID() + " : Machine " + server.getMachineIDForUser(p.getID()));
            }
        }
    }
}
