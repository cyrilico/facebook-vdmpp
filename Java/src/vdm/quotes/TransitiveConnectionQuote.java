package vdm.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TransitiveConnectionQuote {
  private static int hc = 0;
  private static TransitiveConnectionQuote instance = null;

  public TransitiveConnectionQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TransitiveConnectionQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TransitiveConnectionQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TransitiveConnectionQuote;
  }

  public String toString() {

    return "<TransitiveConnection>";
  }
}
