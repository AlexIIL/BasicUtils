package alexiil.utils.render;

public interface IRenderable {
    public abstract Modal getModal();
    
    public abstract double getAngle();
    
    /** This should return the longest distance between the centre point and the point of any of the renderable lines */
    public double getRadius();
}
