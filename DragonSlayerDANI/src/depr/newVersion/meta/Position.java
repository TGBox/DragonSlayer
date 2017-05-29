package depr.newVersion.meta;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 10.05.2017, 21:23.
 */
public class Position {

  public int x;
  public int y;

  /**
   * empty constructor for creating the character before the map.
   * x and y will be set to zero.
   */
  public Position() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * constructor method to create a new Position object.
   *
   * @param x int x.
   * @param y int y.
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * method to compare a position to another one.
   *
   * @param pos the other position.
   * @return boolean true on equal, false else.
   */
  public boolean equals(Position pos) {
    if (pos.x == this.x && pos.y == this.y) {
      return true;
    } else {
      return false;
    }
  }

}
