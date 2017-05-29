package depr.newVersion.classes;


import static depr.newVersion.oldCreators.FieldCreator.createAdaptedField;

import depr.newVersion.meta.GameConstants.Difficulty;
import depr.newVersion.meta.Position;
import depr.newVersion.newCreators.FieldCreator;
import depr.newVersion.oldCreators.ConsumableCreator;
import depr.newVersion.oldCreators.EnemyCreator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 02.05.2017, 21:26.
 */
public class Map {

  private Field[][] fields;
  private Position start;
  private int mapID;
  private boolean mapDefeated;
  private int levelOfPlayerOnStart;
  private Map dungeon;

  /**
   * constructor method to create an empty map.
   *
   * @param width the int width for the map.
   * @param height int height.
   * @param difficulty Difficulty for the map.
   */
  public Map(int width, int height, Difficulty difficulty) {
    this.fields = new Field[width][height];
    this.start = new Position(randInt(0, width - 1), randInt(0, height - 1));
    this.mapDefeated = false;
    this.mapID = 1;
    this.levelOfPlayerOnStart = 1;
    initializeTheFieldsEmpty(false);
    addEnemiesAndItems(difficulty);
    fields[start.x][start.y] = new Field("grass field", mapID);
    this.dungeon = null;
  }

  /**
   * constructor method to create an empty map.
   *
   * @param width the int width for the map.
   * @param height int height.
   * @param difficulty Difficulty for the map.
   * @param mapID the int mapID to keep track of the number of maps that have been played already.
   * @param playerLvlOnStart int to determine the user level on map creation.
   */
  public Map(int width, int height, Difficulty difficulty, int mapID, int playerLvlOnStart) {
    this.fields = new Field[width][height];
    this.start = new Position(randInt(0, width - 1), randInt(0, height - 1));
    this.mapDefeated = false;
    this.mapID = mapID;
    this.levelOfPlayerOnStart = playerLvlOnStart;
    initializeTheFieldsEmpty(false);
    addEnemiesAndItems(difficulty);
    fields[start.x][start.y] = new Field("grass field", mapID);
    this.dungeon = null;
  }

  /**
   * constructor method to create an empty dungeon map.
   *
   * @param width the int width for the map.
   * @param height int height.
   * @param diff Difficulty for the map.
   * @param mapID the int mapID to keep track of the number of maps that have been played already.
   * @param playerLvl int to determine the user level on map creation.
   * @param isDungeon boolean true if map is dungeon, false if not.
   */
  public Map(int width, int height, Difficulty diff, int mapID, int playerLvl, boolean isDungeon) {
    if (isDungeon) {
      this.fields = new Field[width][height];
      this.start = new Position(randInt(0, width - 1), randInt(0, height - 1));
      this.mapDefeated = false;
      this.mapID = mapID;
      this.levelOfPlayerOnStart = playerLvl;
      initializeTheFieldsEmpty(isDungeon);
      addEnemiesAndItemsForDungeon(diff);
    } else {
      new Map(width, height, diff, mapID, playerLvl);
    }
  }

