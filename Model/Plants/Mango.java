package Model.Plants;

/**
 * This class represents the blueprint for a mango plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Mango extends Plant {

    /**
     * Creates an instance of a mango. To create a Mango object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Mango() {
        super(PlantList.MANGO, PlantList.MANGO.getCropType(),
              PlantList.MANGO.getGrowingTimeDays(), PlantList.MANGO.getWaterNeeds(),
              PlantList.MANGO.getWaterBonusLimit(), PlantList.MANGO.getFertilizerNeeds(),
              PlantList.MANGO.getFertilizerBonusLimit(), PlantList.MANGO.getProductNumLowerLimit(),
              PlantList.MANGO.getProductNumUpperLimit(), PlantList.MANGO.getSeedCost(),
              PlantList.MANGO.getBaseSellingCost(), PlantList.MANGO.getExpYield());
    }
}
