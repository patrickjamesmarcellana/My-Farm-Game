import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import Model.*;
import Model.Plants.*;

/**
 * This class represents the View in the MVC Architecture. It handles everything in
 * displaying outputs and getting inputs from the player. It also layouts, designs, and
 * updates the graphical features of the game.
 */
public class MyFarmGUI extends JFrame {

    // variables that needs to be global
    private JTextArea infoText, statsText;
    private ArrayList<JButton> gameButtons;
    private final ArrayList<JPanel> tiles;
    private final ArrayList<JButton> viewButtons;

    /**
     * Creates a GUI object for the MyFarm game.
     */
    public MyFarmGUI() {
        // create the window
        super("My Farm");

        // initialize game elements
        tiles = new ArrayList<>();
        viewButtons = new ArrayList<>();

        // design the frame
        this.setLayout(new BorderLayout());
        this.setSize(1024, 768);
        this.setTitle(this.getPlayerName() + "'s Farm");
        this.setIconImage(new ImageIcon(this.getImage("sprites\\farmemoji.png")).getImage());
        this.setLocationRelativeTo(null); // makes the window appear at the center
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.initMainFrame(); // initialize the main frame elements
        setVisible(true);
    }

    /**
     * Layouts the main elements or components of the application.
     */
    private void initMainFrame() {
        /* -------- NORTH PANEL -------- */
        // contains the game title
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(50, 60));
        northPanel.setBackground(Color.decode("#cea86f"));
        this.initNorthPanel(northPanel);
        this.add(northPanel, BorderLayout.NORTH);

        /* -------- CENTER PANEL -------- */
        // contains the farm lot, the buttons, the information box, and the stats
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        this.initCenterPanel(centerPanel);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Initializes and designs the elements of the north panel of the application.
     * The north panel contains only the game title.
     * @param panel the panel of the application being modified (north panel in
     *              this case)
     */
    private void initNorthPanel(JPanel panel) {
        // title of the game
        JLabel myFarmLabel = new JLabel("MY FARM");
        myFarmLabel.setForeground(Color.decode("#371d10"));
        myFarmLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        myFarmLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
        panel.add(myFarmLabel, BorderLayout.CENTER);
    }

