package depr.old.text.newTextBasedVersion.creators;

import depr.old.text.newTextBasedVersion.classes.Item;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 21:37.
 */
public class WeaponCreator {

  private static final String[] SUFFIX_STRINGS = new String[]{"useless", "broken", "deformed",
      "lightweight",
      "blunt", "regular", "aerodynamic", "sharpened", "razor-sharp", "hardened", "heavy", "ultra"};
  private static final double[] SUFFIX_MULTIPLIER = new double[]{0.35, 0.5, 0.6, 0.7,
      0.8, 1.0, 1.1, 1.19, 1.25, 1.4, 1.5, 1.7};

  private static final String[] WEAPON_NAMES = new String[]{"spoon", "stick", "fork", "stone",
      "knife", "hammer",
      "dagger", "tomahawk", "rapier", "club", "spear", "axe", "katana", "morning star", "sword",
      "longsword",
      "two-handed sword", "war hammer"};
  private static final int[] WEAPON_DAMAGE = new int[]{8, 13, 24, 35, 42, 48,
      59, 65, 72, 80, 85, 90, 104, 110, 122, 130,
      150, 166};

  // Smaller accuracy value == better hitting chance!!!
  private static final double[] ACCURACIES = new double[]{0.25, 0.30, 0.35, 0.40, 0.45, 0.50, 0.55,
      0.60, 0.65};

  /**
   * method to create a totally random weapon. might be heavily over- or underpowered.
   *
   * @return the new Weapon.
   */
  public static Item createRandomWeapon() {
    int suffix = randomIntInRange(0, SUFFIX_STRINGS.length - 1);
    int weapon = randomIntInRange(0, WEAPON_NAMES.length - 1);
    int acc = randomIntInRange(0, ACCURACIES.length - 1);
    String name = SUFFIX_STRINGS[suffix] + " " + WEAPON_NAMES[weapon];
    int damage = (int) (WEAPON_DAMAGE[weapon] * SUFFIX_MULTIPLIER[suffix]);
    double accuracy = ACCURACIES[acc];
    return new Item(name, damage, accuracy);
  }

  /**
   * method to create a weapon that is restricted in the randomness of the index selections for the
   * attributes. int values represent the maximum index for the arrays that hold the attributes.
   *
   * @param maxIndexSuffix the index maximum for the suffix Strings.
   * @param maxIndexName the index maximum for the weapon name Strings.
   * @param maxIndexAccuracy the index maximum for the accuracies array.
   */
  public static Item createRestrictedWeapon(int maxIndexSuffix, int maxIndexName,
      int maxIndexAccuracy) {
    if (maxIndexSuffix >= SUFFIX_STRINGS.length ||
        maxIndexName >= WEAPON_NAMES.length ||
        maxIndexAccuracy >= ACCURACIES.length) {
      // If the input is beyond the bounds of the arrays, a very weak weapon with high accuracy is returned.
      return new Item("broken spoon", 4, 0.15);
    }
    int suffix = randomIntInRange(0, maxIndexSuffix);
    int weapon = randomIntInRange(0, maxIndexName);
    int acc = randomIntInRange(0, maxIndexAccuracy);
    String name = SUFFIX_STRINGS[suffix] + " " + WEAPON_NAMES[weapon];
    int damage = (int) (WEAPON_DAMAGE[weapon] * SUFFIX_MULTIPLIER[suffix]);
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
    int suffix = randomIntInRange(0, SUFFIX_STRINGS.length / 3);
    int weapon = randomIntInRange(0, SUFFIX_STRINGS.length / 3);
    int acc = randomIntInRange(0, ACCURACIES.length / 2);
    String name = SUFFIX_STRINGS[suffix] + " " + WEAPON_NAMES[weapon];
    int damage = (int) (WEAPON_DAMAGE[weapon] * SUFFIX_MULTIPLIER[suffix]);
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


}
