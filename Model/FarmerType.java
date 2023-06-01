package Model;

/**
 * This enum represents the different farmer types, including farmer, registered farmer,
 * distinguished farmer, and legendary farmer, and their respective unmodifiable
 * attributes.
 * <p>
 *     FARMER is the default farmer type, and the farmer may only register
 *     to a higher farmer type. Their respective attributes include the type
 *     name, level requirement, bonus when earning, discount on seed cost,
 *     additional water and fertilizer bonus limits, and the registration fee.
 * </p>
 */
public enum FarmerType {
    FARMER("Farmer", 0, 0.0d, 0.0d,
            0, 0, 0.0d),
    REGISTEREDFARMER("Registered Farmer", 5, 1.0d,
            -1.0d, 0, 0, 200.0d),
    DISTINGUISHEDFARMER("Distinguished Farmer", 10, 2.0d,
            -2.0d, 1, 0, 300.0d),
    LEGENDARYFARMER("Legendary Farmer", 15, 4.0d,
            -3.0d, 2, 1, 400.0d);

    private final String typeName;
    private final int levelRequirement;
    private final double bonusEarnings;
    private final double seedCostReduction;
    private final int addWaterBonusLimit;
    private final int addFertBonusLimit;
    private final double registrationFee;

    /**
     * Creates a farmer type data type with its type name, level
     * requirement, bonus earnings, seed cost reduction, additional water and
     * fertilizer bonus limits, and the registration fee.
     *
     * @param typeName              the name of the farmer type.
     * @param levelRequirement      the level needed before a farmer can
     *                              register to the type
     * @param bonusEarnings         additional Objectcoins added when selling
     *                              a crop
     * @param seedCostReduction     discount when buying a seed
     * @param addWaterBonusLimit    additional to the water bonus limit of
     *                              plants
     * @param addFertBonusLimit     additional to the fertilizer bonus limit
     *                              of plants
     * @param registrationFee       amount of Objectcoins needed for a farmer
     *                              to register
     */
    FarmerType (String typeName,
                int levelRequirement,
                double bonusEarnings,
                double seedCostReduction,
                int addWaterBonusLimit,
                int addFertBonusLimit,
                double registrationFee) {
        this.typeName = typeName;
        this.levelRequirement = levelRequirement;
        this.bonusEarnings = bonusEarnings;
        this.seedCostReduction = seedCostReduction;
        this.addWaterBonusLimit = addWaterBonusLimit;
        this.addFertBonusLimit = addFertBonusLimit;
        this.registrationFee = registrationFee;
    }

    /**
     * Gets the name of the farmer type.
     * @return the farmer type's name.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Gets the level requirement in order for a farmer to register to
     * this type of farmer.
     * @return the farmer type's required level.
     */
    public int getLevelRequirement() {
        return levelRequirement;
    }

    /**
     * Gets the bonus number of coins a farmer earns per product produced
     * in harvesting crops.
     * @return the farmer's bonus earnings per product.
     */
    public double getBonusEarnings() {
        return bonusEarnings;
    }

    /**
     * Gets the number of coins deducted from a seed's cost when buying and
     * planting a seed.
     * @return the discount applied per seed when purchasing/planting seeds.
     */
    public double getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * Gets the additional water bonus limit applied to the computation of
     * the final harvest price.
     * @return the farmer's additional water bonus limit.
     */
    public int getAddWaterBonusLimit() {
        return addWaterBonusLimit;
    }

    /**
     * Gets the additional fertilizer bonus limit applied to the computation
     * of the final harvest price.
     * @return the farmer's additional fertilizer bonus limit.
     */
    public int getAddFertBonusLimit() {
        return addFertBonusLimit;
    }

    /**
     * Gets the amount of coins needed for a farmer to register to this
     * specific farmer type.
     * @return the farmer type's registration fee in Objectcoins.
     */
    public double getRegistrationFee() {
        return registrationFee;
    }
}
