package Model.Plants;

/**
 * This class represents the blueprint for a turnips plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Turnips extends Plant {

    /**
     * Creates an instance of a turnips. To create a Turnips object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Turnips() {
        super(PlantList.TURNIPS, PlantList.TURNIPS.getCropType(),
              PlantList.TURNIPS.getGrowingTimeDays(), PlantList.TURNIPS.getWaterNeeds(),
              PlantList.TURNIPS.getWaterBonusLimit(), PlantList.TURNIPS.getFertilizerNeeds(),
              PlantList.TURNIPS.getFertilizerBonusLimit(), PlantList.TURNIPS.getProductNumLowerLimit(),
              PlantList.TURNIPS.getProductNumUpperLimit(), PlantList.TURNIPS.getSeedCost(),
              PlantList.TURNIPS.getBaseSellingCost(), PlantList.TURNIPS.getExpYield());
    }
}
