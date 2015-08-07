package alexiil.utils.gui;

import java.awt.Color;
import java.util.function.Consumer;

import alexiil.utils.input.AKeyEvent;
import alexiil.utils.input.IMouseEvent;
import alexiil.utils.render.window.IRenderCallList;
import alexiil.utils.render.window.IWindow;

public class GuiManager {
    public boolean debugMouse = true;
    private final GuiMouseDebug guiDebugMouse = new GuiMouseDebug();
    private final IWindow window;
    private Gui currentGui;

    public GuiManager(IWindow window) {
        this.window = window;
        window.addKeyCallback(getKeyConsumer());
        window.addMouseCallback(getMouseConsumer());
        window.setRenderer(getRenderer());
    }

    public void setCurrentGui(Gui gui) {
        currentGui = gui;
    }

    public Runnable getRenderer() {
        return () -> {
            IRenderCallList list = window.makeCallList();
            if (currentGui != null) {
                currentGui.render(list);
            } else {
                int[] size = window.getSize();
                list.offset(size[0] / 2d, size[1] / 2d);
                list.colour(Color.WHITE);
                list.text("No gui! This is a bug!", 0, 0, 14, true, true);
            }
            if (debugMouse) {
                guiDebugMouse.render(list);
            }
            window.renderCallList(list);
            list.dispose();
        };
    }

    public Consumer<AKeyEvent> getKeyConsumer() {
        return (event) -> {
            if (currentGui != null) {
                currentGui.onType(event);
            }
        };
    }

    public Consumer<IMouseEvent> getMouseConsumer() {
        return (event) -> {
            guiDebugMouse.onMouse(event);
            if (currentGui != null) {
                currentGui.onMouse(event);
            }
        };
    }
}
