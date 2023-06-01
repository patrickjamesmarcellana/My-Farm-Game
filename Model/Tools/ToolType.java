package Model.Tools;

/**
 * This enum  represents the different types of tools including the plow,
 * watering can, pickaxe, fertilizer, and the shovel. Each tool has
 * attributes with constant or unmodifiable values.
 * <p>
 *     The constant attributes that each tool has include the name, the cost,
 *     its use, and their experience given per use.
 * </p>
 */
public enum ToolType {

    WATERINGCAN("Watering Can", 0.0d,
                "Waters a plant.", 0.5d),
    PLOW("Plow", 0.0d,
            "Converts an unplowed tile to a plowed tile.", 0.5d),
    PICKAXE("Pickaxe", 50.0d,
            "Removes a rock from a tile.", 15.0d),
    FERTILIZER("Fertilizer", 10.0d,
            "Fertilizes a plant.", 4.0d),
    SHOVEL("Shovel", 7.0d,
            "Removes a withered plant from a tile.", 2.0d);

    private final String name;
    private final double cost;
    private final String use;
    private final double expGain;

    /**
     * Creates a tool type data type with its name, cost, use, and
     * experience gain per use.
     * @param name      the name/type of the tool
     * @param cost      the cost per usage of the tool
     * @param use       the specific use of the tool
     * @param expGain   the amount of experience gained per use of the tool
     */
    ToolType (String name,
              double cost,
              String use,
              double expGain) {
        this.name = name;
        this.cost = cost;
        this.use = use;
        this.expGain = expGain;
    }

    /**
     * Gets the name of the tool type.
     * @return the tool type's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the constant cost of the tool.
     * @return the cost of  the tool.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the specific use of the tool.
     * @return the tool's use or purpose.
     */
    public String getUse() {
        return use;
    }

    /**
     * Gets the tool's experience given per use.
     * @return the experience that the farmer gains per tool use.
     */
    public double getExpGain() {
        return expGain;
    }
}
