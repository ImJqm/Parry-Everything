package net.imjqm.parryeverything.event;

import java.util.*;
import net.imjqm.parryeverything.data.ParryData;
import net.imjqm.parryeverything.*;
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
    }
  }
}
