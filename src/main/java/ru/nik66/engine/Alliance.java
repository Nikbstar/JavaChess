package ru.nik66.engine;

/**
 * Created by nkotkin on 3/12/17.
 * Alliances enum.
 */
public enum Alliance {
    /**
     * White alliance.
     */
    WHITE {
        @Override
        public int getDirection() {
            return -1;
        }
    },
    /**
     * Black alliance.
     */
    BLACK {
        @Override
        public int getDirection() {
            return 1;
        }
    };

    /**
     * Get alliance direction.
     * @return -1 if alliance is white and 1 if alliance is black.
     */
    public abstract int getDirection();
}
