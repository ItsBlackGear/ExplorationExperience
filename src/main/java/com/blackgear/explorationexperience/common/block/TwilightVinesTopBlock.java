package com.blackgear.explorationexperience.common.block;

import java.util.Random;

import com.blackgear.explorationexperience.core.registries.common.EEBlocks;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;

//<>

public class TwilightVinesTopBlock extends AbstractTopPlantBlock {
	public static final IntegerProperty BERRY_AGE = BlockStateProperties.AGE_0_3;
	public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0d, 0.0d, 1.0d, 15.0d, 16.0d, 15.0d);

	public TwilightVinesTopBlock(Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false, 0.1d);
	}

	@Override
	protected int getGrowthAmount(Random rand) {
		return PlantBlockHelper.getGrowthAmount(rand);
	}

	@Override
	protected Block func_230330_d_() {
		return EEBlocks.TWILIGHT_VINES_PLANT.get();
	}
	
	@Override
	protected boolean canGrowIn(BlockState state) {
		return PlantBlockHelper.isAir(state);
	}
}