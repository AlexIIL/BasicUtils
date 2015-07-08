package alexiil.utils.hex;

import java.awt.Color;
import alexiil.utils.render.Modal;

public final class ModalFullHex extends Modal {
    public ModalFullHex(Color colour) {
        addHex(0, 0, 1.95, colour);
        addHex(0, 0, 2.05, colour);
    }
}