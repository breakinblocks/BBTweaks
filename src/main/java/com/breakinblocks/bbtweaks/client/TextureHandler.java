package com.breakinblocks.bbtweaks.client;

import java.util.ArrayList;

import com.breakinblocks.bbtweaks.BBTweaks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TextureHandler {

	public static ArrayList<Item> itemBuffer = new ArrayList<>();
	public static ArrayList<Block> blockBuffer = new ArrayList<>();

	public static void registerFluidRenderers() {
		// For Later
	}

	public static void registerItem(Item item, int meta, String name) {
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(item, meta, new ModelResourceLocation(BBTweaks.MODID + ":" + name, "inventory"));
	}

	public static void registerBlock(Block block, int meta, String name) {
		registerItem(Item.getItemFromBlock(block), meta, name);
	}

	public static void handle(Item item, String name) {
		registerItem(item, 0, name);
		// TODO: Handle variants
	}

	public static void handle(Block block, String name) {
		registerBlock(block, 0, name);
	}

}
