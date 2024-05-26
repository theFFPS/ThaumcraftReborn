package org.ffpsss.thaumcraft.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class AuraNodeType {
    public static final Map<String, AuraNodeType> TYPES = new HashMap<>();

    public boolean bleeding = false;
    public boolean regeneration = true;
    public boolean ableToCure = false;
    public boolean changeBiome = false;
    public boolean spawnEntities = false;
    public boolean taintable = true;
    public boolean consumeNearby = false;
    public double cureChance = 1d;
    public List<EntityType<? extends Entity>> entitiesToSpawn = new ArrayList<>();

    public AuraNodeType() {}
    public AuraNodeType enableBleeding() {
        bleeding = true;
        return this;
    }
    public AuraNodeType enableConsumeNearby() {
        consumeNearby = true;
        return this;
    }
    public AuraNodeType disableTainting() {
        taintable = false;
        return this;
    }
    public AuraNodeType enableSpawnEntities(List<EntityType<? extends Entity>> entities) {
        spawnEntities = true;
        entitiesToSpawn = entities;
        return this;
    }
    public AuraNodeType enableCuring(double cureChance) {
        ableToCure = true;
        this.cureChance = cureChance;
        return this;
    }
    public AuraNodeType enableBiomeChanges() {
        changeBiome = true;
        return this;
    }
    public AuraNodeType disableRegeneration() {
        regeneration = false;
        return this;
    }

    public static void generateTypes() {
        TYPES.put("normal", new AuraNodeType());
        TYPES.put("unstable", new AuraNodeType().enableBleeding().disableRegeneration().enableCuring(0.0001d));
        TYPES.put("sinister", new AuraNodeType().enableBiomeChanges().enableSpawnEntities(Arrays.asList(EntityType.ZOMBIE)));
        TYPES.put("tainted", new AuraNodeType().enableBiomeChanges().disableTainting());
        TYPES.put("hungry", new AuraNodeType().enableConsumeNearby());
        TYPES.put("pure", new AuraNodeType().enableBiomeChanges().disableTainting());
    }

    public static AuraNodeType getType(String ID) {
        return TYPES.get(ID);
    }
}
