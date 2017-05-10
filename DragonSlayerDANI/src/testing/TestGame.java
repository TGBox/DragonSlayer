package testing;

import old.text.newTextBasedVersion.classes.Character;
import old.text.newTextBasedVersion.classes.Enemy;
import old.text.newTextBasedVersion.classes.Item;
import old.text.newTextBasedVersion.creators.WeaponCreator;
import old.text.newTextBasedVersion.meta.Difficulty;
import old.text.newTextBasedVersion.meta.Gender;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 01.05.2017, 02:37.
 */
public class TestGame {

    public Character character;
    public Enemy enemy;

    public TestGame(){
        Item weapon = WeaponCreator.createRandomWeapon();
        character = new Character("player", Gender.Apache_Helicopter, Difficulty.EASY, 3, 3);
        enemy = new Enemy("enemy", 2000, weapon, false);

    }

}
