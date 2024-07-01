package org.ffpsss.thaumcraft;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.ffpsss.thaumcraft.api.aspect.Aspect;
import org.ffpsss.thaumcraft.item.Shard;
import org.ffpsss.thaumcraft.item.SalisMundus;

import java.util.*;

public class ThaumicItems {
    public static Item SALIS_MUNDUS = createItem("salis_mundus", new SalisMundus(new Item.Settings().maxCount(1)));
    public static Item BALANCED_SHARD = createItem("shard_balanced", new Shard(new Item.Settings().maxCount(64)));
    public static Item AER_SHARD = createItem("shard_aer", new Shard(new Item.Settings().maxCount(64)));
    public static Item AQUA_SHARD = createItem("shard_aqua", new Shard(new Item.Settings().maxCount(64)));
    public static Item ORDO_SHARD = createItem("shard_ordo", new Shard(new Item.Settings().maxCount(64)));
    public static Item PERDITIO_SHARD = createItem("shard_perditio", new Shard(new Item.Settings().maxCount(64)));
    public static Item TERRA_SHARD = createItem("shard_terra", new Shard(new Item.Settings().maxCount(64)));
    public static Item IGNIS_SHARD = createItem("shard_ignis", new Shard(new Item.Settings().maxCount(64)));
    public static Item QUICKSILVER = createItem("quicksilver", new Item(new Item.Settings().maxCount(64)));
    public static Item QUICKSILVER_DROP = createItem("quicksilver_drop", new Item(new Item.Settings().maxCount(64)));

    public static Item ARCANE_TABLE;
    public static Item AURANODE;
    public static Item VISHROOM;
    public static Item CINDERPEARL;
    public static Item SHIMMERLEAF;

