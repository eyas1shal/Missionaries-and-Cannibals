package application;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class BreadthFirstSearch {

	public State exec(State initialState) {
		if (initialState.isGoal()) {
			return initialState;
		}
		Queue<State> frontier = new LinkedList<State>();	
		Set<State> explored = new HashSet<State>();		
		frontier.add(initialState);
		while (true) {
			if (frontier.isEmpty()) {
				return null;	// failure
			}
			State state = frontier.poll();
			explored.add(state);
			List<State> successors = state.generateSuccessors();
			for (State child : successors) {
				if (!explored.contains(child) || !frontier.contains(child)) {
					if (child.isGoal()) {
						return child;
					}
					frontier.add(child);
				}
			}
		}
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
(1,3,R,2,0)
(3,3,L,0,0)
*/