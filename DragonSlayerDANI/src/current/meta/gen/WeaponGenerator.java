package current.meta.gen;

import static current.meta.Constants.ACCURACIES;
import static current.meta.Constants.WEAPON_ATTACK_VALUES;
import static current.meta.Constants.WEAPON_PREFIX_VALUES;
import static current.meta.Constants.asString;
import static current.meta.Constants.getEnumAt;
import static current.meta.Constants.getLength;

import current.classes.Item;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 20:48.
 */
public class WeaponGenerator {

  // Reward values.
  private static final String[] SIZE = new String[]{"tiny", "small", "regular", "large", "huge"};
  private static final double[] SIZE_VALUES = new double[]{0.42, 0.66, 1.00, 1.22, 1.42};

  private static final String[] COLOR = new String[]{"white", "gray", "black", "blue",
      "green", "red", "yellow", "orange"};
  private static final double[] COLOR_VALUES = new double[]{0.42, 0.54, 0.76, 1.11,
      1.23, 1.38, 1.42, 1.66};

  private static final String[] REWARD_WEAPON_NAMES = new String[]{"sword of the ancient",
      "club of a giant", "hammer of a god", "spear of the undead", "magical boxing gloves",
      "ritual dagger"};
  private static final int[] REWARD_WEAPON_BASE_ATTACKS = new int[]{100, 120, 140, 160, 180, 200};

  private static final double[] REWARD_WEAPON_ACCURACIES = new double[]{0.45, 0.50, 0.55};

  /**
   * method to create a new weapon item that can be used by NPCs as a reward.
   *
   * @return the created weapon item.
   */
  public static Item createRewardWeapon() {
    int index;
    String name = "";
    if (ThreadLocalRandom.current().nextBoolean()) {
      index = randInt(0, SIZE.length - 1);
      name += SIZE_VALUES[index] + " ";
    } else {
      index = randInt(0, COLOR.length - 1);
      name += COLOR_VALUES[index] + " ";
    }
    name += REWARD_WEAPON_NAMES[randInt(0, REWARD_WEAPON_NAMES.length - 1)];
    int attack = REWARD_WEAPON_BASE_ATTACKS[randInt(0, REWARD_WEAPON_BASE_ATTACKS.length - 1)];
    double accuracy = REWARD_WEAPON_ACCURACIES[randInt(0, REWARD_WEAPON_ACCURACIES.length - 1)];
    return new Item(name, attack, accuracy);
  }

  // Other weapon Stuff.
  /**
   * method to create a totally random weapon. might be heavily over- or underpowered.
   *
   * @return the new Weapon.
   */
  public static Item createRandomWeapon() {
    int prefix = randomIntInRange(0, getLength("WeaponPrefixes") - 1);
    int weapon = randomIntInRange(0, getLength("WeaponNames") - 1);
    int acc = randomIntInRange(0, ACCURACIES.length - 1);
    String name = asString(getEnumAt("WeaponPrefixes", prefix)) +
        " " + asString(getEnumAt("WeaponNames", weapon));
    int damage = (int) (WEAPON_ATTACK_VALUES[weapon] * WEAPON_PREFIX_VALUES[prefix]);
    double accuracy = ACCURACIES[acc];
    return new Item(name, damage, accuracy);
  }

  /**
   * method to create a weapon that is restricted in the randomness of the index selections for the
   * attributes. int values represent the maximum index for the arrays that hold the attributes.
   *
   * @param maxIndexPrefix the index maximum for the prefix Strings.
   * @param maxIndexName the index maximum for the weapon name Strings.
   * @param maxIndexAccuracy the index maximum for the accuracies array.
   */
  public static Item createRestrictedWeapon(int maxIndexPrefix, int maxIndexName,
      int maxIndexAccuracy) {
    if (maxIndexPrefix >= getLength("WeaponPrefixes") ||
        maxIndexName >= getLength("WeaponNames") ||
        maxIndexAccuracy >= ACCURACIES.length) {
      // If the input is beyond the bounds of the arrays, a very weak weapon with high accuracy is returned.
      return new Item("broken spoon", 4, 0.15);
    }
    int prefix = randomIntInRange(0, maxIndexPrefix);
    int weapon = randomIntInRange(0, maxIndexName);
    int acc = randomIntInRange(0, maxIndexAccuracy);
    String name = asString(getEnumAt("WeaponPrefixes", prefix)) +
        " " + asString(getEnumAt("WeaponNames", weapon));
    int damage = (int) (WEAPON_ATTACK_VALUES[weapon] * WEAPON_PREFIX_VALUES[prefix]);
    double accuracy = ACCURACIES[acc];
    return new Item(name, damage, accuracy);
  }

  /**
   * method to create a starting weapon for a new game.
   * returns a relatively weak weapon that has medium to high accuracy.
   *
   * @return the starting Weapon object.
   */
  public static Item createStartingWeapon() {
    int prefix = randomIntInRange(0, getLength("WeaponPrefixes") / 3);
    int weapon = randomIntInRange(0, getLength("WeaponNames") / 3);
    int acc = randomIntInRange(0, ACCURACIES.length / 2);
    String name = asString(getEnumAt("WeaponPrefixes", prefix)) +
        " " + asString(getEnumAt("WeaponNames", weapon));
    int damage = (int) (WEAPON_ATTACK_VALUES[weapon] * WEAPON_PREFIX_VALUES[prefix]);
    double accuracy = ACCURACIES[acc];
    return new Item(name, damage, accuracy);
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
  private static int randomIntInRange(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
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

}
