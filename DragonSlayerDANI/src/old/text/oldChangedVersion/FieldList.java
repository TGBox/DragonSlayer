package old.text.oldChangedVersion;

public class FieldList {
	ItemList il = new ItemList();
	EnemyList el = new EnemyList();
	
	public Field wald_leer = new Field("Wald", null, null);
	public Field wald_kheiltrank = new Field("Wald", null, il.kleinerHeiltrank);
	public Field wald_goblin_kheiltrank = new Field("Wald", el.goblin, il.kleinerHeiltrank);
	public Field wald_kobold_mheiltrank = new Field("Wald", el.kobold, il.mittlererHeiltrank);
	public Field wald_kobold_eisenschwert = new Field("Wald", el.kobold, il.eisenschwert);
	
	public Field wiese_leer = new Field("Wiese", null, null);
	public Field wiese_kHeiltrank = new Field("Wiese", null, il.kleinerHeiltrank);
	public Field wiese_goblin_kheiltrank = new Field("Wiese", el.goblin, il.kleinerHeiltrank);
	public Field wiese_kobold_mheiltrank = new Field("Wiese", el.kobold, il.mittlererHeiltrank);
	public Field wiese_kobold_eisenschwert = new Field("Wiese", el.kobold, il.eisenschwert);
	
	public Field dungeon_leer = new Field("Dungeon", null, null);
	public Field dungeon_berserker_mheiltrank = new Field("Dungeon", el.berserker, il.mittlererHeiltrank);
	public Field dungeon_berserker_eisenschwert = new Field("Dungeon", el.berserker, il.eisenschwert);
	public Field dungeon_berserker_stahlschwert = new Field("Dungeon", el.berserker, il.stahlschwert);
	public Field dungeon_berserker_excalibur = new Field("Dungeon", el.berserker, il.excalibur);
	public Field dungeon_berserker_gheiltrank = new Field("Dungeon", el.berserker, il.grosserHeiltrank);
	public Field dungeon_smaug_drachenatmen = new Field("Dungeon", el.smaug, il.drachenatem);
}
