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
     * Eight.
     */
    public static final int EIGHT = 8;

    /**
     * Num tiles per row.
     */
    public static final int NUM_TILES_PER_ROW = 8;
    /**
     * Border size.
     */
    public static final int BOARD_SIZE = 64;

    /**
     * First column.
     */
    public static final boolean[] FIRST_COLUMN = initColumn(ZERO);
    /**
     * Second column.
     */
    public static final boolean[] SECOND_COLUMN = initColumn(ONE);
    /**
     * Seventh column.
     */
    public static final boolean[] SEVENTH_COLUMN = initColumn(SIX);
    /**
     * Eighth column.
     */
    public static final boolean[] EIGHTH_COLUMN = initColumn(SEVEN);

    /**
     * Do not create this class.
     */
    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    /**
     * Column initialization.
     * @param columnNumberArg Column number.
     * @return Boolean array with column coordinates.
     */
    private static boolean[] initColumn(int columnNumberArg) {
        final boolean[] column = new boolean[BOARD_SIZE];
        do {
            column[columnNumberArg] = true;
            columnNumberArg += NUM_TILES_PER_ROW;
        } while (columnNumberArg < BOARD_SIZE);
        return column;
    }

    /**
     * Check that tile coordinate belongs the border.
     * @param coordinateArg coordinate.
     * @return true if coordinate belongs the board.
     */
    public static boolean isValidTileCoordinate(final int coordinateArg) {
        return coordinateArg >= ZERO && coordinateArg <= BOARD_SIZE;
    }
}
