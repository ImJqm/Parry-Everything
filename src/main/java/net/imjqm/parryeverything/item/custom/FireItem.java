package net.imjqm.parryeverything.item.custom;

import net.imjqm.parryeverything.event.DelayedActionHandler;
import net.minecraft.world.item.Item;

public class FireItem extends Item {
  
  public static DelayedActionHandler handler = new DelayedActionHandler();

  public FireItem(Properties pProperties) {
    super(pProperties);
  }
  

}
