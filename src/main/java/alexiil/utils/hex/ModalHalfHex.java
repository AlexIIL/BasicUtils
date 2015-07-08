package alexiil.utils.hex;

import java.awt.Color;
import alexiil.utils.render.Modal;

public class ModalHalfHex extends Modal {
    public ModalHalfHex(Color colour) {
        addLine(0, 2, root3, 1, colour);
        addLine(root3, -1, 0, -2, colour);
        addLine(-root3, -1, -root3, 1, colour);
    }
}
