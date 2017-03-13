package ru.nik66.engine.pieces;

import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;

import java.util.Collection;

/**
 * Created by nkotkin on 3/10/17.
 * Piece class.
 */
public abstract class Piece {

    /**
     * int Piece position.
     */
    private final int piecePosition;
    /**
     * Alliance Piece alliance.
     */
    private final Alliance pieceAlliance;
    /**
     * First move check.
     */
    private final boolean isFirstMove;

    /**
     * getter for piecePosition.
     * @return piecePosition.
     */
    public int getPiecePosition() {
        return piecePosition;
    }

    /**
     * Getter for pieceAlliance.
     * @return pieceAlliance.
     */
    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    /**
     * getter for isFirstMove.
     * @return true if isFirstMove.
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /**
     * Piece Initialization Constructor.
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg Piece alliance.
     */
    Piece(final int piecePositionArg, final Alliance pieceAllianceArg) {
        this.piecePosition = piecePositionArg;
        this.pieceAlliance = pieceAllianceArg;
        // TODO more work here!!!
        this.isFirstMove = false;
    }

    /**
     * Moves collection.
     * @param boardArg Border.
     * @return Move.
     */
    public abstract Collection<Move> calculateLegalMoves(Board boardArg);

    /**
     * Pieces to string.
     */
    public enum PieceType {

        /**
         * Pawn to string.
         */
        PAWN("P"),
        /**
         * Knight to string.
         */
        KNIGHT("N"),
        /**
         * Bishop to string.
         */
        BISHOP("B"),
        /**
         * Rook to string.
         */
        ROOK("R"),
        /**
         * Queen to string.
         */
        QUEEN("Q"),
        /**
         * King to string.
         */
        KING("K");

        /**
         * piece name.
         */
        private String pieceName;

        /**
         * Piece name initializatoin.
         * @param pieceNameArg piece name.
         */
        PieceType(final String pieceNameArg) {
            this.pieceName = pieceNameArg;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

    }

}
