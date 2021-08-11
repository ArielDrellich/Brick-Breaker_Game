import game.gameLogic.AnimationRunner;
import game.gameLogic.GameFlow;
import game.gameLogic.LevelInformation;
import game.levels.DirectHit;
import game.levels.FinalFour;
import game.levels.Green3;
import game.levels.WideEasy;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Game main method.
 */
public class Ass6Game {
    /**
     * initiallizes and runs the game.
     *
     * @param args aruments from command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levels.add(new DirectHit());
                }
                if (args[i].equals("2")) {
                    levels.add(new WideEasy());
                }
                if (args[i].equals("3")) {
                    levels.add(new Green3());
                }
                if (args[i].equals("4")) {
                    levels.add(new FinalFour());
                }
            }
        }
        GameFlow gameFlow = new GameFlow(animationRunner, gui);
        gameFlow.runLevels(levels);
    }
}
