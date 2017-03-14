package ru.nik66.engine.border;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.pieces.Bishop;
import ru.nik66.engine.pieces.King;
import ru.nik66.engine.pieces.Knight;
import ru.nik66.engine.pieces.Pawn;
import ru.nik66.engine.pieces.Piece;
import ru.nik66.engine.pieces.Queen;
import ru.nik66.engine.pieces.Rook;
import ru.nik66.engine.player.BlackPlayer;
import ru.nik66.engine.player.Player;
import ru.nik66.engine.player.WhitePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nkotkin on 3/12/17.
 * board.
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
     * white player.
     */
    private final WhitePlayer whitePlayer;
    /**
     * black player.
     */
    private final BlackPlayer blackPlayer;
    /**
     * current player.
     */
    private final Player currentPlayer;

    /**
     * Private default constructor.
     * @param builderArg builder.
     */
    private Board(final Builder builderArg) {
        this.gameBoard = createGameBoard(builderArg);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        final Collection<Move> whiteStandardLegalMove = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMove = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMove, blackStandardLegalMove);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMove, blackStandardLegalMove);
        this.currentPlayer = builderArg.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Getter for white player.
     * @return whitePlayer
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Getter for black player.
     * @return blackPlayer.
     */
    public Player getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * Getter for current player.
     * @return current player.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Getter for black pieces.
     * @return black pieces collection.
     */
    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    /**
     * Getter for white pieces.
     * @return white pieces collection.
     */
    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    /**
     * Calculate legal moves.
     * @param piecesArg pieces collection.
     * @return legal moves list.
     */
    private Collection<Move> calculateLegalMoves(final Collection<Piece> piecesArg) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : piecesArg) {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * Calculate white and black active pieces.
     * @param gameBoardArg game board.
     * @param allianceArg alliance.
     * @return  Active pieces list.
     */
    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoardArg, final Alliance allianceArg) {
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
     * @return game board.
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
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_FIRST, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_SECOND, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_THIRD, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_FOURTH, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_FIFTH, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_SIXTH, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_SEVENTH, Alliance.BLACK));
        builder.setPiece(new Pawn(BoardUtils.PAWN_BLACK_EIGHTH, Alliance.BLACK));

        // White layout.
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_FIRST, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_SECOND, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_THIRD, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_FOURTH, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_FIFTH, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_SIXTH, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_SEVENTH, Alliance.WHITE));
        builder.setPiece(new Pawn(BoardUtils.PAWN_WHITE_EIGHTH, Alliance.WHITE));
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
     * Getter for all legal moves.
     * @return All legal moves.
     */
    public Iterable<Move> getAllLegalMoves() {
        return Iterables.unmodifiableIterable(Iterables.concat(this.getWhitePlayer().getLegalMoves(),
                this.getBlackPlayer().getLegalMoves()));
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
            this.boardConfig = new HashMap<>();
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
