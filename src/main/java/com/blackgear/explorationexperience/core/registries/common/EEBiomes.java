package com.blackgear.explorationexperience.core.registries.common;

import java.util.function.Supplier;

import com.blackgear.explorationexperience.common.world.biome.EEBiome;
import com.blackgear.explorationexperience.common.world.biome.nether.FungalJungleBiome;
import com.blackgear.explorationexperience.core.ExplorationExperience;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//<>

public class EEBiomes {
	private static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ExplorationExperience.MOD_ID);
	
	public static final RegistryObject<EEBiome> FUNGAL_JUNGLE = registerBiome("fungal_jungle", FungalJungleBiome::new);
	
	public static void applyFeatures() {
		BIOMES.getEntries().forEach((biome) -> {
			Biome getbiome = biome.get();
			if (getbiome instanceof EEBiome) {
				if (getbiome != null) {
					((EEBiome)getbiome).addFeatures();
				}
			}
		});
	}
	
	public static void register(IEventBus modEventBus) {
		BIOMES.register(modEventBus);
	}
	
	private static <B extends Biome> RegistryObject<B> registerBiome(String name, Supplier<B> supplier) {
		return BIOMES.register(name, supplier);
	}
}