package current.classes;

import static current.meta.gen.MapGenerator.createNewMap;

import current.meta.Constants.Command;
import current.meta.Constants.Difficulty;
import current.meta.Position;
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
   * method to let the current player walk in the given direction.
   *
   * @param command Command to identify the direction.
   * @return boolean true if the step can be made, false else.
   */
  public boolean walk(Command command){
    switch (command){
      case north:
        if(player.getPos().y >= 1){
          player.setPos(new Position(player.getPos().x, player.getPos().y - 1));
          return true;
        } else return false;
      case south:
        if(player.getPos().y <= maps[index - 1].getFields()[0].length - 2){
          player.setPos(new Position(player.getPos().x, player.getPos().y + 1));
          return true;
        } else return false;
      case east:
        if(player.getPos().x <= maps[index - 1].getFields().length - 2){
          player.setPos(new Position(player.getPos().x + 1, player.getPos().y));
          return true;
        } else return false;
      default:  // west
        if(player.getPos().x >= 1){
          player.setPos(new Position(player.getPos().x - 1, player.getPos().y));
          return true;
        } else return false;
    }
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
