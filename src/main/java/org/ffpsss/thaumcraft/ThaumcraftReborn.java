package org.ffpsss.thaumcraft;

import net.fabricmc.api.ModInitializer;

import org.ffpsss.thaumcraft.data.AuraNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThaumcraftReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("thaumcraft");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ThaumicAspects.generateCompoundAspects();
		ThaumicAspects.generateAspects();

		ThaumicItems.registerAll();
		ThaumicBlocks.registerAll();
		ThaumicMultiblock.generateAll();
		AuraNodeType.generateTypes();
	}
}