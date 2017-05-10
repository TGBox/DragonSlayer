package newVersion.classes;

import newVersion.meta.Difficulty;
import newVersion.meta.Gender;
import newVersion.meta.Position;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 02.05.2017, 20:56.
 */
public class Character {

    // Shitload of constants.
    private static final int HEALTH_BABY = 250;
    private static final int HEALTH_VERY_EASY = 225;
    private static final int HEALTH_EASY = 175;
    private static final int HEALTH_REGULAR = 125;
    private static final int HEALTH_TOUGH = 100;
    private static final int HEALTH_HARD = 75;
    private static final int HEALTH_EXTREME = 50;
    private static final int HEALTH_NIGHTMARE = 25;

    private static final int HEALTH_BONUS_BABY = 80;
    private static final int HEALTH_BONUS_VERY_EASY = 70;
    private static final int HEALTH_BONUS_EASY = 60;
    private static final int HEALTH_BONUS_REGULAR = 50;
    private static final int HEALTH_BONUS_TOUGH = 40;
    private static final int HEALTH_BONUS_HARD = 30;
    private static final int HEALTH_BONUS_EXTREME = 20;
    private static final int HEALTH_BONUS_NIGHTMARE = 10;

    private static final int BAG_SIZE_BABY = 8;
    private static final int BAG_SIZE_VERY_EASY = 7;
    private static final int BAG_SIZE_EASY = 6;
    private static final int BAG_SIZE_REGULAR = 5;
    private static final int BAG_SIZE_TOUGH = 4;
    private static final int BAG_SIZE_HARD = 3;
    private static final int BAG_SIZE_EXTREME = 2;
    private static final int BAG_SIZE_NIGHTMARE = 1;

    private static final double XP_MULTIPLIER_BABY = 2.00;
    private static final double XP_MULTIPLIER_VERY_EASY = 1.42;
    private static final double XP_MULTIPLIER_EASY = 1.25;
    private static final double XP_MULTIPLIER_REGULAR = 1.00;
    private static final double XP_MULTIPLIER_TOUGH = 0.85;
    private static final double XP_MULTIPLIER_HARD = 0.75;
    private static final double XP_MULTIPLIER_EXTREME = 0.50;
    private static final double XP_MULTIPLIER_NIGHTMARE = 0.42;

    private static final int DEFAULT_BAG_COUNTER = 0;
    private static final int DEFAULT_LEVEL = 1;
    private static final int DEFAULT_EXPERIENCE = 0;
    private static final int DEFAULT_KILL_COUNT = 0;
    private static final int DEFAULT_START_X = 0;
    private static final int DEFAULT_START_Y = 0;

    private static final int LEVEL_THRESHOLD_2 = 50;
    private static final int LEVEL_THRESHOLD_3 = 100;
    private static final int LEVEL_THRESHOLD_4 = 200;
    private static final int LEVEL_THRESHOLD_5 = 400;
    private static final int LEVEL_THRESHOLD_6 = 500;
    private static final int LEVEL_THRESHOLD_7 = 750;
    private static final int LEVEL_THRESHOLD_8 = 1000;
    private static final int LEVEL_THRESHOLD_9 = 1250;
    private static final int LEVEL_THRESHOLD_10 = 1500;
    private static final int LEVEL_THRESHOLD_11 = 2000;

    private String name;
    public boolean vocal;
    private Gender gender;
    private Item weapon;
    private Difficulty difficulty;
    private int health;
    private Item[] bag;
    private int bagCounter;
    private Position pos;
    private int level;
    private int killCount;
    private int experience;
    private int startingHealth;

    /**
     * constructor method to create a new Character object.
     * @param name String name for the character.
     * @param gender Gender of the character.
     * @param difficulty Difficulty of the game.
     * @param weapon Item weapon of the player.
     * @param pos Position on the map.
     */
    public Character(String name, Gender gender, Difficulty difficulty,
        Item weapon, Position pos){
        this.name = name;
        this.gender = gender;
        this.weapon = weapon;
        this.pos = pos;
        this.level = DEFAULT_LEVEL;
        this.bagCounter = DEFAULT_BAG_COUNTER;
        this.experience = DEFAULT_EXPERIENCE;
        this.killCount = DEFAULT_KILL_COUNT;
        this.difficulty = difficulty;
        initiateVocal();
        switch (difficulty){
            case Baby:
                this.bag = new Item[BAG_SIZE_BABY];
                this.health = HEALTH_BABY;
                this.startingHealth = HEALTH_BABY;
                break;
            case VeryEasy:
                this.bag = new Item[BAG_SIZE_VERY_EASY];
                this.health = HEALTH_VERY_EASY;
                this.startingHealth = HEALTH_VERY_EASY;
                break;
            case Easy:
                this.bag = new Item[BAG_SIZE_EASY];
                this.health = HEALTH_EASY;
                this.startingHealth = HEALTH_EASY;
                break;
            case Regular:
                this.bag = new Item[BAG_SIZE_REGULAR];
                this.health = HEALTH_REGULAR;
                this.startingHealth = HEALTH_REGULAR;
                break;
            case Tough:
                this.bag = new Item[BAG_SIZE_TOUGH];
                this.health = HEALTH_TOUGH;
                this.startingHealth = HEALTH_TOUGH;
                break;
            case Hard:
                this.bag = new Item[BAG_SIZE_HARD];
                this.health = HEALTH_HARD;
                this.startingHealth = HEALTH_HARD;
                break;
            case Extreme:
                this.bag = new Item[BAG_SIZE_EXTREME];
                this.health = HEALTH_EXTREME;
                this.startingHealth = HEALTH_EXTREME;
                break;
            case Nightmare:
                this.bag = new Item[BAG_SIZE_NIGHTMARE];
                this.health = HEALTH_NIGHTMARE;
                this.startingHealth = HEALTH_NIGHTMARE;
                break;
            default:
                this.bag = new Item[BAG_SIZE_REGULAR];
                this.health = HEALTH_REGULAR;
                this.startingHealth = HEALTH_REGULAR;
                break;
        }
    }

