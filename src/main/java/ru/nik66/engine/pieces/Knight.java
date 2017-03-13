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
 * https://en.wikipedia.org/wiki/Knight_(chess)
 */
public class Knight extends Piece {

    /**
     * Candidate move coordinates array.
     */
    private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    /**
     * Constructor Knight initialization.
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg PieceAlliance.
     */
    public Knight(final int piecePositionArg, final Alliance pieceAllianceArg) {
        super(PieceType.KNIGHT, piecePositionArg, pieceAllianceArg);
    }

    /**
     * Moves collection.
     *
     * @param boardArg Border.
     * @return Legal moves list.
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board boardArg) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.getPiecePosition() + currentCandidateOffset;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.getPiecePosition(), currentCandidateOffset)
                        || isSecondColumnExclusion(this.getPiecePosition(), currentCandidateOffset)
                        || isSeventhColumnExclusion(this.getPiecePosition(), currentCandidateOffset)
                        || isEighthColumnExclusion(this.getPiecePosition(), currentCandidateOffset)) {
                    continue;
                }
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

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
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
                    || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.TWO]
                    || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.FOUR]
                    || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.SIX]
                );
    }

    /**
     * Check second column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if knight move on exclusion tile from second column.
     */
    private static boolean isSecondColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.SECOND_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.TWO]
                    || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.FOUR]
                );
    }

    /**
     * Check seventh column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if knight move on exclusion tile from seventh column.
     */
    private static boolean isSeventhColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.SEVENTH_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.THREE]
                    || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.FIVE]
                );
    }

    /**
     * Check eithth column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if knight move on exclusion tile from eighth column.
     */
    private static boolean isEighthColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.EIGHTH_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.ONE]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.THREE]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.FIVE]
                || candidateOffsetArg == CANDIDATE_MOVE_COORDINATES[BoardUtils.SEVEN]
        );
    }

}
