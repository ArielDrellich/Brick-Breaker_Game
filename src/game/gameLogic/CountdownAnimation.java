package game.gameLogic;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper = new Sleeper();
    private boolean stop;
    private int countLeft;

    /**
     * Constructor.
     * @param numOfSeconds length of screen.
     * @param countFrom number to count down from.
     * @param gameScreen where to draw it.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.stop = false;
        this.countLeft = countFrom + 1;
    }

    /**
     * draws animation.
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (countFrom >= countLeft) {
            if (countLeft == 0) {
                d.setColor(Color.white);
                d.drawText(300 - 1, d.getHeight() / 2, "Go!", 150);
                d.drawText(300 + 1, d.getHeight() / 2, "Go!", 150);
                d.drawText(300, d.getHeight() / 2 - 1, "Go!", 150);
                d.drawText(300, d.getHeight() / 2 + 1, "Go!", 150);
                d.setColor(Color.black);
                d.drawText(300, d.getHeight() / 2, "Go!", 150);
            } else {
                d.setColor(Color.white);
                d.drawText(360 - 1, d.getHeight() / 2, String.valueOf(countLeft), 150);
                d.drawText(360 + 1, d.getHeight() / 2, String.valueOf(countLeft), 150);
                d.drawText(360, d.getHeight() / 2 - 1, String.valueOf(countLeft), 150);
                d.drawText(360, d.getHeight() / 2 + 1, String.valueOf(countLeft), 150);
                d.setColor(Color.black);
                d.drawText(360, d.getHeight() / 2, String.valueOf(countLeft), 150);
            }
            this.sleeper.sleepFor((long) (1000 * numOfSeconds / countFrom));
        }
        countLeft--;
    }

    /**
     * lets the program know when to stop.
     * @return whether should stop or not.
     */
    public boolean shouldStop() {
        if (countLeft < -1) {
            this.stop = true;
        }
        return this.stop;
    }
}
