package org.ffpsss.thaumcraft;

import org.ffpsss.sml.api.common.SMLRegistry;
import org.ffpsss.sml.api.util.BlockUtil;
import org.ffpsss.thaumcraft.block.AuraNode;
import org.ffpsss.thaumcraft.block.AuraNodeEntity;
import org.ffpsss.thaumcraft.block.ThaumicCraftingTable;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

public class ThaumicBlocks {
    public static void registerAll() {
        BlockUtil.register("thaumcraft", "arcane_workbench", new ThaumicCraftingTable(FabricBlockSettings.of(Material.WOOD)));
        BlockUtil.register("thaumcraft", "aura_node", new AuraNode(FabricBlockSettings.of(Material.AIR).dropsNothing().strength(1000000f)));

        BlockUtil.assignBlockItem("thaumcraft", "arcane_workbench", new Item.Settings().maxCount(64).group(SMLRegistry.getItemGroup("thaumcraft:group")));
        BlockUtil.assignBlockItem("thaumcraft", "aura_node", new Item.Settings().maxCount(64).group(SMLRegistry.getItemGroup("thaumcraft:group")));

        BlockUtil.assignBlockEntity("thaumcraft", "aura_node", AuraNodeEntity::new);
    }
}
