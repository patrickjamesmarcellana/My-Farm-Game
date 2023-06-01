package Model.Plants;

/**
 * This enum represents the list of plants available in the game, including
 * turnip, carrot, potato, rose, turnips, sunflower, mango, and apple. Each
 * plant has constant or unmodifiable attributes.
 * <p>
 *     The constant attributes that each kind of plant have include the seed
 *     name, crop type, growing time, water and fertilizer needs, water and
 *     fertilizer bonus limits, products produced, seed cost, base selling
 *     cost, and experience yielded.
 * </p>
 */
public enum PlantList {

    TURNIP("Turnip", "Root crop", 2, 1,
            2, 0, 1, 1,
            2, 5.0d, 6.0d, 5.0d),
    CARROT("Carrot", "Root Crop", 3, 1,
            2, 0, 1, 1,
            2, 10.0d, 9.0d, 7.5d),
    POTATO("Potato", "Root Crop", 5, 3,
            4, 1, 2, 1,
            10, 20.0d, 3.0d, 12.5d),
    ROSE("Rose", "Flower", 1, 1,
            2, 0, 1, 1,
            1, 5.0d, 5.0d, 2.5d),
    TURNIPS("Turnips", "Flower", 2, 2,
            3, 0, 1, 1,
            1, 10.0d, 9.0d, 5.0d),
    SUNFLOWER("Sunflower", "Flower", 3, 2,
            3, 1, 2, 1,
            1, 20.0d, 19.0d, 7.5d),
    MANGO("Mango", "Fruit Tree", 10, 7,
            7, 4, 4, 5,
            15, 100.0d, 8.0d, 25.0d),
    APPLE("Apple", "Fruit Tree", 10, 7,
            7, 5, 5, 10,
            15, 200.0d, 5.0d, 25.0d);

    private final String seedName;
    private final String cropType;
    private final int growingTimeDays;
    private final int waterNeeds;
    private final int waterBonusLimit;
    private final int fertilizerNeeds;
    private final int fertilizerBonusLimit;
    private final int productNumLowerLimit;
    private final int productNumUpperLimit;
    private final double seedCost;
    private final double baseSellingCost;
    private final double expYield;

    /**
     * Creates a plant data type with its seed name, crop type,
     * growing time in days, water and fertilizer needs, water and fertilizer
     * bonus limits, number of products produced, seed cost, base selling cost,
     * and experience yield per crop.
     *
     * @param seedName              the name of the seed/plant
     * @param cropType              the type of crop
     * @param growingTimeDays       the amount of days for the plant to grow
     * @param waterNeeds            the amount of water that a plant needs
     * @param waterBonusLimit       the bonus limit of the number of times a
     *                              plant is watered in computing for the price
     * @param fertilizerNeeds       the amount of fertilizer a plant needs
     * @param fertilizerBonusLimit  the bonus limit of the times a plant is
     *      *                       fertilized in computing for the price
     * @param productNumLowerLimit  the lower bound of the products produced
     * @param productNumUpperLimit  the upper bound of the products produced
     * @param seedCost              the cost of buying the seed
     * @param baseSellingCost       the base cost when selling the seed
     * @param expYield              the experience given per product
     */
    PlantList(String seedName,
              String cropType,
              int growingTimeDays,
              int waterNeeds,
              int waterBonusLimit,
              int fertilizerNeeds,
              int fertilizerBonusLimit,
              int productNumLowerLimit,
              int productNumUpperLimit,
              double seedCost,
              double baseSellingCost,
              double expYield) {
        this.seedName = seedName;
        this.cropType = cropType;
        this.growingTimeDays = growingTimeDays;
        this.waterNeeds = waterNeeds;
        this.waterBonusLimit = waterBonusLimit;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonusLimit = fertilizerBonusLimit;
        this.productNumLowerLimit = productNumLowerLimit;
        this.productNumUpperLimit = productNumUpperLimit;
        this.seedCost = seedCost;
        this.baseSellingCost = baseSellingCost;
        this.expYield = expYield;
    }

    /**
     * Gets the name of the seed or plant.
     * @return the plant/seed name.
     */
    public String getSeedName() {
        return seedName;
    }

    /**
     * Gets the type of crop (either Root Crop, Flower, or Fruit Tree)
     * @return the crop type of the plant.
     */
    public String getCropType() {
        return cropType;
    }

    /**
     * Gets the number of days that a plant needs to grow fully.
     * @return the plant's growing time in days.
     */
    public int getGrowingTimeDays() {
        return growingTimeDays;
    }

    /**
     * Gets the amount of water a plant needs for it to be ready to harvest.
     * @return the number of times a plant needs to be watered.
     */
    public int getWaterNeeds() {
        return waterNeeds;
    }

    /**
     * Gets the water bonus limit of the plant.
     * @return the most number of times a plant can be watered in terms of
     *         computing for the plant's final price.
     */
    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    /**
     * Gets the amount of fertilizer a plant needs for it to be ready to harvest.
     * @return the number of times a plant needs to be fertilized.
     */
    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }

    /**
     * Gets the fertilizer bonus limit of the plant.
     * @return the most number of times a plant can be fertilized in terms of
     *         computing for the plant's final price.
     */
    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

    /**
     * Gets the minimum number of products a plant can produce.
     * @return the lower limit of the plant's produced products.
     */
    public int getProductNumLowerLimit() {
        return productNumLowerLimit;
    }

    /**
     * Gets the maximum number of products a plant can produce.
     * @return the upper limit of the plant's produced products.
     */
    public int getProductNumUpperLimit() {
        return productNumUpperLimit;
    }

    /**
     * Gets the cost of the seed when buying and planting it.
     * @return the seed buying cost of the plant.
     */
    public double getSeedCost() {
        return seedCost;
    }

    /**
     * Gets the base selling price of the plant per product upon harvest.
     * @return the plant's base selling cost per product.
     */
    public double getBaseSellingCost() {
        return baseSellingCost;
    }

    /**
     * Gets the experience yielded by the plant to the farmer per product produced.
     * @return the experience yielded by the plant.
     */
    public double getExpYield() {
        return expYield;
    }
}
