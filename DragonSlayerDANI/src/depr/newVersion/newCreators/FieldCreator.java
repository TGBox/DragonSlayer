package depr.newVersion.newCreators;

import java.util.concurrent.ThreadLocalRandom;
import depr.newVersion.classes.Enemy;
import depr.newVersion.classes.Field;
import depr.newVersion.classes.Item;
import depr.newVersion.oldCreators.ConsumableCreator;
import depr.newVersion.oldCreators.EnemyCreator;
import depr.newVersion.oldCreators.WeaponCreator;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 09.05.2017, 20:42.
 */
public class FieldCreator {

  // Select values for createField method.
  public static final int ENEMY = 0;
  public static final int ITEM = 1;
  public static final int ENEMY_AND_ITEM = 2;
  public static final int EMPTY = 3;

  private static final String[] REGULAR_FIELD_NAMES = new String[]{"grass field", "forest field",
      "field with a river", "field with a few trees", "field with a small lake", "muddy field"};
  private static final String[] SPECIAL_FIELD_NAMES = new String[]{"enchanted forest field",
      "field with an entrance to a dungeon", "burned ground"};
  private static final String[] DUNGEON_FIELD_NAMES = new String[]{"rocky ground", "muddy ground",
      "treasure field", "dungeon exit"};

  /**
   * method to create a random empty field for a given map.
   * @param mapID int mapID.
   * @return the newly created field.
   */
  public static Field createEmptyField(int mapID){
    return new Field(REGULAR_FIELD_NAMES[rand(0, REGULAR_FIELD_NAMES.length - 1)], mapID);
  }

  /**
   * method to create a new random field for any given map.
   * @param mapID the mapID int.
   * @param playerLvl the player level as an int.
   * @param select int to determine, what kind of field it shall be; with enemy, item, or both.
   * @return the field.
   */
  public static Field createField(int mapID, int playerLvl, int select){
    Field field;
    Enemy enemy;
    Item item;
    switch (select){
      case ENEMY:
        enemy = EnemyCreator.createEnemy(mapID, playerLvl);
        field = createEmptyField(mapID);
        field.setEnemy(enemy);
        return field;
      case ITEM:
        if(ThreadLocalRandom.current().nextBoolean()){
          item = WeaponCreator.createRandomWeapon();
        } else {
          item = ConsumableCreator.createConsumable();
        }
        field = createEmptyField(mapID);
        field.setItem(item);
        return field;
      case ENEMY_AND_ITEM:
        if(ThreadLocalRandom.current().nextBoolean()){
          item = WeaponCreator.createRandomWeapon();
        } else {
          item = ConsumableCreator.createConsumable();
        }
        enemy = EnemyCreator.createEnemy(mapID, playerLvl);
        field = createEmptyField(mapID);
        field.setEnemy(enemy);
        field.setItem(item);
        return field;
      default:
        enemy = EnemyCreator.createEnemy(mapID, playerLvl);
        field = createEmptyField(mapID);
        field.setEnemy(enemy);
        return field;
    }
  }

  /**
   * method to create a new dungeon field.
   * does not include the dungeon exit field, that has to be added manually for the dungeon maps.
   * @param mapID the int mapID.
   * @param playerLvl int player level.
   * @param select select int to determine if the field shall be with enemy, item, or both.
   * @return the dungeon field.
   */
  public static Field createDungeonField(int mapID, int playerLvl, int select){
    Field field;
    Enemy enemy;
    Item item;
    switch (select){
      case ENEMY:
        enemy = EnemyCreator.createEnemy(mapID, playerLvl);
        field = new Field(DUNGEON_FIELD_NAMES[rand(0, DUNGEON_FIELD_NAMES.length - 2)], mapID);
        field.setEnemy(enemy);
        return field;
      case ITEM:
        if(ThreadLocalRandom.current().nextBoolean()){
          item = WeaponCreator.createRandomWeapon();
        } else {
          item = ConsumableCreator.createConsumable();
        }
        field = new Field(DUNGEON_FIELD_NAMES[rand(0, DUNGEON_FIELD_NAMES.length - 2)], mapID);
        field.setItem(item);
        return field;
      case ENEMY_AND_ITEM:
        if(ThreadLocalRandom.current().nextBoolean()){
          item = WeaponCreator.createRandomWeapon();
        } else {
          item = ConsumableCreator.createConsumable();
        }
        enemy = EnemyCreator.createEnemy(mapID, playerLvl);
        field = new Field(DUNGEON_FIELD_NAMES[rand(0, DUNGEON_FIELD_NAMES.length - 2)], mapID);
        field.setEnemy(enemy);
        field.setItem(item);
        return field;
      case EMPTY:
        return new Field(DUNGEON_FIELD_NAMES[rand(0, DUNGEON_FIELD_NAMES.length - 1)], mapID);
      default:
        enemy = EnemyCreator.createEnemy(mapID, playerLvl);
        field = new Field(DUNGEON_FIELD_NAMES[rand(0, DUNGEON_FIELD_NAMES.length - 2)], mapID);
        field.setEnemy(enemy);
        return field;
    }
  }

  /**
   * method to create a random integer within given bounds.
   * the bounds are inclusive, so the random number can be the bound itself.
   * this method is the preferred standard after java 1.7.
   * @param min int minimum value.
   * @param max int maximum value.
   * @return the randomly generated int.
   */
  private static int rand(int min, int max){
      return ThreadLocalRandom.current().nextInt(min, max + 1);
  }
}