    /**
     * constructor method to create a new Character object.
     * @param name String name for the character.
     * @param gender Gender of the character.
     * @param difficulty Difficulty of the game.
     * @param weapon Item weapon of the player.
     */
    public Character(String name, Gender gender, Difficulty difficulty,
        Item weapon){
        this.name = name;
        this.gender = gender;
        this.weapon = weapon;
        this.pos = new Position(DEFAULT_START_X, DEFAULT_START_Y);
        this.level = DEFAULT_LEVEL;
        this.bagCounter = DEFAULT_BAG_COUNTER;
        this.experience = DEFAULT_EXPERIENCE;
        this.killCount = DEFAULT_KILL_COUNT;
        this.difficulty = difficulty;
        initiateVocal();
        switch (difficulty){
            case Baby:
                this.bag = new Item[BAG_SIZE_BABY];
                this.health = HEALTH_BABY;
                this.startingHealth = HEALTH_BABY;
                break;
            case VeryEasy:
                this.bag = new Item[BAG_SIZE_VERY_EASY];
                this.health = HEALTH_VERY_EASY;
                this.startingHealth = HEALTH_VERY_EASY;
                break;
            case Easy:
                this.bag = new Item[BAG_SIZE_EASY];
                this.health = HEALTH_EASY;
                this.startingHealth = HEALTH_EASY;
                break;
            case Regular:
                this.bag = new Item[BAG_SIZE_REGULAR];
                this.health = HEALTH_REGULAR;
                this.startingHealth = HEALTH_REGULAR;
                break;
            case Tough:
                this.bag = new Item[BAG_SIZE_TOUGH];
                this.health = HEALTH_TOUGH;
                this.startingHealth = HEALTH_TOUGH;
                break;
            case Hard:
                this.bag = new Item[BAG_SIZE_HARD];
                this.health = HEALTH_HARD;
                this.startingHealth = HEALTH_HARD;
                break;
            case Extreme:
                this.bag = new Item[BAG_SIZE_EXTREME];
                this.health = HEALTH_EXTREME;
                this.startingHealth = HEALTH_EXTREME;
                break;
            case Nightmare:
                this.bag = new Item[BAG_SIZE_NIGHTMARE];
                this.health = HEALTH_NIGHTMARE;
                this.startingHealth = HEALTH_NIGHTMARE;
                break;
            default:
                this.bag = new Item[BAG_SIZE_REGULAR];
                this.health = HEALTH_REGULAR;
                this.startingHealth = HEALTH_REGULAR;
                break;
        }
    }

    /**
     * method to heal the character by a given amount.
     * prevents healing above the maximum amount of health.
     * @param amount int amount of healing.
     * @return the amount that was actually healed.
     */
    public int heal(int amount){
        if(this.health + amount >= startingHealth){
            int before = this.health;
            this.health = this.startingHealth;
            return this.health - before;
        } else {
            this.health += amount;
            return amount;
        }
    }

    /**
     * method to level up a character for killing an enemy.
     * calculates bonus for health for the character.
     * @param killedEnemy the killedEnemy.
     * @return int the amount of levels that were gained.
     */
    public int levelUp(Enemy killedEnemy){
        int lvlBefore = this.level;
        this.experience += calculateGainedXP(killedEnemy.getStartingHealth());
        this.level = calculateNewLevel(this.experience);
        int lvlUpDifference = this.level - lvlBefore;
        if(lvlUpDifference > 0){
            for (int i = 0; i < lvlUpDifference; i++) {
                this.startingHealth += getHealthBonus();
            }
        }
        return lvlUpDifference;
    }

