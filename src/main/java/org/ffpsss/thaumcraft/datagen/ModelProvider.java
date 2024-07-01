package org.ffpsss.thaumcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import org.ffpsss.thaumcraft.ThaumicBlocks;
import org.ffpsss.thaumcraft.ThaumicItems;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ThaumicItems.AER_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.AQUA_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.IGNIS_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.BALANCED_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.TERRA_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.ORDO_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.PERDITIO_SHARD, Models.GENERATED);
        itemModelGenerator.register(ThaumicItems.SALIS_MUNDUS, Models.GENERATED);
    }
}
