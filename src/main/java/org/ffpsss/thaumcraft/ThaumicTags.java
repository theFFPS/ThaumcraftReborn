package org.ffpsss.thaumcraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ThaumicTags {

    public static class Blocks {
        public static final TagKey<Block> SALIS_MUNDUS_USAGE = createTag("salis_mundus_usage");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("thaumcraft", name));
        }
    }
    public static class Items {
        public static final TagKey<Item> WANDS = createTag("wands");
        public static final TagKey<Item> SHARDS = createTag("shards");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("thaumcraft", name));
        }
    }
}
