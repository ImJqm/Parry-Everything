package net.imjqm.parryeverything.event;

import net.imjqm.parryeverything.ParryEverything;
import net.imjqm.parryeverything.particle.ParryParticleProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.imjqm.parryeverything.particle.*;
import net.minecraft.resources.*;

@Mod.EventBusSubscriber(modid = ParryEverything.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT) 
public class ClientModEvents {
  @SubscribeEvent
  public static void onRegisterParticlesFactories(RegisterParticleProvidersEvent event) {
    event.registerSpriteSet(ModParticles.PARRY_SPARK.get(), ParryParticleProvider::new);
    System.out.println("[SIGMADebug] Registed Particle sprite set for PARRY_SPARK");

  }

  
}
