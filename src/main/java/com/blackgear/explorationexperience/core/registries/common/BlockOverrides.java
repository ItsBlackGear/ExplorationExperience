package com.blackgear.explorationexperience.core.registries.common;

import com.blackgear.explorationexperience.common.block.EEMushroomBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//<>

public class BlockOverrides {
	public static final DeferredRegister<Block> BLOCK 		= DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
	public static final DeferredRegister<Item> BLOCK_ITEM 	= DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	
	public static void registerOverrides(IEventBus modEventBus) {
		BLOCK.register("brown_mushroom", () -> new EEMushroomBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BROWN).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT).setLightLevel((lightValue) -> { return 1; })));
		BLOCK.register("red_mushroom", () -> new EEMushroomBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.RED).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
		BLOCK.register(modEventBus);
		
		BLOCK_ITEM.register("brown_mushroom", () -> new BlockItem(Blocks.BROWN_MUSHROOM, new Item.Properties().group(ItemGroup.DECORATIONS)));
		BLOCK_ITEM.register("red_mushroom", () -> new BlockItem(Blocks.RED_MUSHROOM, new Item.Properties().group(ItemGroup.DECORATIONS)));
		BLOCK_ITEM.register(modEventBus);		
	}
}