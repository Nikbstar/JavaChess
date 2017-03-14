package ru.nik66.engine.player;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.border.Tile;
import ru.nik66.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nkotkin on 3/13/17.
 * White player.
 */
public class WhitePlayer extends Player {

    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_59 = 59;
    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_58 = 58;
    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_57 = 57;
    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_56 = 56;
    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_61 = 61;
    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_62 = 62;
    /**
     * Tile coordinate.
     */
    static final int TILE_COORDINATE_63 = 63;

    /**
     * White player initialization constructor.
     * @param boardArg board.
     * @param whiteStandardLegalMoveArg white standard legal moves.
     * @param blackStandardLegalMoveArg black standard legal moves.
     */
    public WhitePlayer(final Board boardArg,
                       final Collection<Move> whiteStandardLegalMoveArg,
                       final Collection<Move> blackStandardLegalMoveArg) {
        super(boardArg, whiteStandardLegalMoveArg, blackStandardLegalMoveArg);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.getBoard().getWhitePieces();
    }

    /**
     * Get player alliance.
     *
     * @return alliance.
     */
    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    /**
     * get opponent.
     *
     * @return opponent.
     */
    @Override
    public Player getOpponent() {
        return this.getBoard().getBlackPlayer();
    }

    /**
     * Calculate king castles.
     *
     * @param playerLegalsArg    Player legals.
     * @param opponentsLegalsArg Opponent legals.
     * @return Moves collection.
     */
    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegalsArg,
                                                    Collection<Move> opponentsLegalsArg) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.getPlayerKing().isFirstMove() && !this.isInCheck()) {
            // Whites king side castle
            if (!this.getBoard().getTile(TILE_COORDINATE_61).isTileOccupied()
                    && !this.getBoard().getTile(TILE_COORDINATE_62).isTileOccupied()) {
                final Tile rookTile = this.getBoard().getTile(TILE_COORDINATE_63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(TILE_COORDINATE_61, opponentsLegalsArg).isEmpty()
                            && Player.calculateAttacksOnTile(TILE_COORDINATE_62, opponentsLegalsArg).isEmpty()
                            && rookTile.getPiece().getPieceType().isRook()) {
                        //TODO add a castlemove!
                        kingCastles.add(null);
                    }
                }
            }
            if (this.getBoard().getTile(TILE_COORDINATE_59).isTileOccupied()
                    && !this.getBoard().getTile(TILE_COORDINATE_58).isTileOccupied()
                    && !this.getBoard().getTile(TILE_COORDINATE_57).isTileOccupied()) {
                final Tile rookTile = this.getBoard().getTile(TILE_COORDINATE_56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    //TODO add a castlemove!
                    kingCastles.add(null);
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }

}
