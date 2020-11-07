package com.blackgear.explorationexperience.core;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blackgear.explorationexperience.common.world.VanillaBiomeFeatures;
import com.blackgear.explorationexperience.core.registries.common.BlockOverrides;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

//<>

@SuppressWarnings("deprecation")
@Mod(value = ExplorationExperience.MOD_ID)
@Mod.EventBusSubscriber(modid = ExplorationExperience.MOD_ID, bus = Bus.MOD)
public class ExplorationExperience {
	public static final String MOD_ID = "explorationexperience";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID.toUpperCase());
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public ExplorationExperience() {		
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(EventPriority.LOWEST, this::setupCommon);
		
		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
				
		BlockOverrides.registerOverrides(modEventBus);
		
		MinecraftForge.EVENT_BUS.register(this);
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
		});
		
	}

	@OnlyIn(Dist.CLIENT)
	void setupClient(final FMLClientSetupEvent event) {

	}

	void setupCommon(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
		});
	}
	
	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		VanillaBiomeFeatures.overrideVanillaFeatures();
	}
}
