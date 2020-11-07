package com.blackgear.explorationexperience.common.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

//<>

public enum NetherGenLayerBiome implements IAreaTransformer0, IDimOffset0Transformer {
	INSTANCE;

	@Override
	public int apply(INoiseRandom context, int x, int z) {
		return 0;
	}

}
