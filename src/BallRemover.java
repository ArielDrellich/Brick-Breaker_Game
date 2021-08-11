/**
 * Removes ball from game when black block is hit.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game game.
     * @param remainingBalls blocks to remove.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Note: extra ball blocks and ball killer blocks don't count as blocks needed to beat the game
     * and therefore do not lower the block counter.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}