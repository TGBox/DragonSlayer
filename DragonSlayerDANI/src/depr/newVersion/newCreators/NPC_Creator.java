package depr.newVersion.newCreators;

import java.util.concurrent.ThreadLocalRandom;
import depr.newVersion.classes.Item;
import depr.newVersion.classes.NPC;
import depr.newVersion.meta.NPCWithItem;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 05.05.2017, 00:19.
 */
public class NPC_Creator {

    // NPCs get separated in several groups to keep diversity in the occurrences.
    // one index gets used for name, item and description do ensure believable encounters.
    // Messages are furthermore separated to have space for the item names.
    // Messages get randomly assembled from parts from respective categories.
    // A + name + B + item + C = MEETING_MESSAGE

    // Expand NPC Creator.
    private static final String[] PREFIXES = new String[]{"depr/old ", "wounded ", "fat ",
        "tall ", "muscular "};
    private static final double[] PREFIX_VALS = new double[]{0.42, 0.66, 0.73, 1.25, 1.48};
    private static final String[] NPC_NAMES_1 = new String[]{"hunter", "painter", "baker",
        "blacksmith", "cook", "bard", "lumberjack", "prince"};
    private static final String[] NPC_WEAPONS_1 = new String[]{"hunter's knife", "giant scissors",
        "baking pan", "razor-sharp two-handed sword", "butcher knife", "brass knuckles",
        "bare hands", "walking cane"};
    private static final int[] WEAPON_ATTACKS = new int[]{75, 45, 35, 180, 88, 65, 90, 23};
    private static final double[] ACCURACIES = new double[]{0.35, 0.45, 0.55, 0.65, 0.75, 0.85};
    private static final int[] NPC_HEALTH_1 = new int[]{140, 45, 65, 160, 75, 35, 165, 100};
    private static final String[] LOST_ITEMS_1 = new String[]{"bow", "brush", "cooking spoon",
        "hammer", "knife", "lute", "axe", "crown"};
    private static final String[] ITEM_DESCRIPTIONS_1 = new String[]{"A simple handmade bow. "
        + "Used to hunt animals.", "A brush used by artists to paint.",
        "A spoon used for baking and cooking.", "A hammer used by a blacksmith.",
        "A sharp knife used by a cook to cut meat.", "A lute to play musical tunes on it.",
            "An axe used to chop down trees.", "A crown, like it is worn by royals."};
    private static final String[] MEET_MSG_1_A = new String[]{"Hello there "
        + "fellow traveler! I'm a ", "Greeting my friend! I'm a ", "Oh hello there, I'm a ",
        "Oh thank god you came here! I'm a ", "The lord must have sent you here! I'm a "};
    private static final String[] MEET_MSG_1_B = new String[]{" and I lost my ",
            " and don't ask how, but I lost my ", " and I can't find my ", " and I don't "
        + "know where I put my "};
    private static final String[] MEET_MSG_1_C = new String[]{" and as you can imagine,\n" +
            "this is a quite stressful position for me to be in. You know, "
        + "I could loose my job if I don't get it back!\nWould you be so nice to search it "
        + "for me? Just come back here when you've found it.",
            ". Can you find it?\nPlease bring it back to me if you see it anywhere.",
            ". Will you search it for me?\nI will reward you if you do!",
            ". Pleeeease search it for me. *starts crying*"};
    private static final String[] QUEST_NOT_COMPLETE_MSG_1_A = new String[]{"Oh, you "
        + "haven't found my ", "Hooray you are back! But you didn't bring my ",
        "Hmm, I thought you would come back with my ", "Don't come back until you've found my "};
    private static final String[] QUEST_NOT_COMPLETE_MSG_1_B = new String[]{"!", "!?", "?",
        ", I don't have any more time to wait!", ", I will reward you!"};
    private static final String[] QUEST_COMPLETE_MSG_1_A = new String[]{"Yeah, you found it!",
    "Nice one, I knew I could count on you!", "Oh you brought it back? That is awesome!",
        "Hooray! You found it!", "Great, now I can finally work again!"};
    private static final String[] QUEST_COMPLETE_MSG_1_B = new String[]{" Here, take this "
        + "as a gift! It's not much, but it's all that I have!", " Here is your reward.",
        " Thanks again. And this is for your efforts.", " Have this as a thank you gift from me."};
    private static final String[] ATTACK_RESPONSES_1 = new String[]{"Oh that was a wrong decision!",
        "This will be the last battle you fight kiddo!", "Don't mess with me!", "I will wreck you!",
        "Bad idea motherfucker!", "Oh you want to battle? Then prepare for pain!"};

