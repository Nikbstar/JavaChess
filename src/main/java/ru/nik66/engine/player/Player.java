package ru.nik66.engine.player;

import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.pieces.King;
import ru.nik66.engine.pieces.Piece;

import java.util.Collection;

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
     * Player initialization constructor.
     * @param boardArg board.
     * @param legalMovesArg legal moves.
     * @param opponentMoves opponent moves.
     */
    Player(final Board boardArg, final Collection<Move> legalMovesArg, final Collection<Move> opponentMoves) {
        this.board = boardArg;
        this.playerKing = establishKing();
        this.legalMoves = legalMovesArg;
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

    //TODO implement these methods below!!!
    /**
     * check a check.
     * @return true if check.
     */
    public boolean isInCheck() {
        return false;
    }

    /**
     * check check mate.
     * @return true if check mate.
     */
    public boolean isInCheckMate() {
        return false;
    }

    /**
     * check stale mate.
     * @return true if stale mate.
     */
    public boolean isInStaleMate() {
        return false;
    }

    /**
     * check castled.
     * @return true if castled.
     */
    public boolean isCastled() {
        return false;
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
