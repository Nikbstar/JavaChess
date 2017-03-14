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
     * Checked hash code.
     */
    private final int cachedHashCode;

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
        this.cachedHashCode = computeHashCode();
    }

    /**
     * Compute hash code.
     * @return Hash code.
     */
    private int computeHashCode() {
        final int prime = 31;
        int result = getPieceType().hashCode();
        result = prime * result + getPieceAlliance().hashCode();
        result = prime * result + getPiecePosition();
        if (isFirstMove()) {
            result = prime * result + 1;
        } else {
            result = prime * result + 0;
        }
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        boolean result;
        if (this == other) {
            result = true;
        } else {
            if (!(other instanceof Piece)) {
                result = false;
            } else {
                final Piece otherPiece = (Piece) other;
                result = getPiecePosition() == otherPiece.getPiecePosition()
                        && getPieceType() == otherPiece.getPieceType()
                        && getPieceAlliance() == otherPiece.getPieceAlliance()
                        && isFirstMove() == otherPiece.isFirstMove();
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

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
     * Moves collection.
     * @param boardArg Border.
     * @return Move.
     */
    public abstract Collection<Move> calculateLegalMoves(Board boardArg);

    /**
     * Move piece.
     * @param moveArg move.
     * @return piece.
     */
    public abstract Piece movePiece(Move moveArg);

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

            /**
             * Check Rook.
             *
             * @return true if Piece is Rook.
             */
            @Override
            public boolean isRook() {
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

            /**
             * Check Rook.
             *
             * @return true if Piece is Rook.
             */
            @Override
            public boolean isRook() {
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

            /**
             * Check Rook.
             *
             * @return true if Piece is Rook.
             */
            @Override
            public boolean isRook() {
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

            /**
             * Check Rook.
             *
             * @return true if Piece is Rook.
             */
            @Override
            public boolean isRook() {
                return true;
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

            /**
             * Check Rook.
             *
             * @return true if Piece is Rook.
             */
            @Override
            public boolean isRook() {
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

            /**
             * Check Rook.
             *
             * @return true if Piece is Rook.
             */
            @Override
            public boolean isRook() {
                return false;
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

        /**
         * Check Rook.
         * @return true if Piece is Rook.
         */
        public abstract boolean isRook();
    }

}
