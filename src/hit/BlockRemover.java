package hit;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * Represents a BlockRemover, a BlockRemover is in charge of removing blocks from the game
 * , as well as keeping count of the number of blocks that remain.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-24
 */
//206531139 Izhak Ben David
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor of new Block remover.
     *
     * @param gameLevel          the game.
     * @param removedBlocks the counter that count removed blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // update the number of remainingBlocks.
        this.remainingBlocks.decrease(1);
        // remove the being hit from game.
        beingHit.removeFromGame(this.gameLevel);
        // remove scoreTrackingListener from list HitListeners of beingHit.
        beingHit.removeHitListener(beingHit.getHitListeners().get(1));
        // remove blockRemover from list HitListeners of beingHit.
        beingHit.removeHitListener(beingHit.getHitListeners().get(0));
    }

    /**
     * Gets the counter of blockRemove.
     *
     * @return return the counter of blockRemove.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

}
