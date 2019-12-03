package Q2CallCenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CallHandler {
    /* 3 levels of employees: respondents, managers, directors */
    private final int LEVELS = 3;

    /* Initialize 10 respondents, 4 managers, and 2 directors */
    private final int NUM_RESPONDENTS = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;

    /* List of employees, by level.
    * employeeLevels[0] = respondents
    * employeeLevels[1] = managers
    * employeeLevels[2] = directors
    */
    List<List<Employee>> employeeLevels;

    /* Queues for each call's rank */
    List<List<Call>> callQueues;

    public CallHandler(){
        employeeLevels = new ArrayList<List<Employee>>(LEVELS);
        callQueues = new ArrayList<List<Call>>(LEVELS);

        // Create respondents
        ArrayList<Employee> respondents = new ArrayList<Employee>(NUM_RESPONDENTS);
        for (int k=0; k<NUM_RESPONDENTS; k++)
            respondents.add(new Respondent(this, ""+k));
        employeeLevels.add(respondents);

        // Create managers
        ArrayList<Employee> managers = new ArrayList<Employee>(NUM_MANAGERS);
        for (int k=0; k<NUM_MANAGERS; k++)
            managers.add(new Manager(this, ""+k));
        employeeLevels.add(managers);

        // Create directors
        ArrayList<Employee> directors = new ArrayList<Employee>(NUM_DIRECTORS);
        for (int k=0; k<NUM_DIRECTORS; k++)
            directors.add(new Director(this, ""+k));
        employeeLevels.add(directors);

        for (int levels=0; levels<LEVELS; levels++){
            callQueues.add(new LinkedList<Call>());
        }
    }

    /* Gets the first available employee who can handle this call. */
    public Employee getHandlerForCall(Call call){
        for (int level = call.getRank().getValue(); level < LEVELS; level++){
            List<Employee> employLevel = employeeLevels.get(level);
            for (Employee emp : employLevel)
                if (emp.isFree())
                    return emp;
        }
        return null;
    }

    /* Routes the call to an available employee, or saves in a queue if no employee
    * is available. */
    public Call dispachCall(Caller caller){
        Call call = new Call(caller);
        dispachCall(call);
        return call;
    }

    /* Routes the call to an available employee, or saves in a queue if no employee
    * is available. */
    public void dispachCall(Call call){
        /* Try to route the call to an employee with minimal rank */
        Employee emp = getHandlerForCall(call);
        if (emp != null && emp.isFree()){
            System.out.println(call.getCaller() + " is answered by " + emp);
            emp.receiveCall(call);
            call.setHandler(emp);
        } else {
            /* Place the call into corresponding call queue according to its rank */
            System.out.println(call.getCaller() + " is waiting for reply");
            callQueues.get(call.getRank().getValue()).add(call);
        }
    }

    /* An employee got free. Look for a waiting call that employee can serve. Return
     true if we assigned a call, false otherwise. */
    public void assignCall(Employee emp){
        // Check the queues, starting from the highest rank this employee can serve
        for (int rank = emp.getRank().getValue(); rank >= 0; rank--){
            List<Call> que = callQueues.get(rank);

            // Remove the first call, if any
            if (que.size() > 0){
                Call call = que.remove(0);
                if (call != null){
                    System.out.println(call.getCaller() + " is answered by " + emp);
                    emp.receiveCall(call);
                    call.setHandler(emp);
                    return;
                }
            }
        }
        return;
    }
}
