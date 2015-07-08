package alexiil.utils.event;
public abstract class Event {
    /**
     * This is when the event has terminated, to do either a default action, or to do nothing.
     */
    public abstract void onFinish();
    
    public void post() {
        EventManager.postEvent(this);
    }
}