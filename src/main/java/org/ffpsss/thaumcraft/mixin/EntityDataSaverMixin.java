package org.ffpsss.thaumcraft.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.ffpsss.thaumcraft.api.entity.EntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityDataSaverMixin implements EntityDataSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound thaumcraftReborn$getPersistentData() {
        if (this.persistentData == null) this.persistentData = new NbtCompound();
        return persistentData;
    }
    
    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<?> info) {
        if (persistentData != null) nbt.put("thaumcraft.entity_data_storage", persistentData);
    }
    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("thaumcraft.entity_data_storage", 10)) persistentData = nbt.getCompound("thaumcraft.entity_data_storage");
    }
}
