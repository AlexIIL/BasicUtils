package alexiil.utils.render;

public class RenderableModal implements IRenderable {
    public final Modal modal;
    public final double radius, angle;
    
    public RenderableModal(Modal modal) {
        this.modal = modal;
        this.radius = modal.getRadius();
        angle = 0;
    }
    
    @Override public Modal getModal() {
        return modal;
    }
    
    @Override public double getAngle() {
        return angle;
    }
    
    @Override public double getRadius() {
        return radius;
    }
}
