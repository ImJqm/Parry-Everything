package net.imjqm.parryeverything.client;

import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import net.minecraft.client.player.AbstractClientPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientAnimationManager {
    private static final Map<UUID, ModifierLayer<IAnimation>> animationLayers = new HashMap<>();

    public static ModifierLayer<IAnimation> getOrCreateLayer(AbstractClientPlayer player) {
        return animationLayers.computeIfAbsent(player.getUUID(), uuid -> {
            ModifierLayer<IAnimation> layer = new ModifierLayer<>();
            AnimationStack stack = PlayerAnimationAccess.getPlayerAnimLayer(player);
            stack.addAnimLayer(42, layer);
            return layer;
        });
    }
}
