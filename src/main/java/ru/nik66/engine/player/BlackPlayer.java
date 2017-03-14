package ru.nik66.engine.player;

import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.pieces.Piece;

import java.util.Collection;

/**
 * Created by nkotkin on 3/13/17.
 * Black player.
 */
public class BlackPlayer extends Player {

    /**
     * Black player initialization constructor.
     * @param boardArg board.
     * @param whiteStandardLegalMoveArg white standard legal moves.
     * @param blackStandardLegalMoveArg black standard legal moves.
     */
    public BlackPlayer(Board boardArg,
                       Collection<Move> whiteStandardLegalMoveArg,
                       Collection<Move> blackStandardLegalMoveArg) {
        super(boardArg, blackStandardLegalMoveArg, whiteStandardLegalMoveArg);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.getBoard().getBlackPieces();
    }

    /**
     * Get player alliance.
     *
     * @return alliance.
     */
    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    /**
     * get opponent.
     *
     * @return opponent.
     */
    @Override
    public Player getOpponent() {
        return this.getBoard().getWhitePlayer();
    }

}
