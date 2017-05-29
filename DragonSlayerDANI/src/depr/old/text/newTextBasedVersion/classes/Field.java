package depr.old.text.newTextBasedVersion.classes;


/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 22:53.
 */
public class Field {

  private String name;
  private Enemy enemy;
  private Item item;
  private int mapID;
  private boolean wasVisited;

  /**
   * various constructor methods with mixed parameters.
   *
   * @param name the String name of the field.
   * @param enemy the Enemy that is on the field.
   * @param item the Item that is on the field.
   * @param mapID int to determine the map for the field.
   */
  public Field(String name, Enemy enemy, Item item, int mapID) {
    this.name = name;
    this.enemy = enemy;
    this.item = item;
    this.mapID = mapID;
    this.wasVisited = false;
  }

  public Field(String name, Enemy enemy, int mapID) {
    this.name = name;
    this.enemy = enemy;
    this.item = null;
    this.mapID = mapID;
    this.wasVisited = false;
  }

  public Field(String name, int mapID) {
    this.name = name;
    this.enemy = null;
    this.item = null;
    this.mapID = mapID;
    this.wasVisited = false;
  }

  public Field(String name, Item item, int mapID) {
    this.name = name;
    this.enemy = null;
    this.item = item;
    this.mapID = mapID;
    this.wasVisited = false;
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

  public boolean wasVisited() {
    return wasVisited;
  }

  public void setWasVisited(boolean wasVisited) {
    this.wasVisited = wasVisited;
  }
}
