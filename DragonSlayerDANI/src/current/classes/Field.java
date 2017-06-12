package current.classes;

import current.classes.templates.Thing;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 23:48.
 */
public class Field extends Thing {

  // Default values.
  private static final boolean DEFAULT_BOOLEAN = false;
  private static final Enemy DEFAULT_ENEMY = null;
  private static final Item DEFAULT_ITEM = null;
  private static final NPC DEFAULT_NPC = null;
  private static final int DEFAULT_PORTAL_ID = 0;

  private boolean wasVisited;
  private boolean isPortal;
  private Enemy enemy;
  private Item item;
  private NPC npc;
  private int mapID;
  private int portalID;

  /**
   * constructor method to create a new field object with a portal on it.
   *
   * @param name the String name of the field.
   * @param mapID int mapID.
   * @param portalID int portalID.
   */
  public Field(String name, int mapID, int portalID) {
    super(name);
    this.mapID = mapID;
    this.portalID = portalID;
    this.wasVisited = DEFAULT_BOOLEAN;
    this.isPortal = true;
    this.enemy = DEFAULT_ENEMY;
    this.item = DEFAULT_ITEM;
    this.npc = DEFAULT_NPC;
  }

  /**
   * constructor method to create a new field object containing up to one enemy and one item.
   *
   * @param name String name.
   * @param enemy Enemy. can be null.
   * @param item Item, can be null.
   * @param mapID int mapID.
   */
  public Field(String name, Enemy enemy, Item item, int mapID) {
    super(name);
    this.wasVisited = DEFAULT_BOOLEAN;
    this.isPortal = DEFAULT_BOOLEAN;
    this.enemy = enemy;
    this.item = item;
    this.npc = DEFAULT_NPC;
    this.mapID = mapID;
    this.portalID = DEFAULT_PORTAL_ID;
  }

  /**
   * constructor method to create a new field object with an npc on it.
   *
   * @param name String name of the field.
   * @param npc NPC for the field.
   * @param mapID int mapID.
   */
  public Field(String name, NPC npc, int mapID) {
    super(name);
    this.wasVisited = DEFAULT_BOOLEAN;
    this.isPortal = DEFAULT_BOOLEAN;
    this.enemy = DEFAULT_ENEMY;
    this.item = DEFAULT_ITEM;
    this.npc = npc;
    this.mapID = mapID;
    this.portalID = DEFAULT_PORTAL_ID;
  }

  /**
   * method to check if the current field contains an enemy or not.
   *
   * @return boolean true if an enemy is on that field, false else.
   */
  public boolean hasEnemy(){
    return enemy != null;
  }

  /**
   * method to check if the current field has an npc on it or not.
   *
   * @return boolean true if an npc is on that field, false else.
   */
  public boolean hasNPC(){
    return npc != null;
  }

  // Getter and setter methods.
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

  public int getPortalID() {
    return portalID;
  }

  public void setPortalID(int portalID) {
    this.portalID = portalID;
  }

  public void setWasVisited(boolean wasVisited) {
    this.wasVisited = wasVisited;
  }
}
