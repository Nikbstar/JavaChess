package ru.nik66.engine.pieces;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.BoardUtils;
import ru.nik66.engine.border.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.nik66.engine.border.Move.MajorMove;

/**
 * Created by nkotkin on 3/12/17.
 */
public class Pawn extends Piece {

    /**
     * Candidate move coordinate.
     */
    private static final int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

    /**
     * Piece Initialization Constructor.
     *
     * @param piecePositionArg Piece position.
     * @param pieceAllianceArg Piece alliance.
     */
    public Pawn(final int piecePositionArg, final Alliance pieceAllianceArg) {
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
        final List<Move> legalMove = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.getPiecePosition()
                    + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == CANDIDATE_MOVE_COORDINATE[BoardUtils.ZERO]
                    && !boardArg.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //TODO more work to do here (deal with pronotions)!!!!
                legalMove.add(new MajorMove(boardArg, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == CANDIDATE_MOVE_COORDINATE[BoardUtils.ONE]
                    && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.getPiecePosition()] && this.getPieceAlliance().isBlack())
                    || (BoardUtils.SEVENTH_ROW[this.getPiecePosition()] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.getPiecePosition()
                        + (this.getPieceAlliance().getDirection() * CANDIDATE_MOVE_COORDINATE[BoardUtils.ZERO]);
                if (!boardArg.getTile(behindCandidateDestinationCoordinate).isTileOccupied()
                        && !boardArg.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMove.add(new MajorMove(boardArg, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == CANDIDATE_MOVE_COORDINATE[BoardUtils.TWO]
                    && !((BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isWhite())
                        || (BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isBlack()))) {
                if (boardArg.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = boardArg.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.getPieceAlliance() != pieceOnCandidate.getPieceAlliance()) {
                        //TODO more to do here
                        legalMove.add(new MajorMove(boardArg, this, candidateDestinationCoordinate));
                    }
                }
            } else if (currentCandidateOffset == CANDIDATE_MOVE_COORDINATE[BoardUtils.THREE]
                    && !((BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isWhite())
                        || (BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isBlack()))) {
                if (boardArg.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = boardArg.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.getPieceAlliance() != pieceOnCandidate.getPieceAlliance()) {
                        //TODO more to do here
                        legalMove.add(new MajorMove(boardArg, this, candidateDestinationCoordinate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMove);
    }

}
