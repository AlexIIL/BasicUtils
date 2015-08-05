package alexiil.utils.render.window;

import alexiil.utils.render.list.SwingCallList;

/** Used for interfacing with BasicLwjgl if it exists. */
public class SwingTools implements IRenderingTools {
    public static final SwingTools instance = new SwingTools();

    @Override
    public SwingWindow makeNewWindow() {
        return new SwingWindow();
    }

    @Override
    public SwingCallList makeNewCallList() {
        return new SwingCallList();
    }
}
