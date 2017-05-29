package current.classes;

import current.classes.templates.Thing;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 22:34.
 */
public class Item extends Thing {

  public static final int DEFAULT_INT = 0;
  public static final double DEFAULT_DOUBLE = 0.00D;
  public static final String DEFAULT_STRING = null;

  // Weapon additional variables.
  private int attack;
  private double accuracy;

  // Health potion variables.
  private int healing;

  // Tool variables.
  private String description;
  private int toolID;

  /**
   * constructor method to create a new weapon object.
   *
   * @param name the String name of the weapon.
   * @param attack int attack force of the weapon.
   * @param accuracy double accuracy value for the weapon.
   */
  public Item(String name, int attack, double accuracy) {
    super(name);
    this.attack = attack;
    this.accuracy = accuracy;
    this.healing = DEFAULT_INT;
    this.description = DEFAULT_STRING;
    this.toolID = DEFAULT_INT;
  }

  /**
   * constructor method to create a new healing potion item.
   *
   * @param name String name of the potion.
   * @param healing int healing value.
   */
  public Item(String name, int healing) {
    super(name);
    this.healing = healing;
    this.attack = DEFAULT_INT;
    this.accuracy = DEFAULT_DOUBLE;
    this.description = DEFAULT_STRING;
    this.toolID = DEFAULT_INT;
  }

  /**
   * constructor method to create a new tool item.
   *
   * @param name String name of the item.
   * @param description String description for the item.
   * @param toolID int toolID that will be used to determine the usage of the item.
   */
  public Item(String name, String description, int toolID) {
    super(name);
    this.description = description;
    this.toolID = toolID;
    this.attack = DEFAULT_INT;
    this.accuracy = DEFAULT_DOUBLE;
    this.healing = DEFAULT_INT;
  }

  public int getHealing() {
    return healing;
  }

  // Getter and setter methods.
  public void setHealing(int healing) {
    this.healing = healing;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public double getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(double accuracy) {
    this.accuracy = accuracy;
  }

  public int getToolID() {
    return toolID;
  }

  public void setToolID(int toolID) {
    this.toolID = toolID;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
