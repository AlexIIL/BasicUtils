package alexiil.utils.render.glcompat;

public interface IWindow<RCL extends IRenderCallList> {
    public void open(int width, int height, String title);

    public void close();

    public void renderCallList(RCL list);

    /**
     * Set the runnable object that will be called every rendering tick. Render off the call list via the
     * {@link #renderCallList(IRenderCallList)} method
     */
    public void setRenderer(Runnable renderer);

    /** Set this window to the main window- when this window is closed, the application will exit. */
    public void makeMain();
}