    /**
     * method to get the right health bonus corresponding to the chosen difficulty.
     * is in extra method to keep the level up method shorter.
     * @return int health bonus to be added per level.
     */
    private int getHealthBonus(){
        switch (this.difficulty) {
            case Baby:
                return HEALTH_BONUS_BABY;
            case VeryEasy:
                return HEALTH_BONUS_VERY_EASY;
            case Easy:
                return HEALTH_BONUS_EASY;
            case Regular:
                return HEALTH_BONUS_REGULAR;
            case Tough:
                return HEALTH_BONUS_TOUGH;
            case Hard:
                return HEALTH_BONUS_HARD;
            case Extreme:
                return HEALTH_BONUS_EXTREME;
            case Nightmare:
                return HEALTH_BONUS_NIGHTMARE;
            default:
                return HEALTH_BONUS_REGULAR;
        }
    }

    /**
     * method to calculate the new level with the given constants from the scope.
     * @param xp the current experience of the player.
     * @return the level after the adjustment.
     */
    private int calculateNewLevel(int xp){
        if(xp < LEVEL_THRESHOLD_2){
            return 1;
        } else if(xp >= LEVEL_THRESHOLD_2 && xp < LEVEL_THRESHOLD_3){
            return 2;
        } else if(xp >= LEVEL_THRESHOLD_3 && xp < LEVEL_THRESHOLD_4){
            return 3;
        } else if(xp >= LEVEL_THRESHOLD_4 && xp < LEVEL_THRESHOLD_5){
            return 4;
        } else if(xp >= LEVEL_THRESHOLD_5 && xp < LEVEL_THRESHOLD_6){
            return 5;
        } else if(xp >= LEVEL_THRESHOLD_6 && xp < LEVEL_THRESHOLD_7){
            return 6;
        } else if(xp >= LEVEL_THRESHOLD_7 && xp < LEVEL_THRESHOLD_8){
            return 7;
        } else if(xp >= LEVEL_THRESHOLD_8 && xp < LEVEL_THRESHOLD_9){
            return 8;
        } else if(xp >= LEVEL_THRESHOLD_9 && xp < LEVEL_THRESHOLD_10){
            return 9;
        } else if(xp >= LEVEL_THRESHOLD_10 && xp < LEVEL_THRESHOLD_11){
            return 10;
        } else {
            return 11;
        }
    }

    /**
     * method to calculate the gained experience for the separate difficulties.
     * @param xp the int amount of experience.
     * @return the actual amount of gained experience.
     */
    private int calculateGainedXP(int xp){
        switch (this.difficulty){
            case Baby:
                return (int)(xp * XP_MULTIPLIER_BABY);
            case VeryEasy:
                return (int)(xp * XP_MULTIPLIER_VERY_EASY);
            case Easy:
                return (int)(xp * XP_MULTIPLIER_EASY);
            case Regular:
                return (int)(xp * XP_MULTIPLIER_REGULAR);
            case Tough:
                return (int)(xp * XP_MULTIPLIER_TOUGH);
            case Hard:
                return (int)(xp * XP_MULTIPLIER_HARD);
            case Extreme:
                return (int)(xp * XP_MULTIPLIER_EXTREME);
            case Nightmare:
                return (int)(xp * XP_MULTIPLIER_NIGHTMARE);
            default:
                return (int)(xp * XP_MULTIPLIER_REGULAR);
        }
    }

    /**
     * method to delete an item from the bag at a given index.
     * @param index the int index.
     * @return boolean true if the item was deleted, false if not.
     */
    public boolean deleteItem(int index){
        if(index < 0 || index > bagCounter){
            return false;
        } else {
            if(bag[index] != null){
                bag[index] = null;
            }
            for(int i = index; i < bagCounter; i++){
                bag[i] = bag[i + 1];
            }
            bagCounter--;
            return true;
        }
    }

    /**
     * method to add an item to the bag.
     * @param item the item that needs to be added.
     */
    public boolean addItem(Item item){
        if(bagCounter < bag.length - 1){
            bag[bagCounter] = item;
            bagCounter++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * method to enlarge the bag by one slot.
     */
    public void enlargeBag(){
        Item[] oldBag = bag;
        bag = new Item[oldBag.length + 1];
        for(int i = 0; i < oldBag.length; i++){
            bag[i] = oldBag[i];
        }
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
    public Gender getGender() {
        return gender;
    }
    public Item getWeapon() {
        return weapon;
    }
    public int getHealth() {
        return health;
    }
    public Item[] getBag() {
        return bag;
    }
    public int getBagCounter() {
        return bagCounter;
    }
    public Position getPos() {
        return pos;
    }
    public int getLevel() {
        return level;
    }
    public int getKillCount() {
        return killCount;
    }
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }
    public int getExperience() {
        return experience;
    }
    public int getStartingHealth() {
        return startingHealth;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setPos(Position pos) {
        this.pos = pos;
    }
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setBag(Item[] bag) {
        this.bag = bag;
    }
    public void setBagCounter(int bagCounter) {
        this.bagCounter = bagCounter;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setStartingHealth(int startingHealth) {
        this.startingHealth = startingHealth;
    }

}
