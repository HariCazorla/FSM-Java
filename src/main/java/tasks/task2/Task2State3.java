package tasks.task2;

import state.AbstractState;

public class Task2State3 extends AbstractState {
    public Task2State3(String stateName) {
        super(stateName);
    }

    @Override
    public Boolean doJob() {
        System.out.println(String.format("%s state::%s completed successfully...",Thread.currentThread().getName(), getState()));
        return true;
    }
}
