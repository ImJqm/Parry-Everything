package net.imjqm.parryeverything.item.custom;

import net.imjqm.parryeverything.data.ParryData;
import net.imjqm.parryeverything.particle.ModParticles;
import net.imjqm.parryeverything.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.imjqm.parryeverything.event.DelayedActionHandler;
import net.imjqm.parryeverything.network.ModNetworking;
import net.imjqm.parryeverything.network.PlayAnimationPacket;

import java.util.UUID;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.imjqm.parryeverything.ParryEverything;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
      pPlayer.sendSystemMessage(Component.literal("You are on cooldown"));
      return InteractionResultHolder.pass(stack);
    }
    
    UUID playerUUID = pPlayer.getUUID();
    String animationId = "parry";

    if (!pPlayer.level().isClientSide() && pPlayer instanceof ServerPlayer serverPlayer) {
        ModNetworking.CHANNEL.send(
            PacketDistributor.TRACKING_ENTITY.with(() -> serverPlayer),
            new PlayAnimationPacket(serverPlayer.getUUID(), animationId)
        );
        ModNetworking.CHANNEL.send(
            PacketDistributor.PLAYER.with(() -> serverPlayer),
            new PlayAnimationPacket(serverPlayer.getUUID(), animationId)
        );
    }

    /*if (pLevel.isClientSide()) {
      var animation = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) pPlayer).get(new ResourceLocation(ParryEverything.MODID, "animation"));
      if (animation != null) {
          //You can set an animation from anywhere ON THE CLIENT
          //Do not attempt to do this on a server, that will only fail

          animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation("parryeverything", "parry"))));
          //You might use  animation.replaceAnimationWithFade(); to create fade effect instead of sudden change
          //See javadoc for details
      }

    }*/
  //  pPlayer.swing(pUsedHand);
    pPlayer.getCooldowns().addCooldown(stack.getItem(),40);
    pPlayer.sendSystemMessage(Component.literal("I just set ur cooldown to 40 ticks"));
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

  public static void DoParry(Player pPlayer, Level pLevel, Entity attacker) {
      pPlayer.sendSystemMessage(Component.literal("Parried"));
      if (attacker != null ) {
          if (attacker instanceof LivingEntity livingAttacker) {
              double dx = livingAttacker.getX() - pPlayer.getX();
              double dz = livingAttacker.getZ() - pPlayer.getZ();
              //livingAttacker.knockback(1.5F, -dx, -dz);
              livingAttacker.setDeltaMovement(dx * 0.9, 0.1, dz * 0.9);
              livingAttacker.hurtMarked = true; // flag entity for movement update
              pPlayer.sendSystemMessage(Component.literal("Parried livingAttacker: " + livingAttacker.getName().getString()));

          } else if (attacker instanceof Arrow proj) {
              Arrow arrow = new Arrow(pLevel, pPlayer);
              Vec3 lookVec = pPlayer.getLookAngle();
              arrow.shoot(lookVec.x(), lookVec.y(), lookVec.z(), 2.0f, 0.0f);
              pLevel.addFreshEntity(arrow);
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

      ItemStack stackmain = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
      ItemStack stackoff = pPlayer.getItemInHand(InteractionHand.OFF_HAND);

      if (stackmain.getItem() instanceof ParryItem) {
          pPlayer.getCooldowns().removeCooldown(stackmain.getItem());
          pPlayer.sendSystemMessage(Component.literal("I just reset ur cooldown of the item in ur main hand"));
      }

      if (stackoff.getItem() instanceof ParryItem) {
          pPlayer.getCooldowns().removeCooldown(stackoff.getItem());
          pPlayer.sendSystemMessage(Component.literal("I just reset ur cooldown of the item in ur off hand"));
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



