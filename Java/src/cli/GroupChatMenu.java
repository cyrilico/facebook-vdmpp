package cli;

import java.util.ArrayList;

class GroupChatMenu extends AbstractMenu {
    private MainMenu mainMenu;

    GroupChatMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
    }

    @Override
    void getOptions() {
        printDivision("Group Chat Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "":
                break;
            case BACK_INPUT:
                return;
            case MENU_INPUT:
                mainMenu.getOptions();
                break;
        }
    }
}
