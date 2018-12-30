package cli;

import java.util.ArrayList;

class MainMenu extends AbstractMenu {

    @Override
    void getOptions(boolean hasParent) {
        printDivision("Main Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Users");
        options.add("Groups");
        options.add("Search users by name");
        options.add("Stats");

        String input = printOptions(options, hasParent);
        switch (input) {
            case "Users":
//                usersMenu(true);
                FriendsMenu friendsMenu = new FriendsMenu();
                friendsMenu.getOptions(true);
                getOptions(hasParent);
                break;
            case "Groups":
//                groupsMenu(true);
                getOptions(hasParent);
                break;
            case "Search users by name":
//                searchUsersByName(true);
                getOptions(hasParent);
                break;
            case "Stats":
//                statsMenu(true);
                getOptions(hasParent);
                break;
//            case BACK_INPUT:
//                break;
//            case MENU_INPUT:
//                break;
        }
    }

}
