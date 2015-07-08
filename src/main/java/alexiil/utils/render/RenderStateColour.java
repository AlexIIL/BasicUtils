package alexiil.utils.render;

import java.awt.Color;
import java.awt.Graphics2D;

import alexiil.utils.render.list.RenderPart;
import alexiil.utils.render.list.RenderState;

public class RenderStateColour extends RenderPart {
    private final Color colour;
    
    public RenderStateColour(Color colour) {
        this.colour = colour;
    }
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        g2d.setColor(colour);
    }
    
    @Override public String toString() {
        return "RenderStateColour [colour=0x" + Integer.toHexString(colour.getRGB()) + "]";
    }
}
