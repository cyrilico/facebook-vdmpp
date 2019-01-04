package cli;

import org.overture.codegen.runtime.VDMSet;

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
                break;
            case "Search a Post":
                searchPosts();
                break;
            case BACK_INPUT:
            case MENU_INPUT:
                return;
        }

        getOptions();
    }

    private void searchUsers() {
        System.out.print("Enter username: ");
        String searchText = scanner.nextLine();
        VDMSet users = mainMenu.facebook.searchUser(searchText);

        if (users.isEmpty()) {
            System.out.println("\nUsers not found!");
            return;
        }

        System.out.println("\nResults for users matching '" + searchText + "':\n");
        Utils.printUsersSET(users);
    }

    private void searchPosts() {
        System.out.print("Enter post information: ");
        String searchText = scanner.nextLine();
        VDMSet posts = mainMenu.facebook.searchPublications(mainMenu.user, searchText);

        if (posts.isEmpty()) {
            System.out.println("\nPosts not found!");
            return;
        }

        System.out.println("\nResults for posts matching '" + searchText + "':\n");
        Utils.printPostsSET(posts);
    }

}
