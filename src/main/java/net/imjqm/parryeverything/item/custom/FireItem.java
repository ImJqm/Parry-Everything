package net.imjqm.parryeverything.item.custom;


import net.imjqm.parryeverything.event.DelayedActionHandler;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireItem extends Item {
  
  public static DelayedActionHandler handler = new DelayedActionHandler();

  public FireItem(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
    if(pLevel.isClientSide()) {
      Vec3 lookVec = pPlayer.getLookAngle();
      pLevel.addParticle(ParticleTypes.FLAME, pPlayer.getX() + lookVec.x(), pPlayer.getY()+ lookVec.y() + pPlayer.getEyeHeight(), pPlayer.getZ()+ lookVec.z(), 0.1*lookVec.x(), 0.1*lookVec.y(), 0.1*lookVec.z());
    }
    return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
  }
  

}
