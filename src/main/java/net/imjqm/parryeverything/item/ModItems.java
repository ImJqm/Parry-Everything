package net.imjqm.parryeverything.item;

import net.imjqm.parryeverything.ParryEverything;
import net.imjqm.parryeverything.item.custom.ParryItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

public class ModItems {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ParryEverything.MODID);

  public static final RegistryObject<Item> KUSABIMARU = ITEMS.register("kusabimaru", () -> new SwordItem(Tiers.NETHERITE,16,3,new Item.Properties()) );

  public static final RegistryObject<Item> SHINOBI_PROSTHETIC = ITEMS.register("shinobi_prosthetic",() -> new ParryItem(new Item.Properties()));

  
  public static void regiser(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
