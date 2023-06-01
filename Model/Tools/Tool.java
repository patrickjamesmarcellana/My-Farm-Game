package Model.Tools;

/**
 * This abstract class represents the blueprint for each tool. Depending on the
 * type of tool, its constant attributes vary including its name, use, cost, and
 * experience yielded.
 * <p>
 *     There are five tools available in the game: Plow, Watering Can, Fertilizer,
 *     Pickaxe, and Shovel.
 * </p>
 */
public abstract class Tool {

    // attributes
    private final ToolType type;
    private final double cost;
    private final String use;
    private final double expGain;

    /**
     * Creates a tool object with its tool type.
     * @param type      the type of tool from the list of tool types in the
     *                  ToolType enum class
     * @param cost      the cost of the tool per use
     * @param use       the description of the tool's specific use
     * @param expGain   the amount of exp gained per use of the tool
     */
    public Tool(ToolType type, double cost, String use, double expGain) {
        this.type = type;
        this.cost = cost;
        this.use = use;
        this.expGain = expGain;
    }

    /**
     * Gets all the information about the tool.
     * @return the string that contains tool information.
     */
    public String getToolInfo() {
        return "TOOL INFORMATION\n" + "-".repeat(106) +
                "\nName: " + this.type.getName() +
                "\nCost: " + this.cost + " Objectcoins" +
                "\nUse: " + this.use +
                "\nExperience Gained per Use: " + this.expGain;
    }

    /**
     * Gets the type of the tool.
     * @return the type of tool of the tool object.
     */
    public ToolType getType() {
        return type;
    }

    /**
     * Gets the cost of the tool.
     * @return the cost of the tool.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the tool's specific use.
     * @return the description of the tool's use.
     */
    public String getUse() {
        return use;
    }

    /**
     * Gets the experience gained per tool use.
     * @return the amount of experience gained.
     */
    public double getExpGain() {
        return expGain;
    }
}
