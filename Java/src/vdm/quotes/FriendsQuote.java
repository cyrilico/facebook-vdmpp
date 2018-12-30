package vdm.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FriendsQuote {
  private static int hc = 0;
  private static FriendsQuote instance = null;

  public FriendsQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FriendsQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FriendsQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FriendsQuote;
  }

  public String toString() {

    return "<Friends>";
  }
}
