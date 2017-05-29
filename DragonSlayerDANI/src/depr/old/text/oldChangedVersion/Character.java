package depr.old.text.oldChangedVersion;

public class Character {

  public ItemList il = new ItemList();
  public String name;
  public int health = 100;
  public Item[] bag = new Item[5];
  public int bagcounter = 0;
  public Item weapon = il.holzschwert;
  public int xpos = 13;
  public int ypos = 7;
  public int strength = 100;
  public int lvl = 1;
  public int exp = 0;

  public Character(String name) {
    this.name = name;
  }

  public void addItem(Item item) {
    if (bagcounter != 4) {
      System.out.println("old.text.oldChangedVersion.Item " + item.name
          + " wurde erfolgreich ins Inventar aufgenommen.");
      bag[bagcounter] = item;
      bagcounter++;
    } else {
      System.out.println("Inventar ist voll.");
    }
  }

  public void attack(Enemy enemy) {
    double rand = Math.random();
    if (rand > weapon.accuracy) {
      System.out.println("" + name + " hat verfehlt.");
    } else {
      int damage = (int) (weapon.attack * weapon.accuracy) + strength;
      System.out.println("" + name + " hat " + damage + " Schaden zugefügt.");
      enemy.health = enemy.health - damage;
    }
  }

  public void showInventory() {
    for (int k = 0; k < bagcounter; k++) {
      System.out.println("" + k + ": " + bag[k].name);
    }
  }

  public void delItem(int index) {
    if (bag[index] != null) {
      bag[index] = null;
      for (int k = index; k < bagcounter; k++) {
        bag[k] = bag[k + 1];
      }
      bagcounter--;
    } else {
      System.out.println("Auf diesem Stellplatz ist kein old.text.oldChangedVersion.Item.");
    }
  }

  public void use(int index) {
    if (bag[index].healing != 0) {
      health += bag[index].healing;
      delItem(index);
    } else if (bag[index] != null) {
      weapon = bag[index];
      System.out.println("Du hast " + weapon.name + " angelegt.");
      delItem(index);
    } else {
      System.out.println("leerer Stellplatz.");
    }
  }

  public void move(String direction) {
    switch (direction) {
      case "vorwärts":
        if (xpos > 0) {
          xpos--;
        } else {
          System.out.println("Zugang versperrt!");
        }
        break;
      case "hinten":
        if (xpos < 14) {
          xpos++;
        } else {
          System.out.println("Zugangversperrt!");
        }
        break;
      case "links":
        if (ypos > 0) {
          ypos--;
        } else {
          System.out.println("Zugangversperrt!");
        }
        break;
      case "rechts":
        if (ypos < 14) {
          ypos++;
        } else {
          System.out.println("Zugangversperrt!");
        }
        break;
    }
  }

  public void checkLvl(int newExp) {

    exp += newExp;
    if (exp > 100) {
      lvl = 10;
      strength = 11;

    } else if (exp > 80) {
      lvl = 9;
      strength = 10;

    } else if (exp > 63) {
      lvl = 8;
      strength = 9;

    } else if (exp > 48) {
      lvl = 7;
      strength = 8;

    } else if (exp > 35) {
      lvl = 6;
      strength = 7;

    } else if (exp > 24) {
      lvl = 5;
      strength = 6;
    } else if (exp > 15) {
      lvl = 4;
      strength = 5;
    } else if (exp > 8) {
      lvl = 3;
      strength = 5;

    } else if (exp > 3) {
      lvl = 2;
      strength = 4;
    } else {
      lvl = 1;
    }
  }

  public void status() {
    System.out.println("Status von " + name);
    System.out.println("Leben: " + health);
    System.out.println("Level: " + lvl);
    System.out.println("Erfahrung: " + exp);
    System.out.println("Waffe: " + weapon.name);
  }
}
