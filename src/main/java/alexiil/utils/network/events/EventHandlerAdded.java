package alexiil.utils.network.events;

import alexiil.utils.event.Event;
import alexiil.utils.network.Handler;

public class EventHandlerAdded extends Event
	{
		public final Handler	handler;

		public EventHandlerAdded(Handler h)
			{
				handler = h;
			}

		@Override
		public void onFinish()
			{

			}
	}
