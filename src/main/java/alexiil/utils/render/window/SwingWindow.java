package alexiil.utils.render.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import alexiil.utils.render.list.SwingCallList;

public class SwingWindow implements IWindow {
    private JFrame frame;
    private JPanel outer, inner;
    private Graphics2D graphics;
    private Runnable render;
    private volatile boolean open = false;

    @Override
    public void open(int width, int height, String title) {
        try {
            EventQueue.invokeAndWait(() -> {
                frame = new JFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setSize(width, height);
                frame.setTitle(title);

                outer = new JPanel(new BorderLayout());
                frame.setContentPane(outer);

                inner = new JPanel();
                outer.add(inner, BorderLayout.CENTER);

                frame.setVisible(true);

                Graphics g = inner.getGraphics();
                graphics = (Graphics2D) g.create();

                open = true;

                new Thread(() -> {
                    while (open) {
                        long before = System.currentTimeMillis();

                        if (render != null) {
                            render.run();
                        }

                        long diff = System.currentTimeMillis() - before;
                        if (diff < 17)
                            try {
                                Thread.sleep(17 - diff);
                            }
                            catch (InterruptedException e) {}
                    }
                    frame.dispose();
                } , "Swing-Renderer").start();
            });
        }
        catch (HeadlessException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRenderer(Runnable render) {
        this.render = render;
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public void renderCallList(IRenderCallList list) {
        ((SwingCallList) list).render(graphics);
    }

    @Override
    public SwingCallList makeCallList() {
        return new SwingCallList();
    }

    @Override
    public void makeMain() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    @Override
    public void setFullscreen(boolean fullscreen) {
        // FIXME!
    }
}
