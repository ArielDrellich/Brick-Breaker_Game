package game.gameLogic;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * Keeps track of all Sprites in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();
    /**
    *
    * @param s sprite to add to game.
    */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes sprite from game.
     * @param s sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * runs through list of sprites and calls the timePassed function on each.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprite = new ArrayList<Sprite>(sprites);
        for (Sprite s : sprite) {
            s.timePassed();
        }
    }

    /**
     * @param d surface to draw on.
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}