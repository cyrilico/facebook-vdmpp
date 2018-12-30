package cli;

public class CommandLineInterface {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(false);

        System.out.println("Welcome to Facebook VDM++!");

        mainMenu.getOptions();
    }
}
