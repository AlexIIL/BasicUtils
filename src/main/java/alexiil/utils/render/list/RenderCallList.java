package alexiil.utils.render.list;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RenderCallList extends RenderPart {
    private List<RenderPart> lst = new ArrayList<RenderPart>();
    private RenderState state = new RenderState();
    private Deque<Integer> statePops = new ArrayDeque<Integer>();
    
    public void render(Graphics2D g2d) {
        render(state, g2d);
    }
    
    public void addCall(RenderPart part) {
        lst.add(part);
    }
    
    public void pushStateChange(RenderStateChange change) {
        int idx = lst.size();
        lst.add(change);
        statePops.push(idx);
    }
    
    public void popStateChange() {
        int pos = statePops.pop();
        RenderPart part = lst.get(pos);
        if (part instanceof RenderStateChange) {
            lst.add(((RenderStateChange) part).invert());
        }
    }
    
    @Override public String toString() {
        return "RenderCallList " + lst.toString();
    }
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        for (RenderPart rp : lst)
            rp.render(state, g2d);
    }
}
