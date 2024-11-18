package game;

import Animation.AnimationRunner;
import Animation.EndScreen;
import Animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import hit.Counter;

import java.util.List;

/**
 * Represents a Game flow, defined by keyboard ,ar and score.
 * responsible on game flow.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-9
 */
//206531139 Izhak Ben David
public class GameFlow {
    private AnimationRunner ar;
    private biuoop.KeyboardSensor ks;
    private Counter score;

    /**
     * Constructor of new Game flow.
     *
     * @param ar the animation runner.
     * @param ks the keyboard.
     */
    public GameFlow(AnimationRunner ar, biuoop.KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter();
    }
    /**
     * Run all levels of game.
     *
     * @param levels list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.score);

            level.initialize();

            while (level.getBlockCounter() > 0 && level.getBallCounter() > 0) {
                level.run();
            }
            // if player lose.
            if (level.getBallCounter() == 0) {
                // wrap the pauseScreen with KeyPressStoppableAnimation decorator.
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor
                        .SPACE_KEY, new EndScreen("Game Over! Your score is ", this.score.getValue())));
                this.ar.getGui().close();
                break;
            }

        }
        // if player pass al levels successfully.
        // wrap the pauseScreen with KeyPressStoppableAnimation decorator.
        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor
                .SPACE_KEY, new EndScreen("You Win! Your score is ", this.score.getValue())));
        this.ar.getGui().close();
    }

}
