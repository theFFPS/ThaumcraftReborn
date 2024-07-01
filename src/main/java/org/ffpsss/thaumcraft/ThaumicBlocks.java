package org.ffpsss.thaumcraft;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ffpsss.thaumcraft.block.*;

public class ThaumicBlocks {
    public static Block ARCANE_TABLE =
            createBlock("arcane_workbench", new ThaumicCraftingTable(AbstractBlock.Settings.create().strength(1f)));
    public static Block AURANODE =
            createBlock("auranode",
                    new AuraNode(AbstractBlock.Settings.create().strength(2f).dropsNothing().noCollision().nonOpaque().noBlockBreakParticles()));
    public static BlockEntityType<? extends AuraNodeEntity> AURANODE_ENTITY =
            generateBlockEntity("auranode", BlockEntityType.Builder.create(AuraNodeEntity::new, AURANODE).build());
    public static BlockEntityType<? extends ArcaneWorkbenchEntity> ARCANEWORKBENCH_ENTITY =
            generateBlockEntity("arcane_workbench", BlockEntityType.Builder.create(ArcaneWorkbenchEntity::new, ARCANE_TABLE).build());

    public static Block VISHROOM =
            createBlock("vishroom",
                    new FlowerBlock(StatusEffects.NAUSEA, 10f, AbstractBlock.Settings.copy(Blocks.ALLIUM).noCollision().nonOpaque()));
    public static Block SHIMMERLEAF =
            createBlock("shimmerleaf", new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, AbstractBlock.Settings.copy(Blocks.ALLIUM).noCollision().nonOpaque()));
    public static Block CINDERPEARL =
            createBlock("cinderpearl", new TorchBlock(ParticleTypes.SMOKE, AbstractBlock.Settings.copy(Blocks.ALLIUM).noCollision().nonOpaque()));

    public static Block createBlock(String ID, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of("thaumcraft", ID), block);
    }
    public static Item generateItem(String ID, BlockItem blockItem) {
        return Registry.register(Registries.ITEM, Identifier.of("thaumcraft", ID), blockItem);
    }
    public static <T extends BlockEntity> BlockEntityType<? extends T> generateBlockEntity(String ID, BlockEntityType<? extends T> entity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of("thaumcraft", ID), entity);
    }

    public static void registerAll() {
        ThaumicItems.ARCANE_TABLE = generateItem("arcane_workbench", new BlockItem(ARCANE_TABLE, new Item.Settings().maxCount(64)));
        ThaumicItems.AURANODE = generateItem("auranode", new BlockItem(AURANODE, new Item.Settings().maxCount(64)));
        ThaumicItems.SHIMMERLEAF = generateItem("shimmerleaf", new BlockItem(SHIMMERLEAF, new Item.Settings().maxCount(64)));
        ThaumicItems.VISHROOM = generateItem("vishroom", new BlockItem(VISHROOM, new Item.Settings().maxCount(64)));
        ThaumicItems.CINDERPEARL = generateItem("cinderpearl", new BlockItem(CINDERPEARL, new Item.Settings().maxCount(64)));
    }
}
