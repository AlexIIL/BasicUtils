package alexiil.utils.render;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import alexiil.utils.render.list.RenderCallList;
import alexiil.utils.render.list.RenderStateAngle;

public class Render2D {
    
    @Deprecated public static ArrayList<Line> expandAll(double by, ArrayList<Line> lines) {
        // if (by == 1)
        // return lines;
        // ArrayList<Line> nLines = new ArrayList<Line>();
        // for (Line line : lines)
        // nLines.add(new Line(line.x1 * by, line.y1 * by, line.x2 * by, line.y2 * by, line.colour));
        // return nLines;
        return null;
    }
    
    @Deprecated public static ArrayList<Line> getModalLines(Modal modal) {
        // ArrayList<Line> lines = new ArrayList<Line>();
        // if (modal == null)
        // return lines;
        // for (Line line : modal.lines)
        // lines.add(line);
        // return lines;
        return null;
    }
    
    @Deprecated public static ArrayList<Line> offsetAll(double d, double e, double radians, ArrayList<Line> lines) {
        // return offsetModal(d, e, offsetLinesByAngle(radians, lines));
        return null;
    }
    
    @Deprecated public static ArrayList<Line> offsetModal(double dx, double dy, double angle, Modal modal) {
        // return offsetAll(dx, dy, angle, getModalLines(modal));
        return null;
    }
    
    @Deprecated public static ArrayList<Line> offsetLinesByAngle(double radians, ArrayList<Line> lines) {
        // ArrayList<Line> nLines = new ArrayList<Line>();
        // for (Line line : lines) {
        // nLines.add(offsetLineByAngle(line, radians));
        // }
        // return nLines;
        return null;
    }
    
    @Deprecated public static ArrayList<Line> offsetModalByAngle(double radians, Modal modal) {
        // return offsetLinesByAngle(radians, getModalLines(modal));
        return null;
    }
    
    public static ArrayList<Line> offsetModal(double d, double e, ArrayList<Line> modal) {
        // ArrayList<Line> lines = new ArrayList<Line>();
        // for (Line line : modal)
        // lines.add(new Line(line.x1 + d, line.y1 + e, line.x2 + d, line.y2 + e, line.colour));
        // return lines;
        return null;
    }
    
    @Deprecated public static ArrayList<Line> offsetModal(double dx, double dy, Modal modal) {
        // return offsetModal(dx, dy, getModalLines(modal));
        return null;
    }
    
    @Deprecated public static ArrayList<Line> removeOutsiders(ArrayList<Line> lines, double maxX, double minX, double maxY, double minY) {
        // ArrayList<Line> nLines = new ArrayList<Line>();
        // for (Line line : lines)
        // if (!(line.x1 < minX || line.x1 > maxX || line.y1 < minY || line.y1 > maxY || line.x2 < minX || line.x2 >
        // maxX || line.y2 < minY || line.y2 > maxY))
        // nLines.add(line);
        // return nLines;
        return null;
    }
    
    @Deprecated public static boolean isVisible(IRenderable render, Rectangle paintableArea) {
        // double r = render.getRadius();
        // return render.getXCoord() + r > paintableArea.x && render.getXCoord() - r < paintableArea.x &&
        // render.getYCoord() + r > paintableArea.y && render.getYCoord() - r < paintableArea.y;
        return false;
    }
    
    @Deprecated public static ArrayList<IRenderable> removeOutsiders(ArrayList<IRenderable> modals, Rectangle paintableArea) {
        return modals;
    }
    
    @Deprecated public static ArrayList<Line> processRenderable(IRenderable render) {
        // return offsetAll(render.getXCoord(), render.getYCoord(), render.getAngle(),
        // expandAll(render.getRenderExpansion(), getModalLines(render.getModal())));
        return null;
    }
    
    /** Paints the modal given, but only if they lie within the visible area
     * 
     * @param g2d
     * @param paintableArea
     * @param viewPoint the current position the user has in a world
     * @param render */
    @Deprecated public static void paint(Graphics2D g2d, Rectangle paintableArea, Point2D viewPoint, IRenderable render) {
        if (!isVisible(render, paintableArea))
            return;
        ArrayList<Line> lines = offsetAll(viewPoint.getX(), viewPoint.getY(), 0, processRenderable(render));
        for (Line ln : lines) {
            if (!ln.colour.equals(g2d.getColor()))
                g2d.setColor(ln.colour);
            g2d.drawLine((int) ln.x1, (int) ln.y1, (int) ln.x2, (int) ln.y2);
        }
    }
    
    @Deprecated public static void paintAll(Graphics2D g2d, ArrayList<IRenderable> renders, Rectangle paintableArea, Point2D viewPoint) {
        for (IRenderable render : renders)
            paint(g2d, paintableArea, viewPoint, render);
    }
    
    public static void add(RenderCallList list, IRenderable render) {
        Modal modal = render.getModal();
        list.pushStateChange(new RenderStateAngle((float) render.getAngle()));
        modal.addRenders(list);
        list.popStateChange();
    }
    
    public static void addAll(RenderCallList list, ArrayList<IRenderable> renders) {
        for (IRenderable render : renders)
            add(list, render);
    }
}
