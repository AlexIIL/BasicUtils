package alexiil.utils.event;

import java.util.concurrent.Executors;
import alexiil.utils.logger.Logger;
import com.google.common.eventbus.AsyncEventBus;

public class EventManager {
    private static AsyncEventBus normalBus = new AsyncEventBus(Executors.newCachedThreadPool());
    private static Logger log = new Logger("eventBus");
    
    public static void postEvent(Event e) {
        if (e == null)
            return;
        normalBus.post(e);
        log.fine("Fired off an event (" + e.getClass().getName() + ")");
        e.onFinish();
    }
    
    public static void addListner(Object o) {
        normalBus.register(o);
    }
}