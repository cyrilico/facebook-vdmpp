package cli;

import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;
import vdm.User;

import java.util.ArrayList;

class FriendsMenu extends AbstractMenu {

    private MainMenu mainMenu;

    FriendsMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
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
            case MENU_INPUT:
                return;
        }

        getOptions();
    }


    private void addFriends() {
        System.out.println("\nAdding a friend\n");
        User user2 = Utils.getUser(scanner, mainMenu);
        if (user2 == null) return;
        mainMenu.facebook.addFriendship(mainMenu.user, user2);
    }

    private void removeFriends() {
        System.out.println("\nRemoving a friend\n");
        User user2 = Utils.getUser(scanner, mainMenu);
        if (user2 == null) return;
        mainMenu.facebook.removeFriendship(mainMenu.user, user2);
    }

    private void blockUser() {
        System.out.println("\nBlocking a user\n");
        User user2 = Utils.getUser(scanner, mainMenu);
        if (user2 == null) return;
        mainMenu.user.blockUser(user2);
    }

    private void unblockUser() {
        System.out.println("\nUnblocking a user\n");
        User user2 = Utils.getUser(scanner, mainMenu);
        if (user2 == null) return;
        mainMenu.user.unblockUser(user2);
    }

    private void listFriendsSuggestions() {
        System.out.println("\n" + mainMenu.user.getName() + "'s friend suggestions\n");
        VDMSeq friendSuggestions = mainMenu.facebook.getFriendSuggestions(mainMenu.user);
        Utils.printUsersSEQ(friendSuggestions);
    }

    private void listFriends() {
        System.out.println("\n" + mainMenu.user.getName() + "'s friends\n");
        VDMSet friends = mainMenu.user.getFriends();
        Utils.printUsersSET(friends);
    }

    private void listBlockedUsers() {
        System.out.println("\n" + mainMenu.user.getName() + "'s blocked users\n");
        VDMSet blockedUsers = mainMenu.user.getBlockedUsers();
        Utils.printUsersSET(blockedUsers);
    }
}