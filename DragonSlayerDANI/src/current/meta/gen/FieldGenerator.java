package current.meta.gen;

import static current.meta.Constants.DungeonFieldNames;
import static current.meta.Constants.asString;
import static current.meta.Constants.getRandomEnum;

import current.classes.Enemy;
import current.classes.Field;
import current.classes.Item;
import current.meta.Constants;
import current.meta.Constants.Difficulty;
import current.meta.Constants.SpecialFieldNames;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 23:09.
 */
public class FieldGenerator {

  // Select values for createField method.
  public static final int ENEMY = 0;
  public static final int ITEM = 1;
  public static final int ENEMY_AND_ITEM = 2;
  public static final int EMPTY = 3;

  /**
   * method to create a random empty field for a given map.
   *
   * @param mapID int mapID.
   * @return the newly created field.
   */
  public static Field createEmptyField(int mapID) {
    return new Field(asString(getRandomEnum("FieldNames")), null, mapID);
  }

  /**
   * method to create a new random field for any given map.
   *
   * @param mapID the mapID int.
   * @param playerLvl the player level as an int.
   * @param select int to determine, what kind of field it shall be; with enemy, item, or both.
   * @return the field.
   */
  public static Field createField(int mapID, int playerLvl, int select) {
    Field field;
    Enemy enemy;
    Item item;
    switch (select) {
      case ENEMY:
        enemy = EnemyGenerator.createEnemy(mapID, playerLvl);
        field = createEmptyField(mapID);
        field.setEnemy(enemy);
        return field;
      case ITEM:
        if (ThreadLocalRandom.current().nextBoolean()) {
          item = WeaponGenerator.createRandomWeapon();
        } else {
          item = ConsumableGenerator.createConsumable();
        }
        field = createEmptyField(mapID);
        field.setItem(item);
        return field;
      case ENEMY_AND_ITEM:
        if (ThreadLocalRandom.current().nextBoolean()) {
          item = WeaponGenerator.createRandomWeapon();
        } else {
          item = ConsumableGenerator.createConsumable();
        }
        enemy = EnemyGenerator.createEnemy(mapID, playerLvl);
        field = createEmptyField(mapID);
        field.setEnemy(enemy);
        field.setItem(item);
        return field;
      default:
        enemy = EnemyGenerator.createEnemy(mapID, playerLvl);
        field = createEmptyField(mapID);
        field.setEnemy(enemy);
        return field;
    }
  }

  /**
   * method to create a new dungeon field.
   * does not include the dungeon exit field, that has to be added manually for the dungeon maps.
   *
   * @param mapID the int mapID.
   * @param playerLvl int player level.
   * @param select select int to determine if the field shall be with enemy, item, or both.
   * @return the dungeon field.
   */
  public static Field createDungeonField(int mapID, int playerLvl, int select) {
    Field field;
    Enemy enemy;
    Item item;
    switch (select) {
      case ENEMY:
        enemy = EnemyGenerator.createEnemy(mapID, playerLvl);
        field = new Field(asString(
            DungeonFieldNames.values()[rand(0, DungeonFieldNames.values().length - 2)]), enemy,
            null, mapID);
        return field;
      case ITEM:
        if (ThreadLocalRandom.current().nextBoolean()) {
          item = WeaponGenerator.createRandomWeapon();
        } else {
          item = ConsumableGenerator.createConsumable();
        }
        field = new Field(asString(
            DungeonFieldNames.values()[rand(0, DungeonFieldNames.values().length - 2)]), null,
            item, mapID);
        return field;
      case ENEMY_AND_ITEM:
        if (ThreadLocalRandom.current().nextBoolean()) {
          item = WeaponGenerator.createRandomWeapon();
        } else {
          item = ConsumableGenerator.createConsumable();
        }
        enemy = EnemyGenerator.createEnemy(mapID, playerLvl);
        field = new Field(asString(
            DungeonFieldNames.values()[rand(0, DungeonFieldNames.values().length - 2)]), enemy,
            item, mapID);
        return field;
      case EMPTY:
        return new Field(asString(
            DungeonFieldNames.values()[rand(0, DungeonFieldNames.values().length - 2)]), null,
            null, mapID);
      default:
        enemy = EnemyGenerator.createEnemy(mapID, playerLvl);
        field = new Field(asString(
            DungeonFieldNames.values()[rand(0, DungeonFieldNames.values().length - 2)]), enemy,
            null, mapID);
        return field;
    }
  }

  /**
   * method to create a new dungeon exit field.
   *
   * @param mapID int mapID.
   * @return the Field.
   */
  public static Field createDungeonExit(int mapID) {
    return new Field("field with an exit to the dungeon", mapID, mapID);
  }

  /**
   * method to create a new dungeon entrance field.
   *
   * @param mapID int mapID.
   * @return the new Field.
   */
  public static Field createDungeonEntrance(int mapID) {
    return new Field("field with an entrance to a dungeon", mapID, mapID);
  }

  /**
   * method to create a new boss field.
   *
   * @param mapID int mapID.
   * @param levelOfPlayer int level of the player.
   * @param difficulty Difficulty of the game.
   * @return the field that contains the boss.
   */
  public static Field createBossField(int mapID, int levelOfPlayer, Difficulty difficulty) {
    Enemy boss = EnemyGenerator.createBossEnemy(difficulty, levelOfPlayer, mapID);
    Item item = new Item("dragon's healing potion", 150);
    return new Field(Constants.asString(SpecialFieldNames.burned_ground), boss, item, mapID);
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
  private static int rand(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

}
