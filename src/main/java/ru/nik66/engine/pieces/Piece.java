package ru.nik66.engine.pieces;

import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;

import java.util.List;

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
     * Piece Initialization Constructor.
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg Piece alliance.
     */
    Piece(final int piecePositionArg, final Alliance pieceAllianceArg) {
        this.piecePosition = piecePositionArg;
        this.pieceAlliance = pieceAllianceArg;
    }

    /**
     * Moves collection.
     * @param boardArg Border.
     * @return Move.
     */
    public abstract List<Move> calculateLegalMoves(Board boardArg);

}
