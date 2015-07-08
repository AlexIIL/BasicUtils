package alexiil.utils.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Colour {
    public static final List<Colour> validColours;
    public static final Colour BLUE;
    public static final Colour GOLD;
    public static final Colour GREEN;
    public static final Colour ORANGE;
    public static final Colour PINK;
    public final Color colour;
    public final String name;
    static {
        validColours = Collections.synchronizedList(new ArrayList<Colour>());
        BLUE = new Colour("Blue", 0x6666FF);
        GOLD = new Colour("Gold", 0xDDFF49);
        GREEN = new Colour("Green", 0x00AA00);
        ORANGE = new Colour("Orange", Color.ORANGE);
        PINK = new Colour("Pink", 0xFFAFAF);
    }
    
    public Colour(String n, Color c) {
        colour = c;
        name = n;
        validColours.add(this);
    }
    
    public Colour(String n, int c) {
        this(n, new Color(c));
    }
    
    @Override public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Colour)
            return colour.equals(((Colour) obj).colour) && name.equals(((Colour) obj).name);
        else
            return false;
    }
    
    @Override public String toString() {
        return name;
    }
}
