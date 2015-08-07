package alexiil.utils.gui;

import alexiil.utils.input.AKeyEvent;
import alexiil.utils.input.IMouseEvent;
import alexiil.utils.render.window.IRenderCallList;

public abstract class Gui {
    public abstract void render(IRenderCallList list);

    public void onMouse(IMouseEvent event) {}

    public void onType(AKeyEvent event) {}
}
