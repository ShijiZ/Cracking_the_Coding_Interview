package Q2CallCenter;

public class Caller {
    private int userId;

    public Caller(int id){
        userId = id;
    }

    public String toString(){
        return "Customer " + userId;
    }
}
