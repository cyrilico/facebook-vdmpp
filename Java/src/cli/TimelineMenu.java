package cli;

import java.util.ArrayList;

class TimelineMenu extends AbstractMenu {
    private MainMenu mainMenu;

    TimelineMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
    }

    @Override
    void getOptions() {
        printDivision("Timeline Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("See My Timeline");
        options.add("See Timeline from other users");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "See My Timeline":
                seeMyTimeline();
                break;
            case "See Timeline from other users":
                seeTimelineByUser();
                break;
            case BACK_INPUT:
            case MENU_INPUT:
                return;
        }

        getOptions();
    }

    private void seeMyTimeline() {
    }

    private void seeTimelineByUser() {
    }
}
