package cli;

import org.overture.codegen.runtime.VDMSet;
import vdm.Date;
import vdm.Facebook;
import vdm.User;

class PopulateDatabase {
    private Facebook facebook;
    private User[] users;

    PopulateDatabase(Facebook facebook) {
        this.facebook = facebook;
        this.users = new User[10];
    }

    void addDatabase() {
        addUsers();
        addFriendship();
        addPosts();
        addLikes();
        addChats();
        addFriendsToChats();
        sendChatMessages();
    }

    private void addUsers() {
        for (int i = 1; i < 9; i++) {
            users[i] = new User("user" + i);
            facebook.addUser(users[i]);
        }
    }

    private void addFriendship() {
        facebook.addFriendship(users[1], users[2]);
        facebook.addFriendship(users[1], users[3]);
        facebook.addFriendship(users[1], users[4]);
        facebook.addFriendship(users[2], users[5]);
        facebook.addFriendship(users[3], users[6]);
        facebook.addFriendship(users[3], users[4]);
        facebook.addFriendship(users[4], users[7]);
        facebook.addFriendship(users[7], users[8]);
    }

    private void addBlockedUsers() {
        users[1].blockUser(users[2]);
        users[1].blockUser(users[3]);
        users[1].blockUser(users[4]);
        users[2].blockUser(users[5]);
        users[3].blockUser(users[6]);
        users[3].blockUser(users[4]);
    }

    private void addPosts() {
        users[1].makePublication("greetings, eager young minds", Date.makeDate(2016, 12, 12), vdm.quotes.TransitiveConnectionQuote.getInstance());
        users[1].makePublication("greetings, friends", Date.makeDate(2016, 12, 13), vdm.quotes.FriendsQuote.getInstance());
        users[1].makePublication("fear me, friends, for i am back", Date.makeDate(2017, 12, 17), vdm.quotes.PublicQuote.getInstance());
        users[2].makePublication("back, looking for new friendships", Date.makeDate(2017, 01, 01), vdm.quotes.FriendsOfFriendsQuote.getInstance());
        users[3].makePublication("what do i need friends for? popularity, of course", Date.makeDate(2018, 01, 13), vdm.quotes.FriendsQuote.getInstance());
        users[1].makePublication("my first publication", Date.makeDate(2016, 12, 12), vdm.quotes.PublicQuote.getInstance());
        users[1].makePublication("so, how is everybody doing", Date.makeDate(2017, 01, 01), vdm.quotes.PublicQuote.getInstance());
        users[1].makePublication("hello, but only to my friends!", Date.makeDate(2017, 02, 14), vdm.quotes.FriendsQuote.getInstance());

    }

    private void addLikes() {
        facebook.likePublication(users[1], 1);
        facebook.likePublication(users[1], 2);
        facebook.likePublication(users[2], 4);
        facebook.likePublication(users[3], 5);
        facebook.likePublication(users[6], 1);
    }

    private void addChats() {
        users[1].createGroupChat("i hate eclipse", new VDMSet());
        users[4].createGroupChat("by myself", new VDMSet());
        users[1].createGroupChat("superior users", new VDMSet());
    }

    private void addFriendsToChats() {
        users[1].addFriendToChat(users[2], "i hate eclipse");
        users[1].addFriendToChat(users[3], "i hate eclipse");
        users[4].addFriendToChat(users[3], "by myself");
        users[3].addFriendToChat(users[4], "i hate eclipse");
        users[1].addFriendToChat(users[2], "superior users");
        users[1].addFriendToChat(users[3], "superior users");
        users[3].addFriendToChat(users[4], "superior users");
    }

    private void sendChatMessages() {
        users[1].sendChatMessage("i hate eclipse", "hey guys, we have to do MFES", Date.makeDate(2018, 12, 22));
        users[3].sendChatMessage("i hate eclipse", "well user4 is missing, so let me add him", Date.makeDate(2018, 12, 22));
        users[3].sendChatMessage("i hate eclipse", "hey user4, MFES man, gotta do it", Date.makeDate(2018, 12, 23));
        users[4].sendChatMessage("i hate eclipse", "nope, GL", Date.makeDate(2018, 12, 25));
        users[2].sendChatMessage("i hate eclipse", "fine, then i won't do anything too", Date.makeDate(2018, 12, 26));
        users[1].sendChatMessage("superior users", "hey guys, let's play something", Date.makeDate(2018, 12, 22));
        users[3].sendChatMessage("superior users", "user4 loves to game, let me add him", Date.makeDate(2018, 12, 22));
        users[3].sendChatMessage("superior users", "hey 4 let's game", Date.makeDate(2018, 12, 22));
        users[4].sendChatMessage("superior users", "meh i don't feel like gaming rn", Date.makeDate(2018, 12, 23));
        users[1].sendChatMessage("superior users", "stop being so negative, you love games no matter what", Date.makeDate(2018, 12, 24));
        users[4].sendChatMessage("superior users", "well sure but i have to study", Date.makeDate(2018, 12, 25));
        users[3].sendChatMessage("superior users", "pff come on you always get straight As", Date.makeDate(2018, 12, 26));
        users[2].sendChatMessage("superior users", "yeah, besides exams are like a month away, studying now makes no sense!", Date.makeDate(2018, 12, 28));
        users[4].sendChatMessage("superior users", "look guys i just don't want to game", Date.makeDate(2019, 1, 1));
        users[2].sendChatMessage("superior users", "ok, 1 and 3 let's game ourselves then", Date.makeDate(2019, 2, 15));
        users[1].sendChatMessage("superior users", "sure thing, just give me a second", Date.makeDate(2019, 5, 5));
        users[3].sendChatMessage("superior users", "absolutely, brb too", Date.makeDate(2019, 6, 1));
        users[4].sendChatMessage("superior users", "so what did you guys end up doing for a game?", Date.makeDate(2019, 6, 2));
        users[1].sendChatMessage("superior users", "lol nothing... let's talk later, l8r bro", Date.makeDate(2019, 6, 3));
    }
}
