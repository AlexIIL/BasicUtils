package alexiil.utils.render;

import java.awt.Color;
import java.awt.geom.Point2D;

public class Line {
    public final Color colour;
    public final double x1, x2, y1, y2;
    
    public Line(double x1, double y1, double x2, double y2) {
        this(x1, y1, x2, y2, Colour.GREEN.colour);
    }
    
    public Line(Point2D.Double p0, Point2D.Double p1, Color colour) {
        this(p0.getX(), p0.getY(), p1.getX(), p1.getY(), colour);
    }
    
    public Line(double x1, double y1, double x2, double y2, Color colour) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = colour;
    }
    
    public Line(Line line) {
        this(line.x1, line.y1, line.x2, line.y2, line.colour);
    }
}
