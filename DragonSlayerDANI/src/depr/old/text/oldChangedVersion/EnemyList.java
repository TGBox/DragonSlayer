package depr.old.text.oldChangedVersion;

public class EnemyList {

  public ItemList items = new ItemList();
  public Enemy goblin = new Enemy("Goblin", 50, items.holzschwert);
  public Enemy kobold = new Enemy("Kobold", 60, items.holzschwert);
  public Enemy berserker = new Enemy("Berserker", 70, items.eisenschwert);
  public Enemy smaug = new Enemy("SMAUG", 100, items.drachenatem);
}
