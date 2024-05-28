package org.ffpsss.thaumcraft;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.ffpsss.thaumcraft.block.AuraNode;
import org.ffpsss.thaumcraft.block.AuraNodeEntity;
import org.ffpsss.thaumcraft.block.ThaumicCraftingTable;

import java.util.List;

public class ThaumicBlocks {
    public static Block ARCANE_TABLE;
    public static Block AURANODE;
    public static BlockEntityType<? extends AuraNodeEntity> AURANODE_ENTITY;

    public static void registerAll() {
        ARCANE_TABLE = Registry.register(
                Registries.BLOCK,
                new Identifier("thaumcraft", "arcane_workbench"),
                new ThaumicCraftingTable(AbstractBlock.Settings.create().strength(1f))
        );
        AURANODE = Registry.register(
                Registries.BLOCK,
                new Identifier("thaumcraft", "auranode"),
                new AuraNode(AbstractBlock.Settings.create().strength(2f).dropsNothing().noCollision().nonOpaque())
        );
        AURANODE_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("thaumcraft", "auranode"),
                BlockEntityType.Builder.create(AuraNodeEntity::new, AURANODE).build()
        );
        ThaumicItems.ARCANE_TABLE = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "arcane_workbench"),
                new BlockItem(ARCANE_TABLE, new Item.Settings().maxCount(64))
        );
        ThaumicItems.AURANODE = Registry.register(
                Registries.ITEM,
                new Identifier("thaumcraft", "auranode"),
                new BlockItem(AURANODE, new Item.Settings().maxCount(64))
        );
    }
}
