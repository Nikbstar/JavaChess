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
    },
    /**
     * Move is not legal.
     */
    ILLEGAL_MOVE {
        /**
         * Check is done.
         *
         * @return true if is done.
         */
        @Override
        boolean isDone() {
            return false;
        }
    },
    /**
     * Leaves player in check.
     */
    LEAVES_PLAYER_IN_CHECK {
        /**
         * Check is done.
         *
         * @return true if is done.
         */
        @Override
        boolean isDone() {
            return false;
        }
    };

    /**
     * Check is done.
     * @return true if is done.
     */
    abstract boolean isDone();

}
