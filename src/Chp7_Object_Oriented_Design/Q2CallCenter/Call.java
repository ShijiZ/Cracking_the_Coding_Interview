package Chp7_Object_Oriented_Design.Q2CallCenter;

public class Call {
    private Rank rank = Rank.Responder;   // Minimal rank of employee who can handle this call.
    private Caller caller;    // Person who is calling.
    private Employee handler = null; // Employee who is handling call
    private boolean solved = false;

    public Call(Caller c){
        caller = c;
    }

    public Rank getRank(){
        return rank;
    }

    public void setRank(Rank r){
        rank = r;
    }

    public Rank incrementRank(){
        if (rank == Rank.Responder)
            setRank(Rank.Manager);
        else if (rank == Rank.Manager)
            setRank(Rank.Director);
        return rank;
    }

    public Caller getCaller(){
        return caller;
    }

    public Employee getHandler() {
        return handler;
    }

    /* Set employee who is handling call */
    public void setHandler(Employee e){
        handler = e;
        rank = handler.getRank();
    }

    public boolean isSolved() {
        return solved;
    }

    public void process(){
        if (handler.solvedIssue()){
            System.out.println(caller + " issue solved by " + handler);
            System.out.println();
            handler.callCompleted();
            solved = true;
        } else {
            System.out.println(caller + " issue wasn't solved by " + handler);
            handler.escalateAndReassign();
            solved = false;
        }
    }



}
