package com.blackgear.explorationexperience.common.blocks;

import java.util.Random;

import com.blackgear.explorationexperience.common.biome.EEBiomeFeatures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.server.ServerWorld;

//<>

public class EEMushroomBlock extends BushBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0d, 0.0d, 5.0d, 11.0d, 6.0d, 11.0d);

	public EEMushroomBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (random.nextInt(25) == 0) {
			int i = 5;

			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4))) {
				if (worldIn.getBlockState(blockpos).isIn(this)) {
					--i;
					if (i <= 0) {
						return;
					}
				}
			}

			BlockPos blockpos1 = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2),
					random.nextInt(3) - 1);

			for (int k = 0; k < 4; ++k) {
				if (worldIn.isAirBlock(blockpos1) && state.isValidPosition(worldIn, blockpos1)) {
					pos = blockpos1;
				}

				blockpos1 = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2),
						random.nextInt(3) - 1);
			}

			if (worldIn.isAirBlock(blockpos1) && state.isValidPosition(worldIn, blockpos1)) {
				worldIn.setBlockState(blockpos1, state, 2);
			}
		}
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.isOpaqueCube(worldIn, pos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (!blockstate.isIn(Blocks.MYCELIUM) && !blockstate.isIn(Blocks.PODZOL)) {
			return worldIn.getLightSubtracted(pos, 0) < 13
					&& blockstate.canSustainPlant(worldIn, blockpos, Direction.UP, this);
		} else {
			return true;
		}
	}

	public boolean grow(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
		world.removeBlock(pos, false);
		ConfiguredFeature<BigMushroomFeatureConfig, ?> configuredfeature;
		if (this == Blocks.BROWN_MUSHROOM) {
			configuredfeature = Feature.HUGE_BROWN_MUSHROOM.withConfiguration(EEBiomeFeatures.BIG_BROWN_MUSHROOM);
		} else {
			if (this != Blocks.RED_MUSHROOM) {
				world.setBlockState(pos, state, 3);
				return false;
			}

			configuredfeature = Feature.HUGE_RED_MUSHROOM.withConfiguration(EEBiomeFeatures.BIG_RED_MUSHROOM);
		}

		if (configuredfeature.func_236265_a_(world, world.func_241112_a_(),
				world.getChunkProvider().getChunkGenerator(), rand, pos)) {
			return true;
		} else {
			world.setBlockState(pos, state, 3);
			return false;
		}
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return (double) rand.nextFloat() < 0.4d;
	}

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		this.grow(worldIn, pos, state, rand);
	}

}