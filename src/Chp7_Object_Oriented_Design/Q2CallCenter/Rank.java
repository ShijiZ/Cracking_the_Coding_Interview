package Chp7_Object_Oriented_Design.Q2CallCenter;

public enum Rank {
    Responder(0),
    Manager(1),
    Director(2);

    private int value;

    private Rank(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }
}
