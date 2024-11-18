package hit;

import sprites.Ball;
import sprites.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block who hit by ball.
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
