package current.meta.gen;

import current.classes.Field;
import current.classes.Player;
import current.classes.templates.Thing;
import current.classes.templates.WrongMethodCallException;
import current.meta.Constants.Command;

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
    intro += grammarCheckForVocals(player.getWeapon());
    intro += " in your bag. Find the dragon and use it to "
        + "kill it! Although you might find something better if you just look for "
        + "it.\nWhat do you want to do?";
    return intro;
  }

  /**
   * method to get the String output if the player walks for one field in a given direction.
   * method should only be called on actual move events. not moving needs to be caught elsewhere.
   *
   * @param walkCommand Command that should be one of north, east, west or south.
   * @param newField the new Field that the player will land on after the walk event.
   * @return String the output for the text area.
   * @throws WrongMethodCallException if this method gets called on a wrong command.
   */
  public static String getWalkingString(Command walkCommand, Field newField)
      throws WrongMethodCallException {
    String walkingString;
    switch (walkCommand) {
      case north:
        walkingString = "You walk northward to find yourself on a";
        break;
      case south:
        walkingString = "You walk northward to find yourself on a";
        break;
      case east:
        walkingString = "You walk northward to find yourself on a";
        break;
      case west:
        walkingString = "You walk northward to find yourself on a";
        break;
      default:
        throw new WrongMethodCallException("getWalkingString",
            "WalkCommand misused: " + walkCommand.toString());
    }
    walkingString += grammarCheckForVocals(newField) + ".";
    walkingString += getNewFieldString(newField);
    walkingString += "What do you want to do?";
    return walkingString;
  }

  /**
   * method to get the String output for the event of getting on a new field.
   *
   * @param field the Field that is visited by the player.
   * @return String new field output.
   */
  public static String getNewFieldString(Field field){
    // TODO implement this method for the four different field types.
    if(field.isPortal()){

    } else if(field.hasEnemy()){

    } else if(field.hasNPC()){

    } else {    // regular field.

    }
    return null;
  }

  /**
   * checks if a given thing's name starts with a vocal. if it does, the String gets altered
   * accordingly.
   *
   * @param thing the Thing that needs to be grammar checked.
   * @return String with the correct ending and the thing's name at the end of it.
   */
  private static String grammarCheckForVocals(Thing thing) {
    if (thing.isVocal()) {
      return "n " + thing.getName();
    } else {
      return " " + thing.getName();
    }
  }

}
