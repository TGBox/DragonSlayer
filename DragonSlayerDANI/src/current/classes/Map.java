package current.classes;

import current.classes.templates.AbstractMap;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 16:20.
 */
public class Map extends AbstractMap {

  private Dungeon dungeon;

  /**
   * constructor method to create a new map object.
   *
   * @param fields Field[][] containing the single fields of the map.
   * @param start Position where the player will start.
   * @param mapID int mapID to determine the internal progress.
   * @param playerLevel int current level of the player when the map is created.
   * @param dungeon Dungeon map that is linked to this map. (can be null)
   */
  public Map(Field[][] fields, Position start, int mapID, int playerLevel, Dungeon dungeon) {
    super(fields, start, mapID, playerLevel);
    this.dungeon = dungeon;
  }

  /**
   * method to check if this map has a linked dungeon map.
   * checks if the dungeon is not equal to null.
   *
   * @return boolean true if dungeon is present, false else.
   */
  public boolean hasDungeon() {
    return this.dungeon != null;
  }


  // Getter and setter methods.
  public Dungeon getDungeon() {
    return dungeon;
  }

  public void setDungeon(Dungeon dungeon) {
    this.dungeon = dungeon;
  }

}
