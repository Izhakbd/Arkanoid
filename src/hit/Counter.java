package hit;

/**
 * Represents a Counter.
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-24
 */
//206531139 Izhak Ben David
public class Counter {
    private int counter;

    /**
     * Constructor of new Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increase the counter.
     *
     * @param number the number we add to counter.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * Decrease the counter.
     *
     * @param number the number we subtract from counter.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * Get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}
