package Model.Tools;

import Model.*;

/**
 * This class represents the blueprint for the tool, Pickaxe.
 * <p>
 *     It inherits from the parent class, Tool, and it has one sole method,
 *     which is to remove a rock.
 * </p>
 */
public class Pickaxe extends Tool {

    /**
     * Creates a pickaxe object.
     */
    public Pickaxe() {
        super(ToolType.PICKAXE, ToolType.PICKAXE.getCost(),
              ToolType.PICKAXE.getUse(), ToolType.PICKAXE.getExpGain());
    }

    /**
     * Sets the "has a rock" and "is occupied" statuses of the tile to false.
     * @param tile the tile that supposedly contains the rock
     * @return true if a rock was successfully destroyed on the tile, false
     *         if otherwise.
     */
    public boolean destroyRock(Tile tile) {
        if(tile.getHasRock()) {
            // only destroy a rock if the rock is present on the tile
            tile.setHasRock(false);
            tile.setIsOccupied(false);
            return true;
        }
        return false;
    }
}
