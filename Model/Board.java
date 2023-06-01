package Model;

import java.util.ArrayList;

/**
 * This class represents the farming lot in the game, which contains multiple individual
 * tiles where the farmer can plow tiles, plant seeds, and harvest crops.
 * <p>
 *     The board keeps track of the number of rocks, growing crops, fully-grown crops,
 *     withered crops, and plowed tiles among its individual tiles. It is responsible for
 *     checking if a fruit tree seed can be planted on a specific tile.
 * </p>
 */
public class Board {

    // attributes
    private Tile[][] tiles;
    private int rocksCount;
    private int growingCropCount = 0;
    private int witheredCropCount = 0;
    private int fullyGrownCropCount = 0;
    private int plowedTilesCount = 0;

    /**
     * Creates a board object. When a board is created, all of its tiles
     * are also created with it.
     */
    public Board(int rows, int columns) {
        this.tiles = new Tile[rows][columns];
        for(int rowCount = 0; rowCount < rows; rowCount++)
            for(int colCount = 0; colCount < columns; colCount++)
                this.tiles[rowCount][colCount] = new Tile();
    }

    /**
     * Sets the initial number and positions of rocks on the farm lot.
     * @param rockPositions an array list containing the coordinates where
     *                      the rocks will be placed
     */
    public void setInitialRocks(ArrayList<String> rockPositions) {
        ArrayList<Integer> rockRows = new ArrayList<>();
        ArrayList<Integer> rockColumns = new ArrayList<>();

        // store each coordinate in respective array lists
        for(String line : rockPositions) {
            rockRows.add(Integer.parseInt(line.substring(0, 1)));
            rockColumns.add(Integer.parseInt(line.substring(2)));
        }

        // set the statuses of the tiles given the coordinates
        for(int index = 0; index < rockPositions.size(); index++) {
            int row = rockRows.get(index) - 1;
            int col = rockColumns.get(index) - 1;
            this.tiles[row][col].setHasRock(true);
            this.tiles[row][col].setIsOccupied(true);
        }
    }

    /**
     * Checks if the specific tile can be planted with a fruit tree. Note that a fruit
     * tree can only be planted on a tile where its adjacent tiles from all directions
     * are not occupied and the tile is not at the lot's edge.
     * @param row       the row number of the tile (beginning at index 0)
     * @param col       the column number of the tile (beginning at index 0)
     * @param maxRows   the maximum number of rows of the farm lot
     * @param maxCols   the maximum number of columns of the farm lot
     * @return false if the tile is at the lot's edge or the adjacent tiles are occupied,
     *         true if otherwise.
     */
    public boolean canPlantFruit(int row, int col, int maxRows, int maxCols) {
        if(row == 0 || row == maxRows - 1 || col == 0 || col == maxCols - 1) {
            // check if the tile chosen is at the edge
            return false;
        } else {
            // check if the adjacent tiles from all directions are occupied
            Tile northWest = this.getTile(row - 1, col - 1);
            Tile north = this.getTile(row - 1, col);
            Tile northEast = this.getTile(row - 1, col + 1);
            Tile east = this.getTile(row, col + 1);
            Tile southEast = this.getTile(row + 1, col + 1);
            Tile south = this.getTile(row + 1, col);
            Tile southWest = this.getTile(row + 1, col - 1);
            Tile west = this.getTile(row, col - 1);

            return !northWest.getIsOccupied() && !north.getIsOccupied() &&
                    !northEast.getIsOccupied() && !east.getIsOccupied() &&
                    !southEast.getIsOccupied() && !south.getIsOccupied() &&
                    !southWest.getIsOccupied() && !west.getIsOccupied();
        }
    }

