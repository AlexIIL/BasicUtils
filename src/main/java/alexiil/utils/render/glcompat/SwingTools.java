package alexiil.utils.render.glcompat;

import alexiil.utils.render.list.SwingCallList;
import alexiil.utils.render.list.SwingWindow;

/** Used for interfacing with BasicLwjgl if it exists. */
public class SwingTools {
    public static SwingTools instance = new SwingTools();

    public IWindow<? extends IRenderCallList> makeNewWindow() {
        return new SwingWindow();
    }

    public IRenderCallList makeNewCallList() {
        return new SwingCallList();
    }
}
