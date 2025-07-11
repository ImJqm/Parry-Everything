package net.imjqm.parryeverything.event;

import net.imjqm.parryeverything.data.ParryData;
import net.imjqm.parryeverything.item.custom.ParryItem;
import net.imjqm.parryeverything.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ParryEverything.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParryEvent {
  @SubscribeEvent
  public static void onPlayerHurt(LivingHurtEvent event) {
    if (event.getEntity() instanceof Player player) {
      ParryData.LAST_HIT_TICK.put(player.getUUID(), player.level().getGameTime());
      Entity directSource = event.getSource().getDirectEntity();
      // TODO: Implement some sort of switch case type beat here ☠️
      if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
        player.sendSystemMessage(Component.literal("You this dude deaduzz hit you"));
        ParryData.LAST_ATTACKER.put(player.getUUID(), attacker);
      }
      if (event.getSource().getDirectEntity() instanceof Projectile projectile) {
        player.sendSystemMessage(Component.literal("You parried a projectile ;)"));
        ParryData.LAST_ATTACKER.put(player.getUUID(), projectile);
      }
      if (ParryData.LAST_PARRY.get(player.getUUID())!=null && Math.abs(ParryData.LAST_HIT_TICK.get(player.getUUID())-ParryData.LAST_PARRY.get(player.getUUID()))<=5) {
        ParryItem.DoParry(player, event.getEntity().level(), ParryData.LAST_ATTACKER.get(player.getUUID()));
        event.setAmount(0.0F);
      }
    }
  }
}
