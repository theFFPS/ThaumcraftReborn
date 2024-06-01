package org.ffpsss.thaumcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.ffpsss.thaumcraft.ThaumicItems;
import org.ffpsss.thaumcraft.ThaumicTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ThaumicTags.Items.WANDS);
        getOrCreateTagBuilder(ThaumicTags.Items.SHARDS)
                .add(ThaumicItems.AER_SHARD)
                .add(ThaumicItems.AQUA_SHARD)
                .add(ThaumicItems.TERRA_SHARD)
                .add(ThaumicItems.ORDO_SHARD)
                .add(ThaumicItems.PERDITIO_SHARD)
                .add(ThaumicItems.IGNIS_SHARD);
    }
}
