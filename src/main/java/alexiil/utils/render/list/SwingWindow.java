package alexiil.utils.render.list;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import alexiil.utils.render.glcompat.IWindow;

public class SwingWindow implements IWindow<SwingCallList> {
    private JFrame frame;
    private JPanel outer, inner;
    private Graphics2D graphics;
    private Runnable render;
    private volatile boolean open = false;

    @Override
    public void open(int width, int height, String title) {
        EventQueue.invokeLater(() -> {
            frame = new JFrame();
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
            }, "Swing-Renderer").start();
        });
    }

    public void setRenderer(Runnable render) {
        this.render = render;
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public void renderCallList(SwingCallList list) {
        list.render(graphics);
    }

    @Override
    public void makeMain() {

    }
}
