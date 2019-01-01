package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class GroupChat {
  protected String name;
  protected VDMSet members;
  protected VDMSeq messages;

  public void cg_init_GroupChat_1(
      final String GroupChatName, final VDMSet GroupChatStartingMembers) {

    name = GroupChatName;
    members = Utils.copy(GroupChatStartingMembers);
    messages = SeqUtil.seq();
    return;
  }

  public GroupChat(final String GroupChatName, final VDMSet GroupChatStartingMembers) {

    cg_init_GroupChat_1(GroupChatName, Utils.copy(GroupChatStartingMembers));
  }

  public String getName() {

    return name;
  }

  public VDMSet getMembers() {

    return Utils.copy(members);
  }

  public void addMember(final User user) {

    members = SetUtil.union(Utils.copy(members), SetUtil.set(user));
  }

  public void removeMember(final User user) {

    members = SetUtil.diff(Utils.copy(members), SetUtil.set(user));
  }

  public void sendMessage(final ChatMessage message) {

    messages = SeqUtil.conc(Utils.copy(messages), SeqUtil.seq(message));
  }

  public VDMSeq getMessagesBetween(final Number startDate, final Number endDate) {

    VDMSeq seqCompResult_3 = SeqUtil.seq();
    VDMSet set_21 = SeqUtil.inds(messages);
    for (Iterator iterator_23 = set_21.iterator(); iterator_23.hasNext(); ) {
      Number i = ((Number) iterator_23.next());
      Boolean andResult_29 = false;

      Object ternaryIfExp_2 = null;

      if (!(Utils.equals(startDate, null))) {
        ternaryIfExp_2 = startDate;
      } else {
        ternaryIfExp_2 = Date.minimumDate;
      }

      if (((ChatMessage) Utils.get(messages, i)).getTimestamp().longValue()
          >= ((Number) (ternaryIfExp_2)).doubleValue()) {
        Object ternaryIfExp_3 = null;

        if (!(Utils.equals(endDate, null))) {
          ternaryIfExp_3 = endDate;
        } else {
          ternaryIfExp_3 = Date.maximumDate;
        }

        if (((ChatMessage) Utils.get(messages, i)).getTimestamp().longValue()
            <= ((Number) (ternaryIfExp_3)).doubleValue()) {
          andResult_29 = true;
        }
      }

      if (andResult_29) {
        seqCompResult_3.add(((ChatMessage) Utils.get(messages, i)));
      }
    }
    return Utils.copy(seqCompResult_3);
  }

  public VDMSeq getMessagesWithText(final String searchText) {

    VDMSeq seqCompResult_4 = SeqUtil.seq();
    VDMSet set_22 = SeqUtil.inds(messages);
    for (Iterator iterator_24 = set_22.iterator(); iterator_24.hasNext(); ) {
      Number i = ((Number) iterator_24.next());
      if (((ChatMessage) Utils.get(messages, i)).contentContains(searchText)) {
        seqCompResult_4.add(((ChatMessage) Utils.get(messages, i)));
      }
    }
    return Utils.copy(seqCompResult_4);
  }

  public ChatMessage getMessageById(final Number messageId) {

    ChatMessage iotaExp_3 = null;
    Long iotaCounter_3 = 0L;
    VDMSet set_24 = SeqUtil.elems(Utils.copy(messages));
    for (Iterator iterator_26 = set_24.iterator(); iterator_26.hasNext(); ) {
      ChatMessage message = ((ChatMessage) iterator_26.next());
      if (Utils.equals(message.getId(), messageId)) {
        iotaCounter_3++;
        if (iotaCounter_3.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_3 = message;
        }
      }
    }
    if (Utils.equals(iotaCounter_3, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    return iotaExp_3;
  }

  public GroupChat() {}

  public String toString() {

    return "GroupChat{"
        + "name := "
        + Utils.toString(name)
        + ", members := "
        + Utils.toString(members)
        + ", messages := "
        + Utils.toString(messages)
        + "}";
  }
}
