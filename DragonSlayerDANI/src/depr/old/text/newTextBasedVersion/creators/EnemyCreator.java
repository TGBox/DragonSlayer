package depr.old.text.newTextBasedVersion.creators;

import depr.old.text.newTextBasedVersion.meta.Difficulty;
import depr.old.text.newTextBasedVersion.classes.Enemy;
import depr.old.text.newTextBasedVersion.classes.Item;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 15:53.
 */
public class EnemyCreator {

    private static final String[] PREFIX_NAMES = new String[]{"almost dead", "wounded", "very weak", "weak", "small",
            "regular", "angry", "strong", "large", "very strong", "huge", "giant", "evil", "nightmare", "devilish"};
    private static final double[] PREFIX_VALUES = new double[]{0.22, 0.37, 0.48, 0.66, 0.85,
            1.00, 1.13, 1.19, 1.22, 1.36, 1.45, 1.55, 1.66, 1.88, 1.96};

    private static final String[] ENEMY_NAMES = new String[]{"fairy", "spider", "gnome", "elf", "ghoul", "dwarf",
            "mummy", "zombie", "goblin", "ogre", "skeleton", "troll", "living tree", "griffin", "golem", "knight",
            "unicorn", "centaur", "minotaur", "vampire", "werewolf", "phoenix", "demon"};
    private static final int[] BASE_HEALTH = new int[]{22, 29, 35, 42, 55, 68,
            72, 76, 79, 85, 96, 101, 105, 111, 123, 133,
            139, 142, 153, 158, 162, 166, 170};

    private static final String[] SUFFIX_NAMES = new String[]{"with only one arm", "that seems scared",
            "that looks dangerous", "that has a strong aura of power", "king"};
    private static final double[] SUFFIX_VALUES = new double[]{0.33, 0.67,
            1.22, 1.74, 2.16};

    private static final String[] BOSS_NAMES = new String[]{"Smaug", "Drogon", "Igneel", "Porunga", "Shenlong",
            "Mushu", "Predaking", "Tabaluga", "Toothless", "Alduin", "Kalameet", "Paarthurnax", "Spyro"};

    private static final int BOSS_BASE_LIFE = 125;
    private static final int BOSS_LIFE_MULTIPLIER = 50;
    private static final double BOSS_MULTIPLIER_EASY_VALUE = 0.75;
    private static final double BOSS_MULTIPLIER_MEDIUM_VALUE = 1.00;
    private static final double BOSS_MULTIPLIER_HARD_VALUE = 1.25;
    private static final int BOSS_BASE_ATTACK = 65;
    private static final int BOSS_ATTACK_MULTIPLIER = 10;
    private static final double BOSS_ACCURACY_EASY = 0.65;
    private static final double BOSS_ACCURACY_MEDIUM = 0.55;
    private static final double BOSS_ACCURACY_HARD = 0.50;


    /**
     * method to create a completely random enemy.
     * @param mapID int mapID to adjust the enemies to the current map.
     * @return the enemy.
     */
    public static Enemy createRandomEnemy(int mapID){
        if(ThreadLocalRandom.current().nextBoolean()){
            int decider1, decider2, decider3;
            decider1 = randomIntInRange(0, PREFIX_NAMES.length - 1);
            decider2 = randomIntInRange(0, ENEMY_NAMES.length - 1);
            decider3 = randomIntInRange(0, SUFFIX_NAMES.length - 1);
            String name = PREFIX_NAMES[decider1] + " " + ENEMY_NAMES[decider2] + " " + SUFFIX_NAMES[decider3];
            int health = (int)(BASE_HEALTH[decider2] * PREFIX_VALUES[decider1] * SUFFIX_VALUES[decider3]) * mapID;
            Item weapon = WeaponCreator.createRandomWeapon();

            return new Enemy(name, health, weapon);
        } else {
            int decider1, decider2;
            decider1 = randomIntInRange(0, PREFIX_NAMES.length - 1);
            decider2 = randomIntInRange(0, ENEMY_NAMES.length - 1);
            String name = PREFIX_NAMES[decider1] + " " + ENEMY_NAMES[decider2];
            int health = (int)(BASE_HEALTH[decider2] * PREFIX_VALUES[decider1]) * mapID;
            Item weapon = WeaponCreator.createRandomWeapon();

            return new Enemy(name, health, weapon);
        }

    }

