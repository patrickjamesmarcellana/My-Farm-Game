package Model.Plants;

/**
 * This class represents the blueprint for a sunflower plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Sunflower extends Plant {

    /**
     * Creates an instance of a sunflower. To create a Sunflower object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Sunflower() {
        super(PlantList.SUNFLOWER, PlantList.SUNFLOWER.getCropType(),
              PlantList.SUNFLOWER.getGrowingTimeDays(), PlantList.SUNFLOWER.getWaterNeeds(),
              PlantList.SUNFLOWER.getWaterBonusLimit(), PlantList.SUNFLOWER.getFertilizerNeeds(),
              PlantList.SUNFLOWER.getFertilizerBonusLimit(), PlantList.SUNFLOWER.getProductNumLowerLimit(),
              PlantList.SUNFLOWER.getProductNumUpperLimit(), PlantList.SUNFLOWER.getSeedCost(),
              PlantList.SUNFLOWER.getBaseSellingCost(), PlantList.SUNFLOWER.getExpYield());
    }
}
