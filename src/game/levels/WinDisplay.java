package game.levels;

import game.gameLogic.Animation;
import game.gameLogic.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Display for when winning.
 */
public class WinDisplay implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private int guiHeight = 600;

    /**
     * Constructor.
     *
     * @param k     keyboard sensor.
     * @param score keeps track of score.
     */
    public WinDisplay(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;

    }

    /**
     * Draws "you win" and the score.
     *
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.pink);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(200 + 1, guiHeight / 2, "You Win!", 90);
        d.drawText(200 + 1, guiHeight / 2 + 100, "Your score is " + score.getValue(), 50);
        d.drawText(200 - 1, guiHeight / 2, "You Win!", 90);
        d.drawText(200 - 1, guiHeight / 2 + 100, "Your score is " + score.getValue(), 50);
        d.drawText(200, guiHeight / 2 + 1, "You Win!", 90);
        d.drawText(200, guiHeight / 2 + 100 + 1, "Your score is " + score.getValue(), 50);
        d.drawText(200, guiHeight / 2 - 1, "You Win!", 90);
        d.drawText(200, guiHeight / 2 + 100 - 1, "Your score is " + score.getValue(), 50);
        d.setColor(Color.WHITE);
        d.drawText(200, guiHeight / 2, "You Win!", 90);
        d.drawText(200, guiHeight / 2 + 100, "Your score is " + score.getValue(), 50);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Let's know when should stop.
     *
     * @return state of whether to stop of not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
