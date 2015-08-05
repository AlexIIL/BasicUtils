package alexiil.utils.render;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import alexiil.utils.render.window.IRenderCallList;
import alexiil.utils.render.window.SwingTools;

public abstract class Modal {
    public static final double root3 = Math.sqrt(3);
    public static final Random rand = new Random();

    public static Color applyRandomAddition(int range, Color colour, int toAdd) {
        int r = applyRandomAddition(range, colour.getRed(), toAdd);
        int g = applyRandomAddition(range, colour.getGreen(), toAdd);
        int b = applyRandomAddition(range, colour.getBlue(), toAdd);
        return new Color(r, g, b);
    }

    public static int applyRandomAddition(int range, int cPart, int toAdd) {
        int subtraction = 0;
        if (cPart - range - toAdd < 0)
            subtraction = cPart - range - toAdd;
        else if (cPart + range > 0xFF)
            subtraction = cPart + range + toAdd - 0xFF;
        int addition = rand.nextInt(range * 2) - range - toAdd - subtraction;
        return cPart + addition;
    }

    private Point2D.Double min = new Point2D.Double(), max = new Point2D.Double();
    private Map<ERenderType, BufferedImage> imageMap = new EnumMap<ERenderType, BufferedImage>(ERenderType.class);
    private double radius = -1;
    /** Used to check if it needs to be recompiled to opengl or not */
    protected boolean isDirty = true;
    protected IRenderCallList renderList = SwingTools.instance.makeNewCallList();
    private Color lastColor = null;

    public BufferedImage getImage(ERenderType type) {
        return imageMap.getOrDefault(type, null);
    }

    public double getRadius() {
        if (radius >= 0)
            return radius;
        radius = reCalculateRadius();
        return radius;
    }

    public double reCalculateRadius() {
        if (isDirty) {
            double x = max.getX() - min.getX();
            double y = max.getY() - min.getY();
            radius = Math.sqrt(x * x + y * y);
        }
        return radius;
    }

    protected void addBox(double x1, double y1, double x2, double y2) {
        addBox(x1, y1, x2, y2, Colour.GREEN.colour);
    }

    protected void addBox(double x1, double y1, double x2, double y2, Color colour) {
        addLine(x1, y1, x1, y2, colour);
        addLine(x1, y2, x2, y2, colour);
        addLine(x2, y1, x2, y2, colour);
        addLine(x1, y1, x2, y1, colour);
    }

    /**
     * @param xC
     *            The centre xPos of this hexagon
     * @param yC
     *            The centre yPos of this hexagon
     * @param r
     *            The radius of this hexagon
     * @param colour
     *            The colour of this hexagon
     */
    protected void addHex(double xC, double yC, double r, Color colour) {
        double a = r / 2;
        double b = root3 * a;
        Point2D.Double[] points = new Point2D.Double[6];
        points[0] = new Point.Double(xC, yC + r);
        points[1] = new Point.Double(xC + b, yC + a);
        points[2] = new Point.Double(xC + b, yC - a);
        points[3] = new Point.Double(xC, yC - r);
        points[4] = new Point.Double(xC - b, yC - a);
        points[5] = new Point.Double(xC - b, yC + a);
        addLines(points, colour);
    }

    protected void addFilledHex(double x, double y, double r, Color c) {
        if (!c.equals(lastColor)) {
            renderList.colour(c);
            lastColor = c;
        }
        double a = r / 2;
        double b = root3 * a;
        double[][] points = new double[][] { { x, y + r }, { x + b, y + a }, { x + b, y - a }, { x, y - r }, { x - b, y - a }, { x - b, y + a } };
        renderList.polygon(points);
    }

    protected void addLines(Point2D.Double[] points, Color color) {
        for (int i = 0; i < points.length; i++) {
            Point2D.Double first = points[i];
            Point2D.Double second;
            if (i == 0)
                second = points[points.length - 1];
            else
                second = points[i - 1];
            addLine(first.x, first.y, second.x, second.y, color);
        }
    }

    protected void addLine(double x1, double y1, double x2, double y2) {
        addLine(new Line(x1, y1, x2, y2));
    }

    protected void addLine(double x1, double y1, double x2, double y2, Color colour) {
        addLine(new Line(x1, y1, x2, y2, colour));
    }

    protected void addLine(Line newLine) {
        if (!newLine.colour.equals(lastColor)) {
            lastColor = newLine.colour;
            renderList.colour(lastColor);
        }
        renderList.line(newLine.toPoints());
        isDirty = true;

        double minX = Math.min(min.getX(), Math.min(newLine.x1, newLine.x2));
        double minY = Math.min(min.getY(), Math.min(newLine.y1, newLine.y2));
        min = new Point2D.Double(minX, minY);

        double maxX = Math.max(max.getX(), Math.max(newLine.x1, newLine.x2));
        double maxY = Math.max(max.getY(), Math.max(newLine.y1, newLine.y2));
        max = new Point2D.Double(maxX, maxY);
    }

    protected void addText(double cX, double cY, String text, int size) {
        renderList.text(text, cX, cY, size);
    }

    protected void addMultipleLines(ArrayList<Line> newLines) {
        for (Line newLine : newLines) {
            addLine(newLine);
        }
    }

    protected void setImage(ERenderType renderType, BufferedImage image) {
        imageMap.put(renderType, image);
    }

    public IRenderCallList getRenders() {
        return renderList;
    }

    public void addRenders(IRenderCallList list) {
        list.list(getRenders());
    }
}