    /**
     * Updates the stats of the board by counting each object / tile type
     * every time.
     * @param maxRows  the maximum number of rows of the board
     * @param maxCols  the maximum number of columns of the board
     */
    public void updateBoardStats(int maxRows, int maxCols) {
        this.witheredCropCount = 0;
        this.fullyGrownCropCount = 0;
        this.plowedTilesCount = 0;
        this.growingCropCount = 0;
        this.rocksCount = 0;

        // count the crops, tiles, and rocks on the board
        for(int row = 0; row < maxRows; row++) {
            for(int col = 0; col < maxCols; col++) {
                Tile tile = this.getTile(row, col);

                if(tile.getHasWitheredCrop())
                    this.witheredCropCount++;
                else if(tile.getHasFullyGrownCrop())
                    this.fullyGrownCropCount++;
                else if(tile.getHasCrop())
                    this.growingCropCount++;
                else if(tile.getHasRock())
                    this.rocksCount++;
                else if(tile.getIsPlowed())
                    this.plowedTilesCount++;
            }
        }
    }

    /**
     * Gets all the information about the board.
     * @return  the string that contains board information.
     */
    public String getBoardInfo() {
        return "\nNumber of Plowed Tiles: " + this.plowedTilesCount +
                "\t\tNumber of Growing Crops: " + this.growingCropCount +
                "\nNumber of Ready-to-Harvest Crops: " + this.fullyGrownCropCount +
                "\tNumber of Withered Crops: " + this.witheredCropCount +
                "\nNumber of Rocks: " + this.rocksCount;
    }

    // getters and setters
    /**
     * Gets the tile from the board, given the row and the column.
     * @param row   the row number of the tile (beginning at index 0)
     * @param col   the column number of the tile (beginning at index 0)
     * @return the tile represented by the given positions.
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Gets all the tiles from the board.
     * @return all board tiles.
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Sets the tile of the board given the specific position (row and column)
     * to a specific tile provided.
     * @param tile  the tile to set the tile in board to
     * @param row   the row position of the tile (beginning at index 0)
     * @param col   the column position of the tile (beginning at index 0)
     */
    public void setTile(Tile tile, int row, int col) {
        this.tiles[row][col] = tile;
    }

    /**
     * Gets the number of rocks the board currently has.
     * @return the board's current number of rocks on the tiles.
     */
    public int getRocksCount() {
        return rocksCount;
    }

    /**
     * Sets the number of rocks the board currently has.
     * @param rocksCount the board's current number of rocks on the tiles
     */
    public void setRocksCount(int rocksCount) {
        this.rocksCount = rocksCount;
    }

    /**
     * Gets the current total number of growing crops in the farm lot.
     * @return the total number of growing crops.
     */
    public int getGrowingCropCount() {
        return growingCropCount;
    }

    /**
     * Sets the current total number of growing crops in the farm lot.
     * @param growingCropCount the total number of growing crops
     */
    public void setGrowingCropCount(int growingCropCount) {
        this.growingCropCount = growingCropCount;
    }

    /**
     * Gets the current total number of withered crops present in the farm lot.
     * @return the total number of withered crops.
     */
    public int getWitheredCropCount() {
        return witheredCropCount;
    }

    /**
     * Sets the number of withered crops the board currently has.
     * @param witheredCropCount the number of withered crops on the board.
     */
    public void setWitheredCropCount(int witheredCropCount) {
        this.witheredCropCount = witheredCropCount;
    }

    /**
     * Gets the current total number of ready-to-harvest crops present in the farm lot.
     * @return the total number of fully-grown crops.
     */
    public int getFullyGrownCropCount() {
        return fullyGrownCropCount;
    }

    /**
     * Sets the current total number of ready-to-harvest crops present in the farm lot.
     * @param fullyGrownCropCount the total number of fully-grown crops.
     */
    public void setFullyGrownCropCount(int fullyGrownCropCount) {
        this.fullyGrownCropCount = fullyGrownCropCount;
    }

    /**
     * Gets the current number of plowed tiles on the board.
     * @return the board's current number of plowed tiles.
     */
    public int getPlowedTilesCount() {
        return plowedTilesCount;
    }

    /**
     * Sets the number of plowed tiles the board currently has.
     * @param plowedTilesCount the board's current number of plowed tiles.
     */
    public void setPlowedTilesCount(int plowedTilesCount) {
        this.plowedTilesCount = plowedTilesCount;
    }

}
