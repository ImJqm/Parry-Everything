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
    if (pLevel.isClientSide()) {
    pPlayer.sendSystemMessage(Component.literal("He fucking right clicked the fucking item"));
     ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
     if (itemstack.isEdible()) {
        if (pPlayer.canEat(itemstack.getFoodProperties(pPlayer).canAlwaysEat())) {
           pPlayer.startUsingItem(pUsedHand);
           return InteractionResultHolder.consume(itemstack);
        } else {
           return InteractionResultHolder.fail(itemstack);
        }
     } else {
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
     }
    }
    return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
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



