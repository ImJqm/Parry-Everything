package net.imjqm.parryeverything.animation;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.imjqm.parryeverything.*;

@Mod.EventBusSubscriber(modid = ParryEverything.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParryAnimator {
  @SubscribeEvent 
  public static void onClientSetup(FMLClientSetupEvent event) {
    PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(new ResourceLocation(ParryEverything.MODID, "animation"), 42, ParryAnimator::registerPlayerAnimation);
  }

  private static IAnimation registerPlayerAnimation(AbstractClientPlayer player) {
    return new ModifierLayer<>();
  }
}
