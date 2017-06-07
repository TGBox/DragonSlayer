package current.classes;

import static current.meta.gen.MapGenerator.createNewMap;

import current.meta.Constants.Difficulty;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 06.06.2017, 23:10.
 */
public class Game {

  private static final int MAP_ID_FIRST_MAP = 1;
  private static final int LEVEL_COUNT = 10;

  private Player player;
  private Difficulty difficulty;
  private Map[] maps;
  private int index;

  /**
   * constructor method to create a new game object.
   *
   * @param player the Player of the current game.
   * @param difficulty the user selected difficulty.
   */
  public Game(Player player, Difficulty difficulty) {
    this.player = player;
    this.difficulty = difficulty;
    this.index = MAP_ID_FIRST_MAP;
    this.maps = new Map[LEVEL_COUNT];
    initiateCurrentMap();
  }

  /**
   * method to let the game advance to the next level.
   */
  public void advanceOneLevel() {
    initiateCurrentMap();
  }

  /**
   * method to initiate the current map.
   */
  private void initiateCurrentMap() {
    boolean dungeon = ThreadLocalRandom.current().nextBoolean();
    this.maps[this.index - 1] = createNewMap(this.index, this.player.getLevel(), dungeon,
        this.difficulty);
    this.index++;
  }

  /**
   * method to return the current map object.
   *
   * @return the current map.
   */
  public Map getMap() {
    return maps[index - 1];
  }

  // Getter and setter methods.
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public Map[] getMaps() {
    return maps;
  }

  public void setMaps(Map[] maps) {
    this.maps = maps;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
