package net.imjqm.parryeverything.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.Level;
public class ParryParticle extends TextureSheetParticle {
  private final SpriteSet sprite;

  protected ParryParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet sprite) {
    super(level, x, y, z, vx, vy, vz);
    this.sprite = sprite;
    this.lifetime = 10;
    this.setSize(0.25F, 0.25F);
    this.hasPhysics = false;
    this.setSpriteFromAge(this.sprite);

    System.out.println("[COOLDEBUG] ParryParticle created with random sprite frame");


  }

  @Override
  public void tick() {
    super.tick();
    this.setSpriteFromAge(this.sprite);
    if (this.age>=this.lifetime) {
      this.remove();
    }
  }

  @Override
  public ParticleRenderType getRenderType() {
    return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
  }
  
}
