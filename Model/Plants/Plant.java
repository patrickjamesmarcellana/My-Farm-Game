package Model.Plants;

/**
 * This abstract class represents the blueprint for each plant or
 * crop available in MyFarm.
 * <p>
 *     A plant object keeps track of its current growth, the number of times it was
 *     watered and fertilized, and its status (fully grown, growing, or withered).
 *     It also takes note of the number of products it produced and its selling prices
 *     when harvested and sold. A plant also has constant allocated attributes depending
 *     on the crop.
 * </p>
 */
public abstract class Plant {

    // dynamic attributes
    private int currentGrowthDays = 0;
    private int growthDaysLeft;
    private int wateredCount = 0;
    private int fertilizedCount = 0;
    private int productsProduced;
    private boolean isReadyToHarvest = false;
    private boolean isWithered = false;
    private double harvestTotal;
    private double waterBonus;
    private double fertilizerBonus;
    private double finalHarvestPrice;

    // constant attributes
    private PlantList name;
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
     * Creates a plant object with its name, crop type, growing time in days, water and
     * fertilizer needs, water and fertilizer bonus limits, number of products produced,
     * seed cost, base selling cost, and experience yield per crop.
     * @param name                  the name of the plant
     * @param cropType              the type of crop
     * @param growingTimeDays       the amount of days for the plant to grow
     * @param waterNeeds            the amount of water that a plant needs
     * @param waterBonusLimit       the bonus limit of the number of times a
     *                              plant is watered in computing for the price
     * @param fertilizerNeeds       the amount of fertilizer a plant needs
     * @param fertilizerBonusLimit  the bonus limit of the times a plant is
     *                              fertilized in computing for the price
     * @param productNumLowerLimit  the lower bound of the products produced
     * @param productNumUpperLimit  the upper bound of the products produced
     * @param seedCost              the cost of buying the seed
     * @param baseSellingCost       the base cost when selling the seed
     * @param expYield              the experience given per product
     */
    public Plant(PlantList name, String cropType, int growingTimeDays, int waterNeeds,
                 int waterBonusLimit, int fertilizerNeeds, int fertilizerBonusLimit,
                 int productNumLowerLimit, int productNumUpperLimit, double seedCost,
                 double baseSellingCost, double expYield) {
        this.name = name;
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
     * Updates the status of plants to withered or ready-to-harvest if the growing
     * conditions are not met or met, respectively. The total number of products
     * produced is also computed here if the plant becomes ready to harvest.
     * Credit for random number generator with upper and lower limit:
     * https://www.educative.io/answers/how-to-generate-random-numbers-in-java
     */
    public void updatePlantStatus() {
        if((this.currentGrowthDays > this.growingTimeDays) ||
           ((this.fertilizedCount < this.fertilizerNeeds ||
             this.wateredCount < this.waterNeeds) && this.growthDaysLeft == 0)) {
            this.isWithered = true;
            this.isReadyToHarvest = false;
            this.productsProduced = 0;
        } else if(this.growthDaysLeft == 0) {
            this.isReadyToHarvest = true;
            this.productsProduced  = (int)Math.floor(Math.random() *
                                     (this.productNumUpperLimit -
                                      this.productNumLowerLimit + 1) +
                                      this.productNumLowerLimit);
        }
    }

    /**
     * Gets all the information about the plant.
     * @return the string that contains plant information.
     */
    public String getPlantInfo() {
        int tabCount; // different tab count for sunflower because of its string length
        if(this.name.equals(PlantList.SUNFLOWER))
            tabCount = 2;
        else
            tabCount = 3;

        String plantInfo = "PLANT INFORMATION\n" + "-".repeat(106) +
                "\nName: " + this.name.getSeedName() +
                "\t".repeat(tabCount) + "Type: " + this.cropType +
                "\nGrowing Time: " + this.growingTimeDays + " day(s) " +
                "\t\tWater Needed: " + this.waterNeeds +
                "\nWater Bonus Limit: " + this.waterBonusLimit +
                "\t\tFertilizer Needed: " + this.fertilizerNeeds +
                "\nFertilizer Bonus Limit: " + this.fertilizerBonusLimit;

        // separate those with only 1 constant product count from those with range
        if(this.productNumLowerLimit == this.productNumUpperLimit)
            plantInfo = plantInfo.concat("\t\tProducts Produced: " +
                    this.productNumLowerLimit);
        else
            plantInfo = plantInfo.concat("\t\tProducts Produced: " +
                    this.productNumLowerLimit + "-" +
                    this.productNumUpperLimit);

        plantInfo = plantInfo.concat(
                "\nSeed Cost: " + this.seedCost + " Objectcoins" +
                        "\nBase Selling Cost per Product: " + this.baseSellingCost +
                        " Objectcoins" + "\nExperience Yield per Crop: " +
                        this.expYield + " XP");
        return plantInfo;
    }

    // getters and setters
    /**
     * Gets the name of the plant.
     * @return the name of the plant.
     */
    public PlantList getName() {
        return name;
    }

    /**
     * Sets the name of the plant.
     * @param name the name of the plant.
     */
    public void setName(PlantList name) {
        this.name = name;
    }

    /**
     * Increments the number of days that have passed since the plant was planted.
     */
    public void addCurrentGrowthDays() {
        this.currentGrowthDays++;
    }

    /**
     * Gets the number of days left before the plant becomes ready to harvest.
     * @return the plant's days left of growing.
     */
    public int getGrowthDaysLeft() {
        return growthDaysLeft;
    }

    /**
     * Sets the number of days left before the plant becomes ready to harvest.
     * @param growthDaysLeft an integer representing the plant's number of
     *                       growth days left before it can be harvested.
     */
    public void setGrowthDaysLeft(int growthDaysLeft) {
        this.growthDaysLeft = growthDaysLeft;
    }

    /**
     * Decrements the number of days left before the plant becomes ready to harvest.
     */
    public void reduceGrowthDaysLeft() {
        this.growthDaysLeft--;
    }

    /**
     * Gets the current number of times a plant is watered.
     * @return the number of times a plant has been watered.
     */
    public int getWateredCount() {
        return wateredCount;
    }

    /**
     * Increments the number of times a plant is watered.
     */
    public void addWateredCount() {
        this.wateredCount++;
    }

    /**
     * Gets the number of times a plant has been fertilized.
     * @return the number of times a plant has been fertilized.
     */
    public int getFertilizedCount() {
        return fertilizedCount;
    }

    /**
     * Increments the number of times a plant/tile has been fertilized.
     */
    public void addFertilizedCount() {
        this.fertilizedCount++;
    }

    /**
     * Gets the status of the plant whether it is ready to harvest or not.
     * @return true if the plant is ready to harvest, false if otherwise.
     */
    public boolean getIsReadyToHarvest() {
        return isReadyToHarvest;
    }

    /**
     * Sets the status of the plant to either ready to harvest or not, based
     * on the value provided.
     * @param isReadyToHarvest boolean variable that implies whether the plant
     *                         is ready to harvest or not
     */
    public void setIsReadyToHarvest(boolean isReadyToHarvest) {
        this.isReadyToHarvest = isReadyToHarvest;
    }

    /**
     * Gets the number of products that the plant produced upon becoming
     * ready to harvest.
     * @return the number of products produced by the plant.
     */
    public int getProductsProduced() {
        return productsProduced;
    }

    /**
     * Gets the status of the plant, whether it has already withered or not yet.
     * @return true if the crop is withered, false if otherwise.
     */
    public boolean getIsWithered() {
        return isWithered;
    }

    /**
     * Gets the value of the price of the harvested products without the
     * applied bonuses when selling.
     * @return the price of the harvest without the bonuses yet (not final selling price)
     */
    public double getHarvestTotal() {
        return harvestTotal;
    }

    /**
     * Sets the value of the price of the harvested products, computed by
     * multiplying the number of products produced and the sum of the plant's
     * base selling cost and the farmer's bonus earnings per product.
     * @param harvestTotal the price of the harvest without the bonuses yet
     *                     (not final selling price).
     */
    public void setHarvestTotal(double harvestTotal) {
        this.harvestTotal = harvestTotal;
    }

    /**
     * Gets the computed water bonus of the harvest.
     * @return the water bonus of the harvest.
     */
    public double getWaterBonus() {
        return waterBonus;
    }

    /**
     * Sets the computed water bonus of the harvest, computed by multiplying
     * the 1/5 of the harvest total and the total number of times the crop
     * is watered (capped by bonus limits) minus 1.
     * @param waterBonus the computed water bonus of the harvest.
     */
    public void setWaterBonus(double waterBonus) {
        this.waterBonus = waterBonus;
    }

    /**
     * Gets the computed fertilizer bonus of the harvest.
     * @return the fertilizer bonus of the harvest.
     */
    public double getFertilizerBonus() {
        return fertilizerBonus;
    }

    /**
     * Sets the computed fertilizer bonus of the harvest, computed by
     * multiplying 1/2 of the harvest total to the total number of times
     * the crop was fertilized.
     * @param fertilizerBonus the fertilizer bonus of the harvest.
     */
    public void setFertilizerBonus(double fertilizerBonus) {
        this.fertilizerBonus = fertilizerBonus;
    }

    /**
     * Gets the final computed harvest price, which is the final amount the
     * harvest is sold for, and the amount that the farmer obtains.
     * @return the final price of the harvest.
     */
    public double getFinalHarvestPrice() {
        return finalHarvestPrice;
    }

    /**
     * Sets the final computed harvest price, computed by adding the harvest
     * total, the water bonus, and the fertilizer bonus of the harvest.
     * @param finalHarvestPrice the final price of the harvest
     */
    public void setFinalHarvestPrice(double finalHarvestPrice) {
        this.finalHarvestPrice = finalHarvestPrice;
    }

    /**
     * Gets the type of crop the plant has.
     * @return the plant's crop type.
     */
    public String getCropType() {
        return cropType;
    }

    /**
     * Gets the number of days a plant needs to grow.
     * @return the number of days a plant needs to grow.
     */
    public int getGrowingTimeDays() {
        return growingTimeDays;
    }

    /**
     * Gets the amount of water a plant needs.
     * @return the amount of water a plant needs.
     */
    public int getWaterNeeds() {
        return waterNeeds;
    }

    /**
     * Gets the water bonus limit that the plant has.
     * @return the plant's water bonus limit.
     */
    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    /**
     * Gets the amount of fertilizer a plant needs.
     * @return the amount of fertilizer a plant needs.
     */
    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }

    /**
     * Gets the fertilizer bonus limit a plant has.
     * @return the plant's fertilizer bonus limit.
     */
    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

    /**
     * Gets the amount needed to purchase or plant the seed.
     * @return the plant's seed cost.
     */
    public double getSeedCost() {
        return seedCost;
    }

    /**
     * Gets the base cost of the plant when selling it.
     * @return the plant's base selling cost.
     */
    public double getBaseSellingCost() {
        return baseSellingCost;
    }

    /**
     * Gets the experience yielded by the plant when harvested.
     * @return the amount of experience a plant gives when harvested.
     */
    public double getExpYield() {
        return expYield;
    }
}
