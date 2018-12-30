package cli;

import org.overture.codegen.runtime.VDMSeq;
import vdm.Publication;
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
        for (Object p1 : mainFeed) {
            Publication post = (Publication) p1;
            System.out.println("Post " + post.getId() + "| Author: " + post.getAuthor().getName() + "| Date: " + post.getTimestamp() + "| Likes: " + post.getLikes().size());
            System.out.println("Content " + post.getContent());
        }
    }

    private void seeTimelineByUser() {
        User user2 = getUser();
        VDMSeq timelineByUser = mainMenu.facebook.getUserTimeline(mainMenu.user, user2);
        for (Object p1 : timelineByUser) {
            Publication post = (Publication) p1;
            System.out.println("Post " + post.getId() + "| Author: " + post.getAuthor().getName() + "| Date: " + post.getTimestamp() + "| Likes: " + post.getLikes().size());
            System.out.println("Content " + post.getContent());
        }
    }

    //TODO: Extract this method
    private User getUser() {
        User user;
        int i = 3;

        do {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            user = mainMenu.facebook.getUserByName(username);
            if (user == null)
                System.out.println("User not found");
            i--;
        } while (user == null && i > 0);

        return user;
    }
}
