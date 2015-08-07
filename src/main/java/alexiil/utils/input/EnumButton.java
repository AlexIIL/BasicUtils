package alexiil.utils.input;

import java.awt.event.MouseEvent;

public enum EnumButton {
    NONE,
    PRIMARY,
    SECONDARY,
    MIDDLE;

    /** Set this to true if the mouse buttons should be inverted (so left clicking is considered to have right clicked,
     * and vice versa) */
    public static boolean invertMouse = false;

    public static EnumButton valueOf(MouseEvent event) {
        int id = event.getButton();
        if (id == MouseEvent.BUTTON1) {
            return invertMouse ? SECONDARY : PRIMARY;
        } else if (id == MouseEvent.BUTTON3) {
            return invertMouse ? PRIMARY : SECONDARY;
        } else if (id == MouseEvent.BUTTON2) {
            return MIDDLE;
        } else if (id != 0) {
            System.out.println("EnumButton| Found an unknown ID(" + id + ")!");
        }
        return NONE;
    }

    public EnumButton[] add(EnumButton[] array) {
        for (EnumButton button : array) {
            if (button == this) {
                return array;
            }
        }
        EnumButton[] newArray = new EnumButton[array.length + 1];
        if (array.length > 0) {
            System.arraycopy(array, 0, newArray, 0, array.length);
        }
        newArray[array.length] = this;
        return newArray;
    }

    public EnumButton[] remove(EnumButton[] array) {
        for (int i = 0; i < array.length; i++) {
            EnumButton button = array[i];
            if (button == this) {
                EnumButton[] newArray = new EnumButton[array.length - 1];
                System.arraycopy(array, 0, newArray, 0, i);
                if (array.length - 1 - i > 0) {
                    System.arraycopy(array, i + 1, newArray, i, array.length - 1 - i);
                }
                return newArray;
            }
        }
        return array;
    }

    public boolean isInArray(EnumButton[] array) {
        for (EnumButton button : array) {
            if (button == this) {
                return true;
            }
        }
        return false;
    }
}
