package state;

public abstract class AbstractState {
    protected String stateName;

    protected AbstractState(String stateName) {
        this.stateName = stateName;
    }

    public String getState() {
        return stateName;
    };

    abstract public Boolean doJob();
}
