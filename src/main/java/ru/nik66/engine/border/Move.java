package ru.nik66.engine.border;

import ru.nik66.engine.border.Board.Builder;
import ru.nik66.engine.pieces.Piece;

/**
 * Created by nkotkin on 3/12/17.
 * Moves.
 */
public abstract class Move {

    /**
     * Border.
     */
    private final Board board;
    /**
     * Moved piece.
     */
    private final Piece movedPiece;
    /**
     * Destination coordinate.
     */
    private final int destinationCoordinate;

    /**
     * Constructor for move initialization.
     * @param boardArg Board.
     * @param movedPieceArg Moved piece.
     * @param destinationCoordinateArg Destination coordinate.
     */
    private Move(final Board boardArg, final Piece movedPieceArg, final int destinationCoordinateArg) {
        this.board = boardArg;
        this.movedPiece = movedPieceArg;
        this.destinationCoordinate = destinationCoordinateArg;
    }

    /**
     * Getter for board.
     * @return board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Getter for movedPiece.
     * @return movedPiece.
     */
    Piece getMovedPiece() {
        return movedPiece;
    }

    /**
     * getter for destinationCoordinate.
     * @return destinationCoordinate.
     */
    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    /**
     * Execute move.
     * @return board.
     */
    public abstract Board execute();

    /**
     * Major move.
     */
    public static final class MajorMove extends Move {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         */
        public MajorMove(final Board boardArg, final Piece movedPieceArg, final int destinationCoordinateArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg);
        }

        /**
         * Execute move.
         *
         * @return board.
         */
        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.getBoard().getCurrentPlayer().getActivePieces()) {
                //TODO hashcode and equals for pieces
                if (!this.getMovedPiece().equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            //move the moved piece!
            builder.setPiece(null);
            builder.setMoveMaker(this.getBoard().getCurrentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }

    /**
     * Attack move.
     */
    public static final class AttackMove extends Move {

        /**
         * Attacked peace.
         */
        private final Piece attackedPiece;

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         * @param attackedPieceArg         Attacked piece.
         */
        public AttackMove(final Board boardArg, final Piece movedPieceArg, final int destinationCoordinateArg,
                   final Piece attackedPieceArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg);
            this.attackedPiece = attackedPieceArg;
        }

        /**
         * Execute move.
         *
         * @return board.
         */
        @Override
        public Board execute() {
            return null;
        }
    }

}
