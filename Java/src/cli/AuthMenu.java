package cli;

import vdm.Facebook;
import vdm.User;

import java.util.ArrayList;

class AuthMenu extends AbstractMenu {
    private Facebook facebook;
    private Auth auth;
    private MainMenu mainMenu;

    AuthMenu(boolean hasParent, Facebook facebook) {
        super(hasParent);
        this.facebook = facebook;
        this.auth = new Auth(facebook);
    }

    @Override
    void getOptions() {
        printDivision("Welcome Menu");

        ArrayList<String> options = new ArrayList<>();
        options.add("Login");
        options.add("Register");
        options.add("Exit");

        User user = null;

        String input = printAndSelectOptions(options);
        switch (input) {
            case "Login":
                user = auth.enterUsername();
                break;
            case "Register":
                user = auth.registerUsername();
                break;
            case "Exit":
                return;
        }

        if (user != null) {
            this.mainMenu = new MainMenu(true, facebook, user);
            this.mainMenu.getOptions();
        }

        getOptions();
    }
}
