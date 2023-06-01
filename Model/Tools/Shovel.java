package Model.Tools;

import Model.*;

/**
 * This class represents the blueprint for the tool, Shovel.
 * <p>
 *     It inherits from the parent class, Tool, and it has one sole method, which is to
 *     remove a withered crop. However, this tool can be used outside of its intended
 *     use. Using it on a tile that is unplowed or has a rock will not do anything while
 *     using it on a tile with an active crop will remove the said crop. Both actions
 *     will merit experience and deduct Objectcoins.
 * </p>
 */
public class Shovel extends Tool {

    /**
     * Creates a shovel object.
     */
    public Shovel() {
        super(ToolType.SHOVEL, ToolType.SHOVEL.getCost(),
              ToolType.SHOVEL.getUse(), ToolType.SHOVEL.getExpGain());
    }

    /**
     * Removes the crop and updates the respective tile statuses if used on a withered
     * crop or a growing/fully-grown crop. Meanwhile, it does nothing if used on an
     * unplowed tile. Shovel cannot be used on plowed tiles as per specs details.
     * @param tile the tile that the shovel is going to be used on
     * @return true if the use of shovel was successful, false if otherwise.
     */
    public boolean removeWitheredCrop(Tile tile) {
        if(tile.getHasWitheredCrop()) {
            // if tile has a withered crop
            tile.setCropPlanted(null);
            tile.setHasWitheredCrop(false);
            tile.setIsOccupied(false);
            tile.setHasCrop(false);
            return true;
        } else if(tile.getHasRock() ||
                  !tile.getIsOccupied() && !tile.getIsPlowed()) {
            // if tile is unplowed or has a rock
            return true;
        } else if(tile.getHasCrop() && !tile.getHasWitheredCrop()) {
            // if tile has a growing or a fully-grown crop
            tile.setCropPlanted(null);
            tile.setHasCrop(false);
            tile.setHasFullyGrownCrop(false);
            tile.setIsOccupied(false);
            return true;
        }

        return false;
    }
}
