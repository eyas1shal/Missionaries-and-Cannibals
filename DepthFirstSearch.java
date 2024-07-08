package application;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.Set;

public class DepthFirstSearch {

    public State exec(State initialState) {
        if (initialState.isGoal()) {
            return initialState;
        }

        Stack<State> stack = new Stack<>();
        Set<State> visited = new HashSet<>();

        stack.push(initialState);

        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            
            if (currentState.isGoal()) {
                return currentState;
            }

            visited.add(currentState);
            List<State> successors = currentState.generateSuccessors();
            for (State successor : successors) {
                if (!visited.contains(successor) && !stack.contains(successor)) {
                    stack.push(successor);                    
                }
            }
            
        }
        
        return null; // failure
    }
}
/*
(2,0,L,1,3)
(1,0,R,2,3)
(3,0,L,0,3)
(2,0,R,1,3)
(2,2,L,1,1)
(1,1,R,2,2)
(1,3,L,2,0)
(0,3,R,3,0)
(2,3,L,1,0)
(2,2,R,1,1)
(3,3,L,0,0)
 
 */
