package alexiil.utils.hex;

public class HexGridArray<T> {
    private T[][] array;
    public final HexGridWrapper wrapper;
    
    @SuppressWarnings("unchecked") public HexGridArray(HexGridWrapper wrap) {
        wrapper = wrap;
        array = (T[][]) new Object[wrap.maxX][wrap.maxY];
    }
    
    public T getAtPos(HexPosition pos) {
        return array[wrapper.forceWrapX(pos.x)][wrapper.forceWrapY(pos.y)];
    }
    
    public boolean setAtPos(HexPosition pos, T toSet) {
        if (array[wrapper.forceWrapX(pos.x)][wrapper.forceWrapY(pos.y)] != null)
            return false;
        array[wrapper.forceWrapX(pos.x)][wrapper.forceWrapY(pos.y)] = toSet;
        return true;
    }
}
