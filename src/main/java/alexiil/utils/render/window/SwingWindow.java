package alexiil.utils.render.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import alexiil.utils.input.AKeyEvent;
import alexiil.utils.input.IMouseEvent;
import alexiil.utils.input.MouseContext;
import alexiil.utils.input.MouseMovedEvent;
import alexiil.utils.input.MouseStateChangeEvent;
import alexiil.utils.render.list.SwingCallList;

public class SwingWindow implements IWindow, KeyListener, MouseListener, MouseMotionListener {
    @SuppressWarnings("serial")
    private class PanelRender extends JPanel {
        private PanelRender(LayoutManager layout) {
            super(layout);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            graphics = (Graphics2D) g;
            if (render != null) {
                render.run();
            }
            graphics = null;
        }
    }

    private JFrame frame;
    private JPanel outer, inner;
    private Graphics2D graphics;
    private Runnable render;
    private volatile boolean open = false;
    private final List<Consumer<AKeyEvent>> keyListeners = new ArrayList<>();
    private final List<Consumer<IMouseEvent>> mouseListeners = new ArrayList<>();
    public MouseContext currentContext = new MouseContext();

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

                inner = new PanelRender(new BorderLayout());
                inner.setBackground(Color.BLACK);
                outer.add(inner, BorderLayout.CENTER);

                inner.addKeyListener(this);
                inner.addMouseListener(this);
                inner.addMouseMotionListener(this);

                frame.setVisible(true);

                open = true;

                new Thread(() -> {
                    while (open) {
                        long before = System.currentTimeMillis();

                        inner.repaint();
                        // if (render != null) {
                        // render.run();
                        // }

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
    public void addMouseCallback(Consumer<IMouseEvent> mouseEventListener) {
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
        mouseSomething(e, false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseSomething(e, false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseSomething(e, false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseSomething(e, true);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseSomething(e, true);
    }

    private void mouseSomething(MouseEvent e, boolean move) {
        IMouseEvent event;
        if (move) {
            event = new MouseMovedEvent(currentContext, e);
        } else {
            event = new MouseStateChangeEvent(e);
        }
        event = currentContext.changeFor(event);
        for (Consumer<IMouseEvent> consumer : mouseListeners) {
            consumer.accept(event);
        }

        System.out.println(event.toString());
    }
}
