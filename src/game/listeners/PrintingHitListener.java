package game.listeners;

import game.geometryLogic.Ball;
import game.geometryLogic.Block;

/**
 * test.
 */
public class PrintingHitListener implements HitListener {
    /**
     * test.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}