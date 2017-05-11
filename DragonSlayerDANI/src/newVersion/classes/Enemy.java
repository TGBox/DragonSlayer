package newVersion.classes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 02.05.2017, 21:20.
 */
public class Enemy {

    private String name;
    public boolean vocal;
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
        initiateVocal();
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
        initiateVocal();
        this.health = health;
        this.weapon = weapon;
        this.startingHealth = health;
        this.isBoss = isBoss;
    }

    /**
     * method to inflict damage to the enemy.
     * returns a boolean value to check if the enemy is still alive.
     * @param inflictedDmg the int inflicted damage.
     * @return boolean true if enemy still alive, false if dead.
     */
    public boolean damageAndCheckIfAlive(int inflictedDmg){
        this.health -= inflictedDmg;
        if(this.health <= 0){   // if enemy dies.
            this.health = 0;
            return false;
        } else {                // if enemy survives.
            return true;
        }
    }

    /**
     * method to check if a planned attack of the enemy can hit the player.
     * generates a random double value and checks if it is beyond the threshold of the weapon.
     * @return boolean true if the attack hits, false else.
     */
    public boolean hits(){
        return randDouble() >= this.getWeapon().getAccuracy();
    }

    /**
     * method to generate a random double between 0 and 1.
     * @return the randomly generated double.
     */
    private static double randDouble(){
        return ThreadLocalRandom.current().nextDouble(0.00, 1.00);
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
