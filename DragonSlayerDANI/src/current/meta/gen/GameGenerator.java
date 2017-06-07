package current.meta.gen;

import current.classes.Game;
import current.classes.Item;
import current.classes.Player;
import current.meta.Constants;
import current.meta.Constants.Difficulty;
import current.meta.Constants.UserNames;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 06.06.2017, 23:09.
 */
public class GameGenerator {

  private static final UserNames[] USER_NAMES = UserNames.values();
  private static final Difficulty[] DIFFICULTIES = Difficulty.values();
  private static final String[] DIFFICULTY_VALUES = fillStringArray();

  private Game game;
  private Player player;
  private Difficulty difficulty;

  /**
   * method to initiate all the variables for the current game.
   */
  private GameGenerator() {
    String name = getNameFromUser();
    this.difficulty = getDifficultyFromUser();
    this.player = createPlayer(name, difficulty);
    this.game = new Game(player, difficulty);
    this.player.setPos(game.getMap().getStart());
  }

  /**
   * method to retrieve the chosen difficulty from the current user.
   *
   * @return Difficulty.
   */
  private static Difficulty getDifficultyFromUser() {
    String userSelect = String.valueOf(JOptionPane.showInputDialog(null,
        "Choose your desired difficulty:", "Difficulty for the game needed!",
        JOptionPane.INFORMATION_MESSAGE, null, DIFFICULTY_VALUES, DIFFICULTY_VALUES[0]));
    for (Difficulty diff : DIFFICULTIES) {
      if (Constants.asString(diff).equals(userSelect)) {
        return diff;
      }
    }
    return Difficulty.Warrior;
  }

  /**
   * method to retrieve the user name from a given user.
   *
   * @return String user name.
   */
  private static String getNameFromUser() {
    String name = JOptionPane.showInputDialog(null,
        "Enter your desired name for the game:", "Name needed!",
        JOptionPane.INFORMATION_MESSAGE);
    // Catching weird, too short or too long names.
    if (name.equals("Hitler") || name.equals("hitler") || name.length() < 2 || name.length() > 30) {
      return Constants.asString(USER_NAMES[randInt(0, USER_NAMES.length - 1)]);
    } else {
      return name;
    }
  }

  /**
   * method to initiate the player object.
   *
   * @param name String name of the user.
   * @param difficulty Difficulty of the game.
   * @return Player object.
   */
  private Player createPlayer(String name, Difficulty difficulty) {
    int health;
    switch (difficulty) {
      case Baby:
        health = Constants.HEALTH_BABY;
        break;
      case Novice:
        health = Constants.HEALTH_NOVICE;
        break;
      case Warrior:
        health = Constants.HEALTH_WARRIOR;
        break;
      default: // God.
        health = Constants.HEALTH_GOD;
        break;
    }
    Item weapon = WeaponGenerator.createStartingWeapon();
    return new Player(name, health, weapon, difficulty);
  }

  /**
   * method to fill the String[] with the String versions of the enums.
   *
   * @return the filled String array.
   */
  private static String[] fillStringArray() {
    String[] strings = new String[DIFFICULTIES.length];
    for (int i = 0; i < strings.length; i++) {
      Constants.asString(DIFFICULTIES[i]);
    }
    return strings;
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

  /**
   * main method to initiate the game.
   *
   * @param args .
   */
  public static void main(String[] args) {

  }
}
