package cli;

import java.util.ArrayList;
import java.util.Scanner;

abstract class AbstractMenu {
    static final String BACK_INPUT = "b";
    static final String MENU_INPUT = "m";

    Scanner scanner = new Scanner(System.in);
    private boolean hasParent;

    AbstractMenu(boolean hasParent) {
        this.hasParent = hasParent;
    }

    abstract void getOptions();

    void printDivision(String menu) {
        System.out.println();
        System.out.println("-------------------------" + menu + "------------------------");

    }

    String printAndSelectOptions(ArrayList<String> options) {
        printOptions(options);

        return selectOptions(options);
    }

    private void printOptions(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "- " + options.get(i));
        }

        if (hasParent) {
            System.out.println("'b' to go back or 'm' to go to the main menu.");
        }

        System.out.print("Select one of the options: ");
    }

    private String selectOptions(ArrayList<String> options) {
        boolean validInput = false;
        String input = "";

        while (!validInput) {
            input = scanner.nextLine();
            validInput = isValidInput(options, input);
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

    private boolean isValidInput(ArrayList<String> options, String input) {
        if (hasParent) {
            if (input.equals(BACK_INPUT) || input.equals(MENU_INPUT)) {
                return true;
            }
        }

        try {
            int i = Integer.parseInt(input);
            return (i > 0 && i <= options.size());

        } catch (Exception e) {
            return false;
        }
    }

    private void printInvalidInputMessage() {
        System.out.println("Invalid input!!!");
    }

    private void printSucessMessage() {
        System.out.println("Operation succeed!!!");
    }
}
