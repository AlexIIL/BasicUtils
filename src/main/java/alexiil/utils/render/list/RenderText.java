package alexiil.utils.render.list;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class RenderText extends RenderPart {
    private final String text;
    private final double x, y;
    private final boolean centerX, centerY;

    public RenderText(String text, double x, double y) {
        this(text, x, y, true, true);
    }

    public RenderText(String text, double x, double y, boolean centerX, boolean centerY) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public int[] getSize(Graphics2D g2d, int pointSize) {
        if (g2d == null) {
            // Just a guess
            return new int[] { text.length() * pointSize, pointSize + 2 };
        }
        int[] size = new int[2];
        FontMetrics fontMetrics = g2d.getFontMetrics(g2d.getFont().deriveFont(pointSize));
        size[0] = fontMetrics.stringWidth(text);
        size[1] = fontMetrics.getHeight();
        return size;
    }

    @Override
    public void render(RenderState state, Graphics2D g2d) {
        Font oldFont = g2d.getFont();
        g2d.setFont(oldFont.deriveFont((float) state.currentScale));
        int changedX = (int) state.changeX(x, y);
        int changedY = (int) state.changeY(x, y);
        if (centerX)
            changedX -= g2d.getFontMetrics().stringWidth(text) / 2;
        if (centerY)
            changedY += g2d.getFontMetrics().getHeight() / 2;
        g2d.drawString(text, changedX, changedY);
        g2d.setFont(oldFont);
    }
}
