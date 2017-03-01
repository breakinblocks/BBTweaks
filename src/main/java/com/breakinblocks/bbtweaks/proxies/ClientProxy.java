package com.breakinblocks.bbtweaks.proxies;

import com.breakinblocks.bbtweaks.BBTweaks;
import com.breakinblocks.bbtweaks.client.TextureHandler;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		OBJLoader.INSTANCE.addDomain(BBTweaks.MODID);

	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		TextureHandler.itemBuffer.forEach(e -> TextureHandler.handle(e, e.getRegistryName().getResourcePath()));
		TextureHandler.blockBuffer.forEach(e -> TextureHandler.handle(e, e.getRegistryName().getResourcePath()));
	}
}
