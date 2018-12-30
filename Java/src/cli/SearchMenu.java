package cli;

import org.overture.codegen.runtime.VDMSet;
import vdm.Publication;
import vdm.User;

import java.util.ArrayList;

class SearchMenu extends AbstractMenu {
    private MainMenu mainMenu;

    SearchMenu(boolean hasParent, MainMenu mainMenu) {
        super(hasParent);
        this.mainMenu = mainMenu;
    }

    @Override
    void getOptions() {
        printDivision("Search Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Search an User");
        options.add("Search a Post");

        String input = printAndSelectOptions(options);
        switch (input) {
            case "Search an User":
                searchUsers();
                getOptions();
                break;
            case "Search a Post":
                searchPosts();
                getOptions();
                break;
            case BACK_INPUT:
                return;
            case MENU_INPUT:
                mainMenu.getOptions();
                break;
        }
    }

    private void searchUsers() {
        System.out.print("Enter username: ");
        String searchText = scanner.nextLine();
        VDMSet users = mainMenu.facebook.searchUser(searchText);

        if (users.isEmpty()) {
            System.out.println("Users not found!");
            return;
        }

        for (Object u1 : users) {
            User user = (User) u1;
            System.out.println(user.getName());
        }

    }

    private void searchPosts() {
        System.out.print("Enter post information: ");
        String searchText = scanner.nextLine();
        VDMSet posts = mainMenu.facebook.searchPublications(mainMenu.user, searchText);

        if (posts.isEmpty()) {
            System.out.println("Posts not found!");
            return;
        }

        for (Object p1 : posts) {
            Publication post = (Publication) p1;
            System.out.println(post.getContent());
        }
    }

}
