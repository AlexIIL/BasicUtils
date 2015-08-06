package alexiil.utils.input;

public interface IPressableEvent {
    EnumPressableMotion getMotionType();

    default boolean isTyped() {
        return getMotionType() == EnumPressableMotion.TYPED;
    }

    default boolean isPress() {
        return getMotionType() == EnumPressableMotion.PRESSED;
    }

    default boolean isRelease() {
        return getMotionType() == EnumPressableMotion.RELEASED;
    }
}
