package net.imjqm.parryeverything.network;
import java.util.*;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.imjqm.parryeverything.event.DelayedActionHandler;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.imjqm.parryeverything.ParryEverything;
import net.imjqm.parryeverything.client.ClientAnimationManager;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import dev.kosmx.playerAnim.impl.forge.*;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;


public class PlayAnimationPacket {
  public final UUID playerId;
  public final String animationId;

  public PlayAnimationPacket(UUID playerId, String animationId) {
    this.playerId = playerId;
    this.animationId = animationId;
  }
  
  public static void encode(PlayAnimationPacket msg, FriendlyByteBuf buf) {
    buf.writeUUID(msg.playerId);
    buf.writeUtf(msg.animationId);
  }

  public static PlayAnimationPacket decode(FriendlyByteBuf buf) {
    return new PlayAnimationPacket(buf.readUUID(), buf.readUtf());
  }
  public static void handle(PlayAnimationPacket msg, Supplier<NetworkEvent.Context> ctx) {
    ctx.get().enqueueWork(() -> {
      DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        Player player = level.getPlayerByUUID(msg.playerId);
        if (player instanceof AbstractClientPlayer clientPlayer) {
          ResourceLocation animationKey = new ResourceLocation("parryeverything", msg.animationId);
          var keyframe = PlayerAnimationRegistry.getAnimation(animationKey);
          
          if (keyframe != null) {
            var layer = ClientAnimationManager.getOrCreateLayer(clientPlayer);
            layer.setAnimation(new KeyframeAnimationPlayer(keyframe));
          }
        }
      });
    });
    ctx.get().setPacketHandled(true);
  }

}
