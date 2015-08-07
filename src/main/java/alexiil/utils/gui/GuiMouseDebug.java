package alexiil.utils.gui;

import java.awt.Color;

import alexiil.utils.input.IMouseEvent;
import alexiil.utils.render.window.IRenderCallList;

public class GuiMouseDebug extends Gui {
    private static final int SIZE = 20;
    private int x, y;
    private int xStart, yStart;

    @Override
    public void render(IRenderCallList list) {
        double[][] crosshair = { { 0, -SIZE }, { 0, 0 }, { SIZE, 0 }, { 0, 0 }, { 0, SIZE }, { 0, 0 }, { -SIZE, 0 }, { 0, 0 } };

        list.colour(Color.GREEN);
        list.pushState();
        list.offset(x, y);
        list.line(crosshair);
        list.popState();

        if (xStart != x || yStart != y) {
            list.colour(Color.BLUE);
            list.pushState();
            list.offset(xStart, yStart);
            list.line(crosshair);
            list.popState();

            list.colour(Color.MAGENTA);
            list.line(new double[][] { { xStart, yStart }, { x, y } });
        }
    }

    @Override
    public void onMouse(IMouseEvent event) {
        x = event.getX();
        y = event.getY();

        xStart = event.getXStart();
        yStart = event.getYStart();
    }
}
