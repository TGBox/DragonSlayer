package current.meta.gen;

import static current.meta.Constants.DUNGEON_MAX_HEIGHT;
import static current.meta.Constants.DUNGEON_MAX_WIDTH;
import static current.meta.Constants.DUNGEON_MIN_HEIGHT;
import static current.meta.Constants.DUNGEON_MIN_WIDTH;
import static current.meta.Constants.MAP_MAX_HEIGHT;
import static current.meta.Constants.MAP_MAX_WIDTH;
import static current.meta.Constants.MAP_MIN_HEIGHT;
import static current.meta.Constants.MAP_MIN_WIDTH;
import static current.meta.gen.FieldGenerator.EMPTY;
import static current.meta.gen.FieldGenerator.ENEMY;
import static current.meta.gen.FieldGenerator.createBossField;
import static current.meta.gen.FieldGenerator.createDungeonExit;
import static current.meta.gen.FieldGenerator.createDungeonField;
import static current.meta.gen.FieldGenerator.createField;

import current.classes.Dungeon;
import current.classes.Field;
import current.classes.Item;
import current.classes.Map;
import current.meta.Constants.Difficulty;
import current.meta.NPCWithItem;
import current.meta.Position;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 23:33.
 */
public class MapGenerator {

  /**
   * method to create a new map for the current game.
   *
   * @param mapID int mapID.
   * @param playerLvl int level of the player.
   * @param hasDungeon boolean true if the map has a dungeon, false else.
   * @param diff Difficulty of the game.
   * @return the newly created map.
   */
  public Map createNewMap(int mapID, int playerLvl, boolean hasDungeon, Difficulty diff){
    // Random width and height in specified range. 5x5 - 7x7
    int width = randInt(MAP_MIN_WIDTH, MAP_MAX_WIDTH);
    int height = randInt(MAP_MIN_HEIGHT, MAP_MAX_HEIGHT);
    Field[][] fields = new Field[width][height];
    int nrOfNPCs;
    int nrOfTools;
    // Calculating the number of tools and NPCs on the map.
    int nrFields = width * height;
    if(nrFields == 25){
      nrOfNPCs = 0;
      nrOfTools = 1;
    } else if(nrFields > 25 && nrFields < 40){
      nrOfNPCs = 1;
      nrOfTools = 2;
    } else {
      nrOfNPCs = 2;
      nrOfTools = 3;
    }

    // Iterating over the fields array to initialize it.
    // TODO add some adjustments so that NPCs and tools get placed on the maps as well.
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        fields[x][y] = createField(mapID, playerLvl, randInt(ENEMY, EMPTY));
      }
    }

    Position startPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
    Position bossPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
    while (startPos.equals(bossPos)){
      bossPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
    }
    fields[bossPos.x][bossPos.y] = createBossField(mapID, playerLvl, diff);

    // Setting NPCs, items and tools.
    NPCWithItem nwi;
    Item tool;
    int toolID = 20;
    int npcID = 100;
    Position[] positions = new Position[nrOfNPCs + nrOfTools];
    int index = 0;
    Position current;
    int npcCount = 1;
    while (nrOfNPCs + nrOfTools > 0){
      if(nrOfNPCs == 0){
        switch (randInt(0, 2)){
          case 0:
            tool = ToolGenerator.createAccuracyBooster(randInt(5, 15), toolID);
            break;
          case 1:
            tool = ToolGenerator.createMaxHealthBooster(randInt(10, 50), toolID);
            break;
          default:  // case 2:
            tool = ToolGenerator.createXPBooster(randInt(50, 100), toolID);
            break;
        }
        current = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
        while (current.equals(bossPos) || current.equals(startPos)){
          current = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
        }
        positions[index] = current;
        index++;
        fields[current.x][current.y].setItem(tool);
        toolID++;
        nrOfTools--;
      } else if(nrOfTools == 0){
        switch (randInt(0, 2)){
          case 0:
            nwi = NPC_Generator.createNPCByCategory(0, mapID, npcCount);
            break;
          case 1:
            nwi = NPC_Generator.createNPCByCategory(1, mapID, npcCount);
            break;
          default:  // case 2:
            nwi = NPC_Generator.createNPCByCategory(2, mapID, npcCount);
            break;
        }
        npcCount++;
        Position itemPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
        current = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
        while(itemPos.equals(current) || itemPos.equals(startPos) || itemPos.equals(bossPos) ||
            current.equals(startPos) || current.equals(bossPos)){
          itemPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
          current = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
        }
        // TODO end this else block and the next one.

        nrOfNPCs--;
      } else {  // if none are zero.

      }
    }

    if(hasDungeon){
      Dungeon dungeon = createNewDungeon(mapID, playerLvl);
      Position dungeonPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
      while (dungeonPos.equals(startPos) || dungeonPos.equals(bossPos)){
        dungeonPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
      }
      return new Map(fields, startPos, mapID, playerLvl, dungeon);
    } else {
      return new Map(fields, startPos, mapID, playerLvl, null);
    }
  }

  /**
   * method to create a new dungeon map object.
   *
   * @param mapID int mapID.
   * @param playerLvl int level of the player when the dungeon is created.
   * @return the new Dungeon.
   */
  public Dungeon createNewDungeon(int mapID, int playerLvl){
    // Creating the fields for the dungeon.
    Field[][] fields = new Field[randInt(DUNGEON_MIN_WIDTH, DUNGEON_MAX_WIDTH)][randInt(DUNGEON_MIN_HEIGHT, DUNGEON_MAX_HEIGHT)];
    for (int y = 0; y < fields[0].length; y++) {
      for (int x = 0; x < fields.length; x++) {
        fields[x][y] = createDungeonField(mapID, playerLvl, randInt(ENEMY, EMPTY));
      }
    }
    // Creating the starting position.
    Position portalPos = new Position(randInt(0, fields.length), randInt(0, fields[0].length));
    fields[portalPos.x][portalPos.y] = createDungeonExit(mapID);
    return new Dungeon(fields, portalPos, mapID, playerLvl);
  }

  /**
   * method to create a random integer within given bounds.
   * the bounds are inclusive, so the random number can be the bound itself.
   * this method is the preferred standard after java 1.7.
   *
   * @param min int minimum value.
   * @param max int maximum value.
   * @return the randomly generated int.
   */
  private static int randInt(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }
}
