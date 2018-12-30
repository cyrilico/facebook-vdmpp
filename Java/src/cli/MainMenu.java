package cli;

import java.util.ArrayList;

public class MainMenu extends AbstractMenu {

    @Override
    void getOptions() {
        printDivision("Main Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Users");
        options.add("Groups");
        options.add("Search users by name");
        options.add("Stats");

        String input = printOptions(options, false);
        switch (input) {
            case "Users":
//                usersMenu(true);
                getOptions();
                break;
            case "Groups":
//                groupsMenu(true);
                getOptions();
                break;
            case "Search users by name":
//                searchUsersByName(true);
                getOptions();
                break;
            case "Stats":
//                statsMenu(true);
                getOptions();
                break;
//            case BACK_INPUT:
//                break;
//            case MENU_INPUT:
//                break;
        }
    }

}
