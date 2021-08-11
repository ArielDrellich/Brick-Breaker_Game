package game.levels;

import game.gameLogic.LevelInformation;
import game.gameLogic.Sprite;
import game.geometryLogic.Block;
import game.geometryLogic.Point;
import game.geometryLogic.Rectangle;
import game.geometryLogic.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 4th level.
 */
public class FinalFour implements LevelInformation {
    private int guiWidth = 800;
    private int guiHeight = 600;
    private int wallWidth = 15;
    private int blockHeight = 20;
    private int specialBlocksCount = 0;
    private double blockWidth = (guiWidth - 2 * wallWidth) / 15.0;

    /**
     * number of balls in game.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * List of velocities to start balls at.
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityArrayList = new ArrayList<Velocity>();
            velocityArrayList.add(Velocity.fromAngleAndSpeed(-35, 4));
            velocityArrayList.add(Velocity.fromAngleAndSpeed(10, 5));
            velocityArrayList.add(Velocity.fromAngleAndSpeed(55, 6));
        return velocityArrayList;
    }

    /**
     * Paddle speed.
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
        return 100;
    }

    /**
     *
     * @return level name.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     *
     * @return background wanted for level
     */
    public Sprite getBackground() {
        return new RainBackground();
    }

    /**
     * List of block in the level.
     * @return the list.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Color color = Color.gray;
                if (i == 1) {
                    color = Color.red;
                }
                if (i == 2) {
                    color = Color.yellow;
                }
                if (i == 3) {
                    color = Color.green;
                }
                if (i == 4) {
                    color = Color.white;
                }
                if (i == 5) {
                    color = Color.pink;
                }
                if (i == 6) {
                    color = Color.cyan;
                }

                if ((new Random()).nextInt(60 * (7 - i)) == 7) {
                    color = Color.black;
                    specialBlocksCount++;
                } else if ((new Random()).nextInt(30) == 7) {
                    color = Color.magenta;
                    specialBlocksCount++;
                }
                blockList.add(new Block(new Rectangle(new Point(guiWidth - wallWidth - blockWidth * (j + 1),
                        100 + blockHeight * i), blockWidth, blockHeight), color));
            }
        }
        return blockList;
    }

    /**
     *
     * @return amount of non special blocks that need to be broken to win.
     */
    public int numberOfBlocksToRemove() {
        return 105 - specialBlocksCount;
    }
}
