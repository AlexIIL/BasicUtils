package alexiil.utils.render.window;

public interface IWindow {

    void open(int width, int height, String title);

    void close();

    void renderCallList(IRenderCallList list);

    IRenderCallList makeCallList();

    /**
     * Set the runnable object that will be called every rendering tick. Render off the call list via the
     * {@link #renderCallList(IRenderCallList)} method. This must be called BEFORE the window is opened.
     */
            void setRenderer(Runnable renderer);

    /**
     * Set this window to the main window- when this window is closed, the application will exit. This must be called
     * AFTER the window has opened.
     */
            void makeMain();

    void setSize(int width, int height);

    void setFullscreen(boolean fullscreen);
}
