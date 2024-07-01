package org.ffpsss.thaumcraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ThaumicTags {

    public static class Blocks {
        public static final TagKey<Block> WAND_RECHARGE = createTag("wand_recharge");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of("thaumcraft", name));
        }
    }
    public static class Items {
        public static final TagKey<Item> WANDS = createTag("wands");
        public static final TagKey<Item> SHARDS = createTag("shards");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of("thaumcraft", name));
        }
    }
}
