/**
 * Listens to hits.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit Block being hit.
     * @param hitter The one hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}