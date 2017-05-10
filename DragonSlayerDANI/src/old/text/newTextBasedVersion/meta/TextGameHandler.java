package old.text.newTextBasedVersion.meta;

import old.text.newTextBasedVersion.classes.Character;
import old.text.newTextBasedVersion.classes.Field;
import old.text.newTextBasedVersion.classes.Item;
import old.text.newTextBasedVersion.classes.Map;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 29.04.2017, 15:47.
 */
public class TextGameHandler {

    private static final String[] DIFFICULTIES = new String[]{"Easy", "Medium", "Hard"};
    private static final String[] GENDERS = new String[]{"Male", "Female", "Transgender", "Apache Helicopter"};
    private static final String[] COMMANDS_WORLD = new String[]{"up", "left", "right", "down", "find",
            "open", "use", "delete", "map", "status", "help", "manual"};
    private static final int MIN_WIDTH_MAP = 3;
    private static final int MAX_WIDTH_MAP = 7;
    private static final int MIN_HEIGHT_MAP = 4;
    private static final int MAX_HEIGHT_MAP = 6;


    private static Scanner scanner;

    private static Character player;
    private static Map map;
    private static Difficulty difficulty;
    private static boolean gameWon;
    private static boolean inFight;
    private static boolean gameOver;
    private static int currentMapID = 1;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        gameWon = false;
        inFight = false;
        gameOver = false;
        String name = getPlayerNameFromUser();
        Gender gender = getGenderFromUser();
        difficulty = getDifficultyFromUser();
        map = new Map(randomIntInRange(MIN_WIDTH_MAP, MAX_WIDTH_MAP),
                randomIntInRange(MIN_HEIGHT_MAP, MAX_HEIGHT_MAP), difficulty, currentMapID, 1);
        currentMapID++;
        player = new Character(name, gender, difficulty, map.getStartingPointX(), map.getStartingPointY());
        initialGameStart();
    }

    /**
     * method to get faster access to the current field.
     * @return the current field.
     */
    private static Field currentField(){
        return map.getFields()[player.getxPos()][player.getyPos()];
    }

    /**
     * method to print out the introduction for the game.
     */
    private static void initialGameStart(){
        System.out.println("You wake up, lying on a " + currentField().getName() + ".\nYour only memories are, " +
                "that your name is " + player.getName() + " and that you have seen a dragon.\n" +
                "You also realise that you have a " + player.getWeapon().getName() + ".");
        gameLoop();
    }

    /**
     * method to create a new map and to set up everything for the next adventure.
     */
    private static void additionalGameStart(){
        // Creating the new map.
        map = new Map(randomIntInRange(MIN_WIDTH_MAP, MAX_WIDTH_MAP),
                randomIntInRange(MIN_HEIGHT_MAP, MAX_HEIGHT_MAP), difficulty, currentMapID, player.getLevel());
        //currentMapID++;

        // Enlarging the bag and starting health as an reward.
        player.enlargeBag();
        player.setStartingHealth(player.getStartingHealth() + 20);

        // Setting the user on the start point and resetting the health.
        player.setxPos(map.getStartingPointX());
        player.setyPos(map.getStartingPointY());
        player.setHealth(player.getStartingHealth());
        // And starting again.
        System.out.println(player.getName() + " awakes again on a " + currentField().getName() + ".\n" +
                "But this time he remembers more. He remembers that the dragon told him, that the other dragons will\n" +
                "revenge his death...\nBut " + player.getName() + " cannot show any fear! For he is the Dragon Slayer!\n" +
                "Go and explore map " + currentMapID + "!\n");
        gameLoop();
    }

    /**
     * method to handle the current game loop.
     */
    private static void gameLoop(){
        while (!gameWon && !gameOver){
            if(!currentField().hasEnemy()){     // Handling input on fields without an enemy.
                if(!currentField().wasVisited()){       // If we walk on a field, we set wasVisited true.
                    currentField().setWasVisited(true);
                }
                inFight = false;
                System.out.println("What do you want to do?\nEnter \"help\" to show the commands.\n_____");
                String input = scanner.nextLine();
                switch (input){
                    case "u":
                    case "up":
                        walk("north");
                        break;
                    case "l":
                    case "left":
                        walk("west");
                        break;
                    case "r":
                    case "right":
                        walk("east");
                        break;
                    case "d":
                    case "down":
                        walk("south");
                        break;
                    case "search":
                    case "f":
                    case "find":
                        if(currentField().hasItem()){
                            System.out.println("You found a " + currentField().getItem().getName() + ".");
                            if(player.getBagCounter() < player.getBag().length - 1){
                                System.out.println(currentField().getItem().getName() + " was added to your bag.");
                                player.addItem(currentField().getItem());
                                currentField().setItem(null);
                            } else {
                                System.out.println("No more space left in your bag. Delete or use an item " +
                                        "first to pick up " + currentField().getItem().getName() + ".");
                            }
                        } else {
                            System.out.println("No item found here.");
                        }
                        break;
                    case "o":
                    case "open":
                        player.displayBag();
                        break;
                    case "m":
                    case "map":
                        showMap();
                        break;
                    case "s":
                    case "stat":
                    case "status":
                        showStatus();
                        break;
                    case "h":
                    case "help":
                        showHelp();
                        break;
                    case "man":
                    case "manual":
                        showManual();
                        break;
                    default:    // When a multi word input occurs.
                        handleDoubleInput(input);
                        break;
                }
            } else {                            // Handling fights.
                handleFights();
            }
        }
    }

    /**
     * method to show the game manual with the instructions on how to play the game.
     */
    private static void showManual() {
        System.out.println("You are on a journey to free the world from the terror of the dragons!");
        System.out.println("Try to defeat enemies and level up. Find new and better equipment to explore the region");
        System.out.println("until you meet the almighty dragon boss. If you manage to win, you will advance to the");
        System.out.println("next region. Your goal is defeating ten powerful dragons in ten progressively harder");
        System.out.println("worlds or to reach level 11 to become the ultimate dragon slayer!");
        System.out.println("");
    }

    /**
     * method to handle the fighting outside of the game loop.
     */
    private static void handleFights(){
        inFight = true;
        while (inFight){
            if(currentField().getEnemy().isBoss()){
                System.out.println("You are facing the boss dragon " + currentField().getEnemy().getName() +
                        " with it's " + currentField().getEnemy().getWeapon().getName() + " and " +
                        currentField().getEnemy().getHealth() + " health.");
            } else {
                System.out.println("You are standing in front of a " + currentField().getEnemy().getName() +
                        " carrying a " + currentField().getEnemy().getWeapon().getName() + " and with " +
                        currentField().getEnemy().getHealth() + " health.");
            }
            System.out.println("What do you want to do?");
            System.out.println("1: Attack the enemy");
            System.out.println("2: Try to run away");
            System.out.println("3: Use item from bag");
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    // Player attack.
                    double playerAttack = ThreadLocalRandom.current().nextDouble(0.00, 1.00);
                    if(playerAttack >= player.getWeapon().getAccuracy()){   // Player hits enemy.
                        currentField().getEnemy().setHealth(currentField().getEnemy().getHealth() -
                                player.getWeapon().getAttack());
                        System.out.println(player.getName() + " deals " + player.getWeapon().getAttack() + " damage " +
                                "to the " + currentField().getEnemy().getName() + ".");
                        if(currentField().getEnemy().getHealth() <= 0){     // If we kill the enemy.
                            //inFight = false;
                            player.setKillCount(player.getKillCount() + 1);
                            player.levelUp(currentField().getEnemy(), difficulty);
                            System.out.println(player.getName() + " has defeated " +
                                    currentField().getEnemy().getName() + ".");
                            if(!currentField().hasItem()){  // Dead enemy drops it's weapon if the field is empty.
                                currentField().setItem(currentField().getEnemy().getWeapon());
                            }
                            if(currentField().getEnemy().isBoss()){     // Respawn of player in new map.
                                System.out.println("Congratulations! You have mastered map " + currentMapID +
                                        " by defeating the almighty " + currentField().getEnemy().getName() + "!");
                                System.out.println("Your journey continues in another world...\n\n\n");
                                additionalGameStart();
                            }
                            currentField().setEnemy(null);
                            gameLoop();
                        }
                    } else {                                                // Player misses.
                        System.out.println(player.getName() + " missed.");
                    }
                    // Enemy attack.
                    double enemyAttack = ThreadLocalRandom.current().nextDouble(0.00, 1.00);
                    if(enemyAttack >= currentField().getEnemy().getWeapon().getAccuracy()){ // If the enemy hits.
                        player.setHealth(player.getHealth() - currentField().getEnemy().getWeapon().getAttack());
                        System.out.println(currentField().getEnemy().getName() + " dealt " +
                                currentField().getEnemy().getWeapon().getAttack() + " damage with it's " +
                                currentField().getEnemy().getWeapon().getName() + ".");
                        if(player.getHealth() <= 0){    // If the enemy kills us.
                            gameOver = true;
                            inFight = false;
                            System.out.println("You have been killed by a " +
                                    currentField().getEnemy().getName() + ".\nGame over.\n\n" +
                                    "Play again if you like it!");
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                        } else {
                            System.out.println(player.getName() + " has " + player.getHealth() + " health left.");

                        }
                    } else {                                                            // If the enemy misses.
                        System.out.println(currentField().getEnemy().getName() + " missed.");
                    }
                    break;
                case "2":
                    double threshold;
                    switch (difficulty){
                        case EASY:
                            threshold = 0.42;
                            break;
                        case MEDIUM:
                            threshold = 0.65;
                            break;
                        case HARD:
                            threshold = 0.78;
                            break;
                        default:
                            threshold = 0.65;
                            break;
                    }
                    double runChance = ThreadLocalRandom.current().nextDouble(0.00, 1.00);
                    if(runChance >= threshold){         // If you manage to get away.
                        System.out.println("You managed to escape from the " +
                                currentField().getEnemy().getName() + ".");
                        if(currentField().getEnemy().isBoss()){     // To avoid a map without a boss.
                            map.respawnBoss(currentField().getEnemy(), player.getxPos(), player.getyPos());
                        }
                        currentField().setEnemy(null);
                        gameLoop();
                    } else {                            // Otherwise the enemy gets to attack.
                        System.out.println("You didn't escape from the " +
                                currentField().getEnemy().getName() + ".");
                        enemyAttack = ThreadLocalRandom.current().nextDouble(0.00, 1.00);
                        if(enemyAttack >= currentField().getEnemy().getWeapon().getAccuracy()){ // If the enemy hits.
                            player.setHealth(player.getHealth() - currentField().getEnemy().getWeapon().getAttack());
                            System.out.println(currentField().getEnemy().getName() + " dealt " +
                                    currentField().getEnemy().getWeapon().getAttack() + " damage with it's " +
                                    currentField().getEnemy().getWeapon().getName() + ".");
                            if(player.getHealth() <= 0){    // If the enemy kills us.
                                gameOver = true;
                                inFight = false;
                                System.out.println("You have been killed by a " +
                                        currentField().getEnemy().getName() + ".\nGame over.\n\n" +
                                        "Play again if you like it!");
                            }  else {
                                System.out.println(player.getName() + " has " + player.getHealth() + " health left.");

                            }
                        } else {                                                            // If the enemy misses.
                            System.out.println(currentField().getEnemy().getName() + " missed.");
                        }
                    }
                    break;
                case "3":
                    player.displayBag();
                    if(player.getBagCounter() != 0){
                        System.out.println("Select the index number of the item that you want to use:");
                        int select = scanner.nextInt() - 1;
                        if(select < 0 || select > player.getBagCounter()){
                            System.out.println("There is no item at index " + (select + 1) + "!");
                        } else {
                            if(player.getBag()[select].getName().contains("potion")){    // If item is potion.
                                Item consumable = player.getBag()[select];
                                player.deleteItem(select);
                                player.heal(consumable.getHealing());
                                System.out.println(player.getName() + " healed " + consumable.getHealing() +
                                        " health points with " + consumable.getName() + ".");
                            } else {    // If item is weapon.
                                Item weapon = player.getBag()[select];
                                player.deleteItem(select);
                                player.addItem(player.getWeapon());
                                System.out.println(player.getName() + " exchanged " +
                                        player.getWeapon().getName() + " with " + weapon.getName() + ".");
                                player.setWeapon(weapon);
                            }
                        }
                    }
                    // Enemy gets to attack after the usage of the item.
                    enemyAttack = ThreadLocalRandom.current().nextDouble();
                    if(enemyAttack >= currentField().getEnemy().getWeapon().getAccuracy()){ // If the enemy hits.
                        player.setHealth(player.getHealth() - currentField().getEnemy().getWeapon().getAttack());
                        System.out.println(currentField().getEnemy().getName() + " dealt " +
                                currentField().getEnemy().getWeapon().getAttack() + " damage with it's " +
                                currentField().getEnemy().getWeapon().getName() + ".");
                        if(player.getHealth() <= 0){    // If the enemy kills us.
                            gameOver = true;
                            inFight = false;
                            System.out.println("You have been killed by a " +
                                    currentField().getEnemy().getName() + ".\nGame over.\n\n" +
                                    "Play again if you like it!");
                        } else {
                            System.out.println(player.getName() + " has " + player.getHealth() + " health left.");

                        }
                    } else {                                                            // If the enemy misses.
                        System.out.println(currentField().getEnemy().getName() + " missed.");
                    }
                    break;
                    default:
                        System.out.println("Unknown command \"" + input + "\".");
                        break;
            }
        }
    }

    /**
     * method to show the current status of the player.
     */
    private static void showStatus(){
        System.out.println("Player:\t\t\t\t" + player.getName());
        System.out.println("Health:\t\t\t\t" + player.getHealth() + " of " + player.getStartingHealth());
        System.out.println("Gender:\t\t\t\t" + player.getGender());
        System.out.println("Weapon:\t\t\t\t" + player.getWeapon().getName());
        System.out.println("Attack:\t\t\t\t" + player.getWeapon().getAttack());
        double accuracyToPrint = cropDouble((1.00 - player.getWeapon().getAccuracy()) * 100);
        System.out.println("Accuracy:\t\t\t" + accuracyToPrint + " %");
        System.out.println("Items in bag:\t\t\t" + player.getBagCounter() + " / " + player.getBag().length);
        System.out.println("Experience:\t\t\t" + player.getExperience());
        System.out.println("Level:\t\t\t\t" + player.getLevel());
        System.out.println("Enemies killed:\t\t\t" + player.getKillCount());
        System.out.println("Current mapID:\t\t\t" + (currentMapID - 1));
        System.out.println("Current map size:\t\t" + map.getFields().length + ", " + map.getFields()[0].length);
        System.out.println("Current position:\t\t" + (player.getxPos() + 1) + ", " + (player.getyPos() + 1));
        System.out.println("");
    }

    /**
     * method to show a very rudimentary form of a map to get a faster understanding of the current position.
     */
    private static void showMap(){
        /*
        _________
        | , , , |
        | , ,X, |
        |_,_,_,_|

         */
        System.out.println("----- Current map " + currentMapID + " -----\n");
        // tuning the width and height to get printed correctly.
        int width = (map.getFields().length * 2) + 1;
        int height = map.getFields()[0].length + 1;
        // upper numbers for the field.
        for(int i = 0; i < (width - 1) / 2; i++){
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        // upper line gets drawn outside of the loop because the player's position can't be there.
        for(int i = 0; i < width; i++){
            if(i != width - 1){
                System.out.print("_");
            } else {
                System.out.println("_");
            }
        }
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){

                if(y > 0 && y < height - 1){         // y is inside bounds.

                    if(x == 0){                             // x is left side.
                        System.out.print("|");

                    } else if(x == width - 1){              // x is right side.
                        System.out.println("| " + (y));

                    } else {                                // x is inside.

                        if(((player.getxPos() * 2) + 1) == x && (player.getyPos() + 1) == y){  // player's pos.
                            System.out.print("X");

                        } else {                                                // if it's not the player's position.
                            if(map.getFields()[(x - 1) / 2][y - 1].wasVisited()){   // if it was visited.
                                if(x % 2 == 0){                 // on even x paint corner.
                                    System.out.print("|");

                                } else {                        // on odd x paint space.
                                    System.out.print("o");
                                }
                            } else {        // if it wasn't visited.
                                if(x % 2 == 0){                 // on even x paint corner.
                                    System.out.print("|");

                                } else {                        // on odd x paint space.
                                    System.out.print("_");
                                }
                            }
                        }
                    }
                } else if(y == height - 1){                 // y is bottom line.

                    if(((player.getxPos() * 2) + 1) == x && (player.getyPos() + 1) == y){  // player's pos.
                        System.out.print("X");

                    } else {                                                // if it's not the player's position.
                        if(map.getFields()[(x - 1) / 2][y - 1].wasVisited()) {   // if it was visited.
                            if(x == 0){                         // x is left side.
                                System.out.print("|");

                            } else if(x == width - 1){          // x is right side.
                                System.out.println("| " + y + "\n");

                            } else {                            // x is inside.

                                if(x % 2 == 0){                 // on even x paint corner.
                                    System.out.print("|");

                                } else {                        // on odd x paint space.
                                    System.out.print("o");
                                }
                            }
                        } else {        // if it wasn't visited.
                            if(x == 0){                         // x is left side.
                                System.out.print("|");

                            } else if(x == width - 1){          // x is right side.
                                System.out.println("| " + y + "\n");

                            } else {                            // x is inside.

                                if(x % 2 == 0){                 // on even x paint corner.
                                    System.out.print("|");

                                } else {                        // on odd x paint space.
                                    System.out.print("_");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * method to crop any given double value to two decimal places after the dot.
     * @param value the double value that needs to be cropped.
     * @return the cropped value.
     */
    private static double cropDouble(double value){
        value = Math.round(value * 100.00);
        return value / 100.00;
    }

    /**
     * method to handle walking on the map. warns the user not to walk of the edge.
     * @param direction String direction: north, west, south or east.
     */
    private static void walk(String direction){
        switch (direction){
            case "north":
                if(player.getyPos() > 0){
                    player.setyPos(player.getyPos() - 1);
                    System.out.println("You walk northwards and find yourself on a " + currentField().getName() + ".");
                } else {
                    System.out.println("You can't walk northwards.\nSome kind of magic force holds " +
                            "you back.\nTry another direction.");
                }
                break;
            case "west":
                if(player.getxPos() > 0){
                    player.setxPos(player.getxPos() - 1);
                    System.out.println("You walk westwards and find yourself on a " + currentField().getName() + ".");
                } else {
                    System.out.println("You can't walk westwards.\nSome kind of magic force holds " +
                            "you back.\nTry another direction.");
                }
                break;
            case "south":
                if(player.getyPos() < map.getFields()[0].length - 1){
                    player.setyPos(player.getyPos() + 1);
                    System.out.println("You walk southwards and find yourself on a " + currentField().getName() + ".");
                } else {
                    System.out.println("You can't walk southwards.\nSome kind of magic force holds " +
                            "you back.\nTry another direction.");
                }
                break;
            case "east":
                if(player.getxPos() < map.getFields().length - 1){
                    player.setxPos(player.getxPos() + 1);
                    System.out.println("You walk eastwards and find yourself on a " + currentField().getName() + ".");
                } else {
                    System.out.println("You can't walk eastwards.\nSome kind of magic force holds " +
                            "you back.\nTry another direction.");
                }
                break;
            default:
                break;
        }
    }

    /**
     * method to handle a two worded input.
     * @param input the String input.
     */
    private static void handleDoubleInput(String input) {
        String[] singleInputs = input.split(" ");     // Splitting the input into an array.
        if(singleInputs.length != 2){
            System.out.println("\"" + input + "\" wasn't recognized!");
        } else {
            int index = Integer.parseInt(singleInputs[1]) - 1;
            switch (singleInputs[0]){
                case "u":
                case "use":
                    if(index < 0 || index > player.getBagCounter() || player.getBag()[index] == null){
                        System.out.println("No item at " + (index + 1) + " to use.");
                    } else {
                        if(player.getBag()[index].getName().contains("potion")){    // If item is potion.
                            Item consumable = player.getBag()[index];
                            player.deleteItem(index);
                            int healedAmount = player.heal(consumable.getHealing());
                            System.out.println(player.getName() + " healed " + healedAmount +
                                    " health points with " + consumable.getName() + ".");
                        } else {    // If item is weapon.
                            Item weapon = player.getBag()[index];
                            player.deleteItem(index);
                            player.addItem(player.getWeapon());
                            System.out.println(player.getName() + " exchanged " +
                                    player.getWeapon().getName() + " with " + weapon.getName() + ".");
                            player.setWeapon(weapon);
                        }
                    }
                    break;
                case "d":
                case "del":
                case "delete":
                    if(index < 0 || index > player.getBagCounter()){
                        System.out.println("No item at " + (index + 1) + " to delete.");
                    } else {
                        System.out.println(player.getName() + " throws " + player.getBag()[index].getName() + " away.");
                        if(!currentField().hasItem()){      // Item falls on the ground if no other item is there.
                            currentField().setItem(player.getBag()[index]);
                        }
                        player.deleteItem(index);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * method to display the help screen.
     */
    private static void showHelp(){
        System.out.println("--------------- User commands ----------------");
        System.out.println(COMMANDS_WORLD[0] + ":\t\t\t\twalk northwards on the map");
        System.out.println(COMMANDS_WORLD[1] + ":\t\t\t\twalk westwards on the map");
        System.out.println(COMMANDS_WORLD[2] + ":\t\t\t\twalk eastwards on the map");
        System.out.println(COMMANDS_WORLD[3] + ":\t\t\t\twalk southwards on the map");
        System.out.println(COMMANDS_WORLD[4]  + " / search:\t\t\tsearch for items on the field");
        System.out.println(COMMANDS_WORLD[5] + ":\t\t\t\tshow contents of your bag");
        System.out.println(COMMANDS_WORLD[6] + " + index:\t\t\tuse item at that index from your bag");
        System.out.println(COMMANDS_WORLD[7] + " + index:\t\t\tdelete item at that index from your bag");
        System.out.println(COMMANDS_WORLD[8] + ":\t\t\t\tshow the map of the current region");
        System.out.println(COMMANDS_WORLD[9] + ":\t\t\t\tshow the status of " + player.getName());
        System.out.println(COMMANDS_WORLD[10] + ":\t\t\t\topens this help screen with the commands");
        System.out.println(COMMANDS_WORLD[11] + ":\t\t\t\topen an instruction manual with an explanation on how to play");
        System.out.println("\nAll commands can be shortened to three (manual ==> man) or one (down ==> d) digits.");
        System.out.println("-----------------------------------------------\n");

    }

    /**
     * method to get the user name as a String via a JOptionPane.
     * @return the String name.
     */
    private static String getPlayerNameFromUser(){
        String name = JOptionPane.showInputDialog(null, "Insert your name:");
        if(name.equals("Hitler") || name.equals("")){
            name = "Mister SpecialSnowflake";
        }
        return name;
    }

    /**
     * method to get the selected gender from the user via a JOptionPane with a combo box.
     * @return the selected Gender.
     */
    private static Gender getGenderFromUser(){
        String selection = String.valueOf(JOptionPane.showInputDialog(null,
                "What gender are you?", "I need to assume your gender!",
                JOptionPane.PLAIN_MESSAGE, null, GENDERS, GENDERS[0]));
        switch (selection){
            case "Male":
                return Gender.Male;
            case "Female":
                return Gender.Female;
            case "Transgender":
                return Gender.Transgender;
            case "Apache Helicopter":
                return Gender.Apache_Helicopter;
            default:
                return Gender.Apache_Helicopter;
        }
    }

    /**
     * method to get the chosen difficulty from the user using a JOptionPane.
     * @return the difficulty.
     */
    private static Difficulty getDifficultyFromUser(){
        String result = String.valueOf(JOptionPane.showInputDialog(null, "Choose the difficulty:",
                "How hard are you?", JOptionPane.PLAIN_MESSAGE, null, DIFFICULTIES, DIFFICULTIES[0]));
        switch (result){
            case "Easy":
                return Difficulty.EASY;
            case "Medium":
                return Difficulty.MEDIUM;
            case "Hard":
                return Difficulty.HARD;
            default:
                return Difficulty.MEDIUM;
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
