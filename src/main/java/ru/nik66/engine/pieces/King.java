package ru.nik66.engine.pieces;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.BoardUtils;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.border.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.nik66.engine.border.Move.AttackMove;
import static ru.nik66.engine.border.Move.MajorMove;

/**
 * Created by nkotkin on 3/12/17.
 * https://en.wikipedia.org/wiki/King_(chess)
 */
public class King extends Piece {

    /**
     * Candidate move coordinate.
     */
    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};


    /**
     * Piece Initialization Constructor.
     *
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg Piece alliance.
     */
    public King(final int piecePositionArg, final Alliance pieceAllianceArg) {
        super(PieceType.KING, piecePositionArg, pieceAllianceArg);
    }

    /**
     * Moves collection.
     *
     * @param boardArg Border.
     * @return Move.
     */
    @Override
    public Collection<Move> calculateLegalMoves(Board boardArg) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.getPiecePosition() + currentCandidateOffset;
            if (isFirstColumnExclusion(this.getPiecePosition(), currentCandidateOffset)
                    || isEighthColumnExclusion(this.getPiecePosition(), currentCandidateOffset)) {
                continue;
            }
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = boardArg.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(boardArg, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.getPieceAlliance() != pieceAlliance) {
                        legalMoves.add(new AttackMove(boardArg, this, candidateDestinationCoordinate,
                                pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * Move piece.
     *
     * @param moveArg move.
     * @return piece.
     */
    @Override
    public King movePiece(Move moveArg) {
        return new King(moveArg.getDestinationCoordinate(), moveArg.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    /**
     * Check first column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if move on exclusion tile from first column.
     */
    private static boolean isFirstColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.FIRST_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.ZERO]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.THREE]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.FIVE]
        );
    }

    /**
     * Check second column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if knight move on exclusion tile from second column.
     */
    private static boolean isEighthColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.EIGHTH_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.TWO]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.FOUR]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.SEVEN]
        );
    }

}
