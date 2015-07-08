package alexiil.utils.network.events;

import alexiil.utils.event.Event;

public class PingChangeEvent extends Event {
    public final long ping;
    
    public PingChangeEvent(long ping) {
        this.ping = ping;
    }
    
    @Override public void onFinish() {}
}
