package alexiil.utils.render.list;

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
    
    @Override public void render(RenderState state, Graphics2D g2d) {
        g2d.setFont(g2d.getFont().deriveFont((float) state.currentScale));
        int changedX = (int) state.changeX(x, y);
        int changedY = (int) state.changeY(x, y);
        if (centerX)
            changedX -= g2d.getFontMetrics().stringWidth(text) / 2;
        if (centerY)
            changedY += g2d.getFontMetrics().getHeight() / 2;
        g2d.drawString(text, changedX, changedY);
    }
}
