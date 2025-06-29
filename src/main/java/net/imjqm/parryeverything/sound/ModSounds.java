package net.imjqm.parryeverything.sound;

import net.imjqm.parryeverything.ParryEverything;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

  public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ParryEverything.MODID);

  public static final RegistryObject<SoundEvent> PARRY_DEFLECT = registerSoundEvents("parry_deflect");

  private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
    return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ParryEverything.MODID, name)));
  }


  public static void register(IEventBus eventBus) {
    SOUND_EVENTS.register(eventBus);
  }
}
