package Model;

import Model.Plants.*;

/**
 * This class represents the blueprint for a tile or a block in the farming lot, where
 * seeds can be planted.
 * <p>
 *     To plant a seed on a tile, the tile must first be plowed, which can be done using
 *     the plow tool. Several tools primarily are used on a tile. As things are often
 *     done on tiles, it is responsible for updating its status.
 * </p>
 */
public class Tile {

    // attributes
    private boolean isPlowed = false;
    private boolean isOccupied = false;
    private boolean hasRock = false;
    private boolean hasCrop = false;
    private boolean hasWitheredCrop = false;
    private boolean hasFullyGrownCrop = false;
    private Plant cropPlanted;

    /**
     * Creates a tile object.
     */
    public Tile() {}

    /**
     * Updates the status of the tile based on the status of the plant
     */
    public void updateTileStatus() {
        // update plant status if the tile has a plant
        this.getCropPlanted().updatePlantStatus();

        if(this.getCropPlanted().getIsWithered()) {
            // if plant is now withered
            this.hasWitheredCrop = true;
            this.hasFullyGrownCrop = false;
        } else if(this.getCropPlanted().getIsReadyToHarvest())
            // if plant is now fully-grown
            this.hasFullyGrownCrop = true;
    }

    /**
     * Gets the string that contains all the information about the tile
     * @return the string that contains all the information about the tile.
     */
    public String getTileInfo() {
        String tileInfo;
        if(this.hasCrop) { // if tile has a crop
            tileInfo = "TILE INFORMATION\n" + "-".repeat(106) +
                       "\nPlant name: " + this.getCropPlanted().getName().getSeedName() +
                       "\nCrop type: " + this.getCropPlanted().getName().getCropType() +
                       "\nDays left before harvest : " +
                            (this.getCropPlanted().getGrowthDaysLeft()) +
                       "\nTimes watered: " + this.getCropPlanted().getWateredCount() +
                            " / " + this.getCropPlanted().getName().getWaterNeeds() +
                            " times needed" +
                       "\nTimes fertilized: " + this.getCropPlanted().getFertilizedCount()
                            + " / " + this.getCropPlanted().getName().getFertilizerNeeds() +
                            " times needed" +
                       "\nStatus: ";

            // display plant status
            if(this.cropPlanted.getIsReadyToHarvest())
                tileInfo = tileInfo.concat("Ready to Harvest");
            else if(this.cropPlanted.getIsWithered())
                tileInfo = tileInfo.concat("Withered");
            else
                tileInfo = tileInfo.concat("Growing");
        } else if(this.hasRock) { // if tile has a rock
            tileInfo = "The tile has a rock. Use the pickaxe tool to remove it.";
        } else if(this.isPlowed) { // if tile is plowed and does not have crops
            tileInfo = "This tile is now waiting for its seed!";
        } else { // if the tile is unplowed
            tileInfo = "This tile has no use for now... Try using the plow tool on " +
                       "this tile.";
        }
        return tileInfo;
    }

    // getters and setters
    /**
     * Gets the condition of the tile if it is currently plowed or not.
     * @return true if the tile is plowed, false if otherwise.
     */
    public boolean getIsPlowed() {
        return isPlowed;
    }

    /**
     * Sets the value of the private variable, isPlowed, depending on the value given.
     * @param isPlowed  the boolean variable that implies whether the tile is now
     *                  plowed or not
     */
    public void setIsPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }

    /**
     * Gets the condition of the tile whether it is occupied (by a rock, growing crop,
     * fully grown crop, or withered crop) or not.
     * @return true if the tile is occupied, false if otherwise.
     */
    public boolean getIsOccupied() {
        return isOccupied;
    }

    /**
     * Sets the value of the private variable, isOccupied, depending on the value given.
     * @param isOccupied  the boolean variable that implies whether the tile is now
     *                    occupied or not
     */
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Gets the condition of the tile if it has a rock on it or not.
     * @return true if the tile has a rock, false if otherwise.
     */
    public boolean getHasRock() {
        return hasRock;
    }

    /**
     * Sets the value of the private variable, hasRock, depending on the value given.
     * @param hasRock  the boolean variable that implies whether the tile now has a
     *                 rock or not
     */
    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    /**
     * Gets the condition of the tile whether it has a crop on it (either growing, fully
     * grown, or withered) or not.
     * @return true if the tile has a crop, false if otherwise.
     */
    public boolean getHasCrop() {
        return hasCrop;
    }

    /**
     * Sets the value of the private variable, hasCrop, depending on the value given.
     * @param hasCrop  the boolean variable that implies whether the tile now
     *                 has a crop or not
     */
    public void setHasCrop(boolean hasCrop) {
        this.hasCrop = hasCrop;
    }

    /**
     * Gets the condition of the tile whether it has a withered crop on it or not.
     * @return true if the tile has a withered crop, false if otherwise.
     */
    public boolean getHasWitheredCrop() {
        return hasWitheredCrop;
    }

    /**
     * Sets the value of the private variable, hasWitheredCrop, depending on
     * the value given.
     * @param hasWitheredCrop  the boolean variable that implies whether the
     *                         tile now has a withered crop or not
     */
    public void setHasWitheredCrop(boolean hasWitheredCrop) {
        this.hasWitheredCrop = hasWitheredCrop;
    }

    /**
     * Gets the condition of the tile whether it has a fully grown crop on it or not.
     * @return true if the tile has a ready-to-harvest crop, false if otherwise.
     */
    public boolean getHasFullyGrownCrop() {
        return hasFullyGrownCrop;
    }

    /**
     * Sets the value of the private variable, hasFullyGrownCrop, depending
     * on the value given.
     * @param hasFullyGrownCrop  the boolean variable that implies if
     *                           the crop on the tile is now fully grown
     */
    public void setHasFullyGrownCrop(boolean hasFullyGrownCrop) {
        this.hasFullyGrownCrop = hasFullyGrownCrop;
    }

    /**
     * Gets the kind of crop planted on the tile.
     * @return the crop planted on the tile, if it has.
     */
    public Plant getCropPlanted() {
        return cropPlanted;
    }

    /**
     * Sets the kind of crop planted on the tile.
     * @param cropPlanted  the crop to be planted on the tile.
     */
    public void setCropPlanted(Plant cropPlanted) {
        this.cropPlanted = cropPlanted;
    }
}
