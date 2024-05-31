package org.ffpsss.thaumcraft;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.ffpsss.thaumcraft.api.auranode.AuraNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThaumCraftReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("thaumcraft");
	public static ItemGroup GROUP;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ThaumicAspects.generateAspects();
		AuraNodeType.generateTypes();

		ThaumicBlocks.registerAll();
		ThaumicItems.registerAll();

		ThaumicScreens.registerAll();

		GROUP = Registry.register(
				Registries.ITEM_GROUP,
				new Identifier("thaumcraft", "group"),
				FabricItemGroup.builder()
						.displayName(Text.translatable("itemGroup.thaumcraft.group"))
						.icon(() -> new ItemStack(Items.BIRCH_BOAT)) // placeholder
						.entries(((displayContext, entries) -> {
							entries.add(ThaumicItems.ARCANE_TABLE);
							entries.add(ThaumicItems.AURANODE);
							entries.add(ThaumicItems.SALIS_MUNDUS);
							entries.add(ThaumicItems.BALANCED_SHARD);
							entries.add(ThaumicItems.IGNIS_SHARD);
							entries.add(ThaumicItems.AER_SHARD);
							entries.add(ThaumicItems.AQUA_SHARD);
							entries.add(ThaumicItems.TERRA_SHARD);
							entries.add(ThaumicItems.PERDITIO_SHARD);
							entries.add(ThaumicItems.ORDO_SHARD);
							entries.add(ThaumicItems.QUICKSILVER);
							entries.add(ThaumicItems.QUICKSILVER_DROP);
							entries.add(ThaumicItems.VISHROOM);
							entries.add(ThaumicItems.SHIMMERLEAF);
							entries.add(ThaumicItems.CINDERPEARL);
						}))
						.build()
		);
	}
}