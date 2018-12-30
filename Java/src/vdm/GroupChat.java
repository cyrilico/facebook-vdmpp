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
    VDMSet set_16 = SeqUtil.inds(messages);
    for (Iterator iterator_18 = set_16.iterator(); iterator_18.hasNext(); ) {
      Number i = ((Number) iterator_18.next());
      Boolean andResult_25 = false;

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
          andResult_25 = true;
        }
      }

      if (andResult_25) {
        seqCompResult_3.add(((ChatMessage) Utils.get(messages, i)));
      }
    }
    return Utils.copy(seqCompResult_3);
  }

  public VDMSeq getMessagesWithText(final String searchText) {

    VDMSeq seqCompResult_4 = SeqUtil.seq();
    VDMSet set_17 = SeqUtil.inds(messages);
    for (Iterator iterator_19 = set_17.iterator(); iterator_19.hasNext(); ) {
      Number i = ((Number) iterator_19.next());
      if (((ChatMessage) Utils.get(messages, i)).contentContains(searchText)) {
        seqCompResult_4.add(((ChatMessage) Utils.get(messages, i)));
      }
    }
    return Utils.copy(seqCompResult_4);
  }

  public ChatMessage getMessageById(final Number messageId) {

    ChatMessage iotaExp_1 = null;
    Long iotaCounter_1 = 0L;
    VDMSet set_19 = SeqUtil.elems(Utils.copy(messages));
    for (Iterator iterator_21 = set_19.iterator(); iterator_21.hasNext(); ) {
      ChatMessage message = ((ChatMessage) iterator_21.next());
      if (Utils.equals(message.getId(), messageId)) {
        iotaCounter_1++;
        if (iotaCounter_1.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_1 = message;
        }
      }
    }
    if (Utils.equals(iotaCounter_1, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    return iotaExp_1;
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
