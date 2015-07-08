package alexiil.utils.event;
/**
* This is for when an event can be canceled, e.g. a user trying to log in. This is an event that, once canceled, can never be * uncanceled.
*/
public abstract class CancellableEvent extends Event
	{
		private boolean	canceled;

		public CancellableEvent()
			{
				canceled = false;
			}

		public final void cancel()
			{
				canceled = true;
			}

		public final boolean isAllowed()
			{
				return !canceled;
			}
	}
