package com.blackgear.explorationexperience.common.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

//<>

public class VanillaBiomeFeatures {
	public static void overrideVanillaFeatures() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome.equals(Biomes.DARK_FOREST) || biome.equals(Biomes.DARK_FOREST_HILLS) || biome.equals(Biomes.MUSHROOM_FIELD_SHORE) || biome.equals(Biomes.MUSHROOM_FIELDS)) {
				EEBiomeFeatures.overrideHugeMushrooms(biome);
			}
			if (biome.equals(Biomes.NETHER_WASTES) || biome.equals(Biomes.BASALT_DELTAS)) {
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(EEBiomeFeatures.BROWN_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(0.5F, 0, 0, 128))));
			    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(EEBiomeFeatures.RED_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(0.5F, 0, 0, 128))));
			}
			if (!biome.getCategory().equals(Biome.Category.THEEND)) {
				EEBiomeFeatures.overrideVanillaMushrooms(biome);
				EEBiomeFeatures.addMushrooms(biome);
			}
		}
	}
}
