package depr.newVersion.newCreators;

import depr.newVersion.classes.Item;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 04.05.2017, 23:51.
 */
public class ToolCreator {

    /**
     * method to create a new item object that can be used in the game to advance to the next map.
     * @param mapID the mapID int of the map that is being opened with the key. will be the toolID of the item.
     * @return the key item.
     */
    public static Item createKeyForNextMap(int mapID){
        return new Item("key to map #" + mapID, "this key is needed to advance to map " + mapID, mapID);
    }

    /**
     * method to create a new item object that can be used in the game to open a certain crate.
     * @param crateID int id of the crate that is being opened with the key. will be the toolID of the item.
     * @return the key item.
     */
    public static Item createKeyForCrate(int crateID){
        return new Item("key for crate #" + crateID, "this key is needed to open the crate " + crateID,
                crateID);
    }

    /**
     * method to create and return an experience booster object.
     * @param gainedXP the experience gained by consuming this item.
     * @param boosterID the int id that will be user as the item's toolID.
     * @return the xp booster item.
     */
    public static Item createXPBooster(int gainedXP, int boosterID){
        return new Item("Experience booster", "This magical potion grants "
            + "you visions of fallen war heros,\nwhich boosts your experience by " +
            gainedXP + " points.", boosterID);
    }

    /**
     * method to create and return an health booster object.
     * @param gainedMaxHealth the max health bonus gained by consuming this item.
     * @param boosterID the int id that will be user as the item's toolID.
     * @return the health booster item.
     */
    public static Item createMaxHealthBooster(int gainedMaxHealth, int boosterID){
        return new Item("Maximum health booster", "This magical potion allows "
            + "you to rise your maximum health by " + gainedMaxHealth + " points.", boosterID);
    }

    /**
     * method to create and return an accuracy booster object.
     * @param gainedAccuracyInPercent the accuracy bonus gained by consuming this item.
     * @param boosterID the int id that will be user as the item's toolID.
     * @return the accuracy booster item.
     */
    public static Item createAccuracyBooster(int gainedAccuracyInPercent, int boosterID){
        return new Item("Accuracy Booster", "This magical potion allows you to "
            + "rise the accuracy of your current equipped weapon by " + gainedAccuracyInPercent
            + " percent.", boosterID);
    }

    /**
     * method to create a new quest object.
     * (could have been left out, but to keep everything consistent, I created it anyway.
     * @param name the String name for the quest item.
     * @param description the item description String.
     * @param questID the questID; will be used as the toolID of the item.
     * @return the new quest item object.
     */
    public static Item createQuestItem(String name, String description, int questID){
        return new Item(name, description, questID);
    }
}
