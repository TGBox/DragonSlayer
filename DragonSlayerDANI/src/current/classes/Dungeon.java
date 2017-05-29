package current.classes;

import current.classes.templates.AbstractMap;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 16:21.
 */
public class Dungeon extends AbstractMap {

  /**
   * constructor method to create a new dungeon map object.
   *
   * @param fields Field[][] containing the single fields of the map.
   * @param portalPos Position of the portal entrance to the dungeon.
   * @param mapID int mapID to determine the internal progress.
   * @param playerLevel int current level of the player when the map is created.
   */
  public Dungeon(Field[][] fields, Position portalPos, int mapID, int playerLevel) {
    super(fields, portalPos, mapID, playerLevel);
  }


}
