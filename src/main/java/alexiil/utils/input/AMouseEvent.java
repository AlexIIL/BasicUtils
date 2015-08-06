package alexiil.utils.input;

public class AMouseEvent implements IPressableEvent {
    private final int button;
    private final EnumPressableMotion motion;

    public AMouseEvent(int button, EnumPressableMotion motion) {
        this.button = button;
        this.motion = motion;
    }

    public int getButton() {
        return button;
    }

    @Override
    public EnumPressableMotion getMotionType() {
        return motion;
    }
}
