package cli;

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
    }

    private void searchMessagesByText() {
    }

    private void searchMessagesBetweenDates() {
    }
}
