package Model;

import Model.Plants.Plant;
import Model.Tools.*;

/**
 * This class represents the farmer in the MyFarm game, which has a type, an amount of
 * experience, a level, and an amount of Objectcoins. Depending on the type, the farmer
 * can get specific benefits in earning, selling, watering, and fertilizing.
 * <p>
 *     The level depends on the player's experience, and the Objectcoins are considered
 *     the main currency of the game, which can be used to buy seeds and use tools.
 *     A farmer can typically plant a seed, use the different tools, harvest crops, and
 *     register himself or herself to a higher farmer type.
 * </p>
 */
public class Farmer {

    // dynamic variables
    private FarmerType type;
    private double experience = 0.0d;
    private int level = (int)(this.experience / 100);
    private double objectCoins = 100.0d;

    // constant variables
    private double bonusEarnings;
    private double seedCostReduction;
    private int addWaterBonusLimit;
    private int addFertBonusLimit;

    /**
     * Creates a farmer object. The default farmer type is FARMER.
     */
    public Farmer() {
        this.type = FarmerType.FARMER;
        this.bonusEarnings = type.getBonusEarnings();
        this.seedCostReduction = type.getSeedCostReduction();
        this.addWaterBonusLimit = type.getAddWaterBonusLimit();
        this.addFertBonusLimit = type.getAddFertBonusLimit();
    }

    /**
     * Adds the experience gain on the current experience of the farmer, and updates
     * the farmer's level if there is any update possible.
     * @param expGain the amount of experience added to the current one
     * @return true if the farmer leveled up, false if otherwise.
     */
    public boolean updateExpAndLevel(double expGain) {
        int previousLevel = this.level;
        this.addExperience(expGain);
        this.level = (int)(this.experience / 100);

        return previousLevel < this.level;
    }

    /**
     * Allows the farmer to use the tool specified on the desired tile provided.
     * @param tool   the tool that the farmer will use
     * @param tile   the tile where the tool will be used
     * @param plant  the plant associated with the tile, if there's any
     * @return true if the tool use is successful; otherwise, return false
     */
    public boolean useTool(Tool tool, Tile tile, Plant plant) {
        if(tool.getType().equals(ToolType.PLOW)) {
            Plow plow = new Plow();
            return plow.plowTile(tile);
        } else if(tool.getType().equals(ToolType.WATERINGCAN)) {
            WateringCan wateringCan = new WateringCan();
            return wateringCan.waterPlant(tile, plant);
        } else if(tool.getType().equals(ToolType.FERTILIZER)) {
            Fertilizer fertilizer = new Fertilizer();
            return fertilizer.fertilizePlant(tile, plant);
        } else if(tool.getType().equals(ToolType.PICKAXE)) {
            Pickaxe pickaxe = new Pickaxe();
            return pickaxe.destroyRock(tile);
        } else {
            Shovel shovel = new Shovel();
            return shovel.removeWitheredCrop(tile);
        }
    }

    /**
     * Lets the farmer plant a seed only if the tile is plowed and unoccupied. It then
     * updates the respective tile and plant statuses and deducts the seed cost from the
     * farmer's coins.
     * @param seedChoice   the desired seed that will be planted
     * @param tile         the desired tile where the seed will be planted
     */
    public void plantSeed(Plant seedChoice, Tile tile) {
        // plant the seed only if it is plowed
        if(!tile.getIsOccupied() && tile.getIsPlowed()) {
            tile.setCropPlanted(seedChoice);
            tile.setHasCrop(true);
            tile.setIsOccupied(true);
            tile.setIsPlowed(false);
            seedChoice.setGrowthDaysLeft(seedChoice.getGrowingTimeDays());

            // deduct total price from current Objectcoins
            double totalSeedCost = seedChoice.getSeedCost() +
                                   this.getType().getSeedCostReduction();
            this.objectCoins -= totalSeedCost;
        }
    }

    /**
     * Allows the farmer to harvest the crop by computing for the necessary computations
     * for the final price of the harvest, crediting this amount to the farmer's
     * Objectcoins, and updating the tile and plant statuses.
     * @param tile  the position on the lot where the crop will be harvested
     * @param plant the crop that will be planted
     */
    public void harvestPlant(Tile tile, Plant plant) {
        // compute for times watered and times fertilized
        int totalWaterBonusLimit = plant.getWaterBonusLimit() +
                                   this.getAddWaterBonusLimit();
        int totalFertilizerBonusLimit = plant.getFertilizerBonusLimit() +
                                        this.getAddFertBonusLimit();
        int timesCropWatered = Math.min(plant.getWateredCount(), totalWaterBonusLimit);
        int timesCropFertilized = Math.min(plant.getFertilizedCount(),
                                           totalFertilizerBonusLimit);

        // compute harvest total, water bonus, fertilizer bonus, final price
        plant.setHarvestTotal((double)plant.getProductsProduced() *
                              (plant.getBaseSellingCost() + this.getBonusEarnings()));
        plant.setWaterBonus(plant.getHarvestTotal() * 0.2d *
                           ((double)(timesCropWatered - 1)));
        plant.setFertilizerBonus(plant.getHarvestTotal() * 0.5d *
                                (double)timesCropFertilized);
        plant.setFinalHarvestPrice(plant.getHarvestTotal() + plant.getWaterBonus() +
                                   plant.getFertilizerBonus());

        //check if flower
        if (plant.getCropType().equals("Flower"))
            plant.setFinalHarvestPrice(plant.getFinalHarvestPrice() * 1.1);

        // credit harvest gains to farmer's Objectcoins
        this.objectCoins += plant.getFinalHarvestPrice();

        // update tile and plant statuses
        tile.getCropPlanted().setIsReadyToHarvest(false);
        tile.setHasFullyGrownCrop(false);
        tile.setHasWitheredCrop(false);
        tile.setCropPlanted(null);
        tile.setHasCrop(false);
        tile.setIsOccupied(false);
    }

