package ru.nik66.engine.border;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.pieces.Piece;

import java.util.List;
import java.util.Map;

/**
 * Created by nkotkin on 3/12/17.
 */
public class Board {

    /**
     * List of tiles.
     */
    private final List<Tile> gameBoard;

    /**
     * Private default constructor.
     * @param builderArg builder.
     */
    private Board(Builder builderArg) {
        this.gameBoard = createGameBoard(builderArg);
    }

    /**
     * Get Tile coordinate.
     * @param tileCoordinateArg tile Coordinate.
     * @return null.
     */
    public Tile getTile(final int tileCoordinateArg) {
        return null;
    }

    /**
     * Create game border.
     * @param builderArg builder.
     * @return tiles list.
     */
    private static List<Tile> createGameBoard(final Builder builderArg) {
        final Tile[] tiles = new Tile[BoardUtils.BOARD_SIZE];
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++) {
            tiles[i] = Tile.createTile(i, builderArg.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    /**
     * Create standard board.
     * @return borard with standard initial positions.
     */
    public static Board createStandardBoard() {
        return null;
    }

    /**
     * Builder.
     */
    public static class Builder {

        /**
         * Border config.
         */
        private Map<Integer, Piece> boardConfig;
        /**
         * Next move maker.
         */
        private Alliance nextMoveMaker;

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Setter for piece.
         * @param pieceArg piece.
         * @return this.
         */
        public Builder setPiece(final Piece pieceArg) {
            this.boardConfig.put(pieceArg.getPiecePosition(), pieceArg);
            return this;
        }

        /**
         * Setter for nextMoveMaker.
         * @param nextMoveMakerArg Alliance nextMovemaker.
         * @return this.
         */
        public Builder setMoveMaker(final Alliance nextMoveMakerArg) {
            this.nextMoveMaker = nextMoveMakerArg;
            return this;
        }

        /**
         * Border initialization.
         * @return new border.
         */
        public Board build() {
            return new Board(this);
        }

    }

}
