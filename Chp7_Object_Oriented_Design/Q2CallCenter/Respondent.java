package Q2CallCenter;

public class Respondent extends Employee{
    public Respondent(CallHandler callHandler, String id){
        super(callHandler, id);
        rank = Rank.Responder;
    }
}
