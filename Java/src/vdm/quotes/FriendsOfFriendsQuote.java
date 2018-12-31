package vdm.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FriendsOfFriendsQuote {
  private static int hc = 0;
  private static FriendsOfFriendsQuote instance = null;

  public FriendsOfFriendsQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FriendsOfFriendsQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FriendsOfFriendsQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FriendsOfFriendsQuote;
  }

  public String toString() {

    return "<FriendsOfFriends>";
  }
}
