package ru.nik66.engine.border;

import ru.nik66.engine.border.Board.Builder;
import ru.nik66.engine.pieces.Pawn;
import ru.nik66.engine.pieces.Piece;
import ru.nik66.engine.pieces.Rook;

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
     * Null move.
     */
    public static final Move NULL_MOVE = new NullMove();

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getMovedPiece().hashCode();
        result = prime * result + this.getDestinationCoordinate();
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        boolean result;
        if (this == other) {
            result = true;
        } else {
            if (!(other instanceof Move)) {
                result = false;
            } else {
                final Move otherMove = (Move) other;
                result = getDestinationCoordinate() == otherMove.getDestinationCoordinate()
                        && getMovedPiece().equals(otherMove.getMovedPiece());
            }
        }
        return result;
    }

    /**
     * Get current coordinate.
     * @return current coordinate.
     */
    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    /**
     * Getter for board.
     * @return board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for movedPiece.
     * @return movedPiece.
     */
    public Piece getMovedPiece() {
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
     * Is attack.
     * @return true if is.
     */
    public boolean isAttack() {
        return false;
    }

    /**
     * is castling move.
     * @return true if is.
     */
    public boolean isCastlingMove() {
        return false;
    }

    /**
     * Get attacked piece.
     * @return attacked piece.
     */
    public Piece getAttackedPiece() {
        return null;
    }

    /**
     * Execute move.
     *
     * @return board.
     */
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
        builder.setPiece(this.getMovedPiece().movePiece(this));
        builder.setMoveMaker(this.getBoard().getCurrentPlayer().getOpponent().getAlliance());
        return builder.build();
    }

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

    }

    /**
     * Attack move.
     */
    public static class AttackMove extends Move {

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

        @Override
        public int hashCode() {
            return this.getAttackedPiece().hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(final Object other) {
            boolean result;
            if (this == other) {
                result = true;
            } else {
                if (!(other instanceof AttackMove)) {
                    result = false;
                } else {
                    final AttackMove otherAttackMove = (AttackMove) other;
                    result = super.equals(otherAttackMove)
                            && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
                }
            }
            return result;
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

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }

    }

    /**
     * Pawn move.
     */
    public static final class PawnMove extends Move {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         */
        public PawnMove(final Board boardArg, final Piece movedPieceArg, final int destinationCoordinateArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg);
        }

    }

    /**
     * Pawn attack move.
     */
    public static class PawnAttackMove extends AttackMove {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         * @param attackedPieceArg Attacked piece.
         */
        public PawnAttackMove(final Board boardArg, final Piece movedPieceArg,
                              final int destinationCoordinateArg, final Piece attackedPieceArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg, attackedPieceArg);
        }

    }

    /**
     * Pawn en passant attack move.
     */
    public static final class PawnEnPassantAttackMove extends PawnAttackMove {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         * @param attackedPieceArg Attacked piece.
         */
        public PawnEnPassantAttackMove(final Board boardArg, final Piece movedPieceArg,
                              final int destinationCoordinateArg, final Piece attackedPieceArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg, attackedPieceArg);
        }

    }

    /**
     * Pawn jump.
     */
    public static final class PawnJump extends Move {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         */
        public PawnJump(final Board boardArg, final Piece movedPieceArg, final int destinationCoordinateArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.getBoard().getCurrentPlayer().getActivePieces()) {
                if (!this.getMovedPiece().equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn) this.getMovedPiece().movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.getBoard().getCurrentPlayer().getOpponent().getAlliance());
            return builder.build();
        }

    }

    /**
     * Major move.
     */
    abstract static class CastleMove extends Move {

        /**
         * Castle rook.
         */
        private final Rook castleRook;
        /**
         * Castle rook start.
         */
        private final int castleRookStart;
        /**
         * Castle rook destination.
         */
        private final int castleRookDestination;

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         * @param castleRookArg            Castle Rook.
         * @param castleRookStartArg       Castle Rook start.
         * @param castleRookDestinationArg Castle Rook destination.
         */
        CastleMove(final Board boardArg, final Piece movedPieceArg, final int destinationCoordinateArg,
                   final Rook castleRookArg, final int castleRookStartArg, final int castleRookDestinationArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg);
            this.castleRook = castleRookArg;
            this.castleRookStart = castleRookStartArg;
            this.castleRookDestination = castleRookDestinationArg;
        }

        /**
         * Getter for castleRook.
         * @return castleRook.
         */
        public Rook getCastleRook() {
            return this.castleRook;
        }

        /**
         * Check castling move.
         * @return true if it is.
         */
        @Override
        public boolean isCastlingMove() {
            return true;
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.getBoard().getCurrentPlayer().getActivePieces()) {
                if (!this.getMovedPiece().equals(piece) && !this.castleRook.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            builder.setPiece(this.getMovedPiece().movePiece(this));
            //TODO look into the first move on normal pieces.
            builder.setPiece(new Rook(this.castleRookDestination, this.castleRook.getPieceAlliance()));
            builder.setMoveMaker(this.getBoard().getCurrentPlayer().getOpponent().getAlliance());
            return builder.build();
        }

    }

    /**
     * King side castle move..
     */
    public static final class KingSideCastleMove extends CastleMove {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         * @param castleRookArg            the castle rook arg
         * @param castleRookStartArg       the castle rook start arg
         * @param castleRookDestinationArg the castle rook destination arg
         */
        public KingSideCastleMove(final Board boardArg,
                                  final Piece movedPieceArg,
                                  final int destinationCoordinateArg,
                                  final Rook castleRookArg,
                                  final int castleRookStartArg,
                                  final int castleRookDestinationArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg,
                    castleRookArg, castleRookStartArg, castleRookDestinationArg);
        }

        @Override
        public String toString() {
            return "0-0";
        }

    }

    /**
     * Queen side castle move..
     */
    public static final class QueenSideCastleMove extends CastleMove {

        /**
         * Constructor for move initialization.
         *
         * @param boardArg                 Board.
         * @param movedPieceArg            Moved piece.
         * @param destinationCoordinateArg Destination coordinate.
         * @param castleRookArg            the castle rook arg
         * @param castleRookStartArg       the castle rook start arg
         * @param castleRookDestinationArg the castle rook destination arg
         */
        public QueenSideCastleMove(final Board boardArg,
                                   final Piece movedPieceArg,
                                   final int destinationCoordinateArg,
                                   final Rook castleRookArg,
                                   final int castleRookStartArg,
                                   final int castleRookDestinationArg) {
            super(boardArg, movedPieceArg, destinationCoordinateArg,
                    castleRookArg, castleRookStartArg, castleRookDestinationArg);
        }

        @Override
        public String toString() {
            return "0-0-0";
        }

    }

    /**
     * Null move..
     */
    public static final class NullMove extends Move {

        /**
         * Constructor for move initialization.
         */
        public NullMove() {
            super(null, null, -1);
        }

        @Override
        public Board execute() {
            throw new RuntimeException("Cannot execute the null move!");
        }

    }

    /**
     * Move factory.
     */
    public static class MoveFactory {

        /**
         * Private constructor.
         */
        private MoveFactory() {
            throw new RuntimeException("Not instantiable!");
        }

        /**
         * Create move.
         * @param boardArg board.
         * @param currentCoordinateArg current coordinate.
         * @param destinationCoordinateArg destination coordinate.
         * @return legal move.
         */
        public static Move createMove(final Board boardArg,
                                      final int currentCoordinateArg,
                                      final int destinationCoordinateArg) {
            for (final Move move : boardArg.getAllLegalMoves()) {
                if (move.getCurrentCoordinate() == currentCoordinateArg
                        && move.getDestinationCoordinate() == destinationCoordinateArg) {
                    return move;
                }
            }
            return NULL_MOVE;
        }

    }
}
