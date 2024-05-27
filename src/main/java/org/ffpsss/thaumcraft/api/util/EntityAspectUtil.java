package org.ffpsss.thaumcraft.api.util;

import org.ffpsss.thaumcraft.api.aspect.Aspect;

import net.minecraft.nbt.NbtCompound;
import org.ffpsss.thaumcraft.api.entity.EntityDataSaver;

public class EntityAspectUtil {
    public static void entityGiveAspect(EntityDataSaver player, Aspect aspect) {
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
        if (!nbt.contains("aspects", 10)) {
            NbtCompound aspectsCompound = new NbtCompound();
            aspectsCompound.putInt(aspect.metadata.ID, aspect.count);
            nbt.put("aspects", aspectsCompound);
        } else {
            NbtCompound aspectsCompound = nbt.getCompound("aspects");
            aspectsCompound.putInt(aspect.metadata.ID, aspect.count + aspectsCompound.getInt(aspect.metadata.ID));
            nbt.put("aspects", aspectsCompound);
        }
    }
    public static Aspect entityTakeAspect(EntityDataSaver player, Aspect aspect) {
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
        if (nbt.contains("aspects", 10)) {
            NbtCompound aspectsCompound = new NbtCompound();
            int overFlow = 0;
            if (aspectsCompound.getInt(aspect.metadata.ID) < aspect.count)
                overFlow = aspect.count - aspectsCompound.getInt(aspect.metadata.ID);
            if (aspectsCompound.getInt(aspect.metadata.ID) <= aspect.count) aspectsCompound.remove(aspect.metadata.ID);
            else {
                aspectsCompound.putInt(aspect.metadata.ID, aspectsCompound.getInt(aspect.metadata.ID) - aspect.count);
            }
            nbt.put("aspects", aspectsCompound);
            return Aspect.getAspectById(aspect.metadata.ID, overFlow);
        }
        return aspect;
    }
    public static boolean hasAspect(EntityDataSaver player, String aspectId) {
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
        if (!nbt.contains("aspects", 10)) return false;
        return nbt.getCompound("aspects").contains(aspectId);
    }
    public static boolean hasAspect(EntityDataSaver player, Aspect aspect) {
        NbtCompound nbt = player.thaumcraftReborn$getPersistentData();
        if (!nbt.contains("aspects", 10)) return false;
        return nbt.getCompound("aspects").getInt(aspect.metadata.ID) >= aspect.count;
    }
}
