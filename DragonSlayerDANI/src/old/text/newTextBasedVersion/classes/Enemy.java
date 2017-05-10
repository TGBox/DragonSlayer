package old.text.newTextBasedVersion.classes;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 15:48.
 */
public class Enemy {

    private String name;
    private int health;
    private Item weapon;
    private int startingHealth;
    private boolean isBoss;

    /**
     * method to create a new enemy object.
     * @param name String name of the enemy.
     * @param health int health value.
     * @param weapon the Item weapon that the enemy carries.
     */
    public Enemy(String name, int health, Item weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.startingHealth = health;
        this.isBoss = false;
    }

    /**
     * method to create a new enemy object.
     * @param name String name of the enemy.
     * @param health int health value.
     * @param weapon the Item weapon that the enemy carries.
     * @param isBoss boolean true if the enemy is a boss, else false.
     */
    public Enemy(String name, int health, Item weapon, boolean isBoss){
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.startingHealth = health;
        this.isBoss = isBoss;
    }

    // Getter and setter methods.
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public Item getWeapon() {
        return weapon;
    }
    public boolean isBoss() {
        return isBoss;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }
    public int getStartingHealth() {
        return startingHealth;
    }
    public void setIsBoss(boolean boss) {
        isBoss = boss;
    }
}
