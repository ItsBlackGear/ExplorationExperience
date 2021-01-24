package com.blackgear.explorationexperience.client.renderer;

import com.blackgear.explorationexperience.core.registries.common.EEBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

//<>

public class BlockRenderType {
	public static void setRenderLayers() {
		RenderTypeLookup.setRenderLayer(EEBlocks.TWILIGHT_VINES.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(EEBlocks.TWILIGHT_VINES_PLANT.get(), RenderType.getCutout());
	}
}