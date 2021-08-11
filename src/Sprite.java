import biuoop.DrawSurface;
/**
 * Interface for all sprites in game.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * adds sprite to game.
     * @param g game to add sprite to.
     */
    void addToGame(Game g);
}