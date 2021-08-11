package game.listeners;

import game.gameLogic.Counter;
import game.gameLogic.GameLevel;
import game.geometryLogic.Ball;
import game.geometryLogic.Block;

import java.awt.Color;

/** a BlockRemover is in charge of removing blocks from the game,
 *  as well as keeping count of the number of blocks that remain. */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel game.
     * @param remainingBlocks blocks to remove.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Note: extra ball blocks and ball killer blocks don't count as blocks needed to beat the game
     * and therefore do not lower the block counter.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        if (!(beingHit.getColor().equals(Color.black) || beingHit.getColor().equals(Color.magenta))) {
            remainingBlocks.decrease(1);
        }
    }
}