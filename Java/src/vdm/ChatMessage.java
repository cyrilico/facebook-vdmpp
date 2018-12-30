package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ChatMessage extends Message {
  protected static final Number MAX_SIZE = 250L;
  protected static Number idCounter = 1L;
  protected Number id = ChatMessage.idCounter;

  public void cg_init_ChatMessage_1(
      final User MessageAuthor, final String MessageContent, final Number MessageTimestamp) {

    idCounter = ChatMessage.idCounter.longValue() + 1L;
    author = MessageAuthor;
    content = MessageContent;
    timestamp = MessageTimestamp;
    return;
  }

  public ChatMessage(
      final User MessageAuthor, final String MessageContent, final Number MessageTimestamp) {

    cg_init_ChatMessage_1(MessageAuthor, MessageContent, MessageTimestamp);
  }

  public Number getId() {

    return id;
  }

  public ChatMessage() {}

  public String toString() {

    return "ChatMessage{"
        + "MAX_SIZE = "
        + Utils.toString(MAX_SIZE)
        + ", idCounter := "
        + Utils.toString(idCounter)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
