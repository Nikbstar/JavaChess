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
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    /**
     * Second column.
     */
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    /**
     * Seventh column.
     */
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    /**
     * Eighth column.
     */
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    /**
     * Second row.
     */
    public static final boolean[] SECOND_ROW = initRow(8);
    /**
     * Seventh row.
     */
    public static final boolean[] SEVENTH_ROW = initRow(48);

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
    public static final int PAWN_BLACK_FIRST = 8;
    /**
     * Black second Pawn.
     */
    public static final int PAWN_BLACK_SECOND = 9;
    /**
     * Black third Pawn.
     */
    public static final int PAWN_BLACK_THIRD = 10;
    /**
     * Black fourth Pawn.
     */
    public static final int PAWN_BLACK_FOURTH = 11;
    /**
     * Black fifth Pawn.
     */
    public static final int PAWN_BLACK_FIFTH = 12;
    /**
     * Black sixth Pawn.
     */
    public static final int PAWN_BLACK_SIXTH = 13;
    /**
     * Black seventh Pawn.
     */
    public static final int PAWN_BLACK_SEVENTH = 14;
    /**
     * Black eighth Pawn.
     */
    public static final int PAWN_BLACK_EIGHTH = 15;
    /**
     * White first Pawn.
     */
    public static final int PAWN_WHITE_FIRST = 48;
    /**
     * White second Pawn.
     */
    public static final int PAWN_WHITE_SECOND = 49;
    /**
     * White third Pawn.
     */
    public static final int PAWN_WHITE_THIRD = 50;
    /**
     * White fourth Pawn.
     */
    public static final int PAWN_WHITE_FOURTH = 51;
    /**
     * White fifth Pawn.
     */
    public static final int PAWN_WHITE_FIFTH = 52;
    /**
     * White sixth Pawn.
     */
    public static final int PAWN_WHITE_SIXTH = 53;
    /**
     * White seventh Pawn.
     */
    public static final int PAWN_WHITE_SEVENTH = 54;
    /**
     * White last Pawn.
     */
    public static final int PAWN_WHITE_EIGHTH = 55;
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
     * Row initialization.
     * @param rowNumberArg Row number.
     * @return Boolean array with row coordinates.
     */
    private static boolean[] initRow(int rowNumberArg) {
        final boolean[] row = new boolean[BOARD_SIZE];
        do {
            row[rowNumberArg] = true;
            rowNumberArg++;
        } while (rowNumberArg % NUM_TILES_PER_ROW != ZERO);
        return row;
    }
    /**
     * Check that tile coordinate belongs the border.
     * @param coordinateArg coordinate.
     * @return true if coordinate belongs the board.
     */
    public static boolean isValidTileCoordinate(final int coordinateArg) {
        return coordinateArg >= ZERO && coordinateArg < BOARD_SIZE;
    }
}
