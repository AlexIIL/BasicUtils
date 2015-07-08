package alexiil.utils.render.list;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class RenderArea extends RenderPart {
    private final double[] xPos, yPos;
    private final int length;
    
    public RenderArea(double[] x, double[] y, int num) {
        xPos = x;
        yPos = y;
        length = num;
    }
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        Polygon poly = new Polygon(convertX(state), convertY(state), length);
        g2d.fill(poly);
    }
    
    private int[] convertX(RenderState state) {
        int[] arr = new int[xPos.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) state.changeX((float) xPos[i], (float) yPos[i]);
        return arr;
    }
    
    private int[] convertY(RenderState state) {
        int[] arr = new int[yPos.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) state.changeY((float) xPos[i], (float) yPos[i]);
        return arr;
    }
}
