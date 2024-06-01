package org.ffpsss.thaumcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ffpsss.thaumcraft.ThaumicItems;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, Collections.singletonList(ThaumicItems.BALANCED_SHARD), RecipeCategory.MISC,
                ThaumicItems.SALIS_MUNDUS, 0.7f, 200, "salis_mundus");
        offerShapelessRecipe(exporter, Items.BLAZE_POWDER, ThaumicItems.CINDERPEARL, "cinderpearl", 1);
        offerShapelessRecipe(exporter, ThaumicItems.QUICKSILVER, ThaumicItems.SHIMMERLEAF, "shimmerleaf", 1);
        offerShapelessRecipe(exporter, ThaumicItems.QUICKSILVER_DROP, ThaumicItems.QUICKSILVER, "quicksilver_drop", 1);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ThaumicItems.QUICKSILVER, 1)
                .pattern("DDD").pattern("DDD").pattern("DDD")
                .input('D', ThaumicItems.QUICKSILVER_DROP)
                .offerTo(exporter, new Identifier(getRecipeName(ThaumicItems.QUICKSILVER)));
    }
}
