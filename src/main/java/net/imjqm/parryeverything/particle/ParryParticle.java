package net.imjqm.parryeverything.particle;

import ca.weblite.objc.Client;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParryParticle extends TextureSheetParticle{

  private final SpriteSet sprite;
  public ParryParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet sprite) {
    super(level, x, y, z, vx, vy, vz);
    this.lifetime=1;
    this.setSize(1, 1);
    this.xd=vx;
    this.yd=vy;
    this.zd=vz;
    this.quadSize = 1.5F;
    this.hasPhysics = false;
    this.rCol=1f;
    this.gCol=1f;
    this.bCol=1f;
    this.sprite = sprite;
    this.setSpriteFromAge(this.sprite);
  }

  @Override
  public void tick() {
    super.tick();
    this.remove();
    /*int frame = Math.min(this.age, 10); // Clamp to 0–10
  
    System.out.println("[AWESOMEDEBUG] Particle Frame: " + frame + " Particle age: " + this.age + " Particle lifetime: " + this.lifetime + " TextureSprite: " + this.sprite.get(frame,10));
    this.setSprite(this.sprite.get(frame, 10));

    if(this.age >= this.lifetime) {
      this.remove();
    }*/

  }

  @Override
  public ParticleRenderType getRenderType() {
    return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
  }

  @OnlyIn(Dist.CLIENT)
  public static final class ParryParticleFactory implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet spriteSet;

    public ParryParticleFactory(SpriteSet sprite) {
      this.spriteSet = sprite;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
      return new ParryParticle(level, x, y, z, vx, vy, vz, spriteSet);
    }
  }
}
