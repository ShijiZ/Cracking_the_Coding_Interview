package Q2_Social_Network;

import java.util.HashMap;

public class Server {
    HashMap<Integer, Machine> machines = new HashMap<>();
    HashMap<Integer, Integer> personToMachineMap = new HashMap<>();

    public Server(){}

    public void addMachine(int machineId, Machine m){
        machines.put(machineId, m);
    }

    private Machine getMachineWithId(int machineID){
        return machines.get(machineID);
    }

    public int getMachineIDForUser(int personID){
        Integer machineID = personToMachineMap.get(personID);
        return machineID == null ? -1 : machineID;
    }

    public void addPersonToMachine(int personId, Person p, int machineId){
        Machine m = getMachineWithId(machineId);
        m.addPerson(personId, p);
        personToMachineMap.put(personId, machineId);
    }

    public Person getPersonWithID(int personID){
        Integer machineID = getMachineIDForUser(personID);
        if (machineID == null) return null;

        Machine machine = getMachineWithId(machineID);
        if (machine == null) return null;

        return machine.getPersonWithID(personID);
    }
}