  /**
   * method to create a random integer within given bounds.
   * the bounds are inclusive, so the random number can be the bound itself.
   * this method is the preferred standard after java 1.7.
   *
   * @param min int minimum value.
   * @param max int maximum value.
   * @return the randomly generated int.
   */
  private static int randInt(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  /**
   * method to randomly add enemies and items to the dungeon map respectively to the difficulty.
   *
   * @param difficulty the Difficulty chosen by the player.
   */
  private void addEnemiesAndItemsForDungeon(Difficulty difficulty) {
    int numberOfFields = fields[0].length * fields.length;
    switch (difficulty) {
      case Baby:
        numberOfFields /= 3;    // up to 33 percent enemies/items.
        break;
      case VeryEasy:
        numberOfFields /= 2;    // up to 50 percent enemies/items.
        break;
      case Easy:
        numberOfFields -= (numberOfFields / 4);    // up to 75 percent enemies/items.
        break;
      case Regular:
        numberOfFields -= (numberOfFields / 5);    // up to 80 percent enemies/items.
        break;
      case Tough:
        numberOfFields -= (numberOfFields / 10);    // up to 90 percent enemies/items.
        break;
      case Hard:
        numberOfFields -= (numberOfFields / 20);    // up to 95 percent enemies/items.
        break;
      case Extreme:
      case Nightmare:
        // up to 100 percent enemies/items, except for the starting point.
        break;
      default:
        numberOfFields -= (numberOfFields / 4);    // up to 75 percent enemies/items.
        break;
    }
    for (int i = 0; i < numberOfFields; i++) {
      fields[randInt(0, fields.length - 1)][randInt(0, fields[0].length - 1)] =
          FieldCreator.createDungeonField(mapID, levelOfPlayerOnStart, randInt(0, 2));
    }
  }

  /**
   * method to add items and enemies to the map according to the chosen difficulty.
   *
   * @param difficulty the Difficulty.
   */
  private void addEnemiesAndItems(Difficulty difficulty) {
    int numberOfFields = fields[0].length * fields.length;
    switch (difficulty) {
      case Baby:
        numberOfFields /= 3;    // 33 percent enemies/items.
        break;
      case VeryEasy:
        numberOfFields /= 2;    // 50 percent enemies/items.
        break;
      case Easy:
        numberOfFields -= (numberOfFields / 4);    // 75 percent enemies/items.
        break;
      case Regular:
        numberOfFields -= (numberOfFields / 5);    // 80 percent enemies/items.
        break;
      case Tough:
        numberOfFields -= (numberOfFields / 10);    // 90 percent enemies/items.
        break;
      case Hard:
        numberOfFields -= (numberOfFields / 20);    // 95 percent enemies/items.
        break;
      case Extreme:
      case Nightmare:
        // 100 percent enemies/items, except for the starting point.
        break;
      default:
        numberOfFields -= (numberOfFields / 4);    // 75 percent enemies/items.
        break;
    }
    for (int i = 0; i < numberOfFields; i++) {
      fields[randInt(0, fields.length - 1)][randInt(0, fields[0].length - 1)] =
          createAdaptedField(mapID, levelOfPlayerOnStart);
    }
    // Creating and adding the boss enemy.
    Position boss = new Position(randInt(0, this.fields.length),
        randInt(0, this.fields[0].length));
    if (boss.x == start.x && boss.y == start.y) {
      // To avoid that the boss is on our starting field.
      boss.x = randInt(0, this.fields.length - 1);
      boss.y = randInt(0, this.fields[0].length - 1);
    }
    Enemy bossEnemy = EnemyCreator.createBossEnemy(difficulty, levelOfPlayerOnStart, mapID);
    fields[boss.x][boss.y].setName("giant burned field");
    fields[boss.x][boss.y].setEnemy(bossEnemy);
    fields[boss.x][boss.y].setItem(new Item("dragons healing potion", levelOfPlayerOnStart * 100));
  }

  /**
   * method to initialize the fields array with all empty fields.
   *
   * @param isDungeonMap boolean true if the map is a dungeon, false else.
   */
  private void initializeTheFieldsEmpty(boolean isDungeonMap) {
    for (int y = 0; y < fields[0].length; y++) {
      for (int x = 0; x < fields.length; x++) {
        if (isDungeonMap) {
          fields[x][y] = FieldCreator.createDungeonField(mapID, levelOfPlayerOnStart,
              FieldCreator.EMPTY);
        } else {
          fields[x][y] = FieldCreator.createEmptyField(mapID);
        }
      }
    }
  }

  /**
   * method to respawn the boss on a given map if the user decides to run away from it.
   *
   * @param bossEnemy the boss enemy.
   * @param xPos the x position where the enemy was first encountered.
   * @param yPos y position.
   */
  public void respawnBoss(Enemy bossEnemy, int xPos, int yPos) {
    // Creating the new position for the boss.
    int newX = randInt(0, fields.length - 1);
    int newY = randInt(0, fields[0].length - 1);
    if (xPos == newX && yPos == newY) {   // Second call on the method if the position is the same.
      respawnBoss(bossEnemy, xPos, yPos);
    } else {
      fields[newX][newY] = new Field("burned grass field", bossEnemy,
          ConsumableCreator.createConsumable(), mapID);
    }
  }

  // Getter and setter methods.
  public Field[][] getFields() {
    return fields;
  }

  public void setFields(Field[][] fields) {
    this.fields = fields;
  }

  public Position getStart() {
    return start;
  }

  public void setStart(Position start) {
    this.start = start;
  }

  public boolean isMapDefeated() {
    return mapDefeated;
  }

  public void setMapDefeated(boolean mapDefeated) {
    this.mapDefeated = mapDefeated;
  }

  public int getMapID() {
    return mapID;
  }

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  public Map getDungeon() {
    return dungeon;
  }

  public void setDungeon(Map dungeon) {
    this.dungeon = dungeon;
  }

  public Field getField(Position pos) {
    return fields[pos.x][pos.y];
  }

  public void setField(Field field, Position pos) {
    this.fields[pos.x][pos.y] = field;
  }

  public Position getBossPos() {
    for (int y = 0; y < fields[0].length; y++) {
      for (int x = 0; x < fields.length; x++) {
        if (fields[x][y].getEnemy().isBoss()) {
          return new Position(x, y);
        }
      }
    }
    return new Position(-1, -1);
  }
}
