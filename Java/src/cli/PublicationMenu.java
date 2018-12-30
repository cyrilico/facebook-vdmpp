package cli;

import java.util.ArrayList;

class PublicationMenu extends AbstractMenu {
    private MainMenu mainMenu;

    PublicationMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
    }

    @Override
    void getOptions() {
        printDivision("Posts Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("See Posts");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "See Posts":
                break;
            case BACK_INPUT:
                return;
            case MENU_INPUT:
                mainMenu.getOptions();
                break;
        }
    }
}
