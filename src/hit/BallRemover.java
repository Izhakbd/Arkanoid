package hit;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * Represents a BallRemover, a BallRemover is in charge of removing balls from the game
 * , as well as keeping count of the number of ball that remain.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-24
 */
//206531139 Izhak Ben David
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBall;

    /**
     * Constructor of new Ball remover.
     *
     * @param gameLevel        the game
     * @param removedBall the removed ball
     */
    public BallRemover(GameLevel gameLevel, Counter removedBall) {
        this.gameLevel = gameLevel;
        this.remainingBall = removedBall;
    }

    /**
     * Gets the counter of ballRemove.
     *
     * @return return the counter of ballRemove.
     */
    public Counter getRemainingBall() {
        return this.remainingBall;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // update the number of remainingBlocks.
        this.remainingBall.decrease(1);
        // remove the being hit from game.
        hitter.removeFromGame(this.gameLevel);
    }
}
