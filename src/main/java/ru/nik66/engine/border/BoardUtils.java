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
     * Second row.
     */
    public static final boolean[] SECOND_ROW = null;
    /**
     * Seventh row.
     */
    public static final boolean[] SEVENTH_ROW = null;

    // Standard pieces positions.
    /**
     * Black left Rook.
     */
    public static final int ROOK_BLACK_LEFT = 0;
    /**
     * Black left Knight.
     */
    public static final int KNIGHT_BLACK_LEFT = 1;
    /**
     * Black left Bishop.
     */
    public static final int BISHOP_BLACK_LEFT = 2;
    /**
     * Black Queen.
     */
    public static final int QUEEN_BLACK = 3;
    /**
     * Black King.
     */
    public static final int KING_BLACK = 4;
    /**
     * Black right Bishop.
     */
    public static final int BISHOP_BLACK_RIGHT = 5;
    /**
     * Black right Knight.
     */
    public static final int KNIGHT_BLACK_RIGHT = 6;
    /**
     * Black right Rook.
     */
    public static final int ROOK_BLACK_RIGHT = 7;
    /**
     * Black first Pawn.
     */
    public static final int POWN_BLACK_FIRST = 8;
    /**
     * Black last Pawn.
     */
    public static final int POWN_BLACK_LAST = 15;
    /**
     * White first Pawn.
     */
    public static final int POWN_WHITE_FIRST = 48;
    /**
     * White last Pawn.
     */
    public static final int POWN_WHITE_LAST = 55;
    /**
     * White left Rook.
     */
    public static final int ROOK_WHITE_LEFT = 56;
    /**
     * White left Knight.
     */
    public static final int KNIGHT_WHITE_LEFT = 57;
    /**
     * White left Bishop.
     */
    public static final int BISHOP_WHITE_LEFT = 58;
    /**
     * White Queen.
     */
    public static final int QUEEN_WHITE = 59;
    /**
     * White King.
     */
    public static final int KING_WHITE = 60;
    /**
     * White right Bishop.
     */
    public static final int BISHOP_WHITE_RIGHT = 61;
    /**
     * White right Knight.
     */
    public static final int KNIGHT_WHITE_RIGHT = 62;
    /**
     * White right Rook.
     */
    public static final int ROOK_WHITE_RIGHT = 63;

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
