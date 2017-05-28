package depr.old.text.oldChangedVersion;

public class Item {
	public String name;
	public int attack;
	public int healing = 0;
	public double accuracy;
	
	// For weapons
	public Item(String name, int attack, double accuracy){
		this.name = name;
		this.attack = attack;
		this.accuracy = accuracy;
	}
	
	// For potions
	public Item(String name, int healing) {
		this.name = name;
		this.healing = healing;
	}
	
	
}
