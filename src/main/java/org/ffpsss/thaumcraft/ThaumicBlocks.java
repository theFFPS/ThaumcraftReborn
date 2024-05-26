package org.ffpsss.thaumcraft;

import org.ffpsss.sml.api.common.SMLRegistry;
import org.ffpsss.sml.api.util.BlockUtil;
import org.ffpsss.thaumcraft.block.ThaumicCraftingTable;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

public class ThaumicBlocks {
    public static void registerAll() {
        BlockUtil.register("thaumcraft", "arcane_workbench", new ThaumicCraftingTable(FabricBlockSettings.of(Material.WOOD)));
        BlockUtil.assignBlockItem("thaumcraft", "arcane_workbench", new Item.Settings().maxCount(64).group(SMLRegistry.getItemGroup("thaumcraft:group")));
    }
}
