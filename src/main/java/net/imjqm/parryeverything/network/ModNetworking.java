package net.imjqm.parryeverything.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {
  private static final String PROTOCOL_VERSION = "1";
  public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation("parryeverything", "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

  private static int packetId = 0;

  public static void register() {
    CHANNEL.registerMessage(packetId++, PlayAnimationPacket.class, PlayAnimationPacket::encode, PlayAnimationPacket::decode, PlayAnimationPacket::handle);
  }
}
