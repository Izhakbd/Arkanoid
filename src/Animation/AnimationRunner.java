package Animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Represents a AnimationRunner, defined by GUI , sleeper and framePerSecond.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-7
 */
//206531139 Izhak Ben David
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private static final int RIGHT_AND_LEFT_BORDER = 600;
    private static final int UP_AND_DOWN_BORDER = 800;

    /**
     * Constructor of new Animation runner.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", UP_AND_DOWN_BORDER, RIGHT_AND_LEFT_BORDER);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Gets gui.
     *
     * @return the gui.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Run.
     *
     * @param animation the animation object.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Sets frames per second.
     *
     * @param framesPerSecond the frames per second field.
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }
}
