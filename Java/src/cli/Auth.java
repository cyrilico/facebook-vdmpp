package cli;

import vdm.Facebook;
import vdm.User;

import java.util.Scanner;

class Auth {

    private Facebook facebook;
    private Scanner scanner = new Scanner(System.in);

    Auth(Facebook facebook) {
        this.facebook = facebook;
    }

    User enterUsername() {
        User user;
        int i = 3;

        do {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            user = facebook.getUserByName(username);
            if (user == null)
                printInvalidUsername();
            i--;
        } while (user == null && i > 0);

        return user;
    }

    User registerUsername() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user = new User(username);
        facebook.addUser(user);

        return facebook.getUserByName(username);
    }

    private void printInvalidUsername() {
        System.out.println("Invalid username");
    }

}
