package cli;

import vdm.Facebook;
import vdm.User;

import java.util.ArrayList;

class MainMenu extends AbstractMenu {
    Facebook facebook;
    User user;
    private SearchMenu searchMenu = new SearchMenu(true, this);
    private FriendsMenu friendsMenu = new FriendsMenu(true, this);
    private TimelineMenu timelineMenu = new TimelineMenu(true, this);
    private GroupChatMenu groupChatMenu = new GroupChatMenu(true, this);
    private PublicationMenu publicationMenu = new PublicationMenu(true, this);

    MainMenu(boolean hasParent, Facebook facebook, User user) {
        super(hasParent);
        this.facebook = facebook;
        this.user = user;
    }

    @Override
    void getOptions() {
        printDivision("Main Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Search");
        options.add("Timeline");
        options.add("Posts");
        options.add("Friends");
        options.add("Chat");
        options.add("Logout");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "Search":
                searchMenu.getOptions();
                getOptions();
                break;
            case "Timeline":
                timelineMenu.getOptions();
                getOptions();
                break;
            case "Posts":
                publicationMenu.getOptions();
                getOptions();
                break;
            case "Friends":
                friendsMenu.getOptions();
                getOptions();
                break;
            case "Chat":
                groupChatMenu.getOptions();
                getOptions();
                break;
            case "Logout":
                break;
        }
    }


}