    private static final String[] NPC_NAMES_2 = new String[]{"rabbit", "bird", "cat", "dog",
        "turtle", "chicken", "horse", "wolf", "pig"};
    private static final String[] NPC_ITEMS_2 = new String[]{"carrot", "worm", "fish", "bone",
        "porn magazine", "seeds", "sugar cube", "toothbrush", "hat"};
    private static final String[] ITEM_DESCRIPTIONS_2 = new String[]{"A carrot with bite marks, "
        + "that looks like they're from a rabbit.", "This is an earthworm that a bird would eat.",
        "A delicious fish. Loved by cats.", "Bone that was buried in the ground by a dog.",
        "Filthy animal porn magazine; Big Turtle Asses is the name.",
        "Seeds that would make any bird happy.", "A sugar cube like it is loved by every horse.",
        "Toothbrush for wolves.", "A hat worn by pigs."};
    private static final int[] NPC_HEALTH_2 = new int[]{50, 30, 65, 70, 100, 35, 150, 180, 130};
    private static final int[] NPC_ATTACK_2 = new int[]{35, 25, 40, 65, 10, 15, 90, 100, 42};
    private static final String[] MEET_MSG_2_A = new String[]{"Hello! I am a magical speaking ",
    "Howdy! I bet you've never seen a talking ", "Hey! I'm an enchanted "};
    private static final String[] MEET_MSG_2_B = new String[]{". I lost my ", " and I lost my ",
        " and I have a problem! I lost my ", ". I lost something! My "};
    private static final String[] MEET_MSG_2_C = new String[]{"! Can you find it for me?",
        ", can you bring it back? I will reward you with a special gift!",
        ". Will you search it for me? I will reward your efforts!"};
    private static final String[] QUEST_NOT_COMPLETE_MSG_2_A = new String[]{
        "Oh you haven't found my ", "Oh, I thought you would come back with my ",
        "Thank you, you... you haven't found my "};
    private static final String[] QUEST_NOT_COMPLETE_MSG_2_B = new String[]{
        " yet? Please come back with it!", "? Find it please!", "? Please bring it to me."};
    private static final String[] QUEST_COMPLETE_MSG_2 = new String[]{"Oh thanks, you found it! "
        + "Here, take this as a reward!", "Fantastic! You found it! Here, have this as a reward!",
        "Great, now I have it back! Here, take this as a thank you gift!"};
    private static final String[] ATTACK_RESPONSES_2 = new String[]{
        "Oh no, that was a bad decision!", "I'll fuck you up!", "Bad idea to attack me!",
        "Oh, that was a big mistake!"};

    private static final String NPC_0 = "Daniel the beatboxer";
    private static final int HEALTH_0 = 200;
    private static final double ACC_0 = 42.00;
    private static final int ATTACK_0 = 100;
    private static final String WEAPON_NAME_0 = "dubstep beatbox";
    private static final String LOST_ITEM_0 = "a bag of weed";
    private static final String DESCRIPTION_0 = "A bag of weed with the name \"Daniel\" on it.";
    private static final String MEET_0 = "Hello there dragon slayer! I have wondered how long "
        + "it would take you\nto find me. I have a problem on my hands. I lost my weed! If you "
        + "find it for me,\nI will highly reward you!";
    private static final String Q_N_C_0 = "Haven't you found it yet? You are slow man! "
        + "Please find it and come back!";
    private static final String Q_C_0 = "Oh you have found it! Wicked dude! "
        + "Here, take this as my thank you gift!";
    private static final String ATTACK_RESP_0 = "Oh man, haven't beaten someone in a "
        + "long time! Let's dance!";


    public static final int CATEGORY_0 = 0;
    public static final int CATEGORY_1 = 1;
    public static final int CATEGORY_2 = 2;

    /**
     * method to create an NPC with a corresponding quest item.
     * both should be placed on the same map to ensure that the quest will be solvable.
     * @param category int to determine the category of npc. should be one of the predefined vals.
     * @param mapID int mapID.
     * @param npcNrOnMap int number of the npc on the current map to set correct tool and questIDs.
     * @return the NPCWithItem object.
     */
    public static NPCWithItem createNPCByCategory(int category, int mapID, int npcNrOnMap){
        switch (category){
            case CATEGORY_0:
                Item weapon0 = new Item(WEAPON_NAME_0, ATTACK_0, ACC_0);
                int toolID0 = (mapID * 100) + (npcNrOnMap * 10);
                Item questItem0 = new Item(LOST_ITEM_0, DESCRIPTION_0, toolID0);
                Item reward0 = new Item("420 cannon", 420, 42.0);
                String[] sentences0 = new String[4];
                sentences0[0] = MEET_0;
                sentences0[1] = Q_N_C_0;
                sentences0[2] = Q_C_0;
                sentences0[3] = ATTACK_RESP_0;
                NPC npc0 = new NPC(NPC_0, HEALTH_0, weapon0, toolID0, reward0, sentences0);
                return new NPCWithItem(npc0, questItem0);
            case CATEGORY_1:
                int generalIndex = rand(0, LOST_ITEMS_1.length - 1);
                int preIndex = rand(0, PREFIXES.length - 1);
                int health = (int)(PREFIX_VALS[preIndex] * NPC_HEALTH_1[generalIndex]);
                String weaponName = NPC_WEAPONS_1[generalIndex];
                int attack = (int)(WEAPON_ATTACKS[generalIndex] * PREFIX_VALS[preIndex]);
                double accuracy = ACCURACIES[rand(0, ACCURACIES.length - 1)];
                Item weapon = new Item(weaponName, attack, accuracy);
                String name = PREFIXES[preIndex] + NPC_NAMES_1[generalIndex];
                String item = LOST_ITEMS_1[generalIndex];
                String description = ITEM_DESCRIPTIONS_1[generalIndex];
                int toolID = (mapID * 100) + (npcNrOnMap * 10);
                Item questItem = new Item(item, description, toolID);
                String[] sentences = new String[4];
                sentences[0] = MEET_MSG_1_A[rand(0, MEET_MSG_1_A.length - 1)] + name;
                sentences[0] += MEET_MSG_1_B[rand(0, MEET_MSG_1_B.length - 1)] + item;
                sentences[0] += MEET_MSG_1_C[rand(0, MEET_MSG_1_C.length - 1)];
                sentences[1] = QUEST_NOT_COMPLETE_MSG_1_A[
                    rand(0, QUEST_NOT_COMPLETE_MSG_1_A.length - 1)] + item +
                    QUEST_NOT_COMPLETE_MSG_1_B[rand(0, QUEST_NOT_COMPLETE_MSG_1_B.length - 1)];
                sentences[2] = QUEST_COMPLETE_MSG_1_A[
                    rand(0, QUEST_COMPLETE_MSG_1_A.length - 1)] +
                    QUEST_COMPLETE_MSG_1_B[rand(0, QUEST_COMPLETE_MSG_1_B.length - 1)];
                sentences[3] = ATTACK_RESPONSES_1[rand(0, ATTACK_RESPONSES_1.length - 1)];
                Item reward;
                switch (rand(0, 3)){
                    case 0:     // weapon as reward.
                        reward = WeaponCreator.createRewardWeapon();
                        break;
                    case 1:     // xp booster.
                        reward = ToolCreator.createXPBooster(health, toolID + 1);
                        break;
                    case 2:     // health booster.
                        reward = ToolCreator.createMaxHealthBooster(health / 2,
                            toolID + 1);
                        break;
                    case 3:     // accuracy booster.
                        reward = ToolCreator.createAccuracyBooster(rand(10, 20),
                            toolID + 1);
                        break;
                    default:    // weapon
                        reward = WeaponCreator.createRewardWeapon();
                        break;
                }
                NPC npc = new NPC(name, health, weapon, toolID, reward, sentences);
                return new NPCWithItem(npc, questItem);
            case CATEGORY_2:
                int genIndex = rand(0, NPC_NAMES_2.length - 1);
                int health2 = NPC_HEALTH_2[genIndex];
                String weaponName2 = "claws and teeth";
                int attack2 = NPC_ATTACK_2[genIndex];
                double accuracy2 = ACCURACIES[rand(0, ACCURACIES.length - 1)];
                Item weapon2 = new Item(weaponName2, attack2, accuracy2);
                String name2 = NPC_NAMES_2[genIndex];
                String item2 = NPC_ITEMS_2[genIndex];
                String description2 = ITEM_DESCRIPTIONS_2[genIndex];
                int toolID2 = (mapID * 100) + (npcNrOnMap * 10);
                Item questItem2 = new Item(item2, description2, toolID2);
                // order: meetMsg, notCompl, compl, attackResp;
                String[] sentences2 = new String[4];
                sentences2[0] = MEET_MSG_2_A[rand(0, MEET_MSG_2_A.length - 1)] + name2;
                sentences2[0] += MEET_MSG_2_B[rand(0, MEET_MSG_2_B.length - 1)] + item2;
                sentences2[0] += MEET_MSG_2_C[rand(0, MEET_MSG_2_C.length - 1)];
                sentences2[1] = QUEST_NOT_COMPLETE_MSG_2_A[
                    rand(0, QUEST_NOT_COMPLETE_MSG_2_A.length - 1)] + item2 +
                    QUEST_NOT_COMPLETE_MSG_1_B[rand(0, QUEST_NOT_COMPLETE_MSG_2_B.length - 1)];
                sentences2[2] = QUEST_COMPLETE_MSG_2[
                    rand(0, QUEST_COMPLETE_MSG_2.length - 1)];
                sentences2[3] = ATTACK_RESPONSES_2[rand(0, ATTACK_RESPONSES_2.length - 1)];
                Item reward2;
                switch (rand(0, 3)){
                    case 0:     // weapon as reward.
                        reward2 = WeaponCreator.createRewardWeapon();
                        break;
                    case 1:     // xp booster.
                        reward2 = ToolCreator.createXPBooster(health2, toolID2 + 1);
                        break;
                    case 2:     // health booster.
                        reward2 = ToolCreator.createMaxHealthBooster(health2 / 2,
                            toolID2 + 1);
                        break;
                    case 3:     // accuracy booster.
                        reward2 = ToolCreator.createAccuracyBooster(rand(10, 20),
                            toolID2 + 1);
                        break;
                    default:    // weapon
                        reward2 = WeaponCreator.createRewardWeapon();
                        break;
                }
                NPC npc2 = new NPC(name2, health2, weapon2, toolID2, reward2, sentences2);
                return new NPCWithItem(npc2, questItem2);
            default:    // I'm default.
                weapon0 = new Item(WEAPON_NAME_0, ATTACK_0, ACC_0);
                toolID0 = (mapID * 100) + (npcNrOnMap * 10);
                questItem0 = new Item(LOST_ITEM_0, DESCRIPTION_0, toolID0);
                reward0 = new Item("420 cannon", 420, 42.0);
                sentences0 = new String[4];
                sentences0[0] = MEET_0;
                sentences0[1] = Q_N_C_0;
                sentences0[2] = Q_C_0;
                sentences0[3] = ATTACK_RESP_0;
                npc0 = new NPC(NPC_0, HEALTH_0, weapon0, toolID0, reward0, sentences0);
                return new NPCWithItem(npc0, questItem0);
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
    private static int rand(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void main(String[] args) {
        int item_nr = rand(0, LOST_ITEMS_1.length - 1);
        String name = NPC_NAMES_1[item_nr];
        String item = LOST_ITEMS_1[item_nr];
        String description = ITEM_DESCRIPTIONS_1[item_nr];
        String meeting_msg = MEET_MSG_1_A[rand(0, MEET_MSG_1_A.length - 1)] + name;
        meeting_msg += MEET_MSG_1_B[rand(0, MEET_MSG_1_B.length - 1)] + item;
        meeting_msg += MEET_MSG_1_C[rand(0, MEET_MSG_1_C.length - 1)];
        System.out.println(meeting_msg + "\n" + description);
    }

}
