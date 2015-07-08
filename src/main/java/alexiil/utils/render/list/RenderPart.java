package alexiil.utils.render.list;

import java.awt.Graphics2D;

public abstract class RenderPart {
    public abstract void render(RenderState state, Graphics2D g2d);
}
