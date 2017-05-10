package old.text.oldChangedVersion;

public class Field {
	public String umgebung;
	public Enemy enemy;
	public Item item;
	
	public Field(String umgebung, Enemy enemy, Item item) {
		this.enemy = enemy;
		this.umgebung = umgebung;
		this.item = item;
	}
	
	public boolean isItem() {
		return item == null;
	}
	
	public Item search() {
		System.out.println("Du durchsuchst "+umgebung+".");
		System.out.println("Du hast " + item.name + " gefunden.");
		return item;
	}
	
	public Item getItem() {
		return item;
	}
	
}
