package alexiil.utils.hex;

import java.util.List;
import alexiil.utils.render.IRenderable;

public abstract class HexGrid {
    /** This stores both a wrapper for this grid, and all the data given from the constructor */
    public final HexGridWrapper wrapper;
    
    public HexGrid(int xSize, int ySize, boolean wrapX, boolean wrapY) {
        wrapper = new HexGridWrapper(xSize, ySize, wrapX, wrapY);
    }
    
    public abstract List<IRenderable> getRenderables(HexPosition pos);
    
    public boolean isValidSpace(HexPosition pos) {
        return pos.move(EHexDirection.DOWN_LEFT, 0, wrapper).equals(pos);
    }
}