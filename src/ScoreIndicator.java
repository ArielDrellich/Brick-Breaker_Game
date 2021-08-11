import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Sprite that shows score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int guiWidth;

    /**
     * constuctor.
     * @param score keeps track of score.
     * @param guiWidth width of the playscreen.
     */
    public ScoreIndicator(Counter score, int guiWidth) {
        this.score = score;
        this.guiWidth = guiWidth;
    }

    /**
     * Draws score on screen.
     * @param d surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        String s = "Score: " + score.getValue();
        int fontSize = 18;
        d.setColor(Color.black);
        d.drawText(((guiWidth) / 2) - 30, 18, s, fontSize);
    }

    /**
     * needed for implementation but does nothing here.
     */
    public void timePassed() {

    }

    /**
     * adds sprite to game.
     * @param g game to add sprite to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

}
