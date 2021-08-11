package game.gameLogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Runs animations with the option to stop at a keypress.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor sensor;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     * @param sensor keyboard sesnor.
     * @param key key to stop at.
     * @param animation animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
    this.animation = animation;
    this.sensor = sensor;
    this.key = key;
    this.stop = false;
    }

    /**
     * draws the animation.
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
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
