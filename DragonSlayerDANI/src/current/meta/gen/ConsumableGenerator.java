package current.meta.gen;

import static current.meta.Constants.HEALING_PREFIX_VALUES;
import static current.meta.Constants.HEALING_VALUES;
import static current.meta.Constants.asString;
import static current.meta.Constants.getEnumAt;

import current.classes.Item;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 22:29.
 */
public class ConsumableGenerator {

  private static final String DEFAULT_NAME = "healing potion";

  /**
   * method to create a new Consumable object.
   *
   * @return the new Consumable object.
   */
  public static Item createConsumable() {
    int value = randomIntInRange(0, HEALING_VALUES.length - 1);
    int healing;
    String name;
    if (ThreadLocalRandom.current().nextBoolean()) {
      int prefix = randomIntInRange(0, HEALING_PREFIX_VALUES.length - 1);
      name = asString(getEnumAt("HealingAdjectives", value)) +
          " " + asString(getEnumAt("HealingPrefixNames", prefix)) +
          " " + DEFAULT_NAME;
      healing = (int) (HEALING_VALUES[value] * HEALING_PREFIX_VALUES[prefix]);
    } else {
      name = asString(getEnumAt("HealingAdjectives", value)) + " " + DEFAULT_NAME;
      healing = HEALING_VALUES[value];
    }
    return new Item(name, healing);
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
