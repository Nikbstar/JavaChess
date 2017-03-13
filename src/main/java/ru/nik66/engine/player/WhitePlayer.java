package ru.nik66.engine.player;

import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.pieces.Piece;

import java.util.Collection;

/**
 * Created by nkotkin on 3/13/17.
 * White player.
 */
public class WhitePlayer extends Player {

    /**
     * White player initialization constructor.
     * @param boardArg board.
     * @param whiteStandardLegalMoveArg white standard legal moves.
     * @param blackStandardLegalMoveArg black standard legal moves.
     */
    public WhitePlayer(Board boardArg,
                       Collection<Move> whiteStandardLegalMoveArg,
                       Collection<Move> blackStandardLegalMoveArg) {
        super(boardArg, whiteStandardLegalMoveArg, blackStandardLegalMoveArg);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.getBoard().getWhitePieces();
    }

}
