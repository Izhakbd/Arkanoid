package Animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * Represents a CountdownAnimation Screen, defined by numOfSecond ,countFrom
 * and game screen.
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-7
 */
//206531139 Izhak Ben David
public class CountdownAnimation implements Animation {
    private boolean stop;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Constructor of new Countdown animation.
     *
     * @param numOfSeconds the num of seconds we count.
     * @param countFrom    the number we count from.
     * @param gameScreen   all sprites in game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
            this.gameScreen.drawAllOn(d);
            d.setColor(Color.MAGENTA);
            d.drawText(400, 300, String.valueOf(this.countFrom), 40);
            this.countFrom--;
            if (this.countFrom == 0) {
                this.stop = false;
            }
    }
    @Override
    public boolean shouldStop() {
        return !this.stop;
    }
}
