package cli;

import org.overture.codegen.runtime.VDMSeq;
import vdm.Publication;

import java.util.ArrayList;

class PublicationMenu extends AbstractMenu {
    private MainMenu mainMenu;

    PublicationMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
    }

    @Override
    void getOptions() {
        printDivision("Posts Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("See Posts");
        options.add("New Post");
        options.add("Update Post");
        options.add("Delete Post");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "See Posts":
                seePosts();
                break;
            case "New Post":
                newPost();
                break;
            case "Update Post":
                updatePost();
                break;
            case "Delete Post":
                deletePost();
                break;
            case BACK_INPUT:
            case MENU_INPUT:
                return;
        }

        getOptions();
    }

    private void seePosts() {
        VDMSeq posts = mainMenu.facebook.getUserTimeline(mainMenu.user, mainMenu.user);

        System.out.println("\n" + mainMenu.user.getName() + "'s posts: \n");
        Utils.printPostsSEQ(posts);
    }

    private void newPost() {
        System.out.println("\n Creating new post \n");
        String content = getContent();
        Number date = Utils.getDate(scanner);
        Object perms = getPermissions();

        mainMenu.user.makePublication(content, date, perms);
    }

    private void updatePost() {
        System.out.println("\n Updating a post \n");
        Publication post = Utils.getPost(scanner, mainMenu);
        System.out.println("Current permissions: " + post.getPermissions().toString());
        Object perms = getPermissions();
        mainMenu.user.updatePublicationPermissions(post.getId(), perms);
    }

    private void deletePost() {
        System.out.println("\n Deleting a post \n");
        mainMenu.user.deletePublication(Utils.getPost(scanner, mainMenu).getId());
    }

    private Object getPermissions() {
        Object perms = null;

        do {
            System.out.println("Enter permission(public, friends, friendsOfFriends, transitiveConnection): ");
            String permissions = scanner.nextLine();

            switch (permissions) {
                case "public":
                    perms = vdm.quotes.PublicQuote.getInstance();
                    break;
                case "friends":
                    perms = vdm.quotes.FriendsQuote.getInstance();
                    break;
                case "friendsOfFriends":
                    perms = vdm.quotes.FriendsOfFriendsQuote.getInstance();
                    break;
                case "transitiveConnection":
                    perms = vdm.quotes.TransitiveConnectionQuote.getInstance();
                    break;
            }

        } while (perms == null);

        return perms;
    }

    private String getContent() {
        System.out.print("Enter content: ");
        return scanner.nextLine();
    }

}
