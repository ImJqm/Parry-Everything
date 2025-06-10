package net.imjqm.parryeverything.particle;

import net.imjqm.parryeverything.ParryEverything;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod.EventBusSubscriber(modid= ParryEverything.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticles {
  public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ParryEverything.MODID);
  
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK = PARTICLES.register("parry_spark", () -> new SimpleParticleType(true));

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void registerParticles(RegisterParticleProvidersEvent event) {
    event.registerSpriteSet(ModParticles.PARRY_SPARK.get(), ParryParticle.ParryParticleFactory::new);
  }
  public static void register(IEventBus eventBus) {
    PARTICLES.register(eventBus);
  }
}
