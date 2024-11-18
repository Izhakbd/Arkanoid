package game;

import sprites.Block;
import sprites.Sprite;
import velocity.Velocity;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls in the level.
     *
     * @return The number of balls in the level.
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return the list of velocity of each ball.
     */

    List<Velocity> initialBallVelocities();

    /**
     * return The paddle speed.
     *
     * @return The paddle speed.
     */
    int paddleSpeed();

    /**
     * return The paddle width.
     *
     * @return The paddle width.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return The level name.
     */
    String levelName();

    /**
     * Returns a sprites list with the background of the level.
     *
     * @return The background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     *  its size, color and location.
     *
     * @return The list of blocks that make up this level.
     */

    List<Block> blocks();

    /**
     * Number of blocks that should be removed,
     * before the level is considered to be "cleared".
     *
     * @return The Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
