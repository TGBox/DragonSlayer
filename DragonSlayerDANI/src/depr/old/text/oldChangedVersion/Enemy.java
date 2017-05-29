package depr.old.text.oldChangedVersion;

public class Enemy {

  public String name;
  public int health;
  public Item weapon;

  public Enemy(String name, int health, Item weapon) {
    this.name = name;
    this.health = health;
    this.weapon = weapon;
  }

  public void attack(Character you) {
    double rand = Math.random();
    if (rand > weapon.accuracy) {
      System.out.println("" + name + " verfehlte seine Attacke.");
    } else {
      int damage = (int) (weapon.attack * weapon.accuracy);
      System.out.println("" + name + " hat " + damage + " Schaden zugefügt.");
      you.health = you.health - damage;
    }
  }
}
