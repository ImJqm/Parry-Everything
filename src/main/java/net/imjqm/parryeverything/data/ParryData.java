package net.imjqm.parryeverything.data;

import java.util.HashMap;

import net.minecraft.world.entity.Entity;

import java.util.*;

public class ParryData {
  public static final Map<UUID, Long> LAST_HIT_TICK = new HashMap<>();
  public static final Map<UUID, Boolean> PARRYING = new HashMap<>();
  public static final Map<UUID, Long> LAST_PARRY = new HashMap<>();
  public static final Map<UUID, Entity> LAST_ATTACKER = new HashMap<>();
}
