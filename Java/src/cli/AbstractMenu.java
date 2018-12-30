package cli;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractMenu {
    public static final String BACK_INPUT = "b";
    public static final String MENU_INPUT = "m";

    Scanner scanner = new Scanner(System.in);

    abstract void getOptions();

    public void printDivision(String menu) {
        System.out.println();
        System.out.println("-------------------------" + menu + "------------------------");

    }

    public String printOptions(ArrayList<String> options, boolean hasParent) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "- " + options.get(i));

        }

        if (hasParent) {
            System.out.println("'b' to go back or 'm' to go to the main menu.");
        }
        System.out.print("Select one of the options: ");

        boolean validInput = false;
        String input = "";
        while (!validInput) {
            input = scanner.nextLine();
            validInput = isValidInput(options, hasParent, input);
            if (!validInput) {
                printInvalidInputMessage();
            }
        }


        if (input.equals(BACK_INPUT) || input.equals(MENU_INPUT)) {
            return input;
        } else {
            return options.get(Integer.parseInt(input) - 1);
        }
    }

    public boolean isValidInput(ArrayList<String> options, boolean hasParent, String input) {
        if (hasParent) {
            if (input.equals(BACK_INPUT) || input.equals(MENU_INPUT)) {
                return true;
            }
        }
        try {
            int i = Integer.parseInt(input);

            if (i > 0 && i <= options.size()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void printInvalidInputMessage() {
        System.out.println("Invalid input!!!");

    }

    public void printInvalidUsersId() {
        System.out.println("Invalid user's id");
    }
}
