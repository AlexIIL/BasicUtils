package alexiil.utils.render.list;

import java.awt.Graphics2D;

public class RenderStateAngle extends RenderStateChange {
    private final double angle;
    
    public RenderStateAngle(double angle) {
        this.angle = angle;
    }
    
    @Override public RenderStateChange invert() {
        return new RenderStateAngle(-angle);
    }
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        state.increaseAngle(angle);
    }

    @Override public String toString() {
        return "RenderStateAngle [angle=" + angle + "]";
    }
}
