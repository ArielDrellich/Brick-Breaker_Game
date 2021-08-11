package game.levels;

import game.geometryLogic.Block;
import game.geometryLogic.Rectangle;
import game.gameLogic.LevelInformation;
import game.geometryLogic.Point;
import game.gameLogic.Sprite;
import game.geometryLogic.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 2nd level.
 */
public class WideEasy implements LevelInformation {
    private int guiWidth = 800;
    private int guiHeight = 600;
    private int wallWidth = 15;
    private int blockHeight = 20;
    private double blockWidth = (guiWidth - 2 * wallWidth) / 15.0;

    /**
     * number of balls in game.
     *
     * @return nuber of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * List of velocities to start balls at.
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityArrayList = new ArrayList<Velocity>();
        for (int i = 1; i <= this.numberOfBalls(); i++) {
            velocityArrayList.add(Velocity.fromAngleAndSpeed(-45 + (i * 90) / (numberOfBalls() + 1), 5));
        }
        return velocityArrayList;
    }

    /**
     * Paddle speed.
     *
     * @return speed.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     *
     * @return width of paddle.
     */
    public int paddleWidth() {
        return 500;
    }

    /**
     *
     * @return level name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     *
     * @return background wanted for level
     */
    public Sprite getBackground() {
        return new SunBackground();
    }

    /**
     * List of block in the level.
     *
     * @return the list.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            Color color = Color.red;
            if (i > 1 && i <= 3) {
                color = Color.orange;
            }
            if (i > 3 && i <= 5) {
                color = Color.yellow;
            }
            if (i > 5 && i <= 8) {
                color = Color.green;
            }
            if (i > 8 && i <= 10) {
                color = Color.blue;
            }
            if (i > 10 && i <= 12) {
                color = Color.pink;
            }
            if (i > 12) {
                color = Color.cyan;
            }
            blockList.add(new Block(
                    new Rectangle(new Point(wallWidth + blockWidth * i, guiHeight / 2), blockWidth, blockHeight),
                    color));
        }
        return blockList;
    }

    /**
     *
     * @return amount of non special blocks that need to be broken to win.
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
