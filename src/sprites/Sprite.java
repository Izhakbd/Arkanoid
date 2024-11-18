package sprites;

import game.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on the sprite to draw Surface.
     *
     * @param d the draw surface.
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();

    /**
     * Add a new sprite to game.
     *
     * @param g the Game.
     */
     void addToGame(GameLevel g);
}
