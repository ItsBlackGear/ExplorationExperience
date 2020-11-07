package com.blackgear.explorationexperience.common.world.biome.nether;

import com.blackgear.explorationexperience.common.world.biome.EEBiome;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

//<>

public class FungalJungleBiome extends EEBiome {

	public FungalJungleBiome() {
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.field_237189_ad_, SurfaceBuilder.field_237185_P_).precipitation(Biome.RainType.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).func_235097_a_((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(3343107).setParticle(new ParticleEffectAmbience(ParticleTypes.CRIMSON_SPORE, 0.025F)).build()).parent((String)null), () -> new ConfiguredSurfaceBuilder<>(SurfaceBuilder.field_237189_ad_, SurfaceBuilder.field_237185_P_));
	}

}
