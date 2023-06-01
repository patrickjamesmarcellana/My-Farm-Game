package Model.Plants;

/**
 * This class represents the blueprint for a carrot plant.
 * <p>
 *     It inherits from the parent class, Plant, inheriting all the
 *     attributes that a plant has.
 * </p>
 */
public class Carrot extends Plant {

    /**
     * Creates an instance of a carrot. To create a Carrot object, one must supply its
     * name, crop type, growth time, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost, and
     * experience yielded.
     */
    public Carrot() {
        super(PlantList.CARROT, PlantList.CARROT.getCropType(),
              PlantList.CARROT.getGrowingTimeDays(), PlantList.CARROT.getWaterNeeds(),
              PlantList.CARROT.getWaterBonusLimit(), PlantList.CARROT.getFertilizerNeeds(),
              PlantList.CARROT.getFertilizerBonusLimit(), PlantList.CARROT.getProductNumLowerLimit(),
              PlantList.CARROT.getProductNumUpperLimit(), PlantList.CARROT.getSeedCost(),
              PlantList.CARROT.getBaseSellingCost(), PlantList.CARROT.getExpYield());
    }
}
