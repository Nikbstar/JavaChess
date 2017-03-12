package ru.nik66.engine.border;

/**
 * Created by nkotkin on 3/12/17.
 */
public class BoardUtils {

    /**
     * Zero.
     */
    public static final int ZERO = 0;
    /**
     * One.
     */
    public static final int ONE = 1;
    /**
     * Two.
     */
    public static final int TWO = 2;
    /**
     * Three.
     */
    public static final int THREE = 3;
    /**
     * Four.
     */
    public static final int FOUR = 4;
    /**
     * Five.
     */
    public static final int FIVE = 5;
    /**
     * Six.
     */
    public static final int SIX = 6;
    /**
     * Seven.
     */
    public static final int SEVEN = 7;
    /**
     * Border size.
     */
    public static final int BOARD_SIZE = 64;
    /**
     * First column.
     */
    public static final boolean[] FIRST_COLUMN = null;
    /**
     * Second column.
     */
    public static final boolean[] SECOND_COLUMN = null;
    /**
     * Seventh column.
     */
    public static final boolean[] SEVENTH_COLUMN = null;
    /**
     * Eighth column.
     */
    public static final boolean[] EIGHTH_COLUMN = null;

    /**
     * Do not create this class.
     */
    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    /**
     * Check that tile coordinate belongs the border.
     * @param coordinateArg coordinate.
     * @return true if coordinate belongs the board.
     */
    public static boolean isValidTileCoordinate(int coordinateArg) {
        return coordinateArg >= 0 && coordinateArg <= BOARD_SIZE;
    }
}
