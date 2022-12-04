package statemachine;

import state.AbstractState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachine implements Runnable{
    private List<AbstractState> states = new ArrayList<>();
    private Map<AbstractState, Map<String, AbstractState>> transitions = new HashMap<>();
    private AbstractState initState;
    private AbstractState finalState;

    public StateMachine(AbstractState initState, AbstractState finalState) {
        this.initState = initState;
        this.finalState = finalState;
    }

    public boolean addState(AbstractState state) {
        return this.states.add(state);
    }

    public void addTransition(AbstractState fromState, AbstractState toStateOnSuccess, AbstractState toStateOnFailure) {
        HashMap<String, AbstractState> perStateTransition = new HashMap<>();
        perStateTransition.put("success", toStateOnSuccess);
        perStateTransition.put("failure", toStateOnFailure);
        this.transitions.put(fromState, perStateTransition);
    }

    public AbstractState getNextState(AbstractState state, boolean outcome) {
        if (outcome) {
            return this.transitions.get(state).get("success");
        } else {
            return this.transitions.get(state).get("failure");
        }
    }

    public void run() {
        AbstractState state = this.initState;
        System.out.println(String.format("%s Starting with %s", Thread.currentThread().getName(),state.getState()));
        while (true) {
            Boolean result = state.doJob();
            if (state.getState().equals(this.finalState.getState())) {
                System.out.println(String.format("%s Reached final state...", Thread.currentThread().getName()));
                return;
            }
            state = this.getNextState(state, result);
            System.out.println(String.format("%s Transitioning to %s",Thread.currentThread().getName(), state.getState()));
        }
    }
}