    public static Item createItem(String ID, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of("thaumcraft", ID), item);
    }

    public static void registerAll() {
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
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.SHIMMERLEAF, Arrays.asList(
                Aspect.getAspectById("herba", 2),
                Aspect.getAspectById("permutatio", 2),
                Aspect.getAspectById("praecantatio", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.CINDERPEARL, Arrays.asList(
                Aspect.getAspectById("herba", 2),
                Aspect.getAspectById("ignis", 2),
                Aspect.getAspectById("praecantatio", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.VISHROOM, Arrays.asList(
                Aspect.getAspectById("herba", 2),
                Aspect.getAspectById("venenum", 1),
                Aspect.getAspectById("praecantatio", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.QUICKSILVER, Arrays.asList(
                Aspect.getAspectById("permuatio", 2),
                Aspect.getAspectById("venenum", 1),
                Aspect.getAspectById("metallum", 3)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(ThaumicItems.QUICKSILVER_DROP, Collections.singletonList(
                Aspect.getAspectById("metallum", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.FLOWERS, Arrays.asList(
                Aspect.getAspectById("herba", 1),
                Aspect.getAspectById("sensus", 1),
                Aspect.getAspectById("victus", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.BAMBOO_BLOCKS, Arrays.asList(
                Aspect.getAspectById("herba", 1),
                Aspect.getAspectById("sensus", 1),
                Aspect.getAspectById("victus", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.LEAVES, Collections.singletonList(
                Aspect.getAspectById("herba", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.COAL_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("ignis", 1),
                Aspect.getAspectById("potentia", 2)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.COALS, Arrays.asList(
                Aspect.getAspectById("ignis", 1),
                Aspect.getAspectById("potentia", 2)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.REDSTONE_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("machine", 2),
                Aspect.getAspectById("potentia", 2)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.GOLD_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("lucrum", 1),
                Aspect.getAspectById("metallum", 2)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.IRON_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("metallum", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.COPPER_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("metallum", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.LAPIS_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("sensus", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.DIAMOND_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("lucrum", 3),
                Aspect.getAspectById("vitreus", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.EMERALD_ORES, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("lucrum", 4),
                Aspect.getAspectById("vitreus", 3)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.NETHER_QUARTZ_ORE, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("vitreus", 3)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.GLOWSTONE, Arrays.asList(
                Aspect.getAspectById("lux", 10),
                Aspect.getAspectById("sensus", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.DIRT, Collections.singletonList(Aspect.getAspectById("terra", 2)));
        ThaumicAspects.ITEM_ASPECTS.put(Items.GRASS_BLOCK, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("herba", 1)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.PODZOL, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("herba", 1)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.ACTIVATOR_RAIL, Arrays.asList(
                Aspect.getAspectById("machina", 2),
                Aspect.getAspectById("metallum", 3)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.RAIL, Arrays.asList(
                Aspect.getAspectById("iter", 1),
                Aspect.getAspectById("metallum", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.DETECTOR_RAIL, Arrays.asList(
                Aspect.getAspectById("machina", 1),
                Aspect.getAspectById("metallum", 3),
                Aspect.getAspectById("sensus", 1)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.POWERED_RAIL, Arrays.asList(
                Aspect.getAspectById("machina", 1),
                Aspect.getAspectById("lucrum", 1),
                Aspect.getAspectById("potentia", 1),
                Aspect.getAspectById("metallum", 2)
        ));
        ThaumicAspects.ITEM_ASPECTS.put(Items.STONE, Collections.singletonList(Aspect.getAspectById("terra", 2)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.STONE_TOOL_MATERIALS, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("perditio", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOOL, Arrays.asList(
                Aspect.getAspectById("fabrico", 1),
                Aspect.getAspectById("pannus", 4)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_BUTTONS, Collections.singletonList(Aspect.getAspectById("machina", 1)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.PLANKS, Collections.singletonList(Aspect.getAspectById("arbor", 1)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_SLABS, Collections.singletonList(Aspect.getAspectById("arbor", 1)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_STAIRS, Collections.singletonList(Aspect.getAspectById("arbor", 1)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_FENCES, Collections.singletonList(Aspect.getAspectById("arbor", 2)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.SIGNS, Collections.singletonList(Aspect.getAspectById("arbor", 1)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.ARROWS, Collections.singletonList(Aspect.getAspectById("telum", 1)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.LOGS, Collections.singletonList(Aspect.getAspectById("arbor", 4)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOOL_CARPETS, Collections.singletonList(Aspect.getAspectById("pannus", 2)));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.STONE_BUTTONS, Arrays.asList(
                Aspect.getAspectById("machina", 1),
                Aspect.getAspectById("terra", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.SAPLINGS, Arrays.asList(
                Aspect.getAspectById("arbor", 1),
                Aspect.getAspectById("herba", 2)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_DOORS, Arrays.asList(
                Aspect.getAspectById("machina", 1),
                Aspect.getAspectById("arbor", 4),
                Aspect.getAspectById("motus", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.BEDS, Arrays.asList(
                Aspect.getAspectById("fabrico", 2),
                Aspect.getAspectById("arbor", 2),
                Aspect.getAspectById("pannus", 9)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.SAND, Arrays.asList(
                Aspect.getAspectById("perditio", 1),
                Aspect.getAspectById("ordo", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.COMPASSES, Arrays.asList(
                Aspect.getAspectById("metallum", 12),
                Aspect.getAspectById("potentia", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.CREEPER_DROP_MUSIC_DISCS, Arrays.asList(
                Aspect.getAspectById("aer", 4),
                Aspect.getAspectById("aqua", 4),
                Aspect.getAspectById("lucrum", 4),
                Aspect.getAspectById("sensus", 4)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.CANDLES, Arrays.asList(
                Aspect.getAspectById("corpus", 1),
                Aspect.getAspectById("lux", 2),
                Aspect.getAspectById("praecantatio", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.FISHES, Arrays.asList(
                Aspect.getAspectById("corpus", 3),
                Aspect.getAspectById("aqua", 1),
                Aspect.getAspectById("victus", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.FENCE_GATES, Arrays.asList(
                Aspect.getAspectById("arbor", 4),
                Aspect.getAspectById("machina", 1),
                Aspect.getAspectById("iter", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_TRAPDOORS, Arrays.asList(
                Aspect.getAspectById("arbor", 2),
                Aspect.getAspectById("motus", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.CRIMSON_STEMS, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("herba", 1),
                Aspect.getAspectById("tenebrae", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WARPED_STEMS, Arrays.asList(
                Aspect.getAspectById("terra", 1),
                Aspect.getAspectById("herba", 1),
                Aspect.getAspectById("tenebrae", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WART_BLOCKS, Arrays.asList(
                Aspect.getAspectById("praecantatio", 1),
                Aspect.getAspectById("herba", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.WOODEN_PRESSURE_PLATES, Arrays.asList(
                Aspect.getAspectById("sensus", 1),
                Aspect.getAspectById("arbor", 1),
                Aspect.getAspectById("machina", 1)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.BANNERS, Arrays.asList(
                Aspect.getAspectById("alienis", 1),
                Aspect.getAspectById("arbor", 2),
                Aspect.getAspectById("pannus", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.ANVIL, Arrays.asList(
                Aspect.getAspectById("metallum", 64),
                Aspect.getAspectById("instrumentum", 2),
                Aspect.getAspectById("fabrico", 2)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.BOATS, Arrays.asList(
                Aspect.getAspectById("arbor", 3),
                Aspect.getAspectById("iter", 4),
                Aspect.getAspectById("aqua", 4)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.TERRACOTTA, Arrays.asList(
                Aspect.getAspectById("ignis", 1),
                Aspect.getAspectById("sensus", 1),
                Aspect.getAspectById("terra", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.BOOKSHELF_BOOKS, Arrays.asList(
                Aspect.getAspectById("pannus", 1),
                Aspect.getAspectById("cognitio", 3)
        ));
        ThaumicAspects.ITEM_TAGS_ASPECTS.put(ItemTags.MEAT, Arrays.asList(
                Aspect.getAspectById("victus", 2),
                Aspect.getAspectById("corpus", 4)
        ));
    }
}
