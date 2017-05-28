package depr.newVersion.meta;

import depr.newVersion.classes.Item;
import depr.newVersion.classes.NPC;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 06.05.2017, 16:11.
 */
public class NPCWithItem {

  private NPC npc;
  private Item item;

  public NPCWithItem(NPC npc, Item item){
    this.npc = npc;
    this.item = item;
  }

  // Getter and setter methods.
  public NPC getNpc() {
    return npc;
  }
  public Item getItem() {
    return item;
  }
  public void setNpc(NPC npc) {
    this.npc = npc;
  }
  public void setItem(Item item) {
    this.item = item;
  }
}
