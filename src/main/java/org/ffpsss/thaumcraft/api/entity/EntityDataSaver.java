package org.ffpsss.thaumcraft.api.entity;

import net.minecraft.nbt.NbtCompound;

public interface EntityDataSaver {
    NbtCompound getPersistentData();
}
