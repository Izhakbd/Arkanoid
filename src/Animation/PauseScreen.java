package Animation;

import biuoop.DrawSurface;

/**
 * Represents a Pause Screen, defined by keyboard and stop.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-7
 */
//206531139 Izhak Ben David
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor of new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
