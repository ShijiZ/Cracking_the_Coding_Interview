package Q2CallCenter;

abstract class Employee {
    private Call currentCall;
    protected Rank rank;
    private CallHandler callHandler;
    private String empId;

    public Employee(CallHandler handler, String id){
        callHandler = handler;
        empId = id;
    }

    /* Start the conversation */
    public void receiveCall(Call call){
        currentCall = call;
    }

    /* The issue is resolved, finish the call */
    public void callCompleted(){
        if (currentCall != null){
            currentCall = null;       // Free the employee
        }

        // check if there is a call waiting in queue
        assignNewCall();
    }

    /* The issue has not been resolved. Escalate the call, and assign a new call to
    * the employee. */
    public void escalateAndReassign(){
        if (currentCall != null){
            currentCall.incrementRank();
            callHandler.dispachCall(currentCall);
            currentCall = null;     // Free the employee
        }
        // check if there is a call waiting in queue
        assignNewCall();
    }

    /* Assign a new call to an employee, if the employee is free. */
    public void assignNewCall(){
        if (!isFree())
            return;
        callHandler.assignCall(this);
    }

    /* Returns whether or not the employee is free */
    public boolean isFree(){
        return currentCall == null;
    }

    public Rank getRank(){
        return rank;
    }

    public boolean solvedIssue(){
        double solved = Math.random();
        if (rank == Rank.Responder)
            return solved > 0.5 ? true : false;
        else if (rank == Rank.Manager)
            return solved > 0.2 ? true : false;
        else
            return true;
    }

    public String toString(){
        return rank + " " + empId;
    }

}
