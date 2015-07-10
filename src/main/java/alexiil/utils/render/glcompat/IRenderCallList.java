package alexiil.utils.render.glcompat;

import java.awt.Color;

public interface IRenderCallList {
    // Normal rendering
    void polygon(double[][] points);

    void line(double[][] points);

    /**
     * Returns the size the rendered text will be, in the same units that you gave (so, it will ALWAYS be an array of
     * size 2 [1,2] for example)
     */
    int[] text(String text, double x, double y, int size);

    void colour(Color c);

    /** Add a call list inside of this list */
    void list(IRenderCallList list);

    // State Changes

    void pushState();

    void scale(double mul);

    void offset(double x, double y);

    void rotate(double degrees);

    void popState();

    /** Close the render list, releasing any resources it may have had up until this point */
    void dispose();
}
