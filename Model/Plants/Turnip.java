package Model.Plants;

/**
 * This class represents the blueprint for a turnip plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Turnip extends Plant {

    /**
     * Creates an instance of a turnip. To create a Turnip object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Turnip() {
        super(PlantList.TURNIP, PlantList.TURNIP.getCropType(),
              PlantList.TURNIP.getGrowingTimeDays(),  PlantList.TURNIP.getWaterNeeds(),
              PlantList.TURNIP.getWaterBonusLimit(), PlantList.TURNIP.getFertilizerNeeds(),
              PlantList.TURNIP.getFertilizerBonusLimit(), PlantList.TURNIP.getProductNumLowerLimit(),
              PlantList.TURNIP.getProductNumUpperLimit(), PlantList.TURNIP.getSeedCost(),
              PlantList.TURNIP.getBaseSellingCost(), PlantList.TURNIP.getExpYield());
    }
}
