package ru.nik66.engine.player;

/**
 * Created by nkotkin on 3/14/17.
 * move status.
 */
public enum MoveStatus {

    /**
     * Done move status.
     */
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    };

    /**
     * Check is done.
     * @return true if is done.
     */
    abstract boolean isDone();

}
