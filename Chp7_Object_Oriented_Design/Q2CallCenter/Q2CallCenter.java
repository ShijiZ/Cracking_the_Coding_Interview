package Q2CallCenter;

import java.util.LinkedList;

public class Q2CallCenter {
    public static void main(String[] args){
        CallHandler ch = new CallHandler();    // Initialize call handler
        Caller[] callers = new Caller[20];      // Initialize an array of callers
        LinkedList<Call> calls = new LinkedList<Call>();   // LinkedList for calls

        for (int i=0; i<callers.length; i++){
            callers[i] = new Caller(i);
            Call call = ch.dispachCall(callers[i]);
            calls.add(call);
        }

        while (!calls.isEmpty()){
            Call call = calls.remove();
            if (call.getHandler() != null){  // If this call is being answered
                call.process();
            }
            if (!call.isSolved())      // If call is not solved, put back to LinkedList
                calls.add(call);
        }
    }
}
