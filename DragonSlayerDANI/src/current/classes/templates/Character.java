package current.classes.templates;

import current.classes.Item;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 22:32.
 */
public abstract class Character extends Thing {

  private int health;
  private int maxHealth;
  private Item weapon;
  private Position pos;

  /**
   * constructor method to create a new character object.
   *
   * @param name the String name of the character.
   * @param health int health.
   * @param weapon Item weapon for the character.
   * @param pos Position on the field.
   */
  public Character(String name, int health, Item weapon, Position pos) {
    super(name);
    this.health = health;
    this.maxHealth = health;
    this.weapon = weapon;
    this.pos = pos;
  }

  // Getter and setter methods.
  public Item getWeapon() {
    return weapon;
  }

  public int getHealth() {
    return health;
  }

  public Position getPos() {
    return pos;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public void setPos(Position pos) {
    this.pos = pos;
  }

  public void setWeapon(Item weapon) {
    this.weapon = weapon;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }
}
