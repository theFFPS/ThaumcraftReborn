package org.ffpsss.thaumcraft.api.auranode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    public String auraNodeSubType;
    public String auraNodeType;
    public List<EntityType<? extends Entity>> entitiesToSpawn = new ArrayList<>();

    public AuraNodeType(String type) {
        auraNodeType = type;
    }
    public AuraNodeType randomSubType() {
        List<String> types = Arrays.asList("bright", "pale", "pale", "pale", "pale", "pale", "pale", "pale", "fading", "fading");
        auraNodeSubType = types.get(new Random().nextInt(types.size()));
        return this;
    }
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
        TYPES.put("normal", new AuraNodeType("normal"));
        TYPES.put("unstable", new AuraNodeType("unstable").enableBleeding().disableRegeneration().enableCuring(0.0001d));
        TYPES.put("sinister", new AuraNodeType("sinister").enableBiomeChanges().enableSpawnEntities(Arrays.asList(EntityType.ZOMBIE)));
        TYPES.put("tainted", new AuraNodeType("tainted").enableBiomeChanges().disableTainting());
        TYPES.put("hungry", new AuraNodeType("hungry").enableConsumeNearby());
        TYPES.put("pure", new AuraNodeType("pure").enableBiomeChanges().disableTainting());
    }

    public static AuraNodeType getType(String ID) {
        return TYPES.get(ID);
    }
}
