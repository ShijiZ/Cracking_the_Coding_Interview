package Chp7_Object_Oriented_Design.Q2CallCenter;

public class Director extends Employee {
    public Director(CallHandler callHandler, String id){
        super(callHandler, id);
        rank = Rank.Director;
    }
}
