package com.blackgear.explorationexperience.common.world.provider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.blackgear.explorationexperience.core.registries.common.EEBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

//<>

public class NetherBiomeReceiver {
	public static ForgeRegistry<Biome> BIOME = ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES);

	//Vanilla
	public static final int NETHER_WASTES 		= BIOME.getID(Biomes.NETHER_WASTES);
	public static final int BASALT_DELTAS 		= BIOME.getID(Biomes.BASALT_DELTAS);
	public static final int CRIMSON_FOREST 		= BIOME.getID(Biomes.CRIMSON_FOREST);
	public static final int SOUL_SAND_VALLEY 	= BIOME.getID(Biomes.SOUL_SAND_VALLEY);
	public static final int WARPED_FOREST 		= BIOME.getID(Biomes.WARPED_FOREST);

	//Exploration Experience
	public static final int FUNGAL_JUNGLE 		= BIOME.getID(EEBiomes.FUNGAL_JUNGLE.get());

	public static ArrayList<Integer> BIOME_ID = new ArrayList<>();
	
	static {
		BIOME_ID.add(NETHER_WASTES);
		BIOME_ID.add(BASALT_DELTAS);
		BIOME_ID.add(CRIMSON_FOREST);
		BIOME_ID.add(SOUL_SAND_VALLEY);
		BIOME_ID.add(WARPED_FOREST);
		
		BIOME_ID.add(FUNGAL_JUNGLE);
	}
	
	public static int getRandomBiomes(INoiseRandom context) {
		return BIOME_ID.get(context.random(BIOME_ID.size()));
	}
	
	public static Set<Biome> getBiomeSet() {
		return new HashSet<>(ForgeRegistries.BIOMES.getValues());
	}
}
