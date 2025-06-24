package net.imjqm.parryeverything.item.custom;

import net.imjqm.parryeverything.data.ParryData;
import net.imjqm.parryeverything.particle.ModParticles;
import net.imjqm.parryeverything.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.imjqm.parryeverything.event.DelayedActionHandler;

@Mod.EventBusSubscriber(modid = "parryeverything", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParryItem extends Item{

  public static DelayedActionHandler handler = new DelayedActionHandler();

  public ParryItem(Properties pProperties) {
    super(pProperties);
  }
  
  @Override
  public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
    ItemStack stack = pPlayer.getItemInHand(pUsedHand);
    if (pPlayer.getCooldowns().isOnCooldown(stack.getItem())) {
      return InteractionResultHolder.pass(stack);
    }
    pPlayer.getCooldowns().addCooldown(stack.getItem(),40);
    ParryData.LAST_PARRY.put(pPlayer.getUUID(), pLevel.getGameTime());
    if (pLevel.isClientSide() && ParryData.LAST_HIT_TICK.get(pPlayer.getUUID())!=null) {
      pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.PARRY_DEFLECT.get(), SoundSource.BLOCKS, 1f, 1f);
      Long currTime = pLevel.getGameTime();
      Long hitTime = ParryData.LAST_HIT_TICK.get(pPlayer.getUUID());
      
      //boolean result = (Math.abs(currTime-hitTime)<=20) ; 
      //optional parry timing when right clicked after being hitTime
      //Cannot implement no damage
      
      /*if (result) {
        pPlayer.sendSystemMessage(Component.literal("Parried on item click"));
        DoParry(pPlayer, pLevel);
      }*/
    }
    return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

    

  }

  public static void DoParry(Player pPlayer, Level pLevel, LivingEntity attacker) {
      pPlayer.sendSystemMessage(Component.literal("Parried"));
      if (attacker != null) {
          double dx = attacker.getX() - pPlayer.getX();
          double dz = attacker.getZ() - pPlayer.getZ();
          attacker.knockback(1.5F, -dx, -dz);
          ItemStack stackmain = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
          ItemStack stackoff = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
          if (stackmain.getItem() instanceof ParryItem) {
              pPlayer.getCooldowns().removeCooldown(stackmain.getItem());
          }
          if (stackoff.getItem() instanceof ParryItem) {
              pPlayer.getCooldowns().removeCooldown(stackoff.getItem());
          }
      }
      if (!pLevel.isClientSide()) {
          if (pLevel instanceof ServerLevel serverLevel) {
              int time = (int)((pLevel.getGameTime())%10);
             // pPlayer.sendSystemMessage(Component.literal("Played Particle #" + time + " the time was " + pLevel.getGameTime()));
              handler.setPlayer(pPlayer);
              handler.setTicksRemaining(10);
              handler.setMaxTicks(10);
              handler.setServerLevel(serverLevel);
              handler.setX(pPlayer.getX());
              handler.setY(pPlayer.getY());
              handler.setZ(pPlayer.getZ());
              handler.start();
          }
          pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.PARRY_DEFLECT.get(), SoundSource.MASTER, 1f, 1f);
      }
  }
  
  @SubscribeEvent
  public static void onServerTick(TickEvent.ServerTickEvent event) {
    if (event.phase == TickEvent.Phase.END) {
      handler.tick();
    }
  }







//public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
//Player player = event.getEntity();
//player.sendSystemMessage(Component.literal("Right Clicked"));
//Long time = event.getLevel().getGameTime();
//if (Math.abs(time-ParryData.LAST_HIT_TICK.get(player.getUUID()))>=20) {
//  player.sendSystemMessage(Component.literal("Parrried attack"));
//} else {
//  player.sendSystemMessage(Component.literal("Failed to parry attack"));
//}
}



