package com.blackgear.explorationexperience.common.block;

import java.util.Random;

import com.blackgear.explorationexperience.core.registries.common.EEBlocks;

import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

//<>

public class TwilightVinesBlock extends AbstractBodyPlantBlock {
	public static final IntegerProperty BERRY_AGE = IntegerProperty.create("berry_age", 0, 3);
	public static final VoxelShape SHAPE = Block.makeCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 16.0d, 16.0d);
	
	public TwilightVinesBlock(Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false);
		this.setDefaultState(this.stateContainer.getBaseState().with(BERRY_AGE, Integer.valueOf(0)));
	}
	
	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		return state.get(BERRY_AGE) >= 3 ? 10 : super.getLightValue(state, world, pos);
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (state.get(BERRY_AGE) < 3 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
			worldIn.setBlockState(pos, state.func_235896_a_(BERRY_AGE));
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(BERRY_AGE);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(BERRY_AGE) < 3;
	}
	
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(3, state.get(BERRY_AGE) + 1);
		worldIn.setBlockState(pos, state.with(BERRY_AGE, Integer.valueOf(i)), 2);
	}
	
	@Override
	protected AbstractTopPlantBlock getTopPlantBlock() {
		return (AbstractTopPlantBlock)EEBlocks.TWILIGHT_VINES.get();
	}
}