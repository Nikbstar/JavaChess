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

import static ru.nik66.engine.border.Move.MajorMove;
import static ru.nik66.engine.border.Move.AttackMove;

/**
 * Created by nkotkin on 3/12/17.
 * https://en.wikipedia.org/wiki/Rook_(chess)
 */
public class Rook extends Piece {

    /**
     * Candidate move vector coordinate.
     */
    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8};

    /**
     * Piece Initialization Constructor.
     *
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg Piece alliance.
     */
    public Rook(final int piecePositionArg, final Alliance pieceAllianceArg) {
        super(piecePositionArg, pieceAllianceArg);
    }

    /**
     * Moves collection.
     *
     * @param boardArg Border.
     * @return Move.
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board boardArg) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.getPiecePosition();
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
                        || isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
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
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * Check first column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if move on exclusion tile from first column.
     */
    private static boolean isFirstColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.FIRST_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_VECTOR_COORDINATES[BoardUtils.ONE]);
    }

    /**
     * Check eight column position.
     * @param currentPositionArg Current position.
     * @param candidateOffsetArg Candidate offset.
     * @return true if move on exclusion tile from eight column.
     */
    private static boolean isEightColumnExclusion(final int currentPositionArg, final int candidateOffsetArg) {
        return BoardUtils.EIGHTH_COLUMN[currentPositionArg]
                && (candidateOffsetArg == CANDIDATE_MOVE_VECTOR_COORDINATES[BoardUtils.TWO]);
    }

}
