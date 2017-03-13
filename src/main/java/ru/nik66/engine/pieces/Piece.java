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
     * Piece type.
     */
    private final PieceType pieceType;
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
     * Getter for piece type.
     * @return piece Type.
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

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
     * @param pieceTypeArg  Piece type.
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg Piece alliance.
     */
    Piece(final PieceType pieceTypeArg, final int piecePositionArg, final Alliance pieceAllianceArg) {
        this.pieceType = pieceTypeArg;
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
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        /**
         * Knight to string.
         */
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        /**
         * Bishop to string.
         */
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        /**
         * Rook to string.
         */
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        /**
         * Queen to string.
         */
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        /**
         * King to string.
         */
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

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

        /**
         * Check King.
         * @return true if Piece is king.
         */
        public abstract boolean isKing();

    }

}
