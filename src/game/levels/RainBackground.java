package game.levels;

import game.gameLogic.GameLevel;
import game.gameLogic.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Background for 4th level.
 */
public class RainBackground implements Sprite {
    /**
     * Draws background.
     *
     * @param d surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.lightGray);
        for (int i = 0; i < 15; i++) {
            d.drawLine(80 + 6 * i, 450, 50 + 6 * i, 600);
            d.drawLine(620 + 6 * i, 480, 600 + 6 * i, 600);
        }
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(140, 440, 30);
        d.fillCircle(150, 390, 50);
        d.fillCircle(700, 470, 30);
        d.fillCircle(645, 480, 50);
        d.setColor(Color.lightGray);
        d.fillCircle(90, 450, 40);
        d.fillCircle(100, 400, 50);
        d.fillCircle(680, 500, 35);
        d.fillCircle(645, 500, 35);

    }

    /**
     * needed for sprite, not used here.
     */
    public void timePassed() {

    }

    /**
     * adds sprite to game.
     *
     * @param g game to add sprite to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
