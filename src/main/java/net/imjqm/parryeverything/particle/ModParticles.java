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
  
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK0 = PARTICLES.register("parry_spark0", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK1 = PARTICLES.register("parry_spark1", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK2 = PARTICLES.register("parry_spark2", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK3 = PARTICLES.register("parry_spark3", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK4 = PARTICLES.register("parry_spark4", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK5 = PARTICLES.register("parry_spark5", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK6 = PARTICLES.register("parry_spark6", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK7 = PARTICLES.register("parry_spark7", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK8 = PARTICLES.register("parry_spark8", () -> new SimpleParticleType(true));
  public static final RegistryObject<SimpleParticleType> PARRY_SPARK9 = PARTICLES.register("parry_spark9", () -> new SimpleParticleType(true));

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void registerParticles(RegisterParticleProvidersEvent event) {
    event.registerSpriteSet(ModParticles.PARRY_SPARK0.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK1.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK2.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK3.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK4.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK5.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK6.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK7.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK8.get(), ParryParticle.ParryParticleFactory::new);
    event.registerSpriteSet(ModParticles.PARRY_SPARK9.get(), ParryParticle.ParryParticleFactory::new);
  }
  public static void register(IEventBus eventBus) {
    PARTICLES.register(eventBus);
  }
}
