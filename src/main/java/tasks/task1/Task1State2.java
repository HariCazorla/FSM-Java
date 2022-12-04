package tasks.task1;

import state.AbstractState;

public class Task1State2 extends AbstractState {
    public Task1State2(String stateName) {
        super(stateName);
    }

    @Override
    public Boolean doJob() {
        System.out.println(String.format("%s state::%s completed successfully...", Thread.currentThread().getName(), getState()));
        return true;
    }
}
