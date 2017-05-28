package current.classes;

import current.classes.templates.Character;
import current.meta.Constants.Difficulty;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 22:50.
 */
public class Player extends Character {

  // Default values for the initialization.
  private static final int DEFAULT_STARTING_LEVEL = 1;
  private static final int DEFAULT_BAG_COUNT = 0;
  private static final int DEFAULT_KILL_COUNT = 0;
  private static final int DEFAULT_EXPERIENCE = 0;

  // Difficulty dependent values.
  private static final int BAG_SIZE_BABY = 5;
  private static final int BAG_SIZE_NOVICE = 4;
  private static final int BAG_SIZE_WARRIOR = 3;
  private static final int BAG_SIZE_GOD = 2;

  private Difficulty difficulty;
  private Item[] bag;
  private int bagCounter;
  private int level;
  private int killCount;
  private int experience;


  /**
   * constructor method to create a new player object.
   *
   * @param name the String name of the player.
   * @param health int health.
   * @param weapon Item weapon for the player.
   * @param pos Position on the field.
   * @param difficulty user selected Difficulty.
   */
  public Player(String name, int health, Item weapon, Position pos, Difficulty difficulty) {
    super(name, health, weapon, pos);
    this.level = DEFAULT_STARTING_LEVEL;
    this.bagCounter = DEFAULT_BAG_COUNT;
    this.killCount = DEFAULT_KILL_COUNT;
    this.experience = DEFAULT_EXPERIENCE;
    this.difficulty = difficulty;
    switch (difficulty) {
      case Baby:
        this.bag = new Item[BAG_SIZE_BABY];
        break;
      case Novice:
        this.bag = new Item[BAG_SIZE_NOVICE];
        break;
      case Warrior:
        this.bag = new Item[BAG_SIZE_WARRIOR];
        break;
      case God:
        this.bag = new Item[BAG_SIZE_GOD];
        break;
      default:
        this.bag = new Item[BAG_SIZE_NOVICE];
        break;
    }
  }

  // Getter and setter methods.
  public Item[] getBag() {
    return bag;
  }

  public int getBagCounter() {
    return bagCounter;
  }

  public int getLevel() {
    return level;
  }

  public int getKillCount() {
    return killCount;
  }

  public int getExperience() {
    return experience;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setBag(Item[] bag) {
    this.bag = bag;
  }

  public void setBagCounter(int bagCounter) {
    this.bagCounter = bagCounter;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setKillCount(int killCount) {
    this.killCount = killCount;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }
}
