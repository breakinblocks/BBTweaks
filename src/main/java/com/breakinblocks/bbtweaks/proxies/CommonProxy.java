package com.breakinblocks.bbtweaks.proxies;

import com.breakinblocks.bbtweaks.common.registry.ModItems;
import com.breakinblocks.bbtweaks.util.BBTweaksSounds;
import com.breakinblocks.bbtweaks.util.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		BBTweaksSounds.init();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		ModItems.init();

	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
