package current.classes;

import current.classes.templates.Character;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 23:29.
 */
public class Enemy extends Character {

  private boolean isBoss;
  private int mapID;

  /**
   * constructor method to create a new enemy object.
   *
   * @param name the String name of the enemy.
   * @param health int health.
   * @param weapon Item weapon for the enemy.
   * @param pos Position on the field.
   * @param isBoss boolean true if the enemy is a boss.
   * @param mapID int value to determine the corresponding map.
   */
  public Enemy(String name, int health, Item weapon, Position pos, boolean isBoss, int mapID) {
    super(name, health, weapon, pos);
    this.isBoss = isBoss;
    this.mapID = mapID;
  }

  // Getter and setter methods.
  public boolean isBoss() {
    return isBoss;
  }

  public void setBoss(boolean boss) {
    isBoss = boss;
  }

  public int getMapID() {
    return mapID;
  }

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }
}
