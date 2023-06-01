package Model.Tools;

import Model.*;
import Model.Plants.*;

/**
 * This class represents the blueprint for the tool, Watering Can.
 * <p>
 *     It inherits from the parent class, Tool, and it has one sole method,
 *     which is to water a plant.
 * </p>
 */
public class WateringCan extends Tool {

    /**
     * Creates a watering can object.
     */
    public WateringCan() {
        super(ToolType.WATERINGCAN, ToolType.WATERINGCAN.getCost(),
              ToolType.WATERINGCAN.getUse(), ToolType.WATERINGCAN.getExpGain());
    }

    /**
     * Adds 1 to the number of times a plant has been watered.
     * @param tile   the tile containing the crop that is to be watered
     * @param plant  the crop that will be watered
     * @return true if the watering of plant is successful, false if otherwise
     */
    public boolean waterPlant(Tile tile, Plant plant) {
        if(tile.getHasCrop() && !tile.getHasFullyGrownCrop() &&
           !tile.getHasWitheredCrop()) {
            // only water a plant if it is a growing crop
            plant.addWateredCount();
            return true;
        }
        return false;
    }
}
