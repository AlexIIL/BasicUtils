package alexiil.utils.render.glcompat;

public interface IWindow<RCL extends IRenderCallList> {
    public void open(int width, int height);

    public void close();

    public void renderCallList(RCL list);

    /**
     * Set the runnable object that will be called every rendering tick. Render off the call list via the
     * {@link #renderCallList(IRenderCallList)} method
     */
    public void setRenderer(Runnable renderer);
}
