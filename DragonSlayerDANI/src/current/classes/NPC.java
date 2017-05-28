package current.classes;

import current.classes.templates.Character;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 23:32.
 */
public class NPC extends Character {

  // Default values.
  private static final int DEFAULT_QUEST_ID = 0;
  private static final Item DEFAULT_REWARD = null;
  private static final String DEFAULT_STRING = null;
  private static final boolean DEFAULT_BOOLEAN = false;

  private boolean wasVisited;
  private boolean isQuestCompleted;
  private int questID;
  private Item reward;
  private String meetingPhrase;
  private String questNotCompleted;
  private String questCompleted;
  private String attackResponse;

  /**
   * constructor method to create a new npc object with no quest.
   * meeting phrase will be used for the first visit,
   * questNotCompleted will be used every following visit,
   * attack response will also be used as planned.
   *
   * @param name the String name of the npc.
   * @param health int health.
   * @param weapon Item weapon for the npc.
   * @param pos Position on the field.
   * @param meetingPhrase String for first visit.
   * @param additionalVisit questNotCompleted String.
   * @param attackResponse String attack response.
   */
  public NPC(String name, int health, Item weapon, Position pos, String meetingPhrase,
      String additionalVisit, String attackResponse) {
    super(name, health, weapon, pos);
    this.wasVisited = DEFAULT_BOOLEAN;
    this.isQuestCompleted = DEFAULT_BOOLEAN;
    this.questID = DEFAULT_QUEST_ID;
    this.reward = DEFAULT_REWARD;
    this.meetingPhrase = meetingPhrase;
    this.questNotCompleted = additionalVisit;
    this.questCompleted = DEFAULT_STRING;
    this.attackResponse = attackResponse;
  }

  /**
   * constructor method to create a new npc object that has a quest for the player.
   *
   * @param name String name.
   * @param health int health.
   * @param weapon Item weapon.
   * @param pos Position on the field.
   * @param questID int questID.
   * @param reward Item reward.
   * @param sentences String[] of length 4 with the sentences for the npc.
   */
  public NPC(String name, int health, Item weapon, Position pos, int questID, Item reward,
      String[] sentences) {
    super(name, health, weapon, pos);
    this.wasVisited = DEFAULT_BOOLEAN;
    this.isQuestCompleted = DEFAULT_BOOLEAN;
    this.questID = questID;
    this.reward = reward;
    this.meetingPhrase = sentences[0];
    this.questNotCompleted = sentences[1];
    this.questCompleted = sentences[2];
    this.attackResponse = sentences[3];
  }

  // Getter and setter methods.
  public boolean wasVisited() {
    return wasVisited;
  }

  public boolean hasQuest() {
    return this.questID != DEFAULT_QUEST_ID;
  }

  public boolean isQuestCompleted() {
    return isQuestCompleted;
  }

  public int getQuestID() {
    return questID;
  }

  public Item getReward() {
    return reward;
  }

  public String getMeetingPhrase() {
    return meetingPhrase;
  }

  public String getQuestNotCompleted() {
    return questNotCompleted;
  }

  public String getQuestCompleted() {
    return questCompleted;
  }

  public String getAttackResponse() {
    return attackResponse;
  }

  public void setWasVisited(boolean wasVisited) {
    this.wasVisited = wasVisited;
  }

  public void setIsQuestCompleted(boolean isQuestCompleted) {
    this.isQuestCompleted = isQuestCompleted;
  }

  public void setQuestID(int questID) {
    this.questID = questID;
  }

  public void setReward(Item reward) {
    this.reward = reward;
  }

  public void setMeetingPhrase(String meetingPhrase) {
    this.meetingPhrase = meetingPhrase;
  }

  public void setQuestNotCompleted(String questNotCompleted) {
    this.questNotCompleted = questNotCompleted;
  }

  public void setQuestCompleted(String questCompleted) {
    this.questCompleted = questCompleted;
  }

  public void setAttackResponse(String attackResponse) {
    this.attackResponse = attackResponse;
  }
}
