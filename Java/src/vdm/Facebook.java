package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Facebook {
  public VDMSet users = SetUtil.set();
  private static Facebook globalInstance = new Facebook();

  private void cg_init_Facebook_1() {

    users = SetUtil.set();
    return;
  }

  private Facebook() {

    cg_init_Facebook_1();
  }

  public static Facebook getInstance() {

    return Facebook.globalInstance;
  }

  public static Facebook clearInstance() {

    globalInstance = new Facebook();
    Publication.resetIDCounter();
    ChatMessage.resetIDCounter();
    return getInstance();
  }

  public VDMSet getUsers() {

    return Utils.copy(users);
  }

  public User getUserByName(final String name) {

    User result = null;
    for (Iterator iterator_47 = users.iterator(); iterator_47.hasNext(); ) {
      User user = (User) iterator_47.next();
      if (Utils.equals(user.getName(), name)) {
        result = user;
      } else {
        /* skip */
      }
    }
    return result;
  }

  public void addUser(final User user) {

    users = SetUtil.union(Utils.copy(users), SetUtil.set(user));
  }

  public void addUsers(final VDMSet newUsers) {

    users = SetUtil.union(Utils.copy(users), Utils.copy(newUsers));
  }

  public void addFriendship(final User user1, final User user2) {

    User u1 = user1;
    User u2 = user2;
    VDMSet atomicTmp_1 = SetUtil.union(user1.friends, SetUtil.set(user2));
    VDMSet atomicTmp_2 = SetUtil.union(user2.friends, SetUtil.set(user1));
    {
        /* Start of atomic statement */
      u1.friends = Utils.copy(atomicTmp_1);
      u2.friends = Utils.copy(atomicTmp_2);
    } /* End of atomic statement */
  }

  public void removeFriendship(final User user1, final User user2) {

    User u1 = user1;
    User u2 = user2;
    VDMSet atomicTmp_3 = SetUtil.diff(user1.friends, SetUtil.set(user2));
    VDMSet atomicTmp_4 = SetUtil.diff(user2.friends, SetUtil.set(user1));
    {
        /* Start of atomic statement */
      u1.friends = Utils.copy(atomicTmp_3);
      u2.friends = Utils.copy(atomicTmp_4);
    } /* End of atomic statement */
  }

  protected VDMSet getAllPublications(final User searcher) {

    VDMSet setCompResult_1 = SetUtil.set();
    VDMSet set_4 = Utils.copy(users);
    for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext(); ) {
      User member = ((User) iterator_4.next());
      setCompResult_1.add(SeqUtil.elems(getUserTimeline(searcher, member)));
    }
    return SetUtil.dunion(Utils.copy(setCompResult_1));
  }

  public void likePublication(final User user, final Number publicationId) {

    Publication iotaExp_1 = null;
    Long iotaCounter_1 = 0L;
    VDMSet set_6 = getAllPublications(user);
    for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext(); ) {
      Publication publication = ((Publication) iterator_6.next());
      if (Utils.equals(publication.getId(), publicationId)) {
        iotaCounter_1++;
        if (iotaCounter_1.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_1 = publication;
        }
      }
    }
    if (Utils.equals(iotaCounter_1, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    {
      final Publication publication = iotaExp_1;
      publication.like(user);
    }
  }

  public void unlikePublication(final User user, final Number publicationId) {

    Publication iotaExp_2 = null;
    Long iotaCounter_2 = 0L;
    VDMSet set_8 = getAllPublications(user);
    for (Iterator iterator_8 = set_8.iterator(); iterator_8.hasNext(); ) {
      Publication publication = ((Publication) iterator_8.next());
      if (Utils.equals(publication.getId(), publicationId)) {
        iotaCounter_2++;
        if (iotaCounter_2.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_2 = publication;
        }
      }
    }
    if (Utils.equals(iotaCounter_2, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    {
      final Publication publication = iotaExp_2;
      publication.removeLike(user);
    }
  }

  public VDMSet searchUser(final String searchText) {

    VDMSet setCompResult_2 = SetUtil.set();
    VDMSet set_11 = Utils.copy(users);
    for (Iterator iterator_12 = set_11.iterator(); iterator_12.hasNext(); ) {
      User user = ((User) iterator_12.next());
      if (user.nameContains(searchText)) {
        setCompResult_2.add(user);
      }
    }
    return Utils.copy(setCompResult_2);
  }

  public VDMSet searchPublications(final User searcher, final String searchText) {

    VDMSet setCompResult_3 = SetUtil.set();
    VDMSet setCompResult_4 = SetUtil.set();
    VDMSet set_15 = Utils.copy(users);
    for (Iterator iterator_17 = set_15.iterator(); iterator_17.hasNext(); ) {
      User user = ((User) iterator_17.next());
      if (!(Utils.equals(user, searcher))) {
        setCompResult_4.add(SeqUtil.elems(user.getPublications(searcher)));
      }
    }
    VDMSet set_14 = SetUtil.dunion(Utils.copy(setCompResult_4));
    for (Iterator iterator_16 = set_14.iterator(); iterator_16.hasNext(); ) {
      Publication publication = ((Publication) iterator_16.next());
      if (publication.contentContains(searchText)) {
        setCompResult_3.add(publication);
      }
    }
    return Utils.copy(setCompResult_3);
  }

  public VDMSeq getUserTimeline(final User searcher, final User user) {

    return user.getPublications(searcher);
  }

  public VDMSeq getUserFeed(final User user) {

    VDMSet setCompResult_5 = SetUtil.set();
    VDMSet setCompResult_6 = SetUtil.set();
    VDMSet set_18 = Utils.copy(users);
    for (Iterator iterator_20 = set_18.iterator(); iterator_20.hasNext(); ) {
      User member = ((User) iterator_20.next());
      if (!(Utils.equals(user, member))) {
        setCompResult_6.add(SeqUtil.elems(getUserTimeline(user, member)));
      }
    }
    VDMSet set_17 = SetUtil.dunion(Utils.copy(setCompResult_6));
    for (Iterator iterator_19 = set_17.iterator(); iterator_19.hasNext(); ) {
      Publication publication = ((Publication) iterator_19.next());
      Boolean orResult_3 = false;

      if (SetUtil.inSet(publication.getAuthor(), user.friends)) {
        orResult_3 = true;
      } else {
        orResult_3 = SetUtil.intersect(publication.getLikes(), user.friends).size() > 0L;
      }

      if (orResult_3) {
        setCompResult_5.add(publication);
      }
    }
    VDMSet allPublications = Utils.copy(setCompResult_5);
    VDMSeq seqOfPubs = SeqUtil.seq();
    for (Iterator iterator_48 = allPublications.iterator(); iterator_48.hasNext(); ) {
      Publication publication = (Publication) iterator_48.next();
      seqOfPubs =
          SeqUtil.conc(
              Utils.copy(seqOfPubs),
              SeqUtil.seq(new PublicationRank(publication, publication.calculateScore(user))));
    }
    return sortPublications(Utils.copy(seqOfPubs));
  }

  public VDMSet getCommonFriends(final User user1, final User user2) {

    return SetUtil.intersect(user1.friends, user2.friends);
  }

  public VDMSeq getFriendSuggestions(final User user) {

    VDMSet unsortedSuggestions = SetUtil.diff(user.getFriendsOfFriends(), user.friends);
    VDMSeq scoredSuggestions = SeqUtil.seq();
    VDMSeq sortedSuggestions = SeqUtil.seq();
    for (Iterator iterator_49 = unsortedSuggestions.iterator(); iterator_49.hasNext(); ) {
      User suggestion = (User) iterator_49.next();
      scoredSuggestions =
          SeqUtil.conc(
              Utils.copy(scoredSuggestions),
              SeqUtil.seq(new UserRank(suggestion, getCommonFriends(user, suggestion).size())));
    }
    sortedSuggestions = sortSuggestions(Utils.copy(scoredSuggestions));
    return SeqUtil.subSeq(Utils.copy(sortedSuggestions), 1L, 5L);
  }

  protected VDMSeq sortPublications(final VDMSeq publications) {

    VDMSeq sorted_list = Utils.copy(publications);
    long toVar_2 = 1L;
    long byVar_1 = -1L;
    for (Long i = Long.valueOf(publications.size()); byVar_1 < 0 ? i >= toVar_2 : i <= toVar_2; i += byVar_1) {
      long toVar_1 = i.longValue() - 1L;

      for (Long j = 1L; j <= toVar_1; j++) {
        Boolean orResult_4 = false;

        if (((PublicationRank) Utils.get(sorted_list, j)).score.longValue()
            > ((PublicationRank) Utils.get(sorted_list, j.longValue() + 1L)).score.longValue()) {
          orResult_4 = true;
        } else {
          Boolean andResult_22 = false;

          if (Utils.equals(
              ((PublicationRank) Utils.get(sorted_list, j)).score,
              ((PublicationRank) Utils.get(sorted_list, j.longValue() + 1L)).score)) {
            if (((PublicationRank) Utils.get(sorted_list, j)).publication.getTimestamp().longValue()
                > ((PublicationRank) Utils.get(sorted_list, j.longValue() + 1L))
                    .publication
                    .getTimestamp()
                    .longValue()) {
              andResult_22 = true;
            }
          }

          orResult_4 = andResult_22;
        }

        if (orResult_4) {
          PublicationRank temp = Utils.copy(((PublicationRank) Utils.get(sorted_list, j)));
          Utils.mapSeqUpdate(
              sorted_list,
              j,
              Utils.copy(((PublicationRank) Utils.get(sorted_list, j.longValue() + 1L))));
          Utils.mapSeqUpdate(sorted_list, j.longValue() + 1L, Utils.copy(temp));
        }
      }
    }
    VDMSeq seqCompResult_1 = SeqUtil.seq();
    VDMSet set_19 = SeqUtil.inds(sorted_list);
    for (Iterator iterator_21 = set_19.iterator(); iterator_21.hasNext(); ) {
      Number i = ((Number) iterator_21.next());
      seqCompResult_1.add(((PublicationRank) Utils.get(sorted_list, i)).publication);
    }
    return Utils.copy(seqCompResult_1);
  }

  protected VDMSeq sortSuggestions(final VDMSeq suggestions) {

    VDMSeq sorted_list = Utils.copy(suggestions);
    long toVar_4 = 1L;
    long byVar_2 = -1L;
    for (Long i = Long.valueOf(suggestions.size()); byVar_2 < 0 ? i >= toVar_4 : i <= toVar_4; i += byVar_2) {
      long toVar_3 = i.longValue() - 1L;

      for (Long j = 1L; j <= toVar_3; j++) {
        Boolean orResult_5 = false;

        if (((UserRank) Utils.get(sorted_list, j)).score.longValue()
            < ((UserRank) Utils.get(sorted_list, j.longValue() + 1L)).score.longValue()) {
          orResult_5 = true;
        } else {
          Boolean andResult_23 = false;

          if (Utils.equals(
              ((UserRank) Utils.get(sorted_list, j)).score,
              ((UserRank) Utils.get(sorted_list, j.longValue() + 1L)).score)) {
            if (((UserRank) Utils.get(sorted_list, j)).user.getNameLength().longValue()
                > ((UserRank) Utils.get(sorted_list, j.longValue() + 1L))
                    .user
                    .getNameLength()
                    .longValue()) {
              andResult_23 = true;
            }
          }

          orResult_5 = andResult_23;
        }

        if (orResult_5) {
          UserRank temp = Utils.copy(((UserRank) Utils.get(sorted_list, j)));
          Utils.mapSeqUpdate(
              sorted_list, j, Utils.copy(((UserRank) Utils.get(sorted_list, j.longValue() + 1L))));
          Utils.mapSeqUpdate(sorted_list, j.longValue() + 1L, Utils.copy(temp));
        }
      }
    }
    VDMSeq seqCompResult_2 = SeqUtil.seq();
    VDMSet set_20 = SeqUtil.inds(sorted_list);
    for (Iterator iterator_22 = set_20.iterator(); iterator_22.hasNext(); ) {
      Number i = ((Number) iterator_22.next());
      seqCompResult_2.add(((UserRank) Utils.get(sorted_list, i)).user);
    }
    return Utils.copy(seqCompResult_2);
  }

  public String toString() {

    return "Facebook{"
        + "users := "
        + Utils.toString(users)
        + ", globalInstance := "
        + Utils.toString(globalInstance)
        + "}";
  }

  protected static class PublicationRank implements Record {
    public Publication publication;
    public Number score;

    public PublicationRank(final Publication _publication, final Number _score) {

      publication = _publication != null ? _publication : null;
      score = _score;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof PublicationRank)) {
        return false;
      }

      PublicationRank other = ((PublicationRank) obj);

      return (Utils.equals(publication, other.publication)) && (Utils.equals(score, other.score));
    }

    public int hashCode() {

      return Utils.hashCode(publication, score);
    }

    public PublicationRank copy() {

      return new PublicationRank(publication, score);
    }

    public String toString() {

      return "mk_Facebook`PublicationRank" + Utils.formatFields(publication, score);
    }
  }

  protected static class UserRank implements Record {
    public User user;
    public Number score;

    public UserRank(final User _user, final Number _score) {

      user = _user != null ? _user : null;
      score = _score;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof UserRank)) {
        return false;
      }

      UserRank other = ((UserRank) obj);

      return (Utils.equals(user, other.user)) && (Utils.equals(score, other.score));
    }

    public int hashCode() {

      return Utils.hashCode(user, score);
    }

    public UserRank copy() {

      return new UserRank(user, score);
    }

    public String toString() {

      return "mk_Facebook`UserRank" + Utils.formatFields(user, score);
    }
  }
}
