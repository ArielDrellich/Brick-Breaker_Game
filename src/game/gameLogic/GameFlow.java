package game.gameLogic;

import game.levels.GameOverDisplay;
import game.levels.WinDisplay;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Controls the playing of the game.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter scoreCounter = new Counter(0);

    /**
     * Constructor.
     * @param animationRunner animation runner.
     * @param gui gui.
     */
    public GameFlow(AnimationRunner animationRunner, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = gui.getKeyboardSensor();
        this.gui = gui;
    }

    /**
     * Runs the levels.
     * @param levels list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, scoreCounter);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }

            if (level.ballsLeft() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                        new GameOverDisplay(this.keyboardSensor, scoreCounter)));
                gui.close();
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new WinDisplay(this.keyboardSensor, scoreCounter)));
        gui.close();
    }
}