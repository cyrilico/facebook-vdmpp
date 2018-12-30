package cli;

import org.overture.codegen.runtime.VDMSeq;
import vdm.User;

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
        options.add("See Main Feed");
        options.add("See Timeline from other users");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "See Main Feed":
                seeMainFeed();
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

    private void seeMainFeed() {
        VDMSeq mainFeed = mainMenu.facebook.getUserFeed(mainMenu.user);
        Utils.printPostsSEQ(mainFeed);
    }

    private void seeTimelineByUser() {
        User user2 = Utils.getUser(scanner, mainMenu);
        VDMSeq timelineByUser = mainMenu.facebook.getUserTimeline(mainMenu.user, user2);
        Utils.printPostsSEQ(timelineByUser);
    }
}
