package alexiil.utils.hex;

public class HexPosition {
    public final int x, y;
    
    public HexPosition(int xPos, int yPos, HexGridWrapper wrapper) {
        if (wrapper == null) {
            x = xPos;
            y = yPos;
        }
        else {
            x = wrapper.wrapX(xPos);
            y = wrapper.wrapY(yPos);
        }
    }
    
    public HexPosition move(EHexDirection dir, int by, HexGridWrapper wrapper) {
        return move(by * dir.dx, by * dir.dy, wrapper);
    }
    
    public HexPosition move(int dx, int dy, HexGridWrapper wrapper) {
        return new HexPosition(x + dx, y + dy, wrapper);
    }
    
    public HexPosition move(EHexDirection dir) {
        return move(dir, 1, null);
    }
    
    public HexPosition wrap(HexGridWrapper wrapper) {
        return new HexPosition(x, y, wrapper);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode() */
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object) */
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HexPosition other = (HexPosition) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
