package Q2_Social_Network;

import java.util.HashMap;

public class Machine {
    public HashMap<Integer, Person> persons = new HashMap<>();
    public int machineID;

    public Machine(int ID){
        machineID = ID;
    }

    public void addPerson(int personID, Person p){
        persons.put(personID, p);
    }

    public Person getPersonWithID(int personID){
        return persons.get(personID);
    }
}
