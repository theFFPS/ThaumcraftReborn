package org.ffpsss.thaumcraft;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThaumCraftReborn implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("thaumcraft");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}