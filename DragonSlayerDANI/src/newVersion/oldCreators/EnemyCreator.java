package newVersion.oldCreators;

import newVersion.meta.GameConstants.Difficulty;
import newVersion.classes.Enemy;
import newVersion.classes.Item;

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

    private static final String[] OPINION = new String[]{"angry", "evil", "nightmare", "devilish"};
    private static final double[] OPINION_VALS = new double[]{1.11, 1.42, 1.66, 1.87};

    private static final String[] SIZE = new String[]{"tiny", "small", "short", "thin", "fat", "tall", "large",
            "giant", "enormous", "monstrous"};
    private static final double[] SIZE_VALS = new double[]{0.25, 0.33, 0.65, 0.85, 0.90, 1.20, 1.45,
            1.65, 1.85, 2.05};

    private static final String[] STRENGTH = new String[]{"half-dead", "wounded", "very weak", "weak",
            "strong", "muscular", "very strong"};
    private static final double[] STRENGTH_VALS = new double[]{0.42, 0.67, 0.75, 0.86, 1.20, 1.35, 1.89};

    private static final String[] NAMES = new String[]{"fairy", "spider", "gnome", "elf", "ghoul", "dwarf",
            "mummy", "zombie", "goblin", "ogre", "skeleton", "troll", "living tree", "griffin", "golem", "knight",
            "unicorn", "centaur", "minotaur", "vampire", "werewolf", "phoenix", "demon"};
    private static final int[] HEALTH = new int[]{22, 29, 35, 42, 55, 68,
            72, 76, 79, 85, 96, 101, 105, 111, 123, 133,
            139, 142, 153, 158, 162, 166, 170};

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
    private static final double BOSS_MULTIPLIER_BABY_VALUE = 0.42;
    private static final double BOSS_MULTIPLIER_VERY_EASY_VALUE = 0.55;
    private static final double BOSS_MULTIPLIER_EASY_VALUE = 0.75;
    private static final double BOSS_MULTIPLIER_REGULAR_VALUE = 1.00;
    private static final double BOSS_MULTIPLIER_TOUGH_VALUE = 1.25;
    private static final double BOSS_MULTIPLIER_HARD_VALUE = 1.50;
    private static final double BOSS_MULTIPLIER_EXTREME_VALUE = 2.00;
    private static final double BOSS_MULTIPLIER_NIGHTMARE_VALUE = 5.00;
    private static final int BOSS_BASE_ATTACK = 65;
    private static final int BOSS_ATTACK_MULTIPLIER = 10;
    private static final double BOSS_ACCURACY_BABY = 0.89;
    private static final double BOSS_ACCURACY_VERY_EASY = 0.85;
    private static final double BOSS_ACCURACY_EASY = 0.80;
    private static final double BOSS_ACCURACY_REGULAR = 0.75;
    private static final double BOSS_ACCURACY_TOUGH = 0.65;
    private static final double BOSS_ACCURACY_HARD = 0.55;
    private static final double BOSS_ACCURACY_EXTREME = 0.45;
    private static final double BOSS_ACCURACY_NIGHTMARE = 0.11;

    /**
     * method to create an adequate enemy for the maps with the new adjectives.
     * @param mapID the mapID that the enemy is on.
     * @param levelOfPlayer the level of the player at the start of the map.
     * @return the newly created Enemy.
     */
    public static Enemy createEnemy(int mapID, int levelOfPlayer){
        // Value to determine the specs for the enemy.
        int multiplier = mapID * levelOfPlayer;
        Item weapon;
        String name;
        if(multiplier < 2){     // Enemies get weak weapons if we are on the first map and the player is level 1.
            weapon = WeaponCreator.createStartingWeapon();
        } else {
            weapon = WeaponCreator.createRandomWeapon();
        }
        int baseIndex = randomIntInRange(0, NAMES.length - 1);
        int health = HEALTH[baseIndex];
        name = NAMES[baseIndex];
        int prefixIndex;
        // Then we get the prefix for the enemy.
        // index is generated randomly, multiplier determines how many attributes can be used.
        switch (randomIntInRange(0, 3)){
            case 0:
                prefixIndex = randomIntInRange(0, min(OPINION.length - 1, multiplier));
                name = OPINION[prefixIndex] + " " + name;
                health *= OPINION_VALS[prefixIndex];
                break;
            case 1:
                prefixIndex = randomIntInRange(0, min(SIZE.length - 1, multiplier));
                name = SIZE[prefixIndex] + " " + name;
                health *= SIZE_VALS[prefixIndex];
                break;
            case 2:
                prefixIndex = randomIntInRange(0, min(STRENGTH.length - 1, multiplier));
                name = STRENGTH[prefixIndex] + " " + name;
                health *= STRENGTH_VALS[prefixIndex];
                break;
            case 3:
                name = "regular " + name;
                break;
            default:
                break;
        }
        return new Enemy(name, health, weapon, false);
    }

    /**
     * method to compare two int value to find the smaller one.
     * @param a int a.
     * @param b int b.
     * @return the smaller int. returns a if equal.
     */
    private static int min(int a, int b){
        if(a <= b){
            return a;
        } else return b;
    }

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
            case Baby:
                bossHealth *= BOSS_MULTIPLIER_BABY_VALUE;
                bossAttack *= BOSS_MULTIPLIER_BABY_VALUE;
                bossAccuracy = BOSS_ACCURACY_BABY;
                break;
            case VeryEasy:
                bossHealth *= BOSS_MULTIPLIER_VERY_EASY_VALUE;
                bossAttack *= BOSS_MULTIPLIER_VERY_EASY_VALUE;
                bossAccuracy = BOSS_ACCURACY_VERY_EASY;
                break;
            case Easy:
                bossHealth *= BOSS_MULTIPLIER_EASY_VALUE;
                bossAttack *= BOSS_MULTIPLIER_EASY_VALUE;
                bossAccuracy = BOSS_ACCURACY_EASY;
                break;
            case Regular:
                bossHealth *= BOSS_MULTIPLIER_REGULAR_VALUE;
                bossAttack *= BOSS_MULTIPLIER_REGULAR_VALUE;
                bossAccuracy = BOSS_ACCURACY_REGULAR;
                break;
            case Tough:
                bossHealth *= BOSS_MULTIPLIER_TOUGH_VALUE;
                bossAttack *= BOSS_MULTIPLIER_TOUGH_VALUE;
                bossAccuracy = BOSS_ACCURACY_TOUGH;
                break;
            case Hard:
                bossHealth *= BOSS_MULTIPLIER_HARD_VALUE;
                bossAttack *= BOSS_MULTIPLIER_HARD_VALUE;
                bossAccuracy = BOSS_ACCURACY_HARD;
                break;
            case Extreme:
                bossHealth *= BOSS_MULTIPLIER_EXTREME_VALUE;
                bossAttack *= BOSS_MULTIPLIER_EXTREME_VALUE;
                bossAccuracy = BOSS_ACCURACY_EXTREME;
                break;
            case Nightmare:
                bossHealth *= BOSS_MULTIPLIER_NIGHTMARE_VALUE;
                bossAttack *= BOSS_MULTIPLIER_NIGHTMARE_VALUE;
                bossAccuracy = BOSS_ACCURACY_NIGHTMARE;
                break;
            default:
                bossAccuracy = BOSS_ACCURACY_REGULAR;
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
