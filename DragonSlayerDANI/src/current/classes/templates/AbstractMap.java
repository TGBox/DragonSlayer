package current.classes.templates;

import current.classes.Enemy;
import current.classes.Field;
import current.classes.Item;
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

  // Default values.
  private static final boolean DEFAULT_BOOLEAN = false;
  private static final Position DEFAULT_BOSS_POS = new Position(0, 0);
  private static final String BOSS_FIELD_NAME = "burned grass field";

  private Field[][] fields;
  private Position start;
  private int mapID;
  private boolean mapDefeated;
  private int playerLevel;
  private Position bossPos;

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
    this.bossPos = DEFAULT_BOSS_POS;
  }

  /**
   * method to set the boss on the field.
   * checks if the given position is within the bounds of the map and that it is different from
   * the starting position of the map.
   *
   * @param boss Enemy boss.
   * @param bossPos Position on the field.
   * @param itemOnField Nullable Item for the field.
   * @return boolean true if the enemy was set, false else.
   */
  public boolean setBoss(Enemy boss, Position bossPos, Item itemOnField) {
    if (!bossPos.equals(start) && isWithinBounds(bossPos)) {
      this.bossPos = bossPos;
      this.fields[bossPos.x][bossPos.y] = new Field(BOSS_FIELD_NAME, boss, itemOnField, this.mapID);
      return true;
    } else {
      return false;
    }
  }

  /**
   * method to check if a given Position is within the given bounds of the field [][].
   *
   * @param pos the Position that needs to be checked.
   * @return boolean true if the position is valid, else false.
   */
  public boolean isWithinBounds(Position pos) {
    return pos.x >= 0 && pos.x < fields.length && pos.y >= 0 && pos.y < fields[0].length;
  }

  // Getter and setter methods.
  public Field[][] getFields() {
    return fields;
  }

  public void setFields(Field[][] fields) {
    this.fields = fields;
  }

  public Position getStart() {
    return start;
  }

  public void setStart(Position start) {
    this.start = start;
  }

  public boolean isMapDefeated() {
    return mapDefeated;
  }

  public void setMapDefeated(boolean mapDefeated) {
    this.mapDefeated = mapDefeated;
  }

  public int getMapID() {
    return mapID;
  }

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  public Field getField(Position pos) {
    return fields[pos.x][pos.y];
  }

  public int getPlayerLevel() {
    return playerLevel;
  }

  public Position getBossPos() {
    return bossPos;
  }

  public void setBossPos(Position bossPos) {
    this.bossPos = bossPos;
  }

  public void setField(Field field, Position pos) {
    this.fields[pos.x][pos.y] = field;
  }
}
