package newVersion.classes;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 05.05.2017, 00:01.
 */
public class NPC {

    private String name;
    public boolean vocal;
    private boolean wasVisited;
    private int health;
    private Item weapon;
    private boolean hasQuest;
    private boolean isQuestCompleted;
    private int questID;
    private Item reward;
    private String meetingPhrase;
    private String questNotCompleted;
    private String questCompleted;
    private String attackResponse;

    /**
     * constructor method to create a new npc object.
     * @param name the String name of the npc.
     * @param health int health value.
     * @param weapon Item weapon for the npc.
     * @param questID the int questID to match the npc and the questItem.
     * @param reward the Item that will be provided by the npc for the completion of the quest.
     * @param sentences String[] with four sentences that the npc can speak.
     */
    public NPC(String name, int health, Item weapon,
               int questID, Item reward, String[] sentences){
        this.name = name;
        initiateVocal();
        this.wasVisited = false;
        this.health = health;
        this.weapon = weapon;
        this.questID = questID;
        this.reward = reward;
        this.meetingPhrase = sentences[0];
        this.questNotCompleted = sentences[1];
        this.questCompleted = sentences[2];
        this.attackResponse = sentences[3];
        this.isQuestCompleted = false;
        if(reward != null){
            this.hasQuest = true;
        } else {
            this.hasQuest = false;
        }

    }

    /**
     * method to initialize the vocal boolean.
     * is set to true if the name begins with an vocal.
     * will be needed to adapt the articles for the words.
     */
    private void initiateVocal(){
        char init = this.name.charAt(0);
        if(init == 'a' || init == 'e' || init == 'i' || init == 'o' || init == 'u'){
            this.vocal = true;
        } else {
            this.vocal = false;
        }
    }

    // Getter and setter methods.
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public Item getWeapon() {
        return weapon;
    }
    public boolean hasQuest() {
        return hasQuest;
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
    public boolean wasVisited() {
        return wasVisited;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }
    public void setHasQuest(boolean hasQuest) {
        this.hasQuest = hasQuest;
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
    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }
}
