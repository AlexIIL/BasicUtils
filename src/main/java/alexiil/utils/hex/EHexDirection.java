package alexiil.utils.hex;

public enum EHexDirection {
    /** X - 1, Y + 1 */
    UP_RIGHT(-1, 1, 0), /** Y + 1 */
    UP_LEFT(0, 1, 5), /** X - 1 */
    RIGHT(-1, 0, 1), /** X + 1 */
    LEFT(1, 0, 4), /** X - 1, Y - 1 */
    DOWN_RIGHT(0, -1, 2), /** Y - 1 */
    DOWN_LEFT(1, -1, 3);
    
    public final int dx, dy, index;
    
    EHexDirection(int x, int y, int idx) {
        dx = x;
        dy = y;
        index = idx;
    }
    
    public EHexDirection getClockwiseFrom(int by) {
        int idx = (by + index) % 6;
        for (EHexDirection d : values())
            if (d.index == idx)
                return d;
        return null;
    }
}
