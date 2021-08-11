package game.listeners;

import game.gameLogic.Counter;
import game.gameLogic.GameLevel;
import game.geometryLogic.Ball;
import game.geometryLogic.Block;

/**
 * Removes ball from game when black block is hit.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel game.
     * @param remainingBalls blocks to remove.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Note: extra ball blocks and ball killer blocks don't count as blocks needed to beat the game
     * and therefore do not lower the block counter.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}