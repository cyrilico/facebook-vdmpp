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
        Utils.printPostsSEQ(posts);
    }

    private void newPost() {
        String content = getContent();
        Number date = Utils.getDate(scanner);
        Object perms = getPermissions();

        mainMenu.user.makePublication(content, date, perms);
    }

    private void updatePost() {
        Publication post = getPost();
        System.out.println("Current permissions: " + post.getPermissions().toString());
        Object perms = getPermissions();
        mainMenu.user.updatePublicationPermissions(post.getId(), perms);
    }

    private void deletePost() {
        mainMenu.user.deletePublication(getPost().getId());
    }

    private Publication getPost() {
        Publication post;
        int i = 3;

        do {
            System.out.print("Enter post's ID: ");
            String postID = scanner.nextLine();
            post = mainMenu.user.getPublicationById(Integer.parseInt(postID));
            if (post == null)
                System.out.println("Post not found");
            i--;
        } while (post == null && i > 0);

        return post;
    }


    private Object getPermissions() {
        Object perms = null;

        do {
            System.out.println("Enter permission(public, friends, friendsOfFriends): ");
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
            }

        } while (perms == null);

        return perms;
    }

    private String getContent() {
        System.out.print("Enter content: ");
        return scanner.nextLine();
    }

}
