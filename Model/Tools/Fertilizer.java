package Model.Tools;

import Model.Plants.*;
import Model.*;

/**
 * This class represents the blueprint for the tool, Fertilizer.
 * <p>
 *     It inherits from the parent class, Tool, and it has one sole method,
 *     which is to fertilize a plant/tile.
 * </p>
 */
public class Fertilizer extends Tool {

    /**
     * Creates a fertilizer object.
     */
    public Fertilizer() {
        super(ToolType.FERTILIZER, ToolType.FERTILIZER.getCost(),
              ToolType.FERTILIZER.getUse(), ToolType.FERTILIZER.getExpGain());
    }

    /**
     * Adds 1 to the number of times a plant/tile has been fertilized.
     * @param tile   the tile containing the crop that is to be fertilized
     * @param plant  the crop that will be fertilized
     * @return true if the crop is successfully fertilized, false if otherwise
     */
    public boolean fertilizePlant(Tile tile, Plant plant) {
        if(tile.getHasCrop() && !tile.getHasFullyGrownCrop() &&
           !tile.getHasWitheredCrop()) {
            // only fertilize a tile if it has a growing crop
            plant.addFertilizedCount();
            return true;
        }
        return false;
    }




}
