package depr.old.text.newTextBasedVersion.classes;

import depr.old.text.newTextBasedVersion.meta.Difficulty;
import depr.old.text.newTextBasedVersion.creators.ConsumableCreator;
import depr.old.text.newTextBasedVersion.creators.EnemyCreator;
import depr.old.text.newTextBasedVersion.creators.FieldCreator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 23:35.
 */
public class Map {

    private Field[][] fields;
    private int startingPointX;
    private int startingPointY;
    private int mapID;
    private boolean mapDefeated;
    private int levelOfPlayerOnStart;

    /**
     * constructor method to create an empty map.
     * @param width the int width for the map.
     * @param height int height.
     * @param difficulty Difficulty for the map.
     */
    public Map(int width, int height, Difficulty difficulty){
        this.fields = new Field[width][height];
        this.startingPointX = randomIntInRange(0, width - 1);
        this.startingPointY = randomIntInRange(0, height - 1);
        this.mapDefeated = false;
        this.mapID = 1;
        this.levelOfPlayerOnStart = 1;
        initializeTheFieldsEmpty();
        addEnemiesAndItems(difficulty);
        fields[startingPointX][startingPointY] = new Field("grass field", mapID);
    }

    /**
     * constructor method to create an empty map.
     * @param width the int width for the map.
     * @param height int height.
     * @param difficulty Difficulty for the map.
     * @param mapID the int mapID to keep track of the number of maps that have been played already.
     * @param levelOfPlayerOnStart int to determine the user level on map creation.
     */
    public Map(int width, int height, Difficulty difficulty, int mapID, int levelOfPlayerOnStart){
        this.fields = new Field[width][height];
        this.startingPointX = randomIntInRange(0, width - 1);
        this.startingPointY = randomIntInRange(0, height - 1);
        this.mapDefeated = false;
        this.mapID = mapID;
        this.levelOfPlayerOnStart = levelOfPlayerOnStart;
        initializeTheFieldsEmpty();
        addEnemiesAndItems(difficulty);
        fields[startingPointX][startingPointY] = new Field("grass field", mapID);
    }

    /**
     * method to add items and enemies to the map according to the chosen difficulty.
     * @param difficulty the Difficulty.
     */
    private void addEnemiesAndItems(Difficulty difficulty){
        int numberOfFields = fields[0].length * fields.length;
        switch (difficulty){
            case EASY:
                numberOfFields /= 2;    // 50 percent enemies/items.
                break;
            case MEDIUM:
                numberOfFields -= (numberOfFields / 4);    // 75 percent enemies/items.
                break;
            case HARD:
                numberOfFields -= (numberOfFields / 10);    // 90 percent enemies/items.
                break;
            default:
                numberOfFields -= (numberOfFields / 4);    // 75 percent enemies/items.
                break;
        }
        for(int i = 0; i < numberOfFields; i++){
            fields[randomIntInRange(0, fields.length - 1)][randomIntInRange(0, fields[0].length - 1)] =
                            FieldCreator.createAdaptedField(mapID, levelOfPlayerOnStart);
        }
        // Creating and adding the boss enemy.
        int bossX = randomIntInRange(0, this.fields.length - 1);
        int bossY = randomIntInRange(0, this.fields[0].length - 1);
        if(bossX == startingPointX && bossY == startingPointY){ // To avoid that the boss is on our starting field.
            bossX = randomIntInRange(0, this.fields.length - 1);
            bossY = randomIntInRange(0, this.fields[0].length - 1);
        }
        Enemy bossEnemy = EnemyCreator.createBossEnemy(difficulty, levelOfPlayerOnStart, mapID);
        fields[bossX][bossY].setName("giant burned field");
        fields[bossX][bossY].setEnemy(bossEnemy);
        fields[bossX][bossY].setItem(new Item("dragons healing potion", levelOfPlayerOnStart * 100));
    }

    /**
     * method to initialize the fields array with all empty fields.
     */
    private void initializeTheFieldsEmpty(){
        for(int y = 0; y < fields[0].length; y++){
            for(int x = 0; x < fields.length; x++){
                fields[x][y] = FieldCreator.createEmptyField(mapID);
            }
        }
    }

    /**
     * method to respawn the boss on a given map if the user decides to run away from it.
     * @param bossEnemy the boss enemy.
     * @param xPos the x position where the enemy was first encountered.
     * @param yPos y position.
     */
    public void respawnBoss(Enemy bossEnemy, int xPos, int yPos){
        // Creating the new position for the boss.
        int newX = randomIntInRange(0, fields.length - 1);
        int newY = randomIntInRange(0, fields[0].length - 1);
        if(xPos == newX && yPos == newY){   // Second call on the method if the position is the same.
            respawnBoss(bossEnemy, xPos, yPos);
        } else {
            fields[newX][newY] = new Field("burned grass field", bossEnemy,
                    ConsumableCreator.createConsumable(), mapID);
        }
    }

    /**
     * method to create a random integer within given bounds.
     * the bounds are inclusive, so the random number can be the bound itself.
     * this method is the preferred standard after java 1.7.
     * @param min int minimum value.
     * @param max int maximum value.
     * @return the randomly generated int.
     */
    private static int randomIntInRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // Getter and setter methods.
    public Field[][] getFields() {
        return fields;
    }
    public int getStartingPointX() {
        return startingPointX;
    }
    public int getStartingPointY() {
        return startingPointY;
    }
    public boolean isMapDefeated() {
        return mapDefeated;
    }
    public int getMapID() {
        return mapID;
    }
    public void setFields(Field[][] fields) {
        this.fields = fields;
    }
    public void setStartingPointX(int startingPointX) {
        this.startingPointX = startingPointX;
    }
    public void setStartingPointY(int startingPointY) {
        this.startingPointY = startingPointY;
    }
    public void setMapDefeated(boolean mapDefeated) {
        this.mapDefeated = mapDefeated;
    }
    public void setMapID(int mapID) {
        this.mapID = mapID;
    }
}