    /**
     * method to create a restricted enemy.
     * @param maxIndexPrefix the maximum index int for the prefix array.
     * @param maxIndexName the maximum index int for the name array.
     * @param maxIndexSuffix the maximum index int for the suffix array.
     * @param badWeapon boolean, true if the weapon should be bad, false for random weapon.
     * @param mapID int mapID to adjust the enemies to the current map.
     * @return the restricted enemy.
     */
    public static Enemy createRestrictedEnemy(int maxIndexPrefix, int maxIndexName,
                                              int maxIndexSuffix, boolean badWeapon, int mapID){
        if(maxIndexPrefix >= PREFIX_NAMES.length ||
                maxIndexName >= ENEMY_NAMES.length ||
                maxIndexSuffix >= SUFFIX_NAMES.length){
            return createRandomEnemy(mapID);
        }
        int decider1, decider2, decider3;
        decider1 = randomIntInRange(0, maxIndexPrefix);
        decider2 = randomIntInRange(0, maxIndexName);
        decider3 = randomIntInRange(0, maxIndexSuffix);
        String name = PREFIX_NAMES[decider1] + " " + ENEMY_NAMES[decider2] + " " + SUFFIX_NAMES[decider3];
        int health = (int)(BASE_HEALTH[decider2] * PREFIX_VALUES[decider1] * SUFFIX_VALUES[decider3]);
        Item weapon;
        if(badWeapon){
            weapon = WeaponCreator.createStartingWeapon();
        } else {
            weapon = WeaponCreator.createRandomWeapon();
        }
        return new Enemy(name, health, weapon);
    }

    /**
     * method to create a new boss enemy for the current map.
     * uses different values to calculate the bosses weapon and health.
     * @param difficulty Difficulty of the game.
     * @param levelOfPlayer the level of the player.
     * @param mapID the mapID to tell which map is being played.
     * @return the boss enemy.
     */
    public static Enemy createBossEnemy(Difficulty difficulty, int levelOfPlayer, int mapID){
        int bossAttack = BOSS_BASE_ATTACK + (levelOfPlayer * BOSS_ATTACK_MULTIPLIER) + (mapID * BOSS_ATTACK_MULTIPLIER);
        double bossAccuracy;
        int bossHealth = BOSS_BASE_LIFE + (BOSS_LIFE_MULTIPLIER * levelOfPlayer) + (BOSS_LIFE_MULTIPLIER * mapID);
        switch (difficulty){
            case EASY:
                bossHealth *= BOSS_MULTIPLIER_EASY_VALUE;
                bossAttack *= BOSS_MULTIPLIER_EASY_VALUE;
                bossAccuracy = BOSS_ACCURACY_EASY;
                break;
            case MEDIUM:
                bossHealth *= BOSS_MULTIPLIER_MEDIUM_VALUE;
                bossAttack *= BOSS_MULTIPLIER_MEDIUM_VALUE;
                bossAccuracy = BOSS_ACCURACY_MEDIUM;
                break;
            case HARD:
                bossHealth *= BOSS_MULTIPLIER_HARD_VALUE;
                bossAttack *= BOSS_MULTIPLIER_HARD_VALUE;
                bossAccuracy = BOSS_ACCURACY_HARD;
                break;
            default:
                bossAccuracy = BOSS_ACCURACY_MEDIUM;
                break;
        }
        Item bossWeapon = new Item("fire breath", bossAttack, bossAccuracy);
        return new Enemy(BOSS_NAMES[randomIntInRange(0, BOSS_NAMES.length - 1)], bossHealth, bossWeapon, true);
    }

    /**
     * method to create a hard enemy that can be placed on endgame maps.
     * @return the hard Enemy.
     */
    public static Enemy createHardEnemy(){
        if(ThreadLocalRandom.current().nextBoolean()){
            int decider1, decider2, decider3;
            decider1 = randomIntInRange(6, PREFIX_NAMES.length - 1);
            decider2 = randomIntInRange(7, ENEMY_NAMES.length - 1);
            decider3 = randomIntInRange(2, SUFFIX_NAMES.length - 1);
            String name = PREFIX_NAMES[decider1] + " " + ENEMY_NAMES[decider2] + " " + SUFFIX_NAMES[decider3];
            int health = (int)(BASE_HEALTH[decider2] * PREFIX_VALUES[decider1] * SUFFIX_VALUES[decider3]);
            Item weapon = WeaponCreator.createRandomWeapon();

            return new Enemy(name, health, weapon);
        } else {
            int decider1, decider2;
            decider1 = randomIntInRange(5, PREFIX_NAMES.length - 1);
            decider2 = randomIntInRange(9, ENEMY_NAMES.length - 1);
            String name = PREFIX_NAMES[decider1] + " " + ENEMY_NAMES[decider2];
            int health = (int)(BASE_HEALTH[decider2] * PREFIX_VALUES[decider1]);
            Item weapon = WeaponCreator.createRandomWeapon();

            return new Enemy(name, health, weapon);
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

}
