package net.imjqm.parryeverything.event;

import java.util.*;
import net.imjqm.parryeverything.data.ParryData;
import net.imjqm.parryeverything.item.custom.ParryItem;
import net.imjqm.parryeverything.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ParryEverything.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParryEvent {
  @SubscribeEvent
  public static void onPlayerHurt(LivingHurtEvent event) {
    if (event.getEntity() instanceof Player player) {
      ParryData.LAST_HIT_TICK.put(player.getUUID(), player.level().getGameTime());
      if (ParryData.LAST_PARRY.get(player.getUUID())!=null && Math.abs(ParryData.LAST_HIT_TICK.get(player.getUUID())-ParryData.LAST_PARRY.get(player.getUUID()))<=20) {
        player.sendSystemMessage(Component.literal("Parried on player hit"));
        ParryItem.DoParry(player, event.getEntity().level());
      }
    }
  }
}
