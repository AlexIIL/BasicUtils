package alexiil.utils.render.list;

import java.awt.Graphics2D;

import alexiil.utils.render.Line;

public class RenderLine extends RenderPart {
    private final double x0, y0, x1, y1;

    public RenderLine(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }
    
    public RenderLine(Line line) {
        this((double) line.x1, (double) line.y1, (double) line.x2, (double) line.y2);
    }

    @Override
    public void render(RenderState state, Graphics2D g2d) {
        int ix0 = (int) Math.round(state.changeX(x0, y0));
        int iy0 = (int) Math.round(state.changeY(x0, y0));
        int ix1 = (int) Math.round(state.changeX(x1, y1));
        int iy1 = (int) Math.round(state.changeY(x1, y1));

        g2d.drawLine(ix0, iy0, ix1, iy1);

        // System.out.println("RenderLine [x0 = " + ix0 + ", y0 = " + iy0 + ", x1 = " + ix1 + ", y1 = " + iy1 + "]");
    }

    @Override
    public String toString() {
        return "RenderLine [x0=" + x0 + " y0=" + y0 + " x1=" + x1 + " y1=" + y1 + "]";
    }
}
