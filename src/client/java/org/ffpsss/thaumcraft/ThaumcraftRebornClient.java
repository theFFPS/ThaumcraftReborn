package org.ffpsss.thaumcraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ThaumcraftRebornClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ThaumicBlocks.AURANODE, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(ThaumicBlocks.SHIMMERLEAF, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ThaumicBlocks.CINDERPEARL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ThaumicBlocks.VISHROOM, RenderLayer.getCutout());
	}
}