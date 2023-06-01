package Model.Tools;

import Model.Tile;

/**
 * This class represents the blueprint for the tool, Plow.
 * <p>
 *     It inherits from the parent class, Tool, and it has one sole method,
 *     which is to plow a tile.
 * </p>
 */
public class Plow extends Tool {

    /**
     * Creates a plow object.
     */
    public Plow() {
        super(ToolType.PLOW, ToolType.PLOW.getCost(),
              ToolType.PLOW.getUse(), ToolType.PLOW.getExpGain());
    }

    /**
     * Adjusts the tile status from unplowed to plowed if the tile is not
     * occupied (having a rock, growing crop, fully-grown crop, or withered crop)
     * and not already plowed.
     *
     * @param tile the tile that will be plowed
     * @return true if the tile is successfully plowed, false if otherwise.
     */
    public boolean plowTile(Tile tile) {
        if(!tile.getIsOccupied() &&
           !tile.getIsPlowed()) {
            // only plow tiles that are unplowed and unoccupied
            tile.setIsPlowed(true);
            return true;
        }
        return false;
    }
}
