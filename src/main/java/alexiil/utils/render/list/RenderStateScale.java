package alexiil.utils.render.list;

import java.awt.Graphics2D;

public class RenderStateScale extends RenderStateChange {
    public final double scaleMultiplier;
    
    public RenderStateScale(double multiplier) {
        scaleMultiplier = multiplier;
    }
    
    @Override public RenderStateChange invert() {
        return new RenderStateScale(1 / scaleMultiplier);
    }
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        state.currentX /= scaleMultiplier;
        state.currentY /= scaleMultiplier;
        state.currentScale *= scaleMultiplier;
    }
    
    @Override public String toString() {
        return "RenderStateScale [scaleMultiplier=" + scaleMultiplier + "]";
    }
}
