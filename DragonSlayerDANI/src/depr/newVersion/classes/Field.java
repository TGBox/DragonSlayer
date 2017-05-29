package depr.newVersion.classes;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 02.05.2017, 21:24.
 */
public class Field {

  public boolean vocal;
  private String name;
  private Enemy enemy;
  private NPC npc;
  private Item item;
  private int mapID;
  private boolean wasVisited;
  private boolean isPortal;

  /**
   * various constructor methods with mixed parameters.
   * last constructor also has the possibility to set an npc for a field.
   *
   * @param name the String name of the field.
   * @param enemy the Enemy that is on the field.
   * @param item the Item that is on the field.
   * @param mapID int to determine the map for the field.
   * @param isPortal boolean to determine if this field is a portal to another map.
   */
  public Field(String name, Enemy enemy, Item item, int mapID, boolean isPortal) {
    this.name = name;
    initiateVocal();
    this.enemy = enemy;
    this.npc = null;
    this.item = item;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = isPortal;
  }

  public Field(String name, Enemy enemy, Item item, int mapID) {
    this.name = name;
    initiateVocal();
    this.enemy = enemy;
    this.npc = null;
    this.item = item;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = false;
  }

  public Field(String name, Enemy enemy, int mapID) {
    this.name = name;
    initiateVocal();
    this.enemy = enemy;
    this.npc = null;
    this.item = null;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = false;
  }

  public Field(String name, int mapID, boolean isPortal) {
    this.name = name;
    initiateVocal();
    this.enemy = null;
    this.npc = null;
    this.item = null;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = isPortal;
  }

  public Field(String name, int mapID) {
    this.name = name;
    initiateVocal();
    this.enemy = null;
    this.npc = null;
    this.item = null;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = false;
  }

  public Field(String name, Item item, int mapID) {
    this.name = name;
    initiateVocal();
    this.enemy = null;
    this.npc = null;
    this.item = item;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = false;
  }

  public Field(String name, NPC npc, int mapID) {
    this.name = name;
    initiateVocal();
    this.enemy = null;
    this.npc = null;
    this.npc = npc;
    this.item = null;
    this.mapID = mapID;
    this.wasVisited = false;
    this.isPortal = false;
  }

  /**
   * method to initialize the vocal boolean.
   * is set to true if the name begins with an vocal.
   * will be needed to adapt the articles for the words.
   */
  private void initiateVocal() {
    char init = this.name.charAt(0);
    if (init == 'a' || init == 'e' || init == 'i' || init == 'o' || init == 'u') {
      this.vocal = true;
    } else {
      this.vocal = false;
    }
  }

  /**
   * method to check if the given field has an npc or not.
   *
   * @return boolean true if npc is present, false if not.
   */
  public boolean hasNPC() {
    return this.npc != null;
  }

  /**
   * method to check if an enemy is on the field.
   *
   * @return boolean true if there is one, else false.
   */
  public boolean hasEnemy() {
    return this.enemy != null;
  }

  /**
   * method to check if an item is on the field.
   *
   * @return boolean true if there is one, else false.
   */
  public boolean hasItem() {
    return this.item != null;
  }

  // Getter and setter methods.
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Enemy getEnemy() {
    return enemy;
  }

  public void setEnemy(Enemy enemy) {
    this.enemy = enemy;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getMapID() {
    return mapID;
  }

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  public NPC getNpc() {
    return npc;
  }

  public void setNpc(NPC npc) {
    this.npc = npc;
  }

  public boolean wasVisited() {
    return wasVisited;
  }

  public boolean isPortal() {
    return isPortal;
  }

  public void setPortal(boolean portal) {
    isPortal = portal;
  }

  public void setWasVisited(boolean wasVisited) {
    this.wasVisited = wasVisited;
  }
}
