package Model.Plants;

/**
 * This class represents the blueprint for an apple plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Apple extends Plant {

    /**
     * Creates an instance of an apple. To create an Apple object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Apple() {
        super(PlantList.APPLE, PlantList.APPLE.getCropType(),
              PlantList.APPLE.getGrowingTimeDays(), PlantList.APPLE.getWaterNeeds(),
              PlantList.APPLE.getWaterBonusLimit(), PlantList.APPLE.getFertilizerNeeds(),
              PlantList.APPLE.getFertilizerBonusLimit(), PlantList.APPLE.getProductNumLowerLimit(),
              PlantList.APPLE.getProductNumUpperLimit(), PlantList.APPLE.getSeedCost(),
              PlantList.APPLE.getBaseSellingCost(), PlantList.APPLE.getExpYield());
    }
}
