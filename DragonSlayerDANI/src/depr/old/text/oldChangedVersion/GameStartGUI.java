package depr.old.text.oldChangedVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayer",
 * on 19.04.2017, 22:58.
 */
public class GameStartGUI extends JFrame implements ActionListener{

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int LAYOUT_GAP = 15;
    private static final String WINDOW_TITLE = "Dragon Slayer Alpha v0.2";
    private static final String[] COMMANDS =
            new String[]{"gehe", "suche", "öffne Tasche", "benutze", "lösche", "status", "help"};

    private JPanel mainPanel;
    private JTextField inputField;
    private JTextArea outputArea;
    private JScrollPane outputScrollPane;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem helpItem, aboutItem, exitItem;

    private String userName;
    private Map map;
    private Character player;
    private Field playerPosition;
    private boolean isGameRunning;
    private boolean isBattle;
    private boolean isWaitingForUserInput;
    private String currentInput;

    /**
     * constructor method to create the new old.text.oldChangedVersion.GameStartGUI object.
     * @param userInput the String userInput that was selected as an user name.
     */
    private GameStartGUI(String userInput){
        super(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        setResizable(false);

        // Setting the username so we can use it later.
        this.userName = userInput;
        initializeComponents();
        //addComponentsTogether();
        addActionListeners();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        // Everything is set up now, the game can begin.
        putOut("Du wurdest auf einer Wiese ausgesetzt " + userName + ".\nErforsche die Welt in die du geboren " +
                "wurdest.\nFalls du Hilfe brauchst, gebe \"help\" ein, oder öffne die Hilfe in der Menüleiste.");
        //gameLoop();
    }

    /**
     * method to initialize all the components that are defined in the scope.
     */
    private void initializeComponents(){
/*        // GUI related stuff.
        mainPanel = new JPanel(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        inputField = new JTextField(35);
        outputArea = new JTextArea(6, 35);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        outputScrollPane = new JScrollPane(outputArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        menuBar = new JMenuBar();
        menu = new JMenu("Spielmenü");
        helpItem = new JMenuItem("Hilfe");
        aboutItem = new JMenuItem("Über");
        exitItem = new JMenuItem("Spiel beenden");

        // Game stuff.
        map = new old.text.oldChangedVersion.Map();
        map.createMap();
        player = new old.text.oldChangedVersion.Character(userName);
        playerPosition = map.getField(player.xpos, player.ypos);
        isGameRunning = true;
        isBattle = false;
        isWaitingForUserInput = false;
        currentInput = "";
    }
*/
    /**
     * method to add all the components together.
     */
   // private void addComponentsTogether(){
        this.add(mainPanel);
        mainPanel.add(outputScrollPane, "Center");
        mainPanel.add(inputField, "South");

        menuBar.add(menu);
        menu.add(helpItem);
        menu.add(aboutItem);
        menu.add(new JSeparator());
        menu.add(exitItem);
    }

    /**
     * method to add the action listeners to the necessary components.
     * also sets the action commands that are needed for the actionPerformed method.
     */
    private void addActionListeners(){
        /*
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        */
        helpItem.setActionCommand("help");
        helpItem.addActionListener(this);
        aboutItem.setActionCommand("about");
        aboutItem.addActionListener(this);
        exitItem.setActionCommand("exit");
        exitItem.addActionListener(this);
        inputField.setActionCommand("enter");
        inputField.addActionListener(this);
    }

    /**
     * method to handle the current game loop and the user input.
     */
 /*   private void gameLoop(){

        while (true){
            // Starting conditions.
            playerPosition = map.getField(player.xpos, player.ypos);
            putOut("Du bist auf einem Feld des Typs " + playerPosition.umgebung + ".");

            // Fight.
            if(!map.isEnemy(player.xpos, player.ypos)){
                isBattle = true;
                putOut("Ein wilder " + playerPosition.enemy.name + " erscheint.");
                while ((playerPosition.enemy.health > 0 ^ player.health > 0) && isBattle){
                    putOut("Du befindest dich im Kampf. Was willst du tun?\n" +
                            "1: Attacke\n2: Lauf weg\n3: Benutze old.text.oldChangedVersion.Item");
                    isWaitingForUserInput = true;
                    while (isWaitingForUserInput){  // Kind of an ugly style to handle it like this, but it works. :D

                    }
                    switch (currentInput){
                        case "1":
                            player.attack(playerPosition.enemy);
                            break;
                        case "2":
                            if(Math.random() < 0.5){
                                putOut("Du bist entkommen!");
                                isBattle = false;
                            }
                            break;
                        case "3":
                            if(player.bagcounter == 0) {
                                putOut("Du hast keine Items!");
                                isBattle = false;
                                // Is that really what you want? If I want to use an item within the battle,
                                // and you don't have any, the battle is over? Didn't change it myself, but seems
                                // strange to me. :D
                            } else {
                                player.showInventory();
                                putOut("Welches old.text.oldChangedVersion.Item willst du benutzen?");
                                // TODO port the rest of the block beneath in useable gui code. then alter all methods from classes that use output, so that the methods return Strings. then add the returned strings in the corresponding places to the output area.
                            }

                    }
                }
            }
        }

        while (true) {{

                    if (choice.equals("3")) {
                        if (me.bagcounter == 0) {
                            System.out.println("Du hast keine Items!");
                            battlecheck = false;

                        } else {
                            me.showInventory();
                            System.out.println("Welches old.text.oldChangedVersion.Item willst du benutzen ?");
                            int decision = Integer.parseInt(scan.nextLine());
                            me.use(decision);
                        }
                    }

                    if ((position.enemy.health > 0) && (battlecheck)) {
                        position.enemy.attack(me);
                    }
                    System.out.println("Dein Leben :" + me.health);
                    System.out.println("" + position.enemy.name + ": " + position.enemy.health);

                if (position.enemy.health < 1) {

                    // Nach erstem Kampf wird vor einem Kampf ausgeführt
                    if (position.enemy.name.equals("Goblin")){
                        System.out.println("" + me.name + " hat 5 Exp. bekommen.");
                        me.checkLvl(5);
                        position.enemy = null;
                    } else if (position.enemy.name.equals("Kobold")){
                        System.out.println("" + me.name + " hat 10 Exp. bekommen.");
                        me.checkLvl(10);
                        position.enemy = null;
                    } else if (position.enemy.name.equals("Kobold")){
                        System.out.println("" + me.name + " hat 10 Exp. bekommen.");
                        me.checkLvl(10);
                        position.enemy = null;
                    } else if (position.enemy.name.equals("Berserker")){
                        System.out.println("" + me.name + " hat 15 Exp. bekommen.");
                        me.checkLvl(15);
                        position.enemy = null;
                    } else if (position.enemy.name.equals("Smaug")){
                        System.out.println("" + me.name + " hat 100 Exp. bekommen.");
                        me.checkLvl(100);
                        position.enemy = null;
                    }
                }
            }

            // in der Welt
            if (me.health < 1) {
                System.out.println("GAME OVER");
                break;
            }
            System.out.println("Was willt du tun?");
            String command = scan.nextLine();
            String[] commands = command.split(" ");
            if (command.equals("help")) {
                System.out.println(
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%      MAN PAGE     %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println("1: 'gehe' + 'vorwärts'|'rückwärts'|''links'|'rechts' -bewegt sie auf der Karte.");
                System.out.println("2: 'suche' - Lässt " + me.name
                        + " nach Items suchen (Tipp: versuchen sie es wenn sie einen Gegner besiegt haben)");
                System.out
                        .println("3: 'öffne Tasche' - Zeigt deinen Tascheninhalt, du kannst aber nur 4 Items tragen.");
                System.out.println("4: 'benutze' + index aus Tascheninhalt - Legt einen Ausrüstungsgegenstand an.");
                System.out.println("5: 'lösche' + index aus Tascheninhalt - Wirft ein old.text.oldChangedVersion.Item aus dem Inventar.");
                System.out.println("6: 'status' - Zeigt aktuelles Level und Leben an.");
                System.out.println(
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else if (commands[0].equals("gehe")) {
                me.move(commands[1]);
            } else if (command.equals("suche")) {
                if (!position.isItem()) {
                    old.text.oldChangedVersion.Item item = position.search();
                    System.out.println("Willst du " + item.name + " an dich nehmen ? Ja/Nein");
                    String answer = scan.nextLine();
                    if (answer.equals("Ja")) {
                        me.addItem(item);
                    }
                } else {
                    System.out.println("Nichts gefunden.");
                }
            } else if (command.equals("öffne Tasche")) {
                System.out.println("In deiner Tasche befindet sich:");
                me.showInventory();
            } else if (commands[0].equals("benutze")) {
                me.use(Integer.parseInt(commands[1]));
            } else if (commands[0].equals("lösche")) {
                me.delItem(Integer.parseInt(commands[1]));
            } else if(command.equals("status")) {
                me.status();
            } else {
                System.out.println("Bitte zulässigen Befehle eingeben!!!");
            }
        }
    }
*/
    /**
     * method to add old.text to the output area in a simple way.
     * @param text the String old.text that needs to be added to the area.
     */
    private void putOut(String text){
        if(outputArea.getText().equals("")){    // Set old.text if none is in the area.
            outputArea.setText(text);
        } else {                                // If there is one, add a line break and append the old.text.
            outputArea.setText(outputArea.getText() + "\n" + text);
        }
    }

    /**
     * main method to get everything going.
     * the default look and feel of the GUI is altered to the systems design.
     * afterwards, it gets the user name via a JOptionPane and starts the gui within the designated thread.
     * @param args .
     */
    public static void main(String[] args){
        try{
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                UnsupportedLookAndFeelException | IllegalAccessException e){
            e.printStackTrace();
        }

        invokeLater(new Runnable(){
            @Override
            public void run(){
                // Catching the name.
                String name = JOptionPane.showInputDialog(null, "Herzlich Willkommen zu " +
                        "Dragon Slayer Alpha v0.2!\nVerrate mir doch deinen Namen und dann kanns auch gleich " +
                        "losgehen.", "Dragon Slayer Alpha v0.2", JOptionPane.PLAIN_MESSAGE);
                // If no name was selected, we inform the user and end the game.
                if(name.equals("")){
                    JOptionPane.showMessageDialog(null, "So schwer war das doch nicht!\nStarte" +
                            " das Spiel erneut und dann versuche,\ndiesmal auch etwas einzugeben.",
                            "Nutzereingabe erwartet!", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                // If the name was okay, we create the new GUI object with the name.
                new GameStartGUI(name);
            }
        });
    }

    /**
     * method to handle ActionEvents.
     * needs to be overwritten, because the action listener interface is implemented.
     * in here we can now specify the operations that will be executed when these events occur.
     * @param e the ActionEvent that took place.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "help": // Creating a help message and showing it in a separate window to keep open during the game.
                String helpMessage = "1: \"gehe\" + \"vorwärts\"|\"rückwärts\"|\"links\"|\"rechts\"\t\t\t" +
                        "- Bewegt dich auf der Karte\n2: \"suche\" \t\t\t\t\t\t- Lässt " + player.name +
                        " nach Items suchen. (Tipp: Versuche es nachdem du einen Gegner besiegt hast.)\n" +
                        "3: \"öffne Tasche\" \t\t\t\t\t\t- Zeigt deinen Tascheninhalt, du kannst aber nur " +
                        "4 Items auf einmal tragen.\n4: \"benutze\" + Index des Tascheninhaltes \t\t\t\t\t" +
                        "- Legt einen Ausrüstungsgegenstand an.\n5: \"lösche\" + Index des Tascheninhaltes \t\t" +
                        "\t\t\t- Wirft ein old.text.oldChangedVersion.Item aus dem Inventar.\n6: \"status\" \t\t\t\t\t\t- Zeigt aktuelles " +
                        "Level und Leben an.";
                JOptionPane.showMessageDialog(null, helpMessage, "Hilfefenster",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "about": // Credits. Always fun to have that :D
                JOptionPane.showMessageDialog(null, "Dragon Slayer Alpha v0.2\n" +
                        "Spiel von Robert\nGUI von Dani :)", "Credits", JOptionPane.PLAIN_MESSAGE);
                break;
            case "exit": // Exit via the menu. With confirmation from the user before the game is really closed.
                int decider = JOptionPane.showConfirmDialog(null, "Bist du sicher, dass du das " +
                        "Spiel beenden möchtest?\nDeine Fortschritte werden nicht gespeichert!",
                        "Spiel beenden?", JOptionPane.YES_NO_OPTION);
                if(decider == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
                break;
            case "enter":
                // If the inputField recognizes that enter is being pressed, the user input String will be set,
                // and the isWaitingForUserInput boolean is set to false to let the game loop continue.
                currentInput = inputField.getText();
                inputField.setText("");
                isWaitingForUserInput = false;
                break;
            default: // If an unknown action event occurs, the program will be closed. Might cause problems!?
                JOptionPane.showMessageDialog(null, "Undefined action command: \"" +
                        e.getActionCommand() + "\"!", "Handling issue!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                break;
        }
    }
}
