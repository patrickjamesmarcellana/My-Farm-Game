package Model.Plants;

/**
 * This class represents the blueprint for a rose plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Rose extends Plant {

    /**
     * Creates an instance of a rose. To create a Rose object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Rose() {
        super(PlantList.ROSE, PlantList.ROSE.getCropType(),
              PlantList.ROSE.getGrowingTimeDays(), PlantList.ROSE.getWaterNeeds(),
              PlantList.ROSE.getWaterBonusLimit(), PlantList.ROSE.getFertilizerNeeds(),
              PlantList.ROSE.getFertilizerBonusLimit(), PlantList.ROSE.getProductNumLowerLimit(),
              PlantList.ROSE.getProductNumUpperLimit(), PlantList.ROSE.getSeedCost(),
              PlantList.ROSE.getBaseSellingCost(), PlantList.ROSE.getExpYield());
    }
}
