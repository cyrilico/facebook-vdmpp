package cli;

import java.util.ArrayList;

class FriendsMenu extends AbstractMenu {

    @Override
    void getOptions(boolean hasParent) {
        printDivision("Stats");

        ArrayList<String> options = new ArrayList<>();
        options.add("Most famous user");
        options.add("Most famous group");
        options.add("Average User Distance");

        String input = printOptions(options, hasParent);
        switch (input) {
            case "Most famous user":
//                statsMenu(hasParent);
                break;
            case "Most famous group":
//                statsMenu(hasParent);
                break;
            case "Average User Distance":
//                statsMenu(hasParent);
                break;
            case BACK_INPUT:
                return;
            case MENU_INPUT:
//                mainMenu();
                break;
        }
    }
}