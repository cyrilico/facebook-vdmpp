package cli;

import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;
import vdm.ChatMessage;
import vdm.GroupChat;
import vdm.User;
import vdm.Date;

import java.time.LocalDateTime;
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
        System.out.println("What would you like to name the chat?");

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
        String chatName = selectChat().getName();

        if(chatName.isEmpty())
            return;

        String content = readChatContent();

        LocalDateTime now = LocalDateTime.now();
        Number date = Date.makeDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        mainMenu.user.sendChatMessage(chatName, content, date);

        System.out.println("Message '" + content + "' sent to " + chatName + ".");
    }

    private void addFriend() {
        String chatName = selectChat().getName();
        User friend = getUser();

        System.out.println("\nWhat friend would you like to add to the chat?");
        mainMenu.user.addFriendToChat(friend, chatName);

        System.out.println("\nUser '" + friend.getName() + "' added to " + chatName + ".");
    }

    private void seeAllMessages() {
        GroupChat chat = selectChat();

        // The VDM methods works without paremeters,
        // but Java doesn't allow dynamic overloading
        VDMSeq messages = chat.getMessagesBetween(Date.minimumDate, Date.maximumDate);

        System.out.println("\nAll messages from chat '" + chat.getName()+ "': \n");

        for (Object obj : messages) {
            ChatMessage message = (ChatMessage) obj;
            System.out.println("    " + message.getAuthor().getName() + "[" + message.getTimestamp() + "] - " + message.getContent());
        }
    }

    private void searchMessagesByText() {
        GroupChat chat = selectChat();
        String content = readChatContent();
        VDMSeq messages = chat.getMessagesWithText(content);

        if(messages.isEmpty()) {
            System.out.println("No messages found!");
            return;
        }

        System.out.println("\nMessages from chat '" + chat.getName()+ "' with the content '" + content + "': \n");

        for (Object obj : messages) {
            ChatMessage message = (ChatMessage) obj;
            System.out.println("    " + message.getAuthor().getName() + "[" + message.getTimestamp() + "] - " + message.getContent());
        }
    }

    private void searchMessagesBetweenDates() {
        GroupChat chat = selectChat();

        System.out.println("\nSelecting a beginning date...");
        Number beginDate = Utils.getDate(scanner, mainMenu);

        System.out.println("\nSelecting an ending date...");
        Number endDate = Utils.getDate(scanner, mainMenu);

        VDMSeq messages = chat.getMessagesBetween(beginDate, endDate);

        if(messages.isEmpty()) {
            System.out.println("No messages found!");
            return;
        }

        System.out.println("\nMessages from chat '" + chat.getName()+ "': \n");

        for (Object obj : messages) {
            ChatMessage message = (ChatMessage) obj;
            System.out.println("    " + message.getAuthor().getName() + "[" + message.getTimestamp() + "] - " + message.getContent());
        }
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
                System.out.println("Found a chat named '" + name + "'");

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

            if(username.isEmpty())
                return null;

            user = mainMenu.facebook.getUserByName(username);
            if (user == null)
                System.out.println("User not found");
            i--;
        } while (user == null && i > 0);

        return user;
    }

    private String readChatContent() {
        String content;

        do {
            System.out.print("Enter message content: ");
            content = scanner.nextLine();
        } while (content.isEmpty());

        return content;
    }

}
