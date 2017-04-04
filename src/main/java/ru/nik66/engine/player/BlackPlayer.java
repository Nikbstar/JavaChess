package ru.nik66.engine.player;

import com.google.common.collect.ImmutableList;
import ru.nik66.engine.Alliance;
import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;
import ru.nik66.engine.border.Tile;
import ru.nik66.engine.pieces.Piece;
import ru.nik66.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nkotkin on 3/13/17.
 * Black player.
 */
public class BlackPlayer extends Player {

    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_5 = 5;
    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_6 = 6;
    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_7 = 7;
    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_1 = 1;
    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_2 = 2;
    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_3 = 3;
    /**
     * Tile coordinate.
     */
    public static final int TILE_COORDINATE_0 = 0;

    /**
     * Black player initialization constructor.
     * @param boardArg board.
     * @param whiteStandardLegalMoveArg white standard legal moves.
     * @param blackStandardLegalMoveArg black standard legal moves.
     */
    public BlackPlayer(final Board boardArg,
                       final Collection<Move> whiteStandardLegalMoveArg,
                       final Collection<Move> blackStandardLegalMoveArg) {
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

    /**
     * Calculate king castles.
     *
     * @param playerLegalsArg    Player legals.
     * @param opponentsLegalsArg Opponent legals.
     * @return Moves collection.
     */
    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegalsArg,
                                                    final Collection<Move> opponentsLegalsArg) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.getPlayerKing().isFirstMove() && !this.isInCheck()) {
            // Black king side castle
            if (!this.getBoard().getTile(TILE_COORDINATE_5).isTileOccupied()
                    && !this.getBoard().getTile(TILE_COORDINATE_6).isTileOccupied()) {
                final Tile rookTile = this.getBoard().getTile(TILE_COORDINATE_7);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(TILE_COORDINATE_5, opponentsLegalsArg).isEmpty()
                            && Player.calculateAttacksOnTile(TILE_COORDINATE_6, opponentsLegalsArg).isEmpty()
                            && rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new Move.KingSideCastleMove(this.getBoard(),
                                                                    this.getPlayerKing(),
                                                                    TILE_COORDINATE_6,
                                                                    (Rook) rookTile.getPiece(),
                                                                    rookTile.getTileCoordinate(),
                                                                    TILE_COORDINATE_5));
                    }
                }
            }
            if (this.getBoard().getTile(TILE_COORDINATE_1).isTileOccupied()
                    && !this.getBoard().getTile(TILE_COORDINATE_2).isTileOccupied()
                    && !this.getBoard().getTile(TILE_COORDINATE_3).isTileOccupied()) {
                final Tile rookTile = this.getBoard().getTile(TILE_COORDINATE_0);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    kingCastles.add(new Move.QueenSideCastleMove(this.getBoard(),
                                                                 this.getPlayerKing(),
                                                                 TILE_COORDINATE_2,
                                                                 (Rook) rookTile.getPiece(),
                                                                 rookTile.getTileCoordinate(),
                                                                 TILE_COORDINATE_3));
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }

}
