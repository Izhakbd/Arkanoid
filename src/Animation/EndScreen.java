package Animation;
import biuoop.DrawSurface;

/**
 * Represents a End Screen, defined by keyboard ,ar,message,score and stop.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-9
 */
//206531139 Izhak Ben David
public class EndScreen implements Animation {
    private String message;
    private int score;
    private boolean stop;

    /**
     * Constructor of new Pause screen.
     *
     * @param message the message who printed to screen.
     * @param score   the score of game.
     */
    public EndScreen(String message, int score) {
        this.stop = false;
        this.message = message;
        this.score = score;
    }

    /**
     * print the message to screen.
     *
     * @param d the d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, this.message + this.score, 32);
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop; }
}
