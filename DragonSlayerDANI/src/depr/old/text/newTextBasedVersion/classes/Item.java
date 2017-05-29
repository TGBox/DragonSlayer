package depr.old.text.newTextBasedVersion.classes;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 14:54.
 */
public class Item {

  // Standard item variables.
  private String name;

  // Weapon additional variables.
  private int attack;
  private double accuracy;

  // Health potion variables.
  private int healing;

  /**
   * constructor method to create a new item object.
   *
   * @param name the String name for the item.
   */
  public Item(String name) {
    this.name = name;
  }

  /**
   * constructor method to create a new healing potion item.
   *
   * @param name the String name for the item.
   * @param healing the int healing value.
   */
  public Item(String name, int healing) {
    this.name = name;
    this.healing = healing;
    this.attack = 0;
    this.accuracy = 1.00;
  }

  /**
   * constructor method to create a weapon item.
   *
   * @param name String name for the item.
   * @param attack int attack force.
   * @param accuracy double accuracy for the weapon. (the lower, the better!)
   */
  public Item(String name, int attack, double accuracy) {
    this.name = name;
    this.healing = 0;
    this.attack = attack;
    this.accuracy = accuracy;
  }

  // Getter and Setter methods.
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHealing() {
    return healing;
  }

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
}
