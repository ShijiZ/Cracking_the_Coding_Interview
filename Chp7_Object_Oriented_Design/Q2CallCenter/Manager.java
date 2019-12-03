package Q2CallCenter;

public class Manager extends Employee{
    public Manager(CallHandler callHandler, String id){
        super(callHandler, id);
        rank = Rank.Manager;
    }
}
