package current.classes.templates;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 28.05.2017, 22:27.
 */
public abstract class Thing {

  private String name;
  private boolean vocal;

  /**
   * constructor method to create a new Thing object.
   *
   * @param name the String name of the thing.
   */
  public Thing(String name) {
    this.name = name;
    this.vocal = initVocal();
  }

  /**
   * method to initialize the boolean vocal.
   *
   * @return true, if the name starts with a vocal, false else.
   */
  private boolean initVocal() {
    return this.name.charAt(0) == 'a' || this.name.charAt(0) == 'e' ||
        this.name.charAt(0) == 'i' || this.name.charAt(0) == 'o' || this.name.charAt(0) == 'u';
  }

  public String getName() {
    return name;
  }

  // Getter and setter methods.
  public void setName(String name) {
    this.name = name;
  }

  public boolean isVocal() {
    return vocal;
  }
}
