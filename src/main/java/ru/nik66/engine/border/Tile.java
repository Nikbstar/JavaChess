package ru.nik66.engine.border;

import com.google.common.collect.ImmutableMap;
import ru.nik66.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nkotkin on 3/10/17.
 * Tile class.
 */
public abstract class Tile {

    /**
     * int Tile coordinate.
     */
    private final int tileCoordinate;

    /**
     * Tiles collection.
     */
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    /**
     * Tiles collections initialization.
     * @return collection.
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        // return Collections.unmodifiableMap(emptyTileMap);
        return ImmutableMap.copyOf(emptyTileMap);
    }

    /**
     * Create tile.
     * @param tileCoordinateArg Tile's coordinate.
     * @param pieceArg Piece on the tile.
     * @return if piece is null then return empty tile, else return occupied tile with coordinate and piece.
     */
    public static Tile createTile(final int tileCoordinateArg, final Piece pieceArg) {
        Tile tile;
        if (pieceArg != null) {
            tile = new OccupiedTile(tileCoordinateArg, pieceArg);
        } else {
            tile = EMPTY_TILES_CACHE.get(tileCoordinateArg);
        }
        return tile;
    }

    /**
     * Tile.
     * @param tileCoordinateArg int Tile coordinate
     */
    private Tile(final int tileCoordinateArg) {
        this.tileCoordinate = tileCoordinateArg;
    }

    /**
     * Is Tile Occupies?.
     * @return true if tile is occupied.
     */
    public abstract boolean isTileOccupied();

    /**
     * Get piece.
     * @return Piece/
     */
    public abstract Piece getPiece();

    /**
     * Empty Tile.
     */
    public static final class EmptyTile extends Tile {

        /**
         * Constructor for empty tile.
         * @param coordinateArg Tile coordinate.
         */
        EmptyTile(final int coordinateArg) {
            super(coordinateArg);
        }

        @Override
        public String toString() {
            return "-";
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    /**
     * Occupied Tile.
     */
    public static final class OccupiedTile extends Tile {

        /**
         * Piece on tile.
         */
        private final Piece pieceOnTile;

        /**
         * Occupied Tile.
         * @param tileCoordinateArg int Tile coordinate.
         * @param pieceOnTileArg Piece on tile.
         */
        OccupiedTile(int tileCoordinateArg, final Piece pieceOnTileArg) {
            super(tileCoordinateArg);
            this.pieceOnTile = pieceOnTileArg;
        }

        @Override
        public String toString() {
            String result = getPiece().toString();
            if (getPiece().getPieceAlliance().isBlack()) {
                result = getPiece().toString().toLowerCase();
            }
            return result;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
