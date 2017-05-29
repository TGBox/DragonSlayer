package depr.old.text.newTextBasedVersion.classes;

import depr.old.text.newTextBasedVersion.creators.WeaponCreator;
import depr.old.text.newTextBasedVersion.meta.Difficulty;
import depr.old.text.newTextBasedVersion.meta.Gender;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 14:51.
 */
public class Character {

  private static final int HEALTH_EASY = 150;
  private static final int HEALTH_MEDIUM = 100;
  private static final int HEALTH_HARD = 75;

  private static final int HEALTH_BONUS_EASY = 50;
  private static final int HEALTH_BONUS_MEDIUM = 40;
  private static final int HEALTH_BONUS_HARD = 25;

  private static final int DEFAULT_BAG_COUNTER = 0;
  private static final int STRENGTH_EASY = 120;
  private static final int STRENGTH_MEDIUM = 100;
  private static final int STRENGTH_HARD = 80;
  private static final int DEFAULT_LEVEL = 1;
  private static final int DEFAULT_EXPERIENCE = 0;
  private static final int BAG_SIZE_EASY = 6;
  private static final int BAG_SIZE_MEDIUM = 5;
  private static final int BAG_SIZE_HARD = 4;

  private static final double EASY_XP_MULTIPLIER = 0.42;
  private static final double MEDIUM_XP_MULTIPLIER = 1.00;
  private static final double HARD_XP_MULTIPLIER = 1.42;

  private String name;
  private Gender gender;
  private Item weapon;
  private int health;
  private Item[] bag;
  private int bagCounter;
  private int xPos;
  private int yPos;
  private int strength;
  private int level;
  private int killCount;
  private int experience;
  private int startingHealth;

  /**
   * constructor method to create a new character object.
   *
   * @param name String name of the character.
   * @param gender the Gender.
   * @param difficulty Difficulty.
   * @param xPos the x coordinate on the map.
   * @param yPos the y coordinate on the map.
   */
  public Character(String name, Gender gender, Difficulty difficulty, int xPos, int yPos) {
    this.name = name;
    this.gender = gender;
    this.weapon = WeaponCreator.createStartingWeapon();
    switch (difficulty) {
      case EASY:
        this.health = HEALTH_EASY;
        this.bag = new Item[BAG_SIZE_EASY];
        this.strength = STRENGTH_EASY;
        break;
      case MEDIUM:
        this.health = HEALTH_MEDIUM;
        this.bag = new Item[BAG_SIZE_MEDIUM];
        this.strength = STRENGTH_MEDIUM;
        break;
      case HARD:
        this.health = HEALTH_HARD;
        this.bag = new Item[BAG_SIZE_HARD];
        this.strength = STRENGTH_HARD;
        break;
      default:
        this.health = HEALTH_MEDIUM;
        this.bag = new Item[BAG_SIZE_HARD];
        this.strength = STRENGTH_MEDIUM;
        break;
    }
    this.bagCounter = DEFAULT_BAG_COUNTER;
    this.xPos = xPos;
    this.yPos = yPos;
    this.experience = DEFAULT_EXPERIENCE;
    this.level = DEFAULT_LEVEL;
    this.killCount = 0;
    this.startingHealth = health;
  }

  /**
   * method to crop any given double value to two decimal places after the dot.
   *
   * @param value the double value that needs to be cropped.
   * @return the cropped value.
   */
  private static double cropDouble(double value) {
    value = Math.round(value * 100.00);
    return value / 100.00;
  }

  /**
   * method to enlarge the bag by one slot.
   */
  public void enlargeBag() {
    Item[] oldBag = bag;
    bag = new Item[oldBag.length + 1];
    for (int i = 0; i < oldBag.length; i++) {
      bag[i] = oldBag[i];
    }
  }

