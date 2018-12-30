package cli;

import java.util.ArrayList;

class MainMenu extends AbstractMenu {
    private FriendsMenu friendsMenu = new FriendsMenu(true, this);
    private TimelineMenu timelineMenu = new TimelineMenu(true, this);
    private GroupChatMenu groupChatMenu = new GroupChatMenu(true, this);
    private PublicationMenu publicationMenu = new PublicationMenu(true, this);

    MainMenu(boolean hasParent) {
        super(hasParent);
    }

    @Override
    void getOptions() {
        printDivision("Main Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Friends");
        options.add("Timeline");
        options.add("GroupChat");
        options.add("Posts");
        options.add("Exit");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "Friends":
                friendsMenu.getOptions();
                getOptions();
                break;
            case "Timeline":
                timelineMenu.getOptions();
                getOptions();
                break;
            case "GroupChat":
                groupChatMenu.getOptions();
                getOptions();
                break;
            case "Posts":
                publicationMenu.getOptions();
                getOptions();
                break;
            case "Exit":
                break;
        }
    }
}
