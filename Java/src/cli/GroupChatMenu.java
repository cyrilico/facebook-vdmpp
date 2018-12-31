package cli;

import org.overture.codegen.runtime.VDMSet;
import vdm.GroupChat;
import vdm.User;

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
        options.add("Create Chat");
        options.add("Send message to Chat");
        options.add("Add Friend to Chat");
        options.add("See all messages from a given Chat");
        options.add("Search messages by text from a given Chat");
        options.add("Search messages between dates from a given Chat");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "Create Chat":
                createChat();
                break;
            case "Send message to Chat":
                sendMessage();
                break;
            case "Add Friend to Chat":
                addFriend();
                break;
            case "See all messages from a given Chat":
                seeAllMessages();
                break;
            case "Search messages by text from a given Chat":
                searchMessagesByText();
                break;
            case "Search messages between dates from a given Chat":
                searchMessagesBetweenDates();
                break;
            case BACK_INPUT:
            case MENU_INPUT:
                return;
        }

        getOptions();
    }

    private void createChat() {

        String chatName = readChatName();

        VDMSet initialUsers = new VDMSet();

        int MAX_USERS = 10;

        System.out.println("What friends would you like to add to this chat?");

        while(initialUsers.size() < MAX_USERS) {

            User user = getUser();

            if(user == null)
                break;

            initialUsers.add(user);
        }

        mainMenu.user.createGroupChat(chatName, initialUsers);
        System.out.println("Group chat named '" + chatName + "' with " + initialUsers.size() + " users was created.");
    }

    private void sendMessage() {
    }

    private void addFriend() {
    }

    private void seeAllMessages() {
        GroupChat chat = selectChat();

    }

    private void searchMessagesByText() {
    }

    private void searchMessagesBetweenDates() {
    }

    private GroupChat selectChat() {
        if(!listChats())
            return null;

        System.out.println("Which chat would you like to choose ?");

        GroupChat chat = null;
        int i = 3;
        do {
            System.out.print("Choose a chat: ");
            String option = scanner.nextLine();

            try {
                chat = mainMenu.user.getChatByName(option);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Chat not found.");
            }

            i--;
        } while (chat == null && i > 0);

        return chat;
    }


    private String readChatName() {

        GroupChat chat = null;
        int i = 3;
        String name;
        boolean validName = false;

        System.out.println("What would you like to name the chat?");

        do {
            System.out.print("Enter chat name : ");
            name = scanner.nextLine();

            try {
                chat = mainMenu.user.getChatByName(name);
            }
            catch (IllegalArgumentException e) {
                // In case of IllegalArgumentException, there is no
                // chat with the given name
                validName = true;
            }

            if(!validName)
                System.out.println("You already have a chat named '" + name + "'");

            i--;
        } while (!validName && i > 0);

        return name;
    }

    private boolean listChats() {
        VDMSet chats = mainMenu.user.getChats();

        if(chats.size() == 0) {
            System.out.println("You don't have any open chats. Going back...");
            return false;
        }
        else
            System.out.println("You currently have " + chats.size() + " chats open:");

        for (Object obj : chats) {
            GroupChat chat = (GroupChat) obj;
            System.out.println("    - " + chat.getName() + ": " + chat.getMembers().size() + " members");
        }

        return true;
    }

    //TODO: Extract this method
    private User getUser() {
        User user;
        int i = 3;

        do {
            System.out.print("Enter a username (leave empty to stop) : ");
            String username = scanner.nextLine();

            if(username.isEmpty()) {
                System.out.println("Empty: ");
                return null;
            }
            else {
                System.out.println("Not empty : "  + username);
            }

            user = mainMenu.facebook.getUserByName(username);
            if (user == null)
                System.out.println("User not found");
            i--;
        } while (user == null && i > 0);

        return user;
    }
}