  /**
   * method to add an item to the bag.
   *
   * @param item the item that needs to be added.
   */
  public boolean addItem(Item item) {
    if (bagCounter < bag.length - 1) {
      bag[bagCounter] = item;
      bagCounter++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * method to delete an item from the bag at a given index.
   *
   * @param index the int index.
   * @return boolean true if the item was deleted, false if not.
   */
  public boolean deleteItem(int index) {
    if (index < 0 || index > bagCounter) {
      return false;
    } else {
      if (bag[index] != null) {
        bag[index] = null;
      }
      for (int i = index; i < bagCounter; i++) {
        bag[i] = bag[i + 1];
      }
      bagCounter--;
      return true;
    }
  }

  /**
   * method to display the contents from the players bag.
   */
  public void displayBag() {
    if (this.getBagCounter() == 0) {
      System.out.println("Your bag is empty. " + (bag.length - bagCounter) + " free slots left.");
    } else {
      if (this.getBagCounter() == 1) {
        System.out.println(
            "Your bag contains 1 item. " + (bag.length - bagCounter) + " free slots left.\n");
        System.out.print("1: " + this.getBag()[0].getName());
        if (this.getBag()[0].getAttack() != 0) {
          System.out.print("\n\t\t\t\t\t\t\t\t\t- attack: " + this.getBag()[0].getAttack() + "\n");
          double accuracyToPrint = cropDouble((1.00 - this.getBag()[0].getAccuracy()) * 100);
          System.out.print("\t\t\t\t\t\t\t\t\t- accuracy: " + accuracyToPrint + " %\n");
        } else {
          System.out
              .print("\n\t\t\t\t\t\t\t\t\t- healing: " + this.getBag()[0].getHealing() + "\n");
        }
      } else {
        System.out.println("Your bag contains " + this.getBagCounter() + " items. " +
            (bag.length - bagCounter) + " free slots left.\n");
        for (int i = 0; i < this.getBagCounter(); i++) {
          if (this.getBag()[i] == null) {
            System.out.println((i + 1) + ": ");
          } else {
            System.out.print((i + 1) + ": " + this.getBag()[i].getName());
            if (this.getBag()[i].getAttack() != 0) {
              System.out
                  .print("\n\t\t\t\t\t\t\t\t\t- attack: " + this.getBag()[i].getAttack() + "\n");
              double accuracyToPrint = cropDouble((1.00 - this.getBag()[i].getAccuracy()) * 100);
              System.out.print("\t\t\t\t\t\t\t\t\t- accuracy: " + accuracyToPrint + " %\n");
            } else {
              System.out
                  .print("\n\t\t\t\t\t\t\t\t\t- healing: " + this.getBag()[i].getHealing() + "\n");
            }
          }
        }
      }
    }
    System.out.println("");
  }

  /**
   * method to heal a character by a given amount of healing.
   * prevents the character from gaining to much health.
   *
   * @param amount int amount of healing from a healing item.
   * @return int the amount that was actually healed.
   */
  public int heal(int amount) {
    int beforeHealing = this.health;
    if ((beforeHealing + amount) > this.startingHealth) {
      this.health = startingHealth;
    } else {
      this.health += amount;
    }
    return this.health - beforeHealing;
  }

  /**
   * method to handle the leveling of the character.
   *
   * @param enemy the enemy that was defeated by the character.
   */
  public void levelUp(Enemy enemy, Difficulty difficulty) {
    int levelUpValue = enemy.getStartingHealth();
    switch (difficulty) {
      case EASY:
        levelUpValue *= EASY_XP_MULTIPLIER;
        break;
      case MEDIUM:
        levelUpValue *= MEDIUM_XP_MULTIPLIER;
        break;
      case HARD:
        levelUpValue *= HARD_XP_MULTIPLIER;
        break;
      default:
        break;
    }
    this.setExperience(this.getExperience() + levelUpValue);
    if (this.getExperience() >= 25 && this.getExperience() < 50 && this.getLevel() != 2) {
      this.setLevel(2);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 50 && this.getExperience() < 100 && this.getLevel() != 3) {
      this.setLevel(3);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 100 && this.getExperience() < 200 && this.getLevel() != 4) {
      this.setLevel(4);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 200 && this.getExperience() < 350 && this.getLevel() != 5) {
      this.setLevel(5);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 350 && this.getExperience() < 550 && this.getLevel() != 6) {
      this.setLevel(6);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 550 && this.getExperience() < 850 && this.getLevel() != 7) {
      this.setLevel(7);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 850 && this.getExperience() < 1000 && this.getLevel() != 8) {
      this.setLevel(8);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 1000 && this.getExperience() < 1400 && this.getLevel() != 9) {
      this.setLevel(9);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 1400 && this.getExperience() < 2000 && this.getLevel() != 10) {
      this.setLevel(10);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
    }
    if (this.getExperience() >= 2000 && this.getLevel() != 11) {
      this.setLevel(11);
      this.setStartingHealth(calculateHealth(difficulty));
      this.setHealth(this.getStartingHealth());
      System.out.println(
          "Congratulations! " + this.getName() + " has reached level " + this.getLevel() + "!");
      System.out.println(
          "Your stats have improved. Maximum health now at " + this.getStartingHealth() + ".");
      System.out.println("\n\nBy reaching level 11, you are now a true Dragon Slayer and have " +
          "therefore won the game!");
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.exit(0);
    }
  }

  /**
   * method to calculate the new character health on level up.
   *
   * @param difficulty the Difficulty of the game.
   * @return the int value for the new health.
   */
  private int calculateHealth(Difficulty difficulty) {
    int start;
    switch (difficulty) {
      case EASY:
        start = HEALTH_EASY;
        start += this.getLevel() * HEALTH_BONUS_EASY;
        break;
      case MEDIUM:
        start = HEALTH_MEDIUM;
        start += this.getLevel() * HEALTH_BONUS_MEDIUM;
        break;
      case HARD:
        start = HEALTH_HARD;
        start += this.getLevel() * HEALTH_BONUS_HARD;
        break;
      default:
        start = HEALTH_MEDIUM;
        start += this.getLevel() * HEALTH_BONUS_MEDIUM;
        break;
    }
    return start;
  }

  // Getter and setter methods.
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Item getWeapon() {
    return weapon;
  }

  public void setWeapon(Item weapon) {
    this.weapon = weapon;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public Item[] getBag() {
    return bag;
  }

  public void setBag(Item[] bag) {
    this.bag = bag;
  }

  public int getBagCounter() {
    return bagCounter;
  }

  public void setBagCounter(int bagCounter) {
    this.bagCounter = bagCounter;
  }

  public int getxPos() {
    return xPos;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public int getStrength() {
    return strength;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getKillCount() {
    return killCount;
  }

  public void setKillCount(int killCount) {
    this.killCount = killCount;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public int getStartingHealth() {
    return startingHealth;
  }

  public void setStartingHealth(int startingHealth) {
    this.startingHealth = startingHealth;
  }
}
