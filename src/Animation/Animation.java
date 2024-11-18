package Animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the drawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return true if should stop ,else,false.
     */
    boolean shouldStop();
}
