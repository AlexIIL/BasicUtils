package alexiil.utils.render.window;

public interface IWindow<RCL extends IRenderCallList> {
            void open(int width, int height, String title);

    void close();

    void renderCallList(RCL list);

    /**
     * Set the runnable object that will be called every rendering tick. Render off the call list via the
     * {@link #renderCallList(IRenderCallList)} method
     */
            void setRenderer(Runnable renderer);

    /** Set this window to the main window- when this window is closed, the application will exit. */
            void makeMain();

    void setSize(int width, int height);

    void setFullscreen(boolean fullscreen);
}
