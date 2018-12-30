package cli;

import org.overture.codegen.runtime.VDMSet;
import vdm.GroupChat;

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

        GroupChat chat;
        int i = 3;
        do {
            System.out.print("Choose a chat: ");
            String option = scanner.nextLine();

            chat = mainMenu.user.getChatByName(option);

            if (chat == null)
                System.out.println("Chat not found");
            i--;
        } while (chat == null && i > 0);

        return chat;
    }

    private boolean listChats() {
        VDMSet chats = mainMenu.user.getChats();

        if(chats.size() == 0) {
            System.out.println("You don't have any open chats. Going back...");
            return false;
        }
        else
            System.out.println("You currently have " + chats.size() + " chats open:");

        int i = 0;
        for (Object obj : chats) {
            GroupChat chat = (GroupChat) obj;
            System.out.println("    " + i + " - " + chat.getName());
        }

        return true;
    }
}
