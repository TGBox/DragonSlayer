package current.classes.templates;

import current.classes.Field;
import current.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 00:41.
 */
public abstract class AbstractMap {

  // TODO make instantiated map and dungeon map, add getBossPos method.

  // Default values.
  private static final boolean DEFAULT_BOOLEAN = false;

  private Field[][] fields;
  private Position start;
  private int mapID;
  private boolean mapDefeated;
  private int playerLevel;

  /**
   * constructor method to create a new map object.
   *
   * @param fields Field[][] containing the single fields of the map.
   * @param start Position where the player will start.
   * @param mapID int mapID to determine the internal progress.
   * @param playerLevel int current level of the player when the map is created.
   */
  public AbstractMap(Field[][] fields, Position start, int mapID, int playerLevel) {
    this.fields = fields;
    this.start = start;
    this.mapDefeated = DEFAULT_BOOLEAN;
    this.mapID = mapID;
    this.playerLevel = playerLevel;
  }

  // Getter and setter methods.
  public Field[][] getFields() {
    return fields;
  }

  public Position getStart() {
    return start;
  }

  public boolean isMapDefeated() {
    return mapDefeated;
  }

  public int getMapID() {
    return mapID;
  }

  public Field getField(Position pos) {
    return fields[pos.x][pos.y];
  }

  public int getPlayerLevel() {
    return playerLevel;
  }

  public void setFields(Field[][] fields) {
    this.fields = fields;
  }

  public void setStart(Position start) {
    this.start = start;
  }

  public void setMapDefeated(boolean mapDefeated) {
    this.mapDefeated = mapDefeated;
  }

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  public void setField(Field field, Position pos) {
    this.fields[pos.x][pos.y] = field;
  }

}
