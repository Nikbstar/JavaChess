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

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
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

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }
    };

    /**
     * Get alliance direction.
     * @return -1 if alliance is white and 1 if alliance is black.
     */
    public abstract int getDirection();

    /**
     * Check is white.
     * @return true if is white.
     */
    public abstract boolean isWhite();

    /**
     * Cjeck is black.
     * @return true if is black.
     */
    public abstract boolean isBlack();
}
