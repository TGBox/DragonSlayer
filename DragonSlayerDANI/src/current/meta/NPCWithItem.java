package current.meta;

import current.classes.Item;
import current.classes.NPC;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 20:45.
 */
public class NPCWithItem {

  private NPC npc;
  private Item item;

  /**
   * constructor method to create a new NPCWithItem object.
   * is used to have an easy way to transfer both the npc as well as the corresponding quest item.
   *
   * @param npc the NPC.
   * @param item the quest Item.
   */
  public NPCWithItem(NPC npc, Item item) {
    this.npc = npc;
    this.item = item;
  }

  // Getter and setter methods.
  public NPC getNpc() {
    return npc;
  }

  public void setNpc(NPC npc) {
    this.npc = npc;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

}