    /**
     * Contains and designs the elements of the center panel of the application.
     * @param panel the panel of the application being modified (center panel in
     *              this case)
     */
    private void initCenterPanel(JPanel panel) {
        /* -------- WEST PANEL -------- */
        // contains the farm lot, the information box, and the game/farmer stats
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        this.initWestOfCenterPanel(centerPanel);
        panel.add(centerPanel, BorderLayout.CENTER);

        /* -------- EAST PANEL -------- */
        // contains the tool and game buttons
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(100, 100));
        eastPanel.setLayout(new GridLayout(9, 1));
        eastPanel.setBackground(Color.decode("#cea86f"));
        eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
        this.initEastOfCenterPanel(eastPanel);
        panel.add(eastPanel, BorderLayout.EAST);
    }

    /**
     * Contains and designs the elements of the west panel of the main frame's center panel.
     * @param panel the panel of the application being modified (west panel of the center
     *              panel in this case)
     */
    private void initWestOfCenterPanel(JPanel panel) {
        /* -------- CENTER PANEL -------- */
        // contains the farm lot
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        centerPanel.setLayout(new GridLayout(5, 10, 5, 5));
        centerPanel.setBackground(Color.decode("#371d10"));
        this.initFarmLot(centerPanel);
        panel.add(centerPanel, BorderLayout.CENTER);

        /* -------- SOUTH PANEL -------- */
        // contains the information box and the farmer/game stats
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLACK);
        southPanel.setPreferredSize(new Dimension(100, 200));
        southPanel.setLayout(new GridLayout(1, 2, 2, 2));
        this.initInfoBox(southPanel);
        this.initStatsArea(southPanel);
        panel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Contains and designs the elements of the east panel of the main frame's center panel.
     * @param panel the panel of the application being modified (east panel of the center
     *              panel in this case)
     */
    private void initEastOfCenterPanel(JPanel panel) {
        // initialize all game buttons
        JButton btnPlow = new JButton("Plow");
        JButton btnWater = new JButton("Water");
        JButton btnFertilize = new JButton("Fertilizer");
        JButton btnPickaxe = new JButton("Pickaxe");
        JButton btnShovel = new JButton("Shovel");
        JButton btnPlant = new JButton("Plant");
        JButton btnAdvanceDay = new JButton("Next Day");
        JButton btnHarvest = new JButton("Harvest");
        JButton btnRegister = new JButton("Register");

        // add the game buttons into an arraylist of game buttons
        this.gameButtons = new ArrayList<>();
        this.gameButtons.add(btnPlow);
        this.gameButtons.add(btnWater);
        this.gameButtons.add(btnFertilize);
        this.gameButtons.add(btnPickaxe);
        this.gameButtons.add(btnShovel);
        this.gameButtons.add(btnPlant);
        this.gameButtons.add(btnAdvanceDay);
        this.gameButtons.add(btnHarvest);
        this.gameButtons.add(btnRegister);

        // add each button to panels and add each button panel to the east panel
        for (JButton gameButton : gameButtons) {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.setBackground(Color.decode("#cea86f"));
            gameButton.setLayout(new GridBagLayout());
            gameButton.setPreferredSize(new Dimension(86, 60));
            gameButton.setFocusable(false);
            gameButton.setBackground(Color.decode("#371d10"));
            gameButton.setForeground(Color.decode("#371d10"));

            buttonPanel.add(gameButton);
            panel.add(buttonPanel);
        }

        // set images of buttons
        JLabel plowPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\plow.png"))));
        JLabel waterPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\water.png"))));
        JLabel fertilizerPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\fertilizer.png"))));
        JLabel pickaxePic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\pickaxe.png"))));
        JLabel shovelPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\shovel.png"))));
        JLabel plantPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\plant.png"))));
        JLabel advancePic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\advance.png"))));
        JLabel harvestPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\harvest.png"))));
        JLabel registerPic = new JLabel(new ImageIcon(Objects.requireNonNull(
                this.getImage("sprites\\register.png"))));

        // add images to buttons
        btnPlow.add(plowPic);
        btnWater.add(waterPic);
        btnFertilize.add(fertilizerPic);
        btnPickaxe.add(pickaxePic);
        btnShovel.add(shovelPic);
        btnPlant.add(plantPic);
        btnAdvanceDay.add(advancePic);
        btnHarvest.add(harvestPic);
        btnRegister.add(registerPic);
    }

    /**
     * Creates and designs the tiles of the farm lot at the center panel.
     * @param panel the panel where the farm lot will be located
     */
    private void initFarmLot(JPanel panel) {
        for(int index = 0; index < 50; index++) {
            // tile panels
            JPanel tile = new JPanel();
            tile.setLayout(new BorderLayout());
            tile.setBackground(Color.decode("#845b45"));

            // view information buttons (...)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.decode("#845b45"));
            JButton btnView = new JButton("...");
            btnView.setFocusable(false);
            btnView.setPreferredSize(new Dimension(15, 15));
            btnView.setForeground(Color.WHITE);
            btnView.setBackground(Color.decode("#371d10"));
            btnView.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 9));
            buttonPanel.add(btnView);
            tile.add(buttonPanel, BorderLayout.EAST);
            this.viewButtons.add(btnView);

            // add tile image
            String filePath = "sprites\\unplowed.png";
            this.addImageToTile(filePath, tile);
            this.tiles.add(tile);
        }

        // add each tile to the panel
        for (JPanel tile : tiles)
            panel.add(tile);
    }

    /**
     * Initializes the information box where information about either the tile, plant,
     * tool, or farmer type will be displayed.
     * @param panel panel where the information box is located
     */
    private void initInfoBox(JPanel panel) {
        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
        informationPanel.setBackground(Color.decode("#cea86f"));

        // label of the information box
        JLabel infoLabel = new JLabel("Information Box");
        infoLabel.setBorder(new EmptyBorder(10, 15, 10, 10));
        infoLabel.setForeground(Color.decode("#371d10"));
        infoLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        informationPanel.add(infoLabel, BorderLayout.NORTH);

        // text area where information will be displayed
        this.infoText = new JTextArea("");
        this.infoText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        this.infoText.setBorder(new EmptyBorder(0, 15, 10, 10));
        this.infoText.setBackground(Color.decode("#cea86f"));
        this.infoText.setForeground(Color.decode("#371d10"));
        this.infoText.setEditable(false);
        informationPanel.add(this.infoText, BorderLayout.CENTER);
        panel.add(informationPanel);
    }

    /**
     * Initializes the area where the game and farmer stats will be displayed
     * @param panel panel where the information box is located
     */
    private void initStatsArea(JPanel panel) {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BorderLayout());
        statsPanel.setBackground(Color.decode("#cea86f"));

        // label of the game and farmer stats
        JLabel statsLabel = new JLabel("Game and Farmer Stats");
        statsLabel.setBorder(new EmptyBorder(10, 15, 10, 10));
        statsLabel.setForeground(Color.decode("#371d10"));
        statsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        statsPanel.add(statsLabel, BorderLayout.NORTH);

        // text area where stats will be displayed
        this.statsText = new JTextArea("");
        this.statsText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        this.statsText.setBorder(new EmptyBorder(0, 15, 10, 10));
        this.statsText.setBackground(Color.decode("#cea86f"));
        this.statsText.setForeground(Color.decode("#371d10"));
        this.statsText.setEditable(false);
        statsPanel.add(this.statsText, BorderLayout.CENTER);
        panel.add(statsPanel);
    }

    /**
     * Sets the action listener for each button.
     * @param actionListener the controller of the program
     */
    public void setActionListener(ActionListener actionListener) {
        // set action listener for game buttons
        for(JButton button : this.gameButtons)
            button.addActionListener(actionListener);

        // set action listener for view buttons
        for(JButton btnView : this.viewButtons)
            btnView.addActionListener(actionListener);
    }

    /**
     * Sets and updates the graphical interface of the tile according to its status
     * (i.e., if it contains a rock, a crop, a withered crop, or if it is plowed).
     * @param tile     the tile being modified
     * @param index    the index of the tile in the board
     * @param plants   the list of plants available in My Farm
     */
    public void setTile(Tile tile, int index, ArrayList<Plant> plants) {
        String filePath;

        if(tile.getHasWitheredCrop()) // tile has withered crop
            filePath = "sprites\\withered.png";
        else if(tile.getHasFullyGrownCrop()) // tile has fully-grown crop
            if (tile.getCropPlanted().getName().equals(plants.get(0).getName()))
                filePath = "sprites\\turnip.png";
            else if (tile.getCropPlanted().getName().equals(plants.get(1).getName()))
                filePath = "sprites\\carrot.png";
            else if (tile.getCropPlanted().getName().equals(plants.get(2).getName()))
                filePath = "sprites\\potato.png";
            else if (tile.getCropPlanted().getName().equals(plants.get(3).getName()))
                filePath = "sprites\\rose.png";
            else if (tile.getCropPlanted().getName().equals(plants.get(4).getName()))
                filePath = "sprites\\turnips.png";
            else if (tile.getCropPlanted().getName().equals(plants.get(5).getName()))
                filePath = "sprites\\sunflower.png";
            else if (tile.getCropPlanted().getName().equals(plants.get(6).getName()))
                filePath = "sprites\\mango.png";
            else
                filePath = "sprites\\apple.png";
        else if(tile.getHasRock()) // tile has rock
            filePath = "sprites\\rock.png";
        else if(tile.getHasCrop()) // tile has growing crop
            filePath = "sprites\\growing.png";
        else if(tile.getIsPlowed()) // tile is plowed
            filePath = "sprites\\plowed.png";
        else // tile is unplowed
            filePath = "sprites\\unplowed.png";

        // add image from specified filepath to the tile panel
        this.addImageToTile(filePath, this.tiles.get(index));
    }

    /**
     * Adds image to a file by scanning the file given the file path.
     * @param filePath  the location of the image/sprite
     * @param panel     the panel where the image will be added
     */
    public void addImageToTile(String filePath, JPanel panel) {
        try {
            // read the file image given the file path
            BufferedImage tileImage = ImageIO.read(new File(filePath));
            JPanel centerPanel = new JPanel();
            centerPanel.setBorder(new EmptyBorder(15, 25, 0, 0));
            centerPanel.setLayout(new FlowLayout());
            centerPanel.setBackground(Color.decode("#845b45"));
            JLabel newPicLabel = new JLabel(new ImageIcon(tileImage));
            centerPanel.add(newPicLabel);
            panel.add(centerPanel, BorderLayout.CENTER);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                                            "Image file cannot be read.",
                                            "Error Reading Image File ",
                                            JOptionPane.ERROR_MESSAGE);
        }
    }

    // display methods
    /**
     * Displays a message whether the plow use was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayPlowMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    "Tile successfully plowed.",
                    "Plow Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "This tile cannot be plowed.",
                    "Plow Use Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the watering can use was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayWaterMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    "Plant successfully watered.",
                    "Watering Can Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "There is no crop to water on the tile.",
                    "Watering Can Use Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the fertilizer use was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayFertilizerMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    "Plant successfully fertilized.",
                    "Fertilizer Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "There is no crop to fertilize on the tile.",
                    "Fertilizer Use Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 3 -> JOptionPane.showMessageDialog(null,
                    "You do not have enough Objectcoins to use a fertilizer.",
                    "Insufficient Objectcoins",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the pickaxe use was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayPickaxeMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    "Rock successfully destroyed.",
                    "Pickaxe Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "There is no rock to destroy on the tile.",
                    "Pickaxe Use Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 3 -> JOptionPane.showMessageDialog(null,
                    "You do not have enough Objectcoins to use a pickaxe.",
                    "Insufficient Objectcoins",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the shovel use was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayShovelMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    "Withered crop successfully removed.",
                    "Shovel Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "It seems the shovel has no effect on this type of tile...",
                    "Shovel Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 3 -> JOptionPane.showMessageDialog(null,
                    "Shovel successfully used; however, you removed an active crop...",
                    "Shovel Use Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 4 -> JOptionPane.showMessageDialog(null,
                    "It seems the shovel cannot be used on this tile...",
                    "Shovel Use Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 5 -> JOptionPane.showMessageDialog(null,
                    "You do not have enough Objectcoins to use a shovel.",
                    "Insufficient Objectcoins",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the planting process was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayPlantMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    "You can't plant a seed on this tile.",
                    "Seed Planting Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "This tile seems to be unplowed.",
                    "Seed Planting Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 3 -> JOptionPane.showMessageDialog(null,
                    "Seed successfully planted!",
                    "Seed Planting Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 4 -> JOptionPane.showMessageDialog(null,
                    """
                            Fruit trees can only be planted on a tile that is not at the edge
                             of the lot and where its adjacent tiles are not occupied.""",
                    "Seed Planting Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 5 -> JOptionPane.showMessageDialog(null,
                    "You can't afford this kind of seed!",
                    "Seed Planting Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the harvest process was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayHarvestMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                    """
                            Crop successfully harvested.
                            Check the information box for the harvest details.
                            """,
                    "Crop Harvesting Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                    "This tile has no ready-to-harvest crop!",
                    "Crop Harvesting Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            case 3 -> JOptionPane.showMessageDialog(null,
                    "There is nothing to harvest in the farm lot at the moment",
                    "Crop Harvesting Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message whether the registration process was successful or not.
     * @param messageType the type of message that will be displayed
     */
    public void displayRegistrationMessage(int messageType) {
        switch(messageType) {
            case 1 -> JOptionPane.showMessageDialog(null,
                        "This is already your current farmer type!",
                        "Registration Unsuccessful",
                        JOptionPane.ERROR_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                        "Your current farmer type is already higher than this type!",
                        "Registration Unsuccessful",
                        JOptionPane.ERROR_MESSAGE);
            case 3 -> JOptionPane.showMessageDialog(null,
                        "You don't have enough Objectcoins for this type.",
                        "Registration Unsuccessful",
                        JOptionPane.ERROR_MESSAGE);
            case 4 -> JOptionPane.showMessageDialog(null,
                        "Your farmer level doesn't fit the level requirement of this type.",
                        "Registration Unsuccessful",
                        JOptionPane.ERROR_MESSAGE);
            case 5 ->    JOptionPane.showMessageDialog(null,
                        "Farmer type successfully registered!",
                        "Registration Successful",
                        JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Displays the information regarding the provided farmer type.
     * @param farmerType the farmer type that the player selected
     */
    public void displayFarmerTypeInfo(FarmerType farmerType) {
        String typeInfo =
            "FARMER TYPE INFORMATION\n" + "-".repeat(106) +
            "\nFarmer Type: " + farmerType.getTypeName() +
            "\nLevel Requirement: " + farmerType.getLevelRequirement() +
            "\nBonus Earning per Crop: " + farmerType.getBonusEarnings() + " Objectcoins" +
            "\nSeed Cost Reduction: " + farmerType.getSeedCostReduction() + " Objectcoins" +
            "\nWater Bonus Limit Increase: " + farmerType.getAddWaterBonusLimit() +
            "\nFertilizer Bonus Limit Increase: " + farmerType.getAddFertBonusLimit() +
            "\nRegistration Fee: " + farmerType.getRegistrationFee() + " Objectcoins";

        this.setInfoText(typeInfo);
    }

    /**
     * Displays a message if the farmer leveled up.
     * @param level the farmer's current level
     */
    public void displayLeveledUp(int level) {
        JOptionPane.showMessageDialog(null,
                                        "You leveled up! You're now level " + level,
                                        "Leveled Up",
                                        JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a message if the farmer leveled up.
     * @param gameChecker the integer representing the game's losing conditions
     */
    public void displayGameOver(int gameChecker) {
        switch(gameChecker) {
            case 1 -> JOptionPane.showMessageDialog(null,
                        """
                                Either you no longer have active crops or
                                you no longer have enough money to buy new seeds.""",
                        "GAME OVER!",
                        JOptionPane.ERROR_MESSAGE);
            case 2 -> JOptionPane.showMessageDialog(null,
                        "Your farm lot has been fully-occupied by withered crops!",
                        "GAME OVER!",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a message if a file cannot be read.
     */
    public void displayCannotReadFile() {
        JOptionPane.showMessageDialog(null,
                                      "Either file does not exist or an error occurred " +
                                              "in reading the file.",
                                      "File Cannot be Read",
                                      JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Gets the image given the file path.
     */
    public BufferedImage getImage(String filePath) {
        try {
            // read the file image given the file path
            return ImageIO.read(new File(filePath));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Image file cannot be read.",
                    "Error Reading Image File ",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Gets the player's name via GUI input.
     * @return the player's name.
     */
    public String getPlayerName() {
        String message = "Enter your name: ";
        String title = "WELCOME TO MY FARM GAME!";
        String playerName = JOptionPane.showInputDialog(null, message,
                                                      title, JOptionPane.QUESTION_MESSAGE);
        if(playerName == null)
            playerName = "";
        return playerName;
    }

    /**
     * Gets the filename from the user.
     * @return a string with the filename and file extension.
     */
    public String getFilename() {
        String filename = null;
        while(filename == null)
            filename = JOptionPane.showInputDialog(null,
                        "Input filename that contains rock positions " +
                                "(include file extension):",
                        "Rock Positions Input",
                        JOptionPane.QUESTION_MESSAGE);

        return filename;
    }

    /**
     * Gets the choice of the player to play again.
     * @return 0 if the player wants to play again, 1 if otherwise.
     */
    public int getPlayAgainChoice() {
        return JOptionPane.showOptionDialog(null,
                                            "Do you want to play again?",
                                            "Play Again?",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            null,
                                            null);
    }

    /**
     * Gets the plant choice input from the user.
     * @param plants the list of plants available in My Farm
     * @return  the index of the plant in the list of plants.
     */
    public int getPlantChoice(ArrayList<Plant> plants) {
        // get plant names and store them as the choices of the input question
        Object[] choices = new Object[plants.size()];
        for(int index = 0; index < plants.size(); index++)
            choices[index] = plants.get(index).getName().getSeedName();

        // get plant choice
        String plantChoice = (String)JOptionPane.showInputDialog(null,
                                                        "Choose a seed to plant:",
                                                        "Seed to Plant",
                                                        JOptionPane.QUESTION_MESSAGE,
                                                        null, choices, null);

        // get plant index depending on the plant choice
        int plantIndex = -1;
        if(plantChoice != null) {
            for (int index = 0; index < plants.size(); index++)
                if (choices[index].equals(plantChoice))
                    plantIndex = index;
        }

        return plantIndex;
    }

    /**
     * Gets the row number input from the user.
     * @return the row number if the user selects properly; otherwise, return -1
     */
    public int getRow() {
        // set choices
        Object[] choices = {"Row 1", "Row 2", "Row 3", "Row 4", "Row 5"};

        // get row choice
        String value = (String)JOptionPane.showInputDialog(null,
                        "Choose row of the tile: ", "Tile Choice",
                        JOptionPane.QUESTION_MESSAGE, null, choices, null);

        // return the choice number if the user selects properly; otherwise, return -1
        if(value != null)
            return Integer.parseInt(value.substring(4)) - 1;
        else
            return -1;
    }

    /**
     * Gets the column number input from the user.
     * @return the column number if the user selects properly; otherwise, return -1
     */
    public int getColumn() {
        // set choices
        Object[] choices = {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5",
                            "Column 6", "Column 7", "Column 8", "Column 9", "Column 10"};

        // get column choice
        String value = (String)JOptionPane.showInputDialog(null,
                        "Choose column of the tile: ", "Tile Choice",
                        JOptionPane.QUESTION_MESSAGE, null, choices, null);

        // return column number if user selects properly; otherwise, return -1
        if(value != null)
            return Integer.parseInt(value.substring(7)) - 1;
        else
            return -1;
    }

    /**
     * Gets the farmer type that the user wants to register to.
     * @return  the string that represents the farmer type chosen by the player.
     *          Otherwise, return null.
     */
    public String getFarmerType() {
        // set choices
        Object[] choices = {"Farmer", "Registered Farmer", "Distinguished Farmer",
                            "Legendary Farmer"};

        // return the string that represents the choice
        return (String)JOptionPane.showInputDialog(null,
                "Select a farmer type:", "Upgrade Farmer Type",
                JOptionPane.QUESTION_MESSAGE, null, choices, null);
    }

    /**
     * Gets all the view buttons of the game.
     *
     * @return  the list of view buttons.
     */
    public ArrayList<JButton> getViewButtons() {
        return viewButtons;
    }

    public int getConfirmation() {
        return JOptionPane.showOptionDialog(null,
                "Information about the selected option is displayed on the" +
                        "\ninformation box. Are you sure you're selecting this?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);
    }

    /**
     * Sets the text placed at the information box.
     * @param text  the text to be displayed
     */
    public void setInfoText(String text) {
        this.infoText.setText(text);
    }

    /**
     * Sets the text placed at the game and board stats box.
     * @param currentDay  the current day of the game
     * @param board       the game's board/farm lot
     * @param farmer      the game's farmer/player
     */
    public void setStatsText(int currentDay, Board board, Farmer farmer) {
        this.statsText.setText(farmer.getFarmerInfo() + "\nCurrent Day: " +
                currentDay + board.getBoardInfo());
    }
}
