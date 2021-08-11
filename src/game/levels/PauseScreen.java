package game.levels;

import game.gameLogic.Animation;
import biuoop.DrawSurface;

/**
 * Pause screen.
 */
public class PauseScreen implements Animation {
    /**
     * Shows pause screen.
     *
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "Paused ---- Press space to continue.", 32);
    }

    /**
     *
     * @return if should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}
