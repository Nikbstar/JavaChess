package ru.nik66.engine.border;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.pieces.Rook;
import ru.nik66.engine.pieces.Knight;
import ru.nik66.engine.pieces.Bishop;
import ru.nik66.engine.pieces.Queen;
import ru.nik66.engine.pieces.King;
import ru.nik66.engine.pieces.Pawn;
import ru.nik66.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
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
     * White pieces collection.
     */
    private final Collection<Piece> whitePieces;
    /**
     * Black pieces collection.
     */
    private final Collection<Piece> blackPieces;

    /**
     * Private default constructor.
     * @param builderArg builder.
     */
    private Board(Builder builderArg) {
        this.gameBoard = createGameBoard(builderArg);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
    }

    /**
     * Calculate white and black active pieces.
     * @param gameBoardArg game board.
     * @param allianceArg alliance.
     * @return  Active pieces list.
     */
    private Collection<Piece> calculateActivePieces(final List<Tile> gameBoardArg, final Alliance allianceArg) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoardArg) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getPieceAlliance() == allianceArg) {
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    /**
     * Get Tile coordinate.
     * @param tileCoordinateArg tile Coordinate.
     * @return null.
     */
    public Tile getTile(final int tileCoordinateArg) {
        return this.gameBoard.get(tileCoordinateArg);
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
        final Builder builder = new Builder();
        // Black layout.
        builder.setPiece(new Rook(BoardUtils.ROOK_BLACK_LEFT, Alliance.BLACK));
        builder.setPiece(new Knight(BoardUtils.KNIGHT_BLACK_LEFT, Alliance.BLACK));
        builder.setPiece(new Bishop(BoardUtils.BISHOP_BLACK_LEFT, Alliance.BLACK));
        builder.setPiece(new Queen(BoardUtils.QUEEN_BLACK, Alliance.BLACK));
        builder.setPiece(new King(BoardUtils.KING_BLACK, Alliance.BLACK));
        builder.setPiece(new Bishop(BoardUtils.BISHOP_BLACK_RIGHT, Alliance.BLACK));
        builder.setPiece(new Knight(BoardUtils.KNIGHT_BLACK_RIGHT, Alliance.BLACK));
        builder.setPiece(new Rook(BoardUtils.ROOK_BLACK_RIGHT, Alliance.BLACK));
        for (int i = BoardUtils.POWN_BLACK_FIRST; i <= BoardUtils.POWN_BLACK_LAST; i++) {
            builder.setPiece(new Pawn(i, Alliance.BLACK));
        }
        // White layout.
        for (int i = BoardUtils.POWN_WHITE_FIRST; i <= BoardUtils.POWN_WHITE_LAST; i++) {
            builder.setPiece(new Pawn(i, Alliance.WHITE));
        }
        builder.setPiece(new Rook(BoardUtils.ROOK_WHITE_LEFT, Alliance.WHITE));
        builder.setPiece(new Knight(BoardUtils.KNIGHT_WHITE_LEFT, Alliance.WHITE));
        builder.setPiece(new Bishop(BoardUtils.BISHOP_WHITE_LEFT, Alliance.WHITE));
        builder.setPiece(new Queen(BoardUtils.QUEEN_WHITE, Alliance.WHITE));
        builder.setPiece(new King(BoardUtils.KING_WHITE, Alliance.WHITE));
        builder.setPiece(new Bishop(BoardUtils.BISHOP_WHITE_RIGHT, Alliance.WHITE));
        builder.setPiece(new Knight(BoardUtils.KNIGHT_WHITE_RIGHT, Alliance.WHITE));
        builder.setPiece(new Rook(BoardUtils.ROOK_WHITE_RIGHT, Alliance.WHITE));
        // White to move.
        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();
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
