import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
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
     * runs through list of sprites and calls the timePassed function on each.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
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