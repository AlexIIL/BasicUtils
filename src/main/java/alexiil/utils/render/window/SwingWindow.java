package alexiil.utils.render.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import alexiil.utils.input.AKeyEvent;
import alexiil.utils.input.AMouseEvent;
import alexiil.utils.render.list.SwingCallList;

public class SwingWindow implements IWindow, KeyListener, MouseListener {
    private JFrame frame;
    private JPanel outer, inner;
    private Graphics2D graphics;
    private Runnable render;
    private volatile boolean open = false;
    private final List<Consumer<AKeyEvent>> keyListeners = new ArrayList<>();
    private final List<Consumer<AMouseEvent>> mouseListeners = new ArrayList<>();

    @Override
    public void open(int width, int height, String title) {
        try {
            EventQueue.invokeAndWait(() -> {
                frame = new JFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setSize(width, height);
                frame.setTitle(title);
                frame.setBackground(Color.BLACK);

                outer = new JPanel(new BorderLayout());
                outer.setBackground(Color.BLACK);
                frame.setContentPane(outer);

                inner = new JPanel(new BorderLayout());
                inner.setBackground(Color.BLACK);
                outer.add(inner, BorderLayout.CENTER);

                frame.setVisible(true);

                Graphics g = inner.getGraphics();
                graphics = (Graphics2D) g.create();

                open = true;

                frame.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        graphics = (Graphics2D) inner.getGraphics().create();
                    }
                });

                frame.addKeyListener(this);
                frame.addMouseListener(this);

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
                            } catch (InterruptedException e) {}
                    }
                    frame.dispose();
                } , "Swing-Renderer").start();
            });
        } catch (HeadlessException | InvocationTargetException | InterruptedException e) {
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
        return new SwingCallList(graphics);
    }

    @Override
    public void makeMain() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public int[] getSize() {
        return new int[] { (int) frame.getSize().getWidth(), (int) frame.getSize().getHeight() };
    }

    @Override
    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    @Override
    public void setFullscreen(boolean fullscreen) {
        // FIXME!
    }

    @Override
    public void addKeyCallback(Consumer<AKeyEvent> keyEventListener) {
        keyListeners.add(keyEventListener);
    }

    @Override
    public void addMouseCallback(Consumer<AMouseEvent> mouseEventListener) {
        mouseListeners.add(mouseEventListener);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keySomething(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keySomething(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keySomething(e);
    }

    private void keySomething(KeyEvent e) {
        AKeyEvent event = new AKeyEvent(e);
        for (Consumer<AKeyEvent> consumer : keyListeners) {
            consumer.accept(event);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
