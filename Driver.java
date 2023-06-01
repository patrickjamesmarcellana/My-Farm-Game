import Model.MyFarm;

/**
 * This class represents the Driver of the program, which is responsible for starting and
 * ending the program. As the driver of the program, it contains the main method.
 */
public class Driver {

    /**
     * Starts and ends the program.
     * @param args arguments passed into the main function
     */
    public static void main(String[] args) {
        MyFarmGUI myFarmGUI = new MyFarmGUI();
        MyFarm myFarm = new MyFarm();
        Controller controller = new Controller(myFarmGUI, myFarm);
    }
}
