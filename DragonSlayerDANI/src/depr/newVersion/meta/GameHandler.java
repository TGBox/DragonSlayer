package depr.newVersion.meta;

import depr.newVersion.meta.GameConstants.Difficulty;
import depr.newVersion.meta.GameConstants.Gender;
import javax.swing.JOptionPane;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 01.05.2017, 02:14.
 */
public class GameHandler {

  private Game game;

  /**
   * constructor method to create a new game handler object.
   * collects the user data to create the new game object.
   */
  private GameHandler() {
    String name = getUserName();
    Gender gender = getUserGender();
    Difficulty difficulty = getUserDifficulty();
    this.game = new Game(name, gender, difficulty);
  }

  // Todo implement game logic.

  /**
   * main method to start the program.
   *
   * @param args not needed.
   */
  public static void main(String[] args) {
    new GameHandler();
  }

  /**
   * method to retrieve the user name from the player.
   * handles empty and too long names accordingly.
   *
   * @return String user name.
   */
  private String getUserName() {
    String userName = JOptionPane.showInputDialog(null,
        "Enter your desired name:", "Name needed",
        JOptionPane.QUESTION_MESSAGE);
    if (userName.equals("")) {
      return "Mister Nobody";
    } else if (userName.equals("Hitler") || userName.equals("hitler")) {
      return "Schmul Chaim Goldberg";
    } else if (userName.length() >= 25) {
      return "Mister NameTooLong";
    } else {
      return userName;
    }
  }

  /**
   * method to retrieve the desired gender from the user via a JOptionPane.
   *
   * @return user Gender.
   */
  private Gender getUserGender() {
    Gender[] genders = new Gender[]{Gender.Agender, Gender.ApacheHelicopter, Gender.Cryptogender,
        Gender.Digigender, Gender.Female, Gender.Gendermaverick, Gender.Genderpunk, Gender.Male,
        Gender.Polygenderflux, Gender.Transgender};
    return (Gender) JOptionPane.showInputDialog(null,
        "Please select your gender:", "I need to assume your gender",
        JOptionPane.QUESTION_MESSAGE, null, genders, genders[0]);
  }

  /**
   * method to get the difficulty settings from the user.
   *
   * @return user selected Difficulty.
   */
  private Difficulty getUserDifficulty() {
    Difficulty[] difficulties = new Difficulty[]{Difficulty.Baby, Difficulty.VeryEasy,
        Difficulty.Easy, Difficulty.Regular, Difficulty.Tough, Difficulty.Hard, Difficulty.Extreme,
        Difficulty.Nightmare};
    return (Difficulty) JOptionPane.showInputDialog(null,
        "Choose your difficulty:", "Difficulty needed", JOptionPane.QUESTION_MESSAGE,
        null, difficulties, difficulties[0]);
  }

}
