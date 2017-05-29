package current.meta.gen;

import static current.meta.Constants.BOSS_ACCURACY_BABY;
import static current.meta.Constants.BOSS_ACCURACY_GOD;
import static current.meta.Constants.BOSS_ACCURACY_NOVICE;
import static current.meta.Constants.BOSS_ACCURACY_WARRIOR;
import static current.meta.Constants.BOSS_ATTACK_MULTIPLIER;
import static current.meta.Constants.BOSS_BASE_ATTACK;
import static current.meta.Constants.BOSS_BASE_LIFE;
import static current.meta.Constants.BOSS_LIFE_MULTIPLIER;
import static current.meta.Constants.BOSS_MULTIPLIER_BABY;
import static current.meta.Constants.BOSS_MULTIPLIER_GOD;
import static current.meta.Constants.BOSS_MULTIPLIER_NOVICE;
import static current.meta.Constants.BOSS_MULTIPLIER_WARRIOR;
import static current.meta.Constants.Difficulty;
import static current.meta.Constants.DragonNames;
import static current.meta.Constants.ENEMY_HEALTH;
import static current.meta.Constants.EnemyNamePrefixes;
import static current.meta.Constants.EnemyNames;
import static current.meta.Constants.asString;
import static current.meta.Constants.getEnumAt;

import current.classes.Enemy;
import current.classes.Item;
import current.meta.Position;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 22:36.
 */
public class EnemyGenerator {

  // TODO add methods for regulated enemies and endgame enemies.

  private static final Position DEFAULT_POSITION = new Position(0, 0);

  /**
   * method to create an adequate enemy for the maps with the new adjectives.
   *
   * @param mapID the mapID that the enemy is on.
   * @param levelOfPlayer the level of the player at the start of the map.
   * @return the newly created Enemy.
   */
  public static Enemy createEnemy(int mapID, int levelOfPlayer) {
    // Value to determine the specs for the enemy.
    int multiplier = mapID * levelOfPlayer;
    Item weapon;
    String name;
    if (multiplier < 2) {     // Enemies get weak weapons if first map and the player is level 1.
      weapon = WeaponGenerator.createStartingWeapon();
    } else {
      weapon = WeaponGenerator.createRandomWeapon();
    }
    int baseIndex = randomIntInRange(0, EnemyNames.values().length - 1);
    int prefixIndex = randomIntInRange(0, EnemyNamePrefixes.values().length - 1);
    int health = ENEMY_HEALTH[baseIndex];
    name = asString(getEnumAt("EnemyNamePrefixes", prefixIndex)) + "" +
        asString(getEnumAt("EnemyNames", baseIndex));

    return new Enemy(name, health, weapon, DEFAULT_POSITION, false, mapID);
  }

  /**
   * method to compare two int value to find the smaller one.
   *
   * @param a int a.
   * @param b int b.
   * @return the smaller int. returns a if equal.
   */
  private static int min(int a, int b) {
    if (a <= b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * method to create a new boss enemy for the current map.
   * uses different values to calculate the bosses weapon and health.
   *
   * @param difficulty Difficulty of the game.
   * @param levelOfPlayer the level of the player.
   * @param mapID the mapID to tell which map is being played.
   * @return the boss enemy.
   */
  public static Enemy createBossEnemy(Difficulty difficulty, int levelOfPlayer, int mapID) {
    int bossAttack = BOSS_BASE_ATTACK + (levelOfPlayer * BOSS_ATTACK_MULTIPLIER) + (mapID
        * BOSS_ATTACK_MULTIPLIER);
    double bossAccuracy;
    int bossHealth =
        BOSS_BASE_LIFE + (BOSS_LIFE_MULTIPLIER * levelOfPlayer) + (BOSS_LIFE_MULTIPLIER * mapID);
    switch (difficulty) {
      case Baby:
        bossHealth *= BOSS_MULTIPLIER_BABY;
        bossAttack *= BOSS_MULTIPLIER_BABY;
        bossAccuracy = BOSS_ACCURACY_BABY;
        break;
      case Novice:
        bossHealth *= BOSS_MULTIPLIER_NOVICE;
        bossAttack *= BOSS_MULTIPLIER_NOVICE;
        bossAccuracy = BOSS_ACCURACY_NOVICE;
        break;
      case Warrior:
        bossHealth *= BOSS_MULTIPLIER_WARRIOR;
        bossAttack *= BOSS_MULTIPLIER_WARRIOR;
        bossAccuracy = BOSS_ACCURACY_WARRIOR;
        break;
      case God:
        bossHealth *= BOSS_MULTIPLIER_GOD;
        bossAttack *= BOSS_MULTIPLIER_GOD;
        bossAccuracy = BOSS_ACCURACY_GOD;
        break;
      default:
        bossAccuracy = BOSS_ACCURACY_NOVICE;
        break;
    }
    Item bossWeapon = new Item("fire breath", bossAttack, bossAccuracy);
    return new Enemy(
        asString(DragonNames.values()[randomIntInRange(0, DragonNames.values().length - 1)]),
        bossHealth, bossWeapon, DEFAULT_POSITION, true, mapID);
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
  private static int randomIntInRange(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

}
