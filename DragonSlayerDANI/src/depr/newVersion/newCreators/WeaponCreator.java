package depr.newVersion.newCreators;

import java.util.concurrent.ThreadLocalRandom;
import depr.newVersion.classes.Item;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 04.05.2017, 23:51.
 */
public class WeaponCreator {

  private static final String[] SIZE = new String[]{"tiny", "small", "regular", "large", "huge"};
  private static final double[] SIZE_VALS = new double[]{0.42, 0.66, 1.00, 1.22, 1.42};

  private static final String[] COLOR = new String[]{"white", "gray", "black", "blue",
      "green", "red", "yellow", "orange"};
  private static final double[] COLOR_VALS = new double[]{0.42, 0.54, 0.76, 1.11,
      1.23, 1.38, 1.42, 1.66};

  private static final String[] REWARD_WEAPON_NAMES = new String[]{"sword of the ancient",
      "club of a giant", "hammer of a god", "spear of the undead", "magical boxing gloves",
      "ritual dagger"};
  private static final int[] REWARD_WEAPON_BASE_ATTACKS = new int[]{100, 120, 140, 160, 180, 200};

  private static final double[] REWARD_WEAPON_ACCURACIES = new double[]{0.45, 0.50, 0.55};

  /**
   * method to create a new weapon item that can be used by NPCs as a reward.
   * @return the created weapon item.
   */
  public static Item createRewardWeapon(){
    int index;
    String name = "";
    if(ThreadLocalRandom.current().nextBoolean()){
      index = randInt(0, SIZE.length - 1);
      name += SIZE_VALS[index] + " ";
    } else {
      index = randInt(0, COLOR.length - 1);
      name += COLOR_VALS[index] + " ";
    }
    name += REWARD_WEAPON_NAMES[randInt(0, REWARD_WEAPON_NAMES.length - 1)];
    int attack = REWARD_WEAPON_BASE_ATTACKS[randInt(0, REWARD_WEAPON_BASE_ATTACKS.length - 1)];
    double accuracy = REWARD_WEAPON_ACCURACIES[randInt(0, REWARD_WEAPON_ACCURACIES.length - 1)];
    return new Item(name, attack, accuracy);
  }

  /**
   * method to create a random integer within given bounds.
   * the bounds are inclusive, so the random number can be the bound itself.
   * this method is the preferred standard after java 1.7.
   * @param min int minimum value.
   * @param max int maximum value.
   * @return the randomly generated int.
   */
  private static int randInt(int min, int max){
      return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

}