package game.levels;

import game.gameLogic.GameLevel;
import game.gameLogic.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * background for 1st level.
 */
public class TargetBackground implements Sprite {
    /**
     * @param d surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        d.drawCircle(400, 165, 150);
        d.drawCircle(400, 165, 100);
        d.drawCircle(400, 165, 50);
        d.drawLine(400, 0, 400, 350);
        d.drawLine(200, 165, 600, 165);
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
