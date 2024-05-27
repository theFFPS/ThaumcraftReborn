package org.ffpsss.thaumcraft.api.util;

import net.minecraft.nbt.NbtCompound;
import org.ffpsss.thaumcraft.api.entity.EntityDataSaver;

public class ThaumonomiconUtil {
    public static void unlockFeature(EntityDataSaver player, String feature) {
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
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
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
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
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
        if (!nbt.contains("thaumonomicon", 10)) return false;
        return nbt.getCompound("thaumonomicon").contains("feature_" + feature);
    }
}
