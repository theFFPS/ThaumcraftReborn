package org.ffpsss.thaumcraft;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ffpsss.thaumcraft.api.aspect.Aspect;
import org.ffpsss.thaumcraft.item.Shard;
import org.ffpsss.thaumcraft.item.SalisMundus;

import java.util.*;

public class ThaumicItems {
    public static Item SALIS_MUNDUS;
    public static Item BALANCED_SHARD;
    public static Item AER_SHARD;
    public static Item AQUA_SHARD;
    public static Item ORDO_SHARD;
    public static Item PERDITIO_SHARD;
    public static Item TERRA_SHARD;
    public static Item IGNIS_SHARD;
    public static Item ARCANE_TABLE;
    public static Item AURANODE;

    public static void registerAll() {
        SALIS_MUNDUS = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "salis_mundus"),
                new SalisMundus(new Item.Settings().maxCount(1))
        );
        BALANCED_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_balanced"),
                new Shard(new Item.Settings().maxCount(64))
        );
        PERDITIO_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_perditio"),
                new Shard(new Item.Settings().maxCount(64))
        );
        ORDO_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_ordo"),
                new Shard(new Item.Settings().maxCount(64))
        );
        TERRA_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_terra"),
                new Shard(new Item.Settings().maxCount(64))
        );
        AQUA_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_aqua"),
                new Shard(new Item.Settings().maxCount(64))
        );
        AER_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_aer"),
                new Shard(new Item.Settings().maxCount(64))
        );
        IGNIS_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "shard_ignis"),
                new Shard(new Item.Settings().maxCount(64))
        );

        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.SALIS_MUNDUS, Arrays.asList(
                Aspect.getAspectById("praecantatio", 5),
                Aspect.getAspectById("potentia", 5)
        ));

        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.IGNIS_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("ignis", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.AER_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("aer", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.AQUA_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("aqua", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.TERRA_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("terra", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.PERDITIO_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("perditio", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.ORDO_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("ordo", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.BALANCED_SHARD, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("vitreus", 1),
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("ordo", 1),
                Aspect.getAspectById("perditio", 1),
                Aspect.getAspectById("aer", 2)
        ));
    }
}
