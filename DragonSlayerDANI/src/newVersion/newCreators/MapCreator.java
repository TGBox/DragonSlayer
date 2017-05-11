package newVersion.newCreators;

import java.util.concurrent.ThreadLocalRandom;
import newVersion.meta.GameConstants.Difficulty;
import newVersion.classes.Field;
import newVersion.classes.Map;
import newVersion.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 09.05.2017, 21:09.
 */
public class MapCreator {

  private static final int DUNGEON_MIN_WIDTH = 3;
  private static final int DUNGEON_MIN_HEIGHT = 3;

  /**
   * method to create a new map for an ongoing game.
   * @param x the width of the map as an int.
   * @param y the height of the map as an int.
   * @param diff the Difficulty.
   * @param mapID int mapID.
   * @param playerLvl int player level on start of map.
   * @param hasDungeon boolean to determine if the new map has access to a dungeon or not.
   * @return the new map object.
   */
  public static Map createNewMap(int x, int y, Difficulty diff, int mapID,
      int playerLvl, boolean hasDungeon){
    Map map = new Map(x, y, diff, mapID, playerLvl);
    if(hasDungeon){
      map.setDungeon(createDungeonMap(randInt(DUNGEON_MIN_WIDTH, x), randInt(DUNGEON_MIN_HEIGHT, y),
          diff, mapID, playerLvl));
      Position start = map.getStart();
      Position boss = map.getBossPos();
      Position dungeon = new Position(randInt(0, map.getDungeon().getFields().length),
          randInt(0, map.getDungeon().getFields()[0].length));
      while ((dungeon.equals(start)) || (dungeon.equals(boss))){
        dungeon = new Position(randInt(0, map.getDungeon().getFields().length),
            randInt(0, map.getDungeon().getFields()[0].length));
      }
      map.getFields()[dungeon.x][dungeon.y] = new Field("dungeon entrance", mapID,
          true);
    }
    return map;
  }

  /**
   * method to create a new dungeon map for a certain map.
   * @param x the width of the map as an int.
   * @param y the height of the map as an int.
   * @param diff the Difficulty.
   * @param mapID int mapID.
   * @param playerLvl int player level on start of map.
   * @return the new dungeon map object.
   */
  private static Map createDungeonMap(int x, int y, Difficulty diff, int mapID, int playerLvl){
    Map map = new Map(x, y, diff, mapID, playerLvl, true);
    int exitX = randInt(0, map.getFields().length);
    int exitY = randInt(0, map.getFields()[0].length);
    map.getFields()[exitX][exitY] = new Field("dungeon exit", mapID, true);
    // TODO: maybe more rewards for the dungeons?
    return map;
  }

  /**
   * method to create a random integer within given bounds.
   * the bounds are inclusive, so the random number can be the bound itself.
   * this method is the preferred standard after java 1.7.
   * @param min int minimum value.
   * @param max int maximum value.
   * @return the randomly generated int.
   */
  private static int randInt(int min, int max){
      return ThreadLocalRandom.current().nextInt(min, max + 1);
  }
}
