import Animation.AnimationRunner;
import game.DirectHitLevel;
import game.Green3Level;
import game.LevelInformation;
import game.WideEasyLevel;
import game.GameFlow;


import java.util.ArrayList;

/**
 * The main class, who start the game.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 3-05-24
 */
//206531139 Izhak Ben David
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ArrayList<LevelInformation> levelsList = new ArrayList<>();
        LevelInformation directHit = new DirectHitLevel(); // level 1
        LevelInformation wideEasy = new WideEasyLevel(); // level 2
        LevelInformation green3 = new Green3Level(); // level 3
        if (args.length == 0) {
            levelsList.add(directHit);
            levelsList.add(wideEasy);
            levelsList.add(green3);
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levelsList.add(directHit);
                }
                if (args[i].equals("2")) {
                    levelsList.add(wideEasy);
                }
                if (args[i].equals("3")) {
                    levelsList.add(green3);
                }
            }
            // if all cells in array is not 1,2,3 (Strings ect).
            if (levelsList.size() == 0) {
                levelsList.add(directHit);
                levelsList.add(wideEasy);
                levelsList.add(green3);
            }
        }
        AnimationRunner ar = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(ar, ar.getGui().getKeyboardSensor());
        gameFlow.runLevels(levelsList);
    }
}
