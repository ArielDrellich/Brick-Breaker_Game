package game.levels;

import game.gameLogic.GameLevel;
import game.gameLogic.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Background for Green3.
 */
public class BuildingBackground implements Sprite {
    /**
     * Draws background.
     * @param d surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(46, 42, 41));
        d.fillRectangle(70, 420, 170, 600);
        d.fillRectangle(130, 380, 50, 50);
        d.fillRectangle(145, 230, 20, 150);
        d.setColor(new Color(216, 172, 102));
        d.fillCircle(155, 230, 20);
        d.setColor(Color.red);
        d.fillCircle(155, 230, 10);
        d.setColor(Color.white);
        d.fillCircle(155, 230, 5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(84 + 31 * j, 430 + 40 * i, 20, 30);
            }
        }

    }

    /**
     * needed for sprite, not used here.
     */
    public void timePassed() {

    }

    /**
     * adds sprite to game.
     * @param g game to add sprite to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
