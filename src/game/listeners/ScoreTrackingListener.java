package game.listeners;

import game.gameLogic.Counter;
import game.geometryLogic.Ball;
import game.geometryLogic.Block;

/**
 * listener that keeps track of score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter counts score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Increases score when block is hit.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}