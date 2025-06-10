package net.imjqm.parryeverything.particle;

import net.imjqm.parryeverything.ParryEverything;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
  public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ParryEverything.MODID);

  public static final RegistryObject<SimpleParticleType> PARRY_SPARK = PARTICLES.register("parry_spark", () -> new SimpleParticleType(true));

  public static void register(IEventBus eventBus) {
    System.out.println("[DEBUG] Registered ParticleType: " + PARRY_SPARK.getId());
    PARTICLES.register(eventBus);
  }
}
