package Q2_Social_Network;

import java.util.ArrayList;

public class Person {
    private ArrayList<Integer> friends = new ArrayList<>();
    private int personID;
    private String info;

    public Person(int id){
        this.personID = id;
    }

    public String getInfo(){
        return info;
    }

    public void setInfo(String info){
        this.info = info;
    }

    public ArrayList<Integer> getFriends(){
        return friends;
    }

    public int getID(){
        return personID;
    }

    public void addFriend(int id){
        friends.add(id);
    }
}
