package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Represents a KeyPressStoppableAnimation, defined by sensor, key,animation and stop.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-9
 */
//206531139 Izhak Ben David
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
