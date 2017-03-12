package ru.nik66.engine.pieces;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.border.Tile;

import java.util.ArrayList;
import java.util.List;

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
        super(piecePositionArg, pieceAllianceArg);
    }

    /**
     * Moves collection.
     *
     * @param boardArg Border.
     * @return Legal moves list.
     */
    @Override
    public List<Move> calculateLegalMoves(Board boardArg) {

        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.getPiecePosition() + currentCandidate;
            if (true /* isValidTileCoordinate */) {
                final Tile candidateDestinationTile = boardArg.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.getPieceAlliance() != pieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

}
