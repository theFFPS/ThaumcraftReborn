package org.ffpsss.thaumcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import org.ffpsss.thaumcraft.ThaumicBlocks;

import java.util.concurrent.CompletableFuture;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ThaumicBlocks.ARCANE_TABLE);
        addDrop(ThaumicBlocks.CINDERPEARL);
        addDrop(ThaumicBlocks.SHIMMERLEAF);
    }
}
