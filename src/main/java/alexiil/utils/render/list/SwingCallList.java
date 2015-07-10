package alexiil.utils.render.list;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import alexiil.utils.render.glcompat.IRenderCallList;

public class SwingCallList extends RenderPart implements IRenderCallList {
    List<RenderPart> lst = new ArrayList<RenderPart>();
    private RenderState state = new RenderState();
    private Deque<Deque<Integer>> statePops = new ArrayDeque<Deque<Integer>>();

    public void render(Graphics2D g2d) {
        render(state, g2d);
    }

    private void addCall(RenderPart part) {
        lst.add(part);
    }

    @Override
    public String toString() {
        return "RenderCallList " + lst.toString();
    }

    @Override
    public void render(RenderState state, Graphics2D g2d) {
        for (RenderPart rp : lst)
            rp.render(state, g2d);
    }

    @Override
    public void polygon(double[][] points) {
        addCall(new RenderArea(points));
    }

    @Override
    public void line(double[][] points) {
        // addCall(new RenderLine(points));
    }

    @Override
    public int[] text(String text, double x, double y, int size) {
        return null;
    }

    @Override
    public void colour(Color c) {

    }

    @Override
    public void list(IRenderCallList list) {
        lst.add((SwingCallList) list);
    }

    @Override
    public void pushState() {
        statePops.push(new ArrayDeque<Integer>());
    }

    @Override
    public void scale(double mul) {
        int index = lst.size();
        lst.add(new RenderStateScale(mul));
        if (!statePops.isEmpty()) {
            statePops.peek().push(index);
        }
    }

    @Override
    public void offset(double x, double y) {
        int index = lst.size();
        lst.add(new RenderStatePosition(x, y));
        if (!statePops.isEmpty()) {
            statePops.peek().push(index);
        }
    }

    @Override
    public void rotate(double degrees) {
        int index = lst.size();
        lst.add(new RenderStateAngle(degrees));
        if (!statePops.isEmpty()) {
            statePops.peek().push(index);
        }
    }

    @Override
    public void popState() {
        Deque<Integer> pops = statePops.pop();
        while (!pops.isEmpty()) {
            lst.add(((RenderStateChange) lst.get(pops.pop())).invert());
        }
    }

    @Override
    public void dispose() {/* NO-OP */}
}
