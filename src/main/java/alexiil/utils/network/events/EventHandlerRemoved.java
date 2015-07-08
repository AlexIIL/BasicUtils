package alexiil.utils.network.events;

import alexiil.utils.event.Event;
import alexiil.utils.network.Handler;

public class EventHandlerRemoved extends Event
	{
		public final Handler	handler;

		public EventHandlerRemoved(Handler h)
			{
				handler = h;
			}

		@Override
		public void onFinish()
			{

			}
	}
