package cli;

import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;
import vdm.User;

import java.util.ArrayList;

class FriendsMenu extends AbstractMenu {

    private MainMenu mainMenu;
    private Auth utils;

    FriendsMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
        this.utils = new Auth(mainMenu.facebook);
    }

    @Override
    void getOptions() {
        printDivision("Friends Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Add Friends");
        options.add("Remove Friends");
        options.add("Block User");
        options.add("Unblock User");
        options.add("See Friends Suggestions");
        options.add("List Friends");
        options.add("List Blocked Users");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "Add Friends":
                addFriends();
                break;
            case "Remove Friends":
                removeFriends();
                break;
            case "Block User":
                blockUser();
                break;
            case "Unblock User":
                unblockUser();
                break;
            case "See Friends Suggestions":
                listFriendsSuggestions();
                break;
            case "List Friends":
                listFriends();
                break;
            case "List Blocked Users":
                listBlockedUsers();
                break;
            case BACK_INPUT:
                return;
            case MENU_INPUT:
                return;
        }
        getOptions();
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

    private void addFriends() {
        User user2 = getUser();
        if (user2 == null) return;
        mainMenu.facebook.addFriendship(mainMenu.user, user2);
    }

    private void removeFriends() {
        User user2 = getUser();
        if (user2 == null) return;
        mainMenu.facebook.removeFriendship(mainMenu.user, user2);
    }

    private void blockUser() {
        User user2 = getUser();
        if (user2 == null) return;
        mainMenu.user.blockUser(user2);
    }

    private void unblockUser() {
        User user2 = getUser();
        if (user2 == null) return;
        mainMenu.user.unblockUser(user2);
    }

    private void listFriendsSuggestions() {
        VDMSeq friendSuggestions = mainMenu.facebook.getFriendSuggestions(mainMenu.user);
        for (Object u1 : friendSuggestions) {
            User user = (User) u1;
            System.out.println(user.getName());
        }
    }

    private void listFriends() {
        VDMSet friends = mainMenu.user.getFriends();
        for (Object u1 : friends) {
            User user = (User) u1;
            System.out.println(user.getName());
        }
    }

    private void listBlockedUsers() {
        VDMSet blockedUsers = mainMenu.user.getBlockedUsers();
        for (Object u1 : blockedUsers) {
            User user = (User) u1;
            System.out.println(user.getName());
        }
    }
}