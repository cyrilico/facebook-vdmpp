package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Publication extends Message {
  protected static Number idCounter = 1L;
  protected Number id = Publication.idCounter;
  protected VDMSet likes;
  protected Object permissions;

  public void cg_init_Publication_1(
      final User PublicationAuthor,
      final String PublicationContent,
      final Number PublicationTimestamp,
      final Object PublicationPermissions) {

    idCounter = Publication.idCounter.longValue() + 1L;
    author = PublicationAuthor;
    content = PublicationContent;
    timestamp = PublicationTimestamp;
    permissions = PublicationPermissions;
    likes = SetUtil.set();
    return;
  }

  public Publication(
      final User PublicationAuthor,
      final String PublicationContent,
      final Number PublicationTimestamp,
      final Object PublicationPermissions) {

    cg_init_Publication_1(
        PublicationAuthor, PublicationContent, PublicationTimestamp, PublicationPermissions);
  }

  public Number getId() {

    return id;
  }

  public void updatePermissions(final Object newPermissions) {

    permissions = newPermissions;
  }

  public Boolean userHasPermissions(final User user) {

    Object quotePattern_1 = permissions;
    Boolean success_3 = Utils.equals(quotePattern_1, vdm.quotes.PublicQuote.getInstance());

    if (!(success_3)) {
      Object quotePattern_2 = permissions;
      success_3 = Utils.equals(quotePattern_2, vdm.quotes.FriendsQuote.getInstance());

      if (!(success_3)) {
        Object quotePattern_3 = permissions;
        success_3 = Utils.equals(quotePattern_3, vdm.quotes.FriendsOfFriendsQuote.getInstance());

        if (!(success_3)) {
          Object quotePattern_4 = permissions;
          success_3 =
              Utils.equals(quotePattern_4, vdm.quotes.TransitiveConnectionQuote.getInstance());

          if (success_3) {
            return SetUtil.inSet(
                user, SetUtil.union(author.getFriendsTransitiveClosure(), SetUtil.set(author)));

          } else {
            return !(SetUtil.inSet(user, author.getBlockedUsers()));
          }

        } else {
          return SetUtil.inSet(
              user, SetUtil.union(author.getFriendsOfFriends(), SetUtil.set(author)));
        }

      } else {
        return SetUtil.inSet(user, SetUtil.union(author.getFriends(), SetUtil.set(author)));
      }

    } else {
      return !(SetUtil.inSet(user, author.getBlockedUsers()));
    }
  }

  public VDMSet getLikes() {

    return Utils.copy(likes);
  }

  public void like(final User user) {

    likes = SetUtil.union(Utils.copy(likes), SetUtil.set(user));
  }

  public void removeLike(final User user) {

    likes = SetUtil.diff(Utils.copy(likes), SetUtil.set(user));
  }

  public Number calculateScore(final User user) {

    Number ternaryIfExp_4 = null;

    if (SetUtil.inSet(user, author.friends)) {
      ternaryIfExp_4 = 2L;
    } else {
      ternaryIfExp_4 = 1L;
    }

    return likes.size()
        * (1L + SetUtil.intersect(Utils.copy(likes), user.friends).size())
        * (ternaryIfExp_4.longValue());
  }

  public Publication() {}

  public String toString() {

    return "Publication{"
        + "idCounter := "
        + Utils.toString(idCounter)
        + ", id := "
        + Utils.toString(id)
        + ", likes := "
        + Utils.toString(likes)
        + ", permissions := "
        + Utils.toString(permissions)
        + "}";
  }
}
