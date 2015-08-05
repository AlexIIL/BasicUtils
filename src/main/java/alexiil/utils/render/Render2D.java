package alexiil.utils.render;

import java.util.ArrayList;

import alexiil.utils.render.window.IRenderCallList;

public class Render2D {
    public static void add(IRenderCallList list, IRenderable render) {
        Modal modal = render.getModal();
        list.pushState();
        list.rotate(render.getAngle());
        modal.addRenders(list);
        list.popState();
    }

    public static void addAll(IRenderCallList list, ArrayList<IRenderable> renders) {
        for (IRenderable render : renders) {
            add(list, render);
        }
    }
}
