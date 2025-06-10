package net.imjqm.parryeverything.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.multiplayer.ClientLevel;

public class ParryParticleProvider implements ParticleProvider<SimpleParticleType> {
  private final SpriteSet sprite;  
  
  public ParryParticleProvider(SpriteSet sprite) {
    this.sprite = sprite;
  }

  @Override
  public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
    return new ParryParticle(level, x, y, z, vx, vy, vz, sprite);
  }

}
