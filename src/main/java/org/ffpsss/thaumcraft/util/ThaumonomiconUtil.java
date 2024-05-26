package org.ffpsss.thaumcraft.util;

import org.ffpsss.sml.api.entity.EntityDataSaver;

import net.minecraft.nbt.NbtCompound;

public class ThaumonomiconUtil {
    public static void unlockFeature(EntityDataSaver player, String feature) {
        NbtCompound nbt = player.getPersistentData();
        if (!nbt.contains("thaumonomicon", 10)) {
            NbtCompound thaumonomiconCompound = new NbtCompound();
            thaumonomiconCompound.putBoolean("feature_" + feature, true);
            nbt.put("thaumonomicon", thaumonomiconCompound);
        } else {
            NbtCompound thaumonomiconCompound = nbt.getCompound("thaumonomicon");
            thaumonomiconCompound.putBoolean("feature_" + feature, true);
            nbt.put("thaumonomicon", thaumonomiconCompound);
        }
    }
    public static void lockFeature(EntityDataSaver player, String feature) {
        NbtCompound nbt = player.getPersistentData();
        if (!nbt.contains("thaumonomicon", 10)) {
            NbtCompound thaumonomiconCompound = new NbtCompound();
            thaumonomiconCompound.putBoolean("feature_" + feature, false);
            nbt.put("thaumonomicon", thaumonomiconCompound);
        } else {
            NbtCompound thaumonomiconCompound = nbt.getCompound("thaumonomicon");
            thaumonomiconCompound.putBoolean("feature_" + feature, false);
            nbt.put("thaumonomicon", thaumonomiconCompound);
        }
    }
    public static boolean hasFeature(EntityDataSaver player, String feature) {
        NbtCompound nbt = player.getPersistentData();
        if (!nbt.contains("thaumonomicon", 10)) return false;
        return nbt.getCompound("thaumonomicon").contains("feature_" + feature);
    }
}