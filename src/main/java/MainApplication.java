import tasks.task1.Task1State1;
import statemachine.StateMachine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Welcome!");
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Task1State1 state1 = new Task1State1("initstate");
        Task1State1 state2 = new Task1State1("mainstate");
        Task1State1 state3 = new Task1State1("finalstate");

        for(int i=0; i<3; i++) {
            StateMachine sm = new StateMachine(state1, state3);
            sm.addState(state1);
            sm.addState(state2);
            sm.addState(state3);
            sm.addTransition(state1, state2, state3);
            sm.addTransition(state2, state3, state3);
            executorService.submit(sm);
        }
    }
}

