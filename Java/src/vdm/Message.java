package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Message {
  protected String content;
  protected User author;
  protected Number timestamp;

  public User getAuthor() {

    return author;
  }

  public Number getTimestamp() {

    return timestamp;
  }

  public String getContent() {

    return content;
  }

  public Boolean contentContains(final String string) {

    String msgContent = content;
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = msgContent.length() >= string.length();
      if (!(whileCond_1)) {
        break;
      }

      {
        if (Utils.equals(SeqUtil.subSeq(msgContent, 1L, string.length()), string)) {
          return true;

        } else {
          msgContent = SeqUtil.tail(msgContent);
        }
      }
    }

    return false;
  }

  public Message() {}

  public String toString() {

    return "Message{"
        + "content := "
        + Utils.toString(content)
        + ", author := "
        + Utils.toString(author)
        + ", timestamp := "
        + Utils.toString(timestamp)
        + "}";
  }
}
