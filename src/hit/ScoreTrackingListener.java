package hit;

import sprites.Ball;
import sprites.Block;

/**
 * Represents a ScoreTrackingListener , defined by Counter current score.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-2
 */
//206531139 Izhak Ben David
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor of new Score tracking listener.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
