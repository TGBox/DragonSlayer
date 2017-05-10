package newVersion.classes;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 02.05.2017, 21:24.
 */
public class Field {

    private String name;
    public boolean vocal;
    private Enemy enemy;
    private Item item;
    private int mapID;
    private boolean wasVisited;

    /**
     * various constructor methods with mixed parameters.
     * @param name the String name of the field.
     * @param enemy the Enemy that is on the field.
     * @param item the Item that is on the field.
     * @param mapID int to determine the map for the field.
     */
    public Field(String name, Enemy enemy, Item item, int mapID){
        this.name = name;
        initiateVocal();
        this.enemy = enemy;
        this.item = item;
        this.mapID = mapID;
        this.wasVisited = false;
    }
    public Field(String name, Enemy enemy, int mapID){
        this.name = name;
        initiateVocal();
        this.enemy = enemy;
        this.item = null;
        this.mapID = mapID;
        this.wasVisited = false;
    }
    public Field(String name, int mapID){
        this.name = name;
        initiateVocal();
        this.enemy = null;
        this.item = null;
        this.mapID = mapID;
        this.wasVisited = false;
    }
    public Field(String name, Item item, int mapID){
        this.name = name;
        initiateVocal();
        this.enemy = null;
        this.item = item;
        this.mapID = mapID;
        this.wasVisited = false;
    }

    /**
     * method to initialize the vocal boolean.
     * is set to true if the name begins with an vocal.
     * will be needed to adapt the articles for the words.
     */
    private void initiateVocal(){
        char init = this.name.charAt(0);
        if(init == 'a' || init == 'e' || init == 'i' || init == 'o' || init == 'u'){
            this.vocal = true;
        } else {
            this.vocal = false;
        }
    }

    /**
     * method to check if an enemy is on the field.
     * @return boolean true if there is one, else false.
     */
    public boolean hasEnemy(){
        return this.enemy != null;
    }

    /**
     * method to check if an item is on the field.
     * @return boolean true if there is one, else false.
     */
    public boolean hasItem(){
        return this.item != null;
    }

    // Getter and setter methods.
    public String getName() {
        return name;
    }
    public Enemy getEnemy() {
        return enemy;
    }
    public Item getItem() {
        return item;
    }
    public int getMapID() {
        return mapID;
    }
    public boolean wasVisited() {
        return wasVisited;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public void setMapID(int mapID) {
        this.mapID = mapID;
    }
    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

}
