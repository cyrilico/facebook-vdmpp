package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  protected User user;
  protected String name;
  public VDMSet friends;
  protected VDMSet blocked;
  protected VDMSeq posts;
  protected VDMMap chats;

  public void cg_init_User_1(final String userName) {

    name = userName;
    friends = SetUtil.set();
    blocked = SetUtil.set();
    posts = SeqUtil.seq();
    chats = MapUtil.map();
    user = this;
    return;
  }

  public User(final String userName) {

    cg_init_User_1(userName);
  }

  public String getName() {

    return name;
  }

  public Number getNameLength() {

    return name.length();
  }

  public VDMSet getChats() {

    return MapUtil.rng(Utils.copy(chats));
  }

  public GroupChat getChatByName(final String chatName) {

    return ((GroupChat) Utils.get(chats, chatName));
  }

  public VDMSeq getPublications(final User searcher) {

    VDMSeq seqCompResult_5 = SeqUtil.seq();
    VDMSet set_18 = SeqUtil.inds(posts);
    for (Iterator iterator_20 = set_18.iterator(); iterator_20.hasNext(); ) {
      Number i = ((Number) iterator_20.next());
      if (((Publication) Utils.get(posts, i)).userHasPermissions(searcher)) {
        seqCompResult_5.add(((Publication) Utils.get(posts, i)));
      }
    }
    return Utils.copy(seqCompResult_5);
  }

  public VDMSet getFriends() {

    return Utils.copy(friends);
  }

  public VDMSet getBlockedUsers() {

    return Utils.copy(blocked);
  }

  public VDMSet getFriendsOfFriends() {

    VDMSet setCompResult_6 = SetUtil.set();
    VDMSet setCompResult_7 = SetUtil.set();
    VDMSet set_20 = Utils.copy(friends);
    for (Iterator iterator_22 = set_20.iterator(); iterator_22.hasNext(); ) {
      User friend = ((User) iterator_22.next());
      setCompResult_7.add(friend.friends);
    }
    VDMSet set_19 =
        SetUtil.diff(
            SetUtil.union(Utils.copy(friends), SetUtil.dunion(Utils.copy(setCompResult_7))),
            SetUtil.set(user));
    for (Iterator iterator_21 = set_19.iterator(); iterator_21.hasNext(); ) {
      User people = ((User) iterator_21.next());
      setCompResult_6.add(people);
    }
    return Utils.copy(setCompResult_6);
  }

  public VDMSet getFriendsTransitiveClosure() {

    VDMSet closure = Utils.copy(friends);
    VDMSet visited = SetUtil.set();
    Boolean whileCond_2 = true;
    while (whileCond_2) {
      whileCond_2 = !(Utils.equals(visited, closure));
      if (!(whileCond_2)) {
        break;
      }

      {
        User t = null;
        Boolean success_1 = false;
        VDMSet set_1 = SetUtil.diff(Utils.copy(closure), Utils.copy(visited));
        for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext() && !(success_1); ) {
          t = ((User) iterator_1.next());
          success_1 = true;
        }
        if (!(success_1)) {
          throw new RuntimeException("Let Be St found no applicable bindings");
        }

        {
          closure = SetUtil.union(Utils.copy(closure), t.friends);
          visited = SetUtil.union(Utils.copy(visited), SetUtil.set(t));
        }
      }
    }

    return SetUtil.diff(Utils.copy(closure), SetUtil.set(user));
  }

  public Boolean nameContains(final String string) {

    String userName = name;
    Boolean whileCond_3 = true;
    while (whileCond_3) {
      whileCond_3 = !(Utils.equals(userName, ""));
      if (!(whileCond_3)) {
        break;
      }

      {
        if (Utils.equals(SeqUtil.subSeq(userName, 1L, string.length()), string)) {
          return true;

        } else {
          userName = SeqUtil.tail(userName);
        }
      }
    }

    return false;
  }

  public void makePublication(
      final String content, final Number timestamp, final Object permissions) {

    posts =
        SeqUtil.conc(
            SeqUtil.seq(new Publication(this, content, timestamp, ((Object) permissions))),
            Utils.copy(posts));
  }

  public void updatePublicationPermissions(
      final Number publicationId, final Object newPermissions) {

    Number iotaExp_1 = null;
    Long iotaCounter_1 = 0L;
    VDMSet set_22 = SeqUtil.inds(posts);
    for (Iterator iterator_24 = set_22.iterator(); iterator_24.hasNext(); ) {
      Number i = ((Number) iterator_24.next());
      if (Utils.equals(((Publication) Utils.get(posts, i)).getId(), publicationId)) {
        iotaCounter_1++;
        if (iotaCounter_1.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_1 = i;
        }
      }
    }
    if (Utils.equals(iotaCounter_1, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    {
      final Number index = iotaExp_1;
      ((Publication) Utils.get(posts, index)).updatePermissions(newPermissions);
    }
  }

  public void deletePublication(final Number publicationId) {

    VDMSeq seqCompResult_6 = SeqUtil.seq();
    VDMSet set_25 = SeqUtil.inds(posts);
    for (Iterator iterator_27 = set_25.iterator(); iterator_27.hasNext(); ) {
      Number i = ((Number) iterator_27.next());
      if (!(Utils.equals(((Publication) Utils.get(posts, i)).getId(), publicationId))) {
        seqCompResult_6.add(((Publication) Utils.get(posts, i)));
      }
    }
    posts = Utils.copy(seqCompResult_6);
  }

  public void createGroupChat(final String chatName, final VDMSet initialMembers) {

    {
      final GroupChat newChat =
          new GroupChat(chatName, SetUtil.union(Utils.copy(initialMembers), SetUtil.set(this)));
      {
        chats = MapUtil.munion(Utils.copy(chats), MapUtil.map(new Maplet(chatName, newChat)));
        for (Iterator iterator_35 = initialMembers.iterator(); iterator_35.hasNext(); ) {
          User member = (User) iterator_35.next();
          member.addGroupChat(newChat);
        }
      }
    }
  }

  public void addFriendToChat(final User friend, final String chatName) {

    friend.addGroupChat(((GroupChat) Utils.get(chats, chatName)));
  }

  protected void addGroupChat(final GroupChat chat) {

    chat.addMember(this);
    chats = MapUtil.munion(Utils.copy(chats), MapUtil.map(new Maplet(chat.getName(), chat)));
  }

  public void leaveGroupChat(final String chatName) {

    ((GroupChat) Utils.get(chats, chatName)).removeMember(this);
    chats = MapUtil.domResBy(SetUtil.set(chatName), Utils.copy(chats));
  }

  public void sendChatMessage(final String chatName, final String content, final Number timestamp) {

    ((GroupChat) Utils.get(chats, chatName)).sendMessage(new ChatMessage(user, content, timestamp));
  }

  public VDMSeq searchTextInChat(final String chatName, final String searchText) {

    return ((GroupChat) Utils.get(chats, chatName)).getMessagesWithText(searchText);
  }

  public VDMSeq filterDateInChat(
          final String chatName, final Number beginningDate, final Number endDate) {

    return ((GroupChat) Utils.get(chats, chatName)).getMessagesBetween(beginningDate, endDate);
  }

  public void blockUser(final User userBlocked) {

    blocked = SetUtil.union(Utils.copy(blocked), SetUtil.set(userBlocked));
  }

  public void unblockUser(final User userBlocked) {

    blocked = SetUtil.diff(Utils.copy(blocked), SetUtil.set(userBlocked));
  }

  public User() {}

  public String toString() {

    return "User{"
        + "user := "
        + Utils.toString(user)
        + ", name := "
        + Utils.toString(name)
        + ", friends := "
        + Utils.toString(friends)
        + ", blocked := "
        + Utils.toString(blocked)
        + ", posts := "
        + Utils.toString(posts)
        + ", chats := "
        + Utils.toString(chats)
        + "}";
  }
}
