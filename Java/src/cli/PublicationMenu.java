package cli;

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
        options.add("New Post");
        options.add("Update Post");
        options.add("Delete Post");

        String input = printAndSelectOptions(options);
        switch (input) {
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

    private void newPost() {
    }

    private void updatePost() {
    }

    private void deletePost() {
    }
}
