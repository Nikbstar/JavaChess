package ru.nik66.engine.player;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.pieces.King;
import ru.nik66.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nkotkin on 3/13/17.
 * Player.
 */
public abstract class Player {

    /**
     * board.
     */
    private final Board board;
    /**
     * King.
     */
    private final King playerKing;
    /**
     * legal moves.
     */
    private final Collection<Move> legalMoves;
    /**
     * Check a check.
     */
    private final boolean isInCheck;


    /**
     * Player initialization constructor.
     * @param boardArg board.
     * @param legalMovesArg legal moves.
     * @param opponentMoves opponent moves.
     */
    Player(final Board boardArg, final Collection<Move> legalMovesArg, final Collection<Move> opponentMoves) {
        this.board = boardArg;
        this.playerKing = establishKing();
        this.legalMoves = legalMovesArg;
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    /**
     * Calculate attacks on tile.
     * @param piecePositionArg piece position.
     * @param movesArg move.
     * @return Attacks on tile collection.
     */
    private static Collection<Move> calculateAttacksOnTile(int piecePositionArg, Collection<Move> movesArg) {
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : movesArg) {
            if (piecePositionArg == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    /**
     * Getter for board.
     * @return board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * King.
     * @return King piece.
     */
    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board!");
    }

    /**
     * Check legal move.
     * @param moveArg move.
     * @return true if move is legal.
     */
    public boolean isMoveLegal(final Move moveArg) {
        return this.legalMoves.contains(moveArg);
    }

    /**
     * check a check.
     * @return true if check.
     */
    public boolean isInCheck() {
        return this.isInCheck;
    }

    /**
     * check check mate.
     * @return true if check mate.
     */
    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    /**
     * check stale mate.
     * @return true if stale mate.
     */
    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    //TODO implement these methods below!!!
    /**
     * check castled.
     * @return true if castled.
     */
    public boolean isCastled() {
        return false;
    }

    /**
     * Has escape moves?
     * @return true if it.
     */
    protected boolean hasEscapeMoves() {
        boolean result = false;
        for (final Move move : this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * make move.
     * @param moveArg move.
     * @return move transition.
     */
    public MoveTransition makeMove(final Move moveArg) {
        return null;
    }

    /**
     * Active pieces collection.
     * @return Pieces collection.
     */
    public abstract Collection<Piece> getActivePieces();

    /**
     * Get player alliance.
     * @return alliance.
     */
    public abstract Alliance getAlliance();

    /**
     * get opponent.
     * @return opponent.
     */
    public abstract Player getOpponent();

}