    /**
     * Upgrades the farmer type to a higher farmer type. The farmer can only register
     * to higher farmer types. He or she cannot register to the same farmer type as well.
     * @param farmerType the farmer type to register to
     */
    public void upgradeFarmer(FarmerType farmerType) {
        //TODO FIX
        this.setType(farmerType);
        this.objectCoins -= farmerType.getRegistrationFee();

    }

    /**
     * Gets all the information about the harvest, including the number of products
     * produced, the computations, and the final Objectcoins gain.
     * @param plant the plant/crop that is being harvested.
     * @return the string that contains the details of the harvest.
     */
    public String getHarvestDetails(Plant plant) {
        String waterBonus = String.format("%.1f", plant.getWaterBonus());
        String finalHarvestGain = String.format("%.1f", plant.getFinalHarvestPrice());

        return "HARVEST DETAILS\n" + "-".repeat(106) +
                "\nAmount of Products: " + plant.getProductsProduced() +
                "\nHarvest Total: " + plant.getHarvestTotal() +
                "\nWater Bonus: " + waterBonus +
                "\nFertilizer Bonus: " + plant.getFertilizerBonus() +
                "\nFinal Harvest Gain: " + finalHarvestGain +
                "\nExperience Gained: " + (plant.getExpYield());
    }

    /**
     * Gets all the information about the farmer.
     * @return the string that contains the farmer information.
     */
    public String getFarmerInfo() {
        return "Farmer Type: " + this.type.getTypeName() +
                "\nLevel: " + this.level +
                "\nCurrent Exp: " + this.experience +
                "\nObjectcoins: " + this.objectCoins + "\n" +
                "-".repeat(106);
    }

    // getters and setters
    /**
     * Gets the current farmer type of the farmer.
     * @return the farmer's current farmer type.
     */
    public FarmerType getType() {
        return type;
    }

    /**
     * Sets the farmer type of the farmer object. Only a higher farmer type from the
     * current one should be set to the farmer.
     * @param type  the farmer's type with respective attributes/bonuses attached.
     */
    public void setType(FarmerType type) {
        this.type = type;
        this.bonusEarnings = type.getBonusEarnings();
        this.seedCostReduction = type.getSeedCostReduction();
        this.addWaterBonusLimit = type.getAddWaterBonusLimit();
        this.addFertBonusLimit = type.getAddFertBonusLimit();
    }

    /**
     * Gets the amount of experience that the farmer has.
     * @return the farmer's current experience amount.
     */
    public double getExperience() {
        return experience;
    }

    /**
     * Sets the amount of experience a farmer currently has.
     * @param experience the farmer's current experience amount.
     */
    public void setExperience(double experience) {
        this.experience = experience;
    }

    /**
     * Adds experience to the current experience of the farmer based on the value given.
     * @param experience the amount of experience that is going to be added to the
     *                   farmer's experience
     */
    public void addExperience(double experience) {
        this.experience += experience;
    }

    /**
     * Gets the current level the farmer has.
     * @return the farmer's current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level that the farmer currently has.
     * @param level the farmer's current level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the current amount of Objectcoins that a farmer has.
     * @return the farmer's current Objectcoins amount.
     */
    public double getObjectCoins() {
        return objectCoins;
    }

    /**
     * Sets the current number of objectCoins that the farmer currently has.
     * @param objectCoins the farmer's current Objectcoins amount
     */
    public void setObjectCoins(double objectCoins) {
        this.objectCoins = objectCoins;
    }

    /**
     * Gets the bonus earnings of the farmer when selling a product.
     * @return the farmer's bonus earnings amount.
     */
    public double getBonusEarnings() {
        return bonusEarnings;
    }

    /**
     * Gets the seed discount of the farmer when buying seeds.
     * @return the farmer's seed cost reduction amount.
     */
    public double getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * Gets the additional water bonus limit of the farmer.
     * @return the farmer's additional water bonus limit.
     */
    public int getAddWaterBonusLimit() {
        return addWaterBonusLimit;
    }

    /**
     * Gets the additional fertilizer bonus limit of the farmer.
     * @return the farmer's additional fertilizer bonus limit.
     */
    public int getAddFertBonusLimit() {
        return addFertBonusLimit;
    }
}
