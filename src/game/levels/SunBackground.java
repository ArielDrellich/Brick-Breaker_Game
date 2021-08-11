package game.levels;

import game.gameLogic.GameLevel;
import java.awt.Color;
import biuoop.DrawSurface;
import game.gameLogic.Sprite;

/**
 * Background for 2nd level.
 */
public class SunBackground implements Sprite {
    /**
     *
     * @param d surface to draw on.
     */
    public void drawOn(final DrawSurface d) {
        d.setColor(new Color(239, 231, 176));
        for (int i = 0; i < 125; ++i) {
            d.drawLine(150, 150, i * 7, 300);
        }
        d.fillCircle(150, 150, 70);
        d.setColor(new Color(236, 215, 73));
        d.fillCircle(150, 150, 58);
        d.setColor(new Color(255, 225, 24));
        d.fillCircle(150, 150, 46);
    }

    /**
     * needed for sprite. not used here.
     */
    public void timePassed() {
    }

    /**
     *
     * @param g game to add sprite to.
     */
    public void addToGame(final GameLevel g) {
        g.addSprite((Sprite) this);
    }
}