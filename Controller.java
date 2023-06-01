import Model.FarmerType;
import Model.MyFarm;
import Model.Plants.*;
import Model.Tools.*;
import Model.Tile;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents the Controller in the MVC Architecture. It coordinates
 * mainly with the Model (MyFarm) and the View (MyFarmGUI).
 * <p>
 *     This class mainly handles the tasks done by clicking each button, coordinating
 *     with the model in getting values and the view in displaying information,
 *     messages, and statuses.
 * </p>
 */
public class Controller implements ActionListener, DocumentListener {

    private final MyFarmGUI myFarmGUI;
    private final MyFarm myFarm;

    /**
     * Creates a controller object.
     * @param myFarmGUI  the view of the MVC architecture
     * @param myFarm     the model of the MVC architecture
     */
    public Controller(MyFarmGUI myFarmGUI, MyFarm myFarm) {
        this.myFarmGUI = myFarmGUI;
        this.myFarm = myFarm;
        this.initializeRocks();

        // update view and set buttons' action listener
        updateView();
        myFarmGUI.setActionListener(this);
    }

    /**
     * Initializes the initial set of rocks on the board with corresponding positions
     * from a file input.
     */
    public void initializeRocks() {
        // asking for file input for the initial position of rocks
        boolean fileExisting = false;
        ArrayList<String> rockPositions = new ArrayList<>();
        while(!fileExisting) {
            String filename = this.myFarmGUI.getFilename();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = reader.readLine()) != null)
                    rockPositions.add(line);
                fileExisting = true;
            } catch (IOException e) {
                myFarmGUI.displayCannotReadFile();
            }
        }

        this.myFarm.getBoard().setInitialRocks(rockPositions);
    }

    /**
     * Updates the view or the graphical representation of the game by updating the text
     * elements, and images present in the screen.
     */
    public void updateView() {
        // update board stats
        this.myFarm.getBoard().updateBoardStats(this.myFarm.getMAXROWS(),
                                                this.myFarm.getMAXCOLUMNS());

        // update graphics/view for each tile
        for(int row = 0; row < myFarm.getMAXROWS(); row++) {
            for(int col = 0; col < myFarm.getMAXCOLUMNS(); col++) {
                Tile tile = this.myFarm.getBoard().getTile(row, col);
                this.myFarmGUI.setTile(tile, row * this.myFarm.getMAXCOLUMNS() + col,
                                       this.myFarm.getPlants());
            }
        }

        // update the text placed on the game and farmer stats
        this.myFarmGUI.setStatsText(this.myFarm.getCurrentDay(), this.myFarm.getBoard(),
                                    this.myFarm.getFarmer());
    }

    /**
     * Specifies the command to be done when a specific button is clicked.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "..." -> this.viewButton(e);
            case "Plow" -> this.plowButton();
            case "Water" -> this.waterButton();
            case "Fertilizer" -> this.fertilizerButton();
            case "Pickaxe" -> this.pickaxeButton();
            case "Shovel" -> this.shovelButton();
            case "Plant" -> this.plantButton();
            case "Next Day" -> this.nextDayButton();
            case "Harvest" -> this.harvestButton();
            case "Register" -> this.registerButton();
        }
        this.updateView();

        int gameChecker = myFarm.checkIfGameOver();
        if(gameChecker == 1 || gameChecker == 2) {
            // end the game
            this.myFarmGUI.displayGameOver(gameChecker);
            this.myFarmGUI.setVisible(false);


            int gameChoice = this.myFarmGUI.getPlayAgainChoice();
            if (gameChoice == 1 || gameChoice == -1)
                System.exit(0);
            else
                this.newGame();
        }
    }

    /**
     * Sets the action to be performed when a view/info button is clicked.
     * @param e the event to be processed
     */
    public void viewButton(ActionEvent e) {
        // get row and column of the tile where the view button was selected
        int row = 0, col = 0;
        for(int index = 0; index < this.myFarmGUI.getViewButtons().size(); index++) {
            if(e.getSource().equals(this.myFarmGUI.getViewButtons().get(index))) {
                row = index / this.myFarm.getMAXCOLUMNS();
                col = index % this.myFarm.getMAXCOLUMNS();
            }
        }

        // place the tile information in the information box
        Tile tile = this.myFarm.getBoard().getTile(row, col);
        String tileInfo = tile.getTileInfo();
        this.myFarmGUI.setInfoText(tileInfo);
    }

    /**
     * Sets the action to be performed when the plow button is clicked.
     */
    public void plowButton() {
        // place plow information in the information box
        Plow plow = new Plow();
        this.myFarmGUI.setInfoText(plow.getToolInfo());

        // get the position of the tile to plow
        int row = this.myFarmGUI.getRow();
        int col = -1;
        if(row != -1)
            col = this.myFarmGUI.getColumn();

        if(row != -1 && col != -1) {
            Tile tile = this.myFarm.getBoard().getTile(row, col);
            if(this.myFarm.getFarmer().useTool(plow, tile, null)) {
                // display plow use success message
                this.myFarmGUI.setInfoText(tile.getTileInfo());
                boolean leveledUp = this.myFarm.getFarmer().updateExpAndLevel(plow.getExpGain());
                this.updateView();
                this.myFarmGUI.displayPlowMessage(1);

                // display leveled up message, if applicable
                if(leveledUp)
                    this.myFarmGUI.displayLeveledUp(this.myFarm.getFarmer().getLevel());
            } else {
                // display message if plowing was unsuccessful
                this.myFarmGUI.displayPlowMessage(2);
            }
        }
    }

    /**
     * Sets the action to be performed when the water button is clicked.
     */
    public void waterButton() {
        // place watering can information in the information box
        WateringCan wateringCan = new WateringCan();
        this.myFarmGUI.setInfoText(wateringCan.getToolInfo());

        // get position of the plant on a tile to water
        int row = this.myFarmGUI.getRow();
        int col = -1;
        if(row != -1)
            col = this.myFarmGUI.getColumn();

        if(row != -1 && col != -1) {
            Tile tile = this.myFarm.getBoard().getTile(row, col);
            if(this.myFarm.getFarmer().useTool(wateringCan, tile, tile.getCropPlanted())) {
                // display watering can use success message
                this.myFarmGUI.setInfoText(tile.getTileInfo());
                boolean leveledUp = this.myFarm.getFarmer().updateExpAndLevel(wateringCan.getExpGain());
                this.updateView();
                this.myFarmGUI.displayWaterMessage(1);

                // display leveled up message if applicable
                if(leveledUp)
                    this.myFarmGUI.displayLeveledUp(this.myFarm.getFarmer().getLevel());
            } else {
                // display message indicating unsuccessful watering process
                this.myFarmGUI.displayWaterMessage(2);
            }
        }
    }

    /**
     * Sets the action to be performed when the fertilizer button is clicked.
     */
    public void fertilizerButton() {
        // display the fertilizer information in the information box
        Fertilizer fertilizer = new Fertilizer();
        this.myFarmGUI.setInfoText(fertilizer.getToolInfo());

        // get the position of the plant/tile to fertilize
        int row = this.myFarmGUI.getRow();
        int col = -1;
        if(row != -1)
            col = this.myFarmGUI.getColumn();

        if(row != -1 && col != -1) {
            Tile tile = this.myFarm.getBoard().getTile(row, col);
            // check if farmer has enough objectcoins
            if(this.myFarm.getFarmer().getObjectCoins() >= fertilizer.getCost()) {
                // check if fertilizer use was successful
                if(this.myFarm.getFarmer().useTool(fertilizer, tile, tile.getCropPlanted())) {
                    // deduct amount from current objectcoins
                    double currCoins = this.myFarm.getFarmer().getObjectCoins();
                    this.myFarm.getFarmer().setObjectCoins(currCoins - fertilizer.getCost());

                    // display fertilizer use success message
                    this.myFarmGUI.setInfoText(tile.getTileInfo());
                    boolean leveledUp = this.myFarm.getFarmer().updateExpAndLevel(fertilizer.getExpGain());
                    this.updateView();
                    this.myFarmGUI.displayFertilizerMessage(1);

                    // display leveled up message if applicable
                    if(leveledUp)
                        this.myFarmGUI.displayLeveledUp(this.myFarm.getFarmer().getLevel());
                } else {
                    // display message indicating that the tile has no growing crop planted
                    this.myFarmGUI.displayFertilizerMessage(2);
                }
            } else {
                // display message indicating player doesn't have enough objectcoins
                this.myFarmGUI.displayFertilizerMessage(3);
            }
        }
    }

    /**
     * Sets the action to be performed when the pickaxe button is clicked.
     */
    public void pickaxeButton() {
        // display pickaxe information in the information box
        Pickaxe pickaxe = new Pickaxe();
        this.myFarmGUI.setInfoText(pickaxe.getToolInfo());

        // get tile position where the rock is located
        int row = this.myFarmGUI.getRow();
        int col = -1;
        if(row != -1)
            col = this.myFarmGUI.getColumn();

        if(row != -1 && col != -1) {
            Tile tile = this.myFarm.getBoard().getTile(row, col);
            // check if farmer has enough objectcoins
            if(this.myFarm.getFarmer().getObjectCoins() >= pickaxe.getCost()) {
                // check if the use of pickaxe was successful
                if(this.myFarm.getFarmer().useTool(pickaxe, tile, null)) {
                    // deduct tool cost
                    double currCoins = this.myFarm.getFarmer().getObjectCoins();
                    this.myFarm.getFarmer().setObjectCoins(currCoins - pickaxe.getCost());

                    // display pickaxe  use success message
                    this.myFarmGUI.setInfoText(tile.getTileInfo());
                    boolean leveledUp = this.myFarm.getFarmer().updateExpAndLevel(pickaxe.getExpGain());
                    this.updateView();
                    this.myFarmGUI.displayPickaxeMessage(1);

                    // display leveled up message if applicable
                    if(leveledUp)
                        this.myFarmGUI.displayLeveledUp(this.myFarm.getFarmer().getLevel());
                } else {
                    // display message about unsuccessful rock destroying
                    this.myFarmGUI.displayPickaxeMessage(2);
                }
            } else {
                // display message indicating insufficient objectcoins
                this.myFarmGUI.displayPickaxeMessage(3);
            }
        }
    }

    /**
     * Sets the action to be performed when the shovel button is clicked.
     */
    public void shovelButton() {
        // display shovel information in the information box
        Shovel shovel = new Shovel();
        this.myFarmGUI.setInfoText(shovel.getToolInfo());

        // get tile position where the shovel will be used
        int row = this.myFarmGUI.getRow();
        int col = -1;
        if(row != -1)
            col = this.myFarmGUI.getColumn();

        if(row != -1 && col != -1) {
            Tile tile = this.myFarm.getBoard().getTile(row, col);
            // check if there is sufficient objectcoins
            if(this.myFarm.getFarmer().getObjectCoins() >= shovel.getCost()) {
                // get message type to display after shovel use
                int messageType;
                if(tile.getHasWitheredCrop())
                    messageType = 1;
                else if(tile.getHasRock() || !tile.getIsOccupied() && !tile.getIsPlowed())
                    messageType = 2;
                else
                    messageType = 3;

                // check if shovel use was successful
                if(this.myFarm.getFarmer().useTool(shovel, tile, null)) {
                    // if used on eligible tiles, deduct amount
                    double currCoins = this.myFarm.getFarmer().getObjectCoins();
                    this.myFarm.getFarmer().setObjectCoins(currCoins - shovel.getCost());

                    // display shovel use success message
                    this.myFarmGUI.setInfoText(tile.getTileInfo());
                    boolean leveledUp = this.myFarm.getFarmer().updateExpAndLevel(shovel.getExpGain());
                    this.updateView();
                    this.myFarmGUI.displayShovelMessage(messageType);

                    // update exp and level
                    if(leveledUp)
                        this.myFarmGUI.displayLeveledUp(this.myFarm.getFarmer().getLevel());
                } else {
                    // display message indicating that the tool cannot be used on the tile
                    this.myFarmGUI.displayShovelMessage(4);
                }
            } else {
                // display insufficient objectcoins message
                this.myFarmGUI.displayShovelMessage(5);
            }
        }
    }

    /**
     * Sets the action to be performed when the plant button is clicked.
     */
    public void plantButton() {
        // get plant choice
        int plantChoice, confirm = 0;
        Plant seedToPlant = null;
        do {
            plantChoice = this.myFarmGUI.getPlantChoice(this.myFarm.getPlants());
            if(plantChoice != -1) {
                // display plant information
                switch(plantChoice) {
                    case 0 -> seedToPlant = new Turnip();
                    case 1 -> seedToPlant = new Carrot();
                    case 2 -> seedToPlant = new Potato();
                    case 3 -> seedToPlant = new Rose();
                    case 4 -> seedToPlant = new Turnips();
                    case 5 -> seedToPlant = new Sunflower();
                    case 6 -> seedToPlant = new Mango();
                    case 7 -> seedToPlant = new Apple();
                }
                assert seedToPlant != null;
                String plantInfo = seedToPlant.getPlantInfo();
                this.myFarmGUI.setInfoText(plantInfo);
                confirm = this.myFarmGUI.getConfirmation();
            } else {
                break;
            }
        } while(confirm == 1);

        if(plantChoice != -1 && confirm != -1) {
            // check if there is sufficient objectcoins amount to buy the seed
            if (this.myFarm.getFarmer().getObjectCoins() >=
                (seedToPlant.getSeedCost() + this.myFarm.getFarmer().getSeedCostReduction())) {
                // get tile position
                int row = this.myFarmGUI.getRow();
                int col = -1;
                if(row != -1)
                    col = this.myFarmGUI.getColumn();

                if (row != -1 && col != -1) {
                    // check if the tile can be planted with the provided seed choice
                    if (!seedToPlant.getCropType().equals("Fruit Tree") ||
                        this.myFarm.getBoard().canPlantFruit(row, col, this.myFarm.getMAXROWS(),
                                                             this.myFarm.getMAXCOLUMNS())) {
                        Tile tile = this.myFarm.getBoard().getTile(row, col);
                        // get message type to display after planting process
                        int messageType;
                        if(tile.getIsOccupied())
                            messageType = 1;
                        else if(!tile.getIsPlowed())
                            messageType = 2;
                        else
                            messageType = 3;

                        // plant seed and display planting message
                        this.myFarm.getFarmer().plantSeed(seedToPlant, tile);
                        this.myFarmGUI.setInfoText(tile.getTileInfo());
                        this.updateView();
                        this.myFarmGUI.displayPlantMessage(messageType);
                    } else {
                        // display message indicating that the seed cannot be planted on the tile
                        this.myFarmGUI.displayPlantMessage(4);
                    }
                }
            } else {
                // display message indicating that the user doesn't have enough objectcoins
                this.myFarmGUI.displayPlantMessage(5);
            }
        }
    }

    /**
     * Sets the action to be performed when the next day button is clicked.
     */
    public void nextDayButton() {
        // increment day
        this.myFarm.setCurrentDay(this.myFarm.getCurrentDay() + 1);

        // check each tile on the board if they have a crop planted
        for(int row = 0; row < this.myFarm.getMAXROWS(); row++) {
            for(int col = 0; col < this.myFarm.getMAXCOLUMNS(); col++) {
                Tile tile = this.myFarm.getBoard().getTile(row, col);
                if(tile.getHasCrop()) {
                    tile.getCropPlanted().addCurrentGrowthDays();
                    if(tile.getCropPlanted().getGrowthDaysLeft() > 0)
                        tile.getCropPlanted().reduceGrowthDaysLeft();

                    // update tile and plant status, if applicable
                    tile.updateTileStatus();
                    this.myFarmGUI.setInfoText(tile.getTileInfo());
                    this.updateView();
                }
            }
        }
    }

    /**
     * Sets the action to be performed when the harvest button is clicked.
     */
    public void harvestButton() {
        // check if there is a ready-to-harvest crop in the farm lot
        if(this.myFarm.getBoard().getFullyGrownCropCount() >= 1) {
            // get tile choice
            int row = this.myFarmGUI.getRow();
            int col = -1;
            if(row != -1)
                col = this.myFarmGUI.getColumn();

            if(row != -1 && col != -1) {
                Tile tile = this.myFarm.getBoard().getTile(row, col);
                // check if the chosen tile has a fully grown crop
                if (tile.getHasCrop() && tile.getHasFullyGrownCrop() &&
                    !tile.getHasWitheredCrop()) {
                    Plant plant = tile.getCropPlanted();

                    // harvest the plant, and display harvest details
                    this.myFarm.getFarmer().harvestPlant(tile, plant);
                    this.myFarmGUI.setInfoText(this.myFarm.getFarmer().getHarvestDetails(plant));
                    boolean leveledUp = this.myFarm.getFarmer().updateExpAndLevel(plant.getExpYield());
                    this.updateView();
                    this.myFarmGUI.displayHarvestMessage(1);

                    // update exp, level, and the view
                    if(leveledUp)
                        this.myFarmGUI.displayLeveledUp(this.myFarm.getFarmer().getLevel());
                } else {
                    // display message indicating that the tile has no fully-grown crop
                    this.myFarmGUI.displayHarvestMessage(2);
                }
            }
        } else {
            // display message indicating that there is no fully-grown crop in the farm lot
            this.myFarmGUI.displayHarvestMessage(3);
        }
    }

    /**
     * Sets the action to be performed when the register button is clicked.
     */
    public void registerButton() {
        // get farmer type
        FarmerType farmerType = null;
        String type;
        int confirm = -1;
        do {
            type = this.myFarmGUI.getFarmerType();
            if(type != null) {
                // display farmer type information
                if(type.equals(FarmerType.FARMER.getTypeName()))
                    farmerType = FarmerType.FARMER;
                else if(type.equals(FarmerType.REGISTEREDFARMER.getTypeName()))
                    farmerType = FarmerType.REGISTEREDFARMER;
                else if(type.equals(FarmerType.DISTINGUISHEDFARMER.getTypeName()))
                    farmerType = FarmerType.DISTINGUISHEDFARMER;
                else
                    farmerType = FarmerType.LEGENDARYFARMER;

                this.myFarmGUI.displayFarmerTypeInfo(farmerType);
                confirm = this.myFarmGUI.getConfirmation();
            } else {
                break;
            }
        } while(confirm == 1);

        if(farmerType != null && confirm != -1 && confirm != 1) {
            // check if farmer can register
            int messageType;
            if (farmerType == this.myFarm.getFarmer().getType())
                messageType = 1;
            else if (farmerType.getRegistrationFee() <
                    this.myFarm.getFarmer().getType().getRegistrationFee())
                messageType = 2;
            else if (this.myFarm.getFarmer().getObjectCoins() < farmerType.getRegistrationFee())
                messageType = 3;
            else if (this.myFarm.getFarmer().getLevel() < farmerType.getLevelRequirement())
                // if player doesn't meet with the level requirement
                messageType = 4;
            else {
                // if all conditions are met and farmer is eligible
                this.myFarm.getFarmer().upgradeFarmer(farmerType);
                messageType = 5;
                this.updateView();
            }

            // display message
            this.myFarmGUI.displayRegistrationMessage(messageType);
        }
    }

    /**
     * Creates a new game.
     */
    public void newGame() {
        Controller controller = new Controller(new MyFarmGUI(), new MyFarm());
    }

    /**
     * Inserts an update. Function is unused; however, overriding and implementing
     * it is necessary as the class implements required interfaces.
     *
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {}

    /**
     * Removes an update. Function is unused; however, overriding and implementing
     * it is necessary as the class implements required interfaces.
     *
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {}

    /**
     * Changes an update. Function is unused; however, overriding and implementing
     * it is necessary as the class implements required interfaces.
     *
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {}
}
