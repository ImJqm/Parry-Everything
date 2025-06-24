package net.imjqm.parryeverything.event;

import java.util.*;
import net.imjqm.parryeverything.data.ParryData;
import net.imjqm.parryeverything.item.custom.ParryItem;
import net.imjqm.parryeverything.*;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.phys.Vec3;

@Mod.EventBusSubscriber(modid = ParryEverything.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParryEvent {
  @SubscribeEvent
  public static void onPlayerHurt(LivingHurtEvent event) {
    if (event.getEntity() instanceof Player player) {
      ParryData.LAST_HIT_TICK.put(player.getUUID(), player.level().getGameTime());
      if (event.getSource().getEntity() instanceof LivingEntity attacker) {
        ParryData.LAST_ATTACKER.put(player.getUUID(), attacker);
      }
      if (ParryData.LAST_PARRY.get(player.getUUID())!=null && Math.abs(ParryData.LAST_HIT_TICK.get(player.getUUID())-ParryData.LAST_PARRY.get(player.getUUID()))<=5) {
        Level level = event.getEntity().level();
        ParryItem.DoParry(player, event.getEntity().level(), ParryData.LAST_ATTACKER.get(player.getUUID()));
        event.setAmount(0.0F);
      }
    }
  }
}
