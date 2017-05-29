package usageExample;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 22:08.
 */
public class Start {

  /*
   * This is the way that the game needs to be implemented! one giant controller class needs to
   * handle all occurring events and the user input, as well as the gui.
   * everything needs to be event based, to that single methods can be called on occurring events.
   */

  public static void main(String[] args) {
    TestController ctrl = new TestController();
    ctrl.startTheGame();
  }

}
