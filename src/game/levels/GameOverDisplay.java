package game.levels;

import game.gameLogic.Animation;
import game.gameLogic.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Prints game over whe you die.
 */
public class GameOverDisplay implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private int guiHeight = 600;

    /**
     * constructor.
     *
     * @param k     keyboard sensor.
     * @param score current score.
     */
    public GameOverDisplay(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;

    }

    /**
     * Draws game over.
     *
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(170 + 1, guiHeight / 2, "Game Over", 90);
        d.drawText(220 + 1, guiHeight / 2 + 100, "Your score is " + score.getValue(), 50);
        d.drawText(170 - 1, guiHeight / 2, "Game Over", 90);
        d.drawText(220 - 1, guiHeight / 2 + 100, "Your score is " + score.getValue(), 50);
        d.drawText(170, guiHeight / 2 + 1, "Game Over", 90);
        d.drawText(220, guiHeight / 2 + 100 + 1, "Your score is " + score.getValue(), 50);
        d.drawText(170, guiHeight / 2 - 1, "Game Over", 90);
        d.drawText(220, guiHeight / 2 + 100 - 1, "Your score is " + score.getValue(), 50);
        d.setColor(Color.red);
        d.drawText(170, guiHeight / 2, "Game Over", 90);
        d.drawText(220, guiHeight / 2 + 100, "Your score is " + score.getValue(), 50);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     *
     * @return state of whether to stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
