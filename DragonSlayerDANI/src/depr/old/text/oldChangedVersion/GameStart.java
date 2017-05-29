package depr.old.text.oldChangedVersion;

import java.util.Scanner;

public class GameStart {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Map map = new Map();
    map.createMap();

    System.out.println("Herzlich Willkommen zu Dragon Slayer Alpha 0.1");
    System.out.println("Wie ist dein Name ?");
    String name = scan.nextLine();
    Character me = new Character(name);
    System.out.println("Du wurdest auf einer Wiese ausgesetzt " + name + ".");
    System.out.println(
        "Erforsche die Welt in die du geboren wurdest, falls du Hilfe brauchst gebe 'help' ein.");

    while (true) {
      Field position = map.getField(me.xpos, me.ypos);
      System.out.println("Du bist auf einem Feld des Typs " + position.umgebung + ".");

      // Kampf
      if (!map.isEnemy(me.xpos, me.ypos)) {
        boolean battlecheck = true;
        System.out.println("Eine wilder " + position.enemy.name + " erscheint.");
        while (!(position.enemy.health > 0) ^ (me.health > 0)) {
          System.out.println("Du befindest dich im Kampf. Was willst du tun ?");
          System.out.println("1: Attacke");
          System.out.println("2: Lauf weg");
          System.out.println("3: Benutze old.text.oldChangedVersion.Item");
          String choice = scan.nextLine();
          if (choice.equals("1")) {
            me.attack(position.enemy);
          } else if (choice.equals("2")) {
            double rand = Math.random();
            if (rand < 0.5) {
              System.out.println("Du bist entkommen!");
              break;
            } else {
              System.out.println("Du bist nicht entkommen!");
            }
          } else if (choice.equals("3")) {
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
        }
        if (position.enemy.health < 1) {

          // Nach erstem Kampf wird vor einem Kampf ausgeführt
          if (position.enemy.name.equals("Goblin")) {
            System.out.println("" + me.name + " hat 5 Exp. bekommen.");
            me.checkLvl(5);
            position.enemy = null;
          } else if (position.enemy.name.equals("Kobold")) {
            System.out.println("" + me.name + " hat 10 Exp. bekommen.");
            me.checkLvl(10);
            position.enemy = null;
          } else if (position.enemy.name.equals("Kobold")) {
            System.out.println("" + me.name + " hat 10 Exp. bekommen.");
            me.checkLvl(10);
            position.enemy = null;
          } else if (position.enemy.name.equals("Berserker")) {
            System.out.println("" + me.name + " hat 15 Exp. bekommen.");
            me.checkLvl(15);
            position.enemy = null;
          } else if (position.enemy.name.equals("Smaug")) {
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
        System.out.println(
            "1: 'gehe' + 'vorwärts'|'rückwärts'|''links'|'rechts' -bewegt sie auf der Karte.");
        System.out.println("2: 'suche' - Lässt " + me.name
            + " nach Items suchen (Tipp: versuchen sie es wenn sie einen Gegner besiegt haben)");
        System.out
            .println(
                "3: 'öffne Tasche' - Zeigt deinen Tascheninhalt, du kannst aber nur 4 Items tragen.");
        System.out.println(
            "4: 'benutze' + index aus Tascheninhalt - Legt einen Ausrüstungsgegenstand an.");
        System.out.println(
            "5: 'lösche' + index aus Tascheninhalt - Wirft ein old.text.oldChangedVersion.Item aus dem Inventar.");
        System.out.println("6: 'status' - Zeigt aktuelles Level und Leben an.");
        System.out.println(
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
      } else if (commands[0].equals("gehe")) {
        me.move(commands[1]);
      } else if (command.equals("suche")) {
        if (!position.isItem()) {
          Item item = position.search();
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
      } else if (command.equals("status")) {
        me.status();
      } else {
        System.out.println("Bitte zulässigen Befehle eingeben!!!");
      }
    }
  }
}
