package com.blackgear.explorationexperience.common.world;

import java.util.ArrayList;
import java.util.List;

import com.blackgear.explorationexperience.core.registries.common.EEBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BigBrownMushroomFeature;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BigRedMushroomFeature;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredRandomFeatureList;
import net.minecraft.world.gen.feature.DecoratedFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeature;
import net.minecraft.world.gen.feature.TwoFeatureChoiceConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

//<>

@SuppressWarnings("unchecked")
public class EEBiomeFeatures {
	private static final BlockState BROWN_MUSHROOM = Blocks.BROWN_MUSHROOM.getDefaultState();
	private static final BlockState RED_MUSHROOM = Blocks.RED_MUSHROOM.getDefaultState();
	private static final BlockState RED_MUSHROOM_BLOCK = Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
	private static final BlockState BROWN_MUSHROOM_BLOCK = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(true)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
	private static final BlockState MUSHROOM_STEM = EEBlocks.MUSHROOM_STEM.get().getDefaultState();
	public static final BigMushroomFeatureConfig BIG_RED_MUSHROOM = new BigMushroomFeatureConfig(new SimpleBlockStateProvider(RED_MUSHROOM_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 2);
	public static final BigMushroomFeatureConfig BIG_BROWN_MUSHROOM = new BigMushroomFeatureConfig(new SimpleBlockStateProvider(BROWN_MUSHROOM_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 3);
	public static final BlockClusterFeatureConfig RED_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RED_MUSHROOM), SimpleBlockPlacer.field_236447_c_)).tries(64).func_227317_b_().build();
	public static final BlockClusterFeatureConfig BROWN_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BROWN_MUSHROOM), SimpleBlockPlacer.field_236447_c_)).tries(64).func_227317_b_().build();
	
	public static void addMushrooms(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BROWN_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(4))));
	    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(RED_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(8))));
	}
	
	public static void overrideVanillaMushrooms(Biome biome) {
		for (Decoration stage : GenerationStage.Decoration.values()) {
			List<ConfiguredFeature<?, ?>> list = biome.getFeatures(stage);
			for (int j = 0; j<list.size(); j++) {
				ConfiguredFeature<?, ?> configuredFeature = list.get(j);
				if (configuredFeature.config instanceof DecoratedFeatureConfig) {
					DecoratedFeatureConfig decorated = (DecoratedFeatureConfig)configuredFeature.config;
					
					if (decorated.feature.feature instanceof RandomPatchFeature) {
						BlockClusterFeatureConfig patch = (BlockClusterFeatureConfig)decorated.feature.config;
						
						if (patch.equals(DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG) || patch.equals(DefaultBiomeFeatures.RED_MUSHROOM_CONFIG)) {
							biome.getFeatures(stage).remove(configuredFeature);
						}
					}
				}
			}
		}
	}
	
	public static void overrideHugeMushrooms(Biome biome) {
		List<ConfiguredFeature<?, ?>> list = biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
		List<ConfiguredFeature<?, ?>> toRemove = new ArrayList<>();
		int listSize = list.size();
		
		for (int i = 0; i < listSize; i++) {
			ConfiguredFeature<?, ?> configuredFeature = list.get(i);
			if (configuredFeature.config instanceof DecoratedFeatureConfig) {
				DecoratedFeatureConfig decorated = (DecoratedFeatureConfig) configuredFeature.config;
				if (decorated.feature.config instanceof TwoFeatureChoiceConfig) {
					TwoFeatureChoiceConfig mushroom = (TwoFeatureChoiceConfig) decorated.feature.config;
					if (mushroom.field_227285_a_.config instanceof BigMushroomFeatureConfig && mushroom.field_227286_b_.config instanceof BigMushroomFeatureConfig) {
						toRemove.add(configuredFeature);
						biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.withConfiguration(new TwoFeatureChoiceConfig(Feature.HUGE_RED_MUSHROOM.withConfiguration(BIG_RED_MUSHROOM), Feature.HUGE_BROWN_MUSHROOM.withConfiguration(BIG_BROWN_MUSHROOM))).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
					}
				} else if (decorated.feature.config instanceof MultipleRandomFeatureConfig) {
					MultipleRandomFeatureConfig mushroom = (MultipleRandomFeatureConfig) decorated.feature.config;
					List<ConfiguredRandomFeatureList<?>> tempFeatures = new ArrayList<>();
					for (ConfiguredRandomFeatureList<?> crfl : mushroom.features) {
						if (crfl.feature.feature instanceof BigBrownMushroomFeature) {
							tempFeatures.add(new ConfiguredRandomFeatureList<BigMushroomFeatureConfig>(Feature.HUGE_BROWN_MUSHROOM.withConfiguration(BIG_BROWN_MUSHROOM), crfl.chance));
						} else if (crfl.feature.feature instanceof BigRedMushroomFeature) {
							tempFeatures.add(new ConfiguredRandomFeatureList<BigMushroomFeatureConfig>(Feature.HUGE_RED_MUSHROOM.withConfiguration(BIG_RED_MUSHROOM), crfl.chance));
						} else {
							tempFeatures.add(crfl);
						}
					}
					ConfiguredFeature<DecoratedFeatureConfig, ?> tempFeature = new ConfiguredFeature<DecoratedFeatureConfig, DecoratedFeature>((DecoratedFeature) configuredFeature.feature, new DecoratedFeatureConfig(new ConfiguredFeature<MultipleRandomFeatureConfig, Feature<MultipleRandomFeatureConfig>>((Feature<MultipleRandomFeatureConfig>) decorated.feature.feature, new MultipleRandomFeatureConfig(tempFeatures, mushroom.defaultFeature)), decorated.decorator));
					toRemove.add(configuredFeature);
					biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, tempFeature);
				} else if (decorated.feature.config instanceof BigMushroomFeatureConfig) {
					biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new ConfiguredFeature<DecoratedFeatureConfig, DecoratedFeature>((DecoratedFeature) configuredFeature.feature,new DecoratedFeatureConfig(new ConfiguredFeature<BigMushroomFeatureConfig, Feature<BigMushroomFeatureConfig>>((Feature<BigMushroomFeatureConfig>) decorated.feature.feature, BIG_BROWN_MUSHROOM), decorated.decorator)));
					toRemove.add(configuredFeature);
				}
			}
		}
		for (int i = 0; i < toRemove.size(); i++) {
			list.remove(toRemove.get(i));
        }
	}
}
