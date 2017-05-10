package newVersion.oldCreators;

import newVersion.classes.Enemy;
import newVersion.classes.Field;
import newVersion.classes.Item;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 23:39.
 */
public class FieldCreator {

    private static final String[] FIELD_NAMES = new String[]{"grass field", "muddy field",
            "field with with some trees", "forest field", "special field"};

    /**
     * method to create an empty field.
     * @param mapID int to determine the map for the field.
     * @return the empty field.
     */
    public static Field createEmptyField(int mapID){
        int fieldType = randomIntInRange(0, 3);
        return new Field(FIELD_NAMES[fieldType], mapID);
    }

    /**
     * method to create a custom field.
     * @param fieldName the name of the field.
     * @param enemy the Enemy for the field.
     * @param item the item for the field.
     * @param mapID int to determine the map for the field.
     * @return the custom field.
     */
    public static Field createCustomField(String fieldName, Enemy enemy, Item item, int mapID){
        return new Field(fieldName, enemy, item, mapID);
    }

    /**
     * method to create a new field that contains an enemy.
     * @param createEasierEnemy boolean true, if the enemy should be easy, false for a random enemy.
     * @param mapID int to determine the map for the field.
     * @return the new field with the enemy on it.
     */
    public static Field createFieldWithEnemy(boolean createEasierEnemy, int mapID){
        Enemy enemy;
        int fieldType = randomIntInRange(0, 3);
        if(createEasierEnemy){
            enemy = EnemyCreator.createRestrictedEnemy(4, 4, 1,
                    true, mapID);
        } else {
            enemy = EnemyCreator.createRandomEnemy(mapID);
        }
        return new Field(FIELD_NAMES[fieldType], enemy, null, mapID);
    }

    /**
     * method to create a random field with a random item on it.
     * @param mapID int to determine the map for the field.
     * @return the field with the item on it.
     */
    public static Field createFieldWithItem(int mapID){
        int fieldType = randomIntInRange(0, 3);
        if(ThreadLocalRandom.current().nextBoolean()){
            return new Field(FIELD_NAMES[fieldType], ConsumableCreator.createConsumable(), mapID);
        } else {
            return new Field(FIELD_NAMES[fieldType], WeaponCreator.createRandomWeapon(), mapID);
        }

    }

    /**
     * method to create a field with an enemy and an item on it.
     * @param createEasierEnemy boolean true, if the enemy should be easier, false for random enemy.
     * @param mapID int to determine the map for the field.
     * @return the new field.
     */
    public static Field createFieldWithEnemyAndItem(boolean createEasierEnemy, int mapID){
        Field field = createFieldWithEnemy(createEasierEnemy, mapID);
        if(ThreadLocalRandom.current().nextBoolean()){
            field.setItem(WeaponCreator.createRandomWeapon());
        } else {
            field.setItem(ConsumableCreator.createConsumable());
        }
        return field;
    }

    /**
     * method to create an adapted field that changes the occurring enemies and weapons according to the player's level.
     * @param mapID the int mapID.
     * @param levelOfPlayer the int level of the player at the start.
     * @return the adapted field that fits the requirements.
     */
    public static Field createAdaptedField(int mapID, int levelOfPlayer){
        int decider = randomIntInRange(0, 2);
        Enemy enemy = null;
        Item item = null;
        if(mapID * levelOfPlayer <= 5){
            switch (decider){
                case 0:     // only enemy.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        enemy = EnemyCreator.createRestrictedEnemy(9, 7, 2, true, mapID);
                    } else {
                        enemy = EnemyCreator.createRestrictedEnemy(9, 7, 2, false, mapID);
                    }
                    break;
                case 1:     // enemy and item.
                    enemy = EnemyCreator.createRestrictedEnemy(8, 6, 2, true, mapID);
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                case 2:     // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                default:    // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
            }
        } else if(mapID * levelOfPlayer > 5 && mapID * levelOfPlayer <= 15){
            switch (decider){
                case 0:     // only enemy.
                    enemy = EnemyCreator.createRestrictedEnemy(11, 8, 3, false, mapID);
                    break;
                case 1:     // enemy and item.
                    enemy = EnemyCreator.createRestrictedEnemy(11, 15, 3, true, mapID);
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                case 2:     // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                default:    // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
            }
        } else if(mapID * levelOfPlayer > 15 && mapID * levelOfPlayer <= 25){
            switch (decider){
                case 0:     // only enemy.
                    enemy = EnemyCreator.createRandomEnemy(mapID);
                    break;
                case 1:     // enemy and item.
                    enemy = EnemyCreator.createRandomEnemy(mapID);
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                case 2:     // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                default:    // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
            }
        } else {            // endgame! enemies need to be hard!
            switch (decider){
                case 0:     // only enemy.
                    enemy = EnemyCreator.createHardEnemy();
                    break;
                case 1:     // enemy and item.
                    enemy = EnemyCreator.createHardEnemy();
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                case 2:     // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
                default:    // only item.
                    if(ThreadLocalRandom.current().nextBoolean()){
                        item = ConsumableCreator.createConsumable();
                    } else {
                        item = WeaponCreator.createRandomWeapon();
                    }
                    break;
            }
        }
        return createCustomField(FIELD_NAMES[randomIntInRange(0, FIELD_NAMES.length - 1)], enemy, item, mapID);
    }

    /**
     * method to create a random integer within given bounds.
     * the bounds are inclusive, so the random number can be the bound itself.
     * this method is the preferred standard after java 1.7.
     * @param min int minimum value.
     * @param max int maximum value.
     * @return the randomly generated int.
     */
    private static int randomIntInRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
