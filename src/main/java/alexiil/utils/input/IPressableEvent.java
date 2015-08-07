package alexiil.utils.input;

public interface IPressableEvent {
    EnumKeyStateChanged getMotionType();

    default boolean isTyped() {
        return getMotionType() == EnumKeyStateChanged.TYPED;
    }

    default boolean isPress() {
        return getMotionType() == EnumKeyStateChanged.PRESSED;
    }

    default boolean isRelease() {
        return getMotionType() == EnumKeyStateChanged.RELEASED;
    }
}
