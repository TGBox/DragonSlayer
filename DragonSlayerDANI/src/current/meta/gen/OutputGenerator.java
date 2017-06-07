package current.meta.gen;

import current.classes.Field;
import current.classes.Player;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 07.06.2017, 21:43.
 */
public class OutputGenerator {

  /**
   * method to create the specified intro for a given player on a given field.
   *
   * @param player Player of the game.
   * @param field Starting field.
   * @return String introduction.
   */
  public static String getIntroduction(Player player, Field field) {
    String intro = "Welcome to the world of Dragon Slayer. You find yourself stranded on a " +
        field.getName() + " and you can't remember how you got here. Although you can recall "
        + "a few things:\nYour name is " + player.getName() + " and you were on the field, "
        + "looking after your sheep, when a huge dragon appeared and then the memory kind of "
        + "fades. You also notice that you have a";
    if (player.getWeapon().isVocal()) {
      intro += "n ";
    } else {
      intro += " ";
    }
    intro += player.getWeapon().getName() + " in your bag. Find the dragon and use it to "
        + "kill it! Although you might find something better if you just look for "
        + "it.\nWhat do you want to do?";
    return intro;
  }

}
