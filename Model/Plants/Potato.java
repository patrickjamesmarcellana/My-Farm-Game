package Model.Plants;

/**
 * This class represents the blueprint for a potato plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 */
public class Potato extends Plant {

    /**
     * Creates an instance of a potato. To create a Potato object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Potato() {
        super(PlantList.POTATO, PlantList.POTATO.getCropType(),
              PlantList.POTATO.getGrowingTimeDays(),  PlantList.POTATO.getWaterNeeds(),
              PlantList.POTATO.getWaterBonusLimit(), PlantList.POTATO.getFertilizerNeeds(),
              PlantList.POTATO.getFertilizerBonusLimit(), PlantList.POTATO.getProductNumLowerLimit(),
              PlantList.POTATO.getProductNumUpperLimit(), PlantList.POTATO.getSeedCost(),
              PlantList.POTATO.getBaseSellingCost(), PlantList.POTATO.getExpYield());
    }
}
