package alexiil.utils.render.list;

public class RenderState {
    // The angle is in radians
    public double currentScale, currentX, currentY;
    
    private double angle, sin, cos;
    
    public RenderState() {
        currentScale = 1;
        currentX = 0;
        currentY = 0;
        setAngle(0);
    }
    
    public double changeX(double x, double y) {
        double nX = x + currentX;
        double nY = y + currentY;
        double nnX = nX * cos + nY * sin;
        return nnX * currentScale;
    }
    
    public double changeY(double x, double y) {
        double nX = x + currentX;
        double nY = y + currentY;
        double nnY = nX * sin + nY * cos;
        return nnY * currentScale;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
        sin = Math.sin(angle);
        cos = Math.cos(angle);
    }
    
    public double getAngle() {
        return angle;
    }
    
    public void increaseAngle(double diff) {
        setAngle(getAngle() + diff);
    }
    
    @Override public String toString() {
        return "RenderState [currentScale=" + currentScale + ", currentX=" + currentX + ", currentY=" + currentY + ", angle=" + angle + ", sin="
                + sin + ", cos=" + cos + "]";
    }
}
