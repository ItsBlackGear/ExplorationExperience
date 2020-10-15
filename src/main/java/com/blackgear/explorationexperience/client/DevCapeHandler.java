package com.blackgear.explorationexperience.client;

import java.util.Map;
import java.util.Set;

import com.blackgear.explorationexperience.core.ExplorationExperience;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//<>

/*
* @author bageldotjpg
* original source: abnormals-core
*/
@Mod.EventBusSubscriber(modid = ExplorationExperience.MOD_ID)
public class DevCapeHandler {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ExplorationExperience.MOD_ID, "textures/dev_cape.png");
	private static final ImmutableSet<String> UUID = ImmutableSet.of("58c28704-b377-4080-b3cf-e53bc53eda0a");

	private static final Set<String> RENDER = Sets.newHashSet();
	
	@SubscribeEvent
	public static void playerRender(RenderPlayerEvent.Post event) {
		PlayerEntity player = event.getPlayer();
		String uuid = PlayerEntity.getUUID(player.getGameProfile()).toString();
		if (player instanceof AbstractClientPlayerEntity && UUID.contains(uuid) && !RENDER.contains(uuid)) {
			AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity)player;
			if (clientPlayer.hasPlayerInfo()) {
				Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = clientPlayer.playerInfo.playerTextures;
				playerTextures.put(MinecraftProfileTexture.Type.CAPE, TEXTURE);
				playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, TEXTURE);
				RENDER.add(uuid);
			}
		}
	}
}