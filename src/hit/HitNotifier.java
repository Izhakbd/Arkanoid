package hit;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the HitListener we add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener we remove.
     */
    void removeHitListener(HitListener hl);
}
