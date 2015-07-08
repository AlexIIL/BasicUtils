package alexiil.utils.render.list;

import java.awt.Graphics2D;

public class RenderStatePosition extends RenderStateChange {
    private final double x, y;
    
    public RenderStatePosition(double xDiff, double yDiff) {
        x = xDiff;
        y = yDiff;
    }
    
    @Override public RenderStateChange invert() {
        return new RenderStatePosition(-x, -y);
    }
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        state.currentX += x;
        state.currentY += y;
    }
    
    @Override public String toString() {
        return "RenderStatePosition [x=" + x + " y=" + y + "]";
    }
}
