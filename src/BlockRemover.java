import java.awt.Color;

/** a BlockRemover is in charge of removing blocks from the game,
 *  as well as keeping count of the number of blocks that remain. */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game game.
     * @param remainingBlocks blocks to remove.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Note: extra ball blocks and ball killer blocks don't count as blocks needed to beat the game
     * and therefore do not lower the block counter.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        if (!(beingHit.getColor().equals(Color.black) || beingHit.getColor().equals(Color.white))) {
            remainingBlocks.decrease(1);
        }
    }
}