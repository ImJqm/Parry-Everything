package net.imjqm.parryeverything.event;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.imjqm.parryeverything.particle.ModParticles;
import net.minecraft.world.level.Level;
import java.util.ArrayList;

public class DelayedActionHandler {
  
  private int ticksRemaining;
  private int maxTicks;
  private boolean active = false;
  private Player player;
  private ServerLevel serverLevel;
  private double x;
  private double y;
  private double z;

 // private ArrayList<RegistryObject<SimpleParticleType>> particles = new ArrayList<RegistryObject<SimpleParticleType>>();


  public DelayedActionHandler() {

  }
   
  public DelayedActionHandler(int ticksRemaining, Player player, ServerLevel serverLevel) {
    this.ticksRemaining = ticksRemaining;
    this.maxTicks = ticksRemaining;
    this.player = player;
    this.serverLevel = serverLevel;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double x) {
    this.y = x;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public void setTicksRemaining(int ticksRemaining) {
    this.ticksRemaining = ticksRemaining;
  }

  public int getTicksRemaining() {
    return this.ticksRemaining;
  }

  public void setMaxTicks(int maxTicks) {
    this.maxTicks = maxTicks;
  }

  public int getMaxTicks() {
    return this.maxTicks;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setServerLevel(ServerLevel serverLevel) {
    this.serverLevel = serverLevel;
  } 

  public ServerLevel getServerLevel() {
    return this.serverLevel;
  }

  public boolean isActive() {
    return this.active;
  }

  public void start() {
    if (!this.active) {
      this.ticksRemaining = this.maxTicks;
      this.active = true;
    }
  }

  public void tick() {
    if (!active) return;
    
    //this.player.sendSystemMessage(Component.literal("Tick Action! Ticks Left: " + this.getTicksRemaining() + " Current Gametime: " + this.serverLevel.getGameTime()));
    switch(this.ticksRemaining) {
        case 10: 
            serverLevel.sendParticles(ModParticles.PARRY_SPARK0.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 9:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK1.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 8:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK2.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 7:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK3.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 6:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK4.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 5:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK5.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 4:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK6.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 3:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK7.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 2:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK8.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
        case 1:
            serverLevel.sendParticles(ModParticles.PARRY_SPARK9.get(), this.x, this.y+1, this.z, 1, 0, 0, 0, 0);
            break;
    }

    this.ticksRemaining--;

    if (ticksRemaining <= 0 ) {
      active = false;
      this.player.sendSystemMessage(Component.literal("Done!"));
    }
    //serverLevel.sendParticles(particles[this.ticksRemaining-1].get(), x, y+1, z, 1, 0, 0, 0, 0);

     
  }
}
