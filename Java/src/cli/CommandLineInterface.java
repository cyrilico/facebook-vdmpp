package cli;

import vdm.Facebook;

public class CommandLineInterface {
    public static void main(String[] args) {
        Facebook facebook = Facebook.getInstance();

        AuthMenu authMenu = new AuthMenu(false, facebook);

        System.out.println("Welcome to Facebook VDM++!");

        authMenu.getOptions();
    }
}
