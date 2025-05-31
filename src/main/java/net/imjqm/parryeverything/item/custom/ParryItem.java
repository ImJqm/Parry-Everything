package net.imjqm.parryeverything.item.custom;

import net.imjqm.parryeverything.data.ParryData;
import net.minecraft.network.chat.Component;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ParryItem extends Item{
  public ParryItem(Properties pProperties) {
    super(pProperties);
  }
  
  @Override
  public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
    ParryData.LAST_PARRY.put(pPlayer.getUUID(), pLevel.getGameTime());
    if (pLevel.isClientSide() && ParryData.LAST_HIT_TICK.get(pPlayer.getUUID())!=null) {
      Long currTime = pLevel.getGameTime();
      Long hitTime = ParryData.LAST_HIT_TICK.get(pPlayer.getUUID());
      
      boolean result = (Math.abs(currTime-hitTime)<=20) ; 
      if (result) {
        pPlayer.sendSystemMessage(Component.literal("Parried on item click"));
        DoParry(pPlayer, pLevel);
      }
    }
    return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

    

  }

  public static void DoParry(Player pPlayer, Level pLevel) {
      pPlayer.sendSystemMessage(Component.literal("Parried"));
      pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.MASTER, 1f, 1f);
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



