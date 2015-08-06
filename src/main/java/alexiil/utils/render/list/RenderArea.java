package alexiil.utils.render.list;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class RenderArea extends RenderPart {
    private final double[] xPos, yPos;
    private final int length;
    private final boolean filled;

    public RenderArea(double[][] points, boolean filled) {
        length = points.length;
        xPos = new double[length + 1];
        yPos = new double[length + 1];
        for (int i = 0; i < length; i++) {
            xPos[i] = points[i][0];
            yPos[i] = points[i][1];
        }
        xPos[length] = points[0][0];
        yPos[length] = points[0][1];
        this.filled = filled;
    }

    public RenderArea(double[] x, double[] y, int num, boolean filled) {
        xPos = x;
        yPos = y;
        length = num;
        this.filled = filled;
    }

    @Override
    public void render(RenderState state, Graphics2D g2d) {
        Polygon poly = new Polygon(convertX(state), convertY(state), length + 1);
        if (filled) {
            g2d.fillPolygon(poly);
        } else {
            g2d.drawPolygon(poly);
        }
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
