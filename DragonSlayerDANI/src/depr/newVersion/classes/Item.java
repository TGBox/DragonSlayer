package depr.newVersion.classes;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 02.05.2017, 21:09.
 */
public class Item {

    // Standard item variables.
    private String name;
    public boolean vocal;

    // Weapon additional variables.
    private int attack;
    private double accuracy;

    // Health potion variables.
    private int healing;

    // Tool variables.
    private String description;
    private int toolID;

    /**
     * constructor method to create a new item object.
     * @param name the String name for the item.
     */
    public Item(String name){
        this.name = name;
        initiateVocal();
        this.healing = 0;
        this.attack = 0;
        this.accuracy = 0;
        this.toolID = 0;
        this.description = null;
    }

    /**
     * constructor method to create a new healing potion item.
     * @param name the String name for the item.
     * @param healing the int healing value.
     */
    public Item(String name, int healing){
        this.name = name;
        initiateVocal();
        this.healing = healing;
        this.attack = 0;
        this.accuracy = 0;
        this.toolID = 0;
        this.description = null;
    }

    /**
     * constructor method to create a weapon item.
     * @param name String name for the item.
     * @param attack int attack force.
     * @param accuracy double accuracy for the weapon. (the lower, the better!)
     */
    public Item(String name, int attack, double accuracy){
        this.name = name;
        initiateVocal();
        this.healing = 0;
        this.attack = attack;
        this.accuracy = accuracy;
        this.toolID = 0;
        this.description = null;
    }

    /**
     * constructor method to create a tool item.
     * @param name the String name of the item.
     * @param description String description of the item.
     * @param toolID int toolID to determine the later usage.
     */
    public Item(String name, String description, int toolID){
        this.name = name;
        initiateVocal();
        this.healing = 0;
        this.attack = 0;
        this.accuracy = 0;
        this.toolID = toolID;
        this.description = description;
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

    // Getter and Setter methods.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHealing(int healing) {
        this.healing = healing;
    }
    public int getHealing() {
        return healing;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
    public int getAttack() {
        return attack;
    }
    public double getAccuracy() {
        return accuracy;
    }
    public int getToolID() {
        return toolID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setToolID(int toolID) {
        this.toolID = toolID;
    }
}
