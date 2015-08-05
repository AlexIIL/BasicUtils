package alexiil.utils.render.window;

public interface IRenderingTools {

    IWindow<? extends IRenderCallList> makeNewWindow();

    IRenderCallList makeNewCallList();
}
