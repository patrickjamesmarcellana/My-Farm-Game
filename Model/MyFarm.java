package Model;

import Model.Plants.*;
import Model.Tools.*;
import java.util.ArrayList;

/**
 * This class represents the Model in the MVC Architecture of the game, which has a day
 * counter, a board, a farmer, the different tools, and the different kinds of plants.
 * <p>
 *     As the main model of the game, the MyFarm class is responsible for keeping track
 *     whether game over conditions are already met.
 * </p>
 */
public class MyFarm {

    // game elements
    private int currentDay;
    private Board board;
    private Farmer farmer;
    private ArrayList<Plant> plants;
    private ArrayList<Tool> tools;

    // constants
    private final int MAXROWS = 5;
    private final int MAXCOLUMNS = 10;

    public MyFarm() {
        // initialize elements
        this.currentDay = 1;
        this.board = new Board(MAXROWS, MAXCOLUMNS);
        this.farmer = new Farmer();
        this.plants = new ArrayList<>();
        this.tools = new ArrayList<>();

        // plants
        Turnip turnip = new Turnip();
        Carrot carrot = new Carrot();
        Potato potato = new Potato();
        Rose rose = new Rose();
        Turnips turnips = new Turnips();
        Sunflower sunflower = new Sunflower();
        Mango mango = new Mango();
        Apple apple = new Apple();

        this.plants.add(turnip);
        this.plants.add(carrot);
        this.plants.add(potato);
        this.plants.add(rose);
        this.plants.add(turnips);
        this.plants.add(sunflower);
        this.plants.add(mango);
        this.plants.add(apple);

        // tools
        Plow plow = new Plow();
        WateringCan wateringCan = new WateringCan();
        Fertilizer fertilizer = new Fertilizer();
        Pickaxe pickaxe = new Pickaxe();
        Shovel shovel = new Shovel();

        this.tools.add(plow);
        this.tools.add(wateringCan);
        this.tools.add(fertilizer);
        this.tools.add(pickaxe);
        this.tools.add(shovel);
    }

    /**
     * Checks if the game's losing conditions are met. These conditions include having
     * no more active crops and zero Objectcoins or having a lot that is totally
     * filled with withered crops.
     *
     * @return 1 if the player no longer has crops and enough money to buy seeds,
     *         2 if all the tiles have withered crops
     *         3 if no losing conditions are met yet
     */
    public int checkIfGameOver() {
        double cheapestSeedCost = PlantList.TURNIP.getSeedCost();
        if(!(this.board.getGrowingCropCount() +
             this.board.getFullyGrownCropCount() > 0) &&
           !((this.farmer.getObjectCoins() >=
             (cheapestSeedCost + this.farmer.getSeedCostReduction())))) {
            // no more crops and no longer have enough money to buy seeds
            return 1;
        } else if(!(this.board.getWitheredCropCount() <
                   (this.MAXROWS * this.MAXCOLUMNS))) {
            // all tiles have withered crops
            return 2;
        }

        return 3;
    }

    // getters and setters
    /**
     * Gets the maximum number of rows applied to the board. MyFarm has
     * 5 maximum rows as it is a 10x5 farm lot.
     * @return the maximum number of rows of the farm lot.
     */
    public int getMAXROWS() {
        return MAXROWS;
    }

    /**
     * Gets the maximum number of columns applied to the board. MyFarm has
     * 10 maximum rows as it is a 10x5 farm lot.
     * @return the maximum number of columns of the farm lot.
     */
    public int getMAXCOLUMNS() {
        return MAXCOLUMNS;
    }

    /**
     * Gets the current day number of the game.
     * @return  the integer representing the current day of the game.
     */
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Sets the current day number of the game.
     * @param currentDay the integer representing the current day of the game.
     */
    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    /**
     * Gets the board of the game.
     * @return My Farm's board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the board of the game.
     * @param board My Farm's board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /** Gets the farmer of the game.
     * @return My Farm's farmer.
     */
    public Farmer getFarmer() {
        return farmer;
    }

    /**
     * Sets the farmer of the game.
     * @param farmer My Farm's farmer
     */
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    /**
     * Gets the plants available in My Farm.
     * @return the list of plants in My Farm.
     */
    public ArrayList<Plant> getPlants() {
        return plants;
    }

    /**
     * Sets the plants available in My Farm.
     * @param plants the list of plants in My Farm
     */
    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    /**
     * Gets the tools available in My Farm.
     * @return the list of tools in My Farm.
     */
    public ArrayList<Tool> getTools() {
        return tools;
    }

    /**
     * Sets the tools available in My Farm.
     * @param tools the list of tools in My Farm.
     */
    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }
}
