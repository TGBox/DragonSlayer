package newVersion.oldCreators;


import newVersion.classes.Item;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 28.04.2017, 22:03.
 */
public class ConsumableCreator {

    private static final String[] PREFIX_NAMES = new String[]{"cursed", "half empty", "dark-purple", "partially empty",
            "blue colored", "light green", "glowing", "enchanted"};
    private static final double[] PREFIX_MULTIPLIERS = new double[]{0.33, 0.55, 0.65, 0.81,
            1.16, 1.28, 1.42, 1.66};

    private static final String DEFAULT_NAME = "healing potion";

    private static final String[] HEALING_VALUE_ADJECTIVES = new String[]{"tiny", "small", "light", "regular-sized",
            "big", "large", "huge"};
    private static final int[] HEALING_VALUES = new int[]{10, 35, 42, 67,
            75, 81, 111};

    /**
     * method to create a new Consumable object.
     * @return the new Consumable object.
     */
    public static Item createConsumable(){
        int value = randomIntInRange(0, HEALING_VALUES.length - 1);
        int healing;
        String name;
        if(ThreadLocalRandom.current().nextBoolean()){
            int prefix = randomIntInRange(0, PREFIX_NAMES.length - 1);
            name = HEALING_VALUE_ADJECTIVES[value] + " " + PREFIX_NAMES[prefix] + " " + DEFAULT_NAME;
            healing = (int)(HEALING_VALUES[value] * PREFIX_MULTIPLIERS[prefix]);
        } else {
            name = HEALING_VALUE_ADJECTIVES[value] + " " + DEFAULT_NAME;
            healing = HEALING_VALUES[value];
        }
        return new Item(name, healing);
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
