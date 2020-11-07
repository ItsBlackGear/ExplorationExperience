package com.blackgear.explorationexperience.common.world.biome;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;

//<>

public abstract class EEBiome extends Biome {
	protected final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder;
	
	public EEBiome(Builder biomeBuilder, Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder) {
		super(biomeBuilder);
		this.surfaceBuilder = surfaceBuilder;
	}
	
	@Override
	public void buildSurface(Random random, IChunk chunkIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed) {
		this.surfaceBuilder.get().setSeed(seed);
		this.surfaceBuilder.get().buildSurface(random, chunkIn, this, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed);
	}
	
	@Override
	public ConfiguredSurfaceBuilder<?> getSurfaceBuilder() {
		return this.surfaceBuilder.get();
	}
	
	@Override
	public ISurfaceBuilderConfig getSurfaceBuilderConfig() {
		return this.surfaceBuilder.get().getConfig();
	}
	
	public void addFeatures() {
	}
}
