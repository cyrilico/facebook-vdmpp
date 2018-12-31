package vdm.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PublicQuote {
  private static int hc = 0;
  private static PublicQuote instance = null;

  public PublicQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PublicQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PublicQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PublicQuote;
  }

  public String toString() {

    return "<Public>";
  }
}
