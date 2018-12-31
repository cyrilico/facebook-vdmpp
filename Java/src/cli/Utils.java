package cli;

import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;
import vdm.Publication;
import vdm.User;

import java.util.Scanner;

public class Utils {

    static User getUser(Scanner scanner, MainMenu mainMenu) {
        User user;
        int i = 3;

        do {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            user = mainMenu.facebook.getUserByName(username);
            if (user == null)
                System.out.println("User not found");
            i--;
        } while (user == null && i > 0);

        return user;
    }

    private static void printUser(User u1) {
        System.out.println("Name: " + u1.getName() + "| no. friends: " + u1.getFriends().size());
    }

    static void printUsersSET(VDMSet users) {
        for (Object u1 : users) {
            printUser((User) u1);
        }
    }

    static void printUsersSEQ(VDMSeq users) {
        for (Object u1 : users) {
            printUser((User) u1);
        }
    }

    private static void printPost(Publication p1) {
        System.out.println("Post " + p1.getId() + "| Author: " + p1.getAuthor().getName() + "| Date: " + p1.getTimestamp() + "| Likes: " + p1.getLikes().size());
        System.out.println("Content " + p1.getContent());
    }

    static void printPostsSET(VDMSet posts) {
        for (Object p1 : posts) {
            printPost((Publication) p1);
        }
    }

    static void printPostsSEQ(VDMSeq posts) {
        for (Object p1 : posts) {
            printPost((Publication) p1);
        }
    }
}