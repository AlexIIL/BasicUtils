package alexiil.utils.hex;

public class HexGridWrapper {
    public final int maxX, maxY;
    public final boolean wrapX, wrapY;
    
    public HexGridWrapper(int maxX, int maxY, boolean wrapX, boolean wrapY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.wrapX = wrapX;
        this.wrapY = wrapY;
    }
    
    public int forceWrapX(int x) {
        if (x < 0)
            x = (x % maxX) + maxX;
        return x % maxX;
    }
    
    public int forceWrapY(int y) {
        if (y < 0)
            y = (y % maxY) + maxY;
        return y % maxY;
    }
    
    public int wrapX(int x) {
        if (wrapX)
            return forceWrapX(x);
        return x;
    }
    
    public int wrapY(int y) {
        if (wrapY)
            return forceWrapY(y);
        return y;
    }
}
