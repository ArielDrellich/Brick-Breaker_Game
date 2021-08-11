package game.gameLogic;
import biuoop.DrawSurface;

/**
 * Interface for animations.
 */
public interface Animation {
    /**
     * Does one frame.
     * @param d surface to draw on.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Returns if should stop.
     * @return if should stop.
     */
    boolean shouldStop();
}