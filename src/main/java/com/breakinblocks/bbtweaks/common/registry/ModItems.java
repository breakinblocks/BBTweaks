package com.breakinblocks.bbtweaks.common.registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.breakinblocks.bbtweaks.BBTweaks;
import com.breakinblocks.bbtweaks.ModInformation;
import com.breakinblocks.bbtweaks.client.TextureHandler;
import com.breakinblocks.bbtweaks.items.ItemAwakenedCore;
import com.breakinblocks.bbtweaks.items.ItemBreakBitElectrum;
import com.breakinblocks.bbtweaks.items.ItemBreakBitEnderium;
import com.breakinblocks.bbtweaks.items.ItemBreakBitInvar;
import com.breakinblocks.bbtweaks.items.ItemBreakBitWorldBreaker;
import com.breakinblocks.bbtweaks.items.ItemInertCore;
import com.breakinblocks.bbtweaks.items.ItemPurifiedWill;
import com.breakinblocks.bbtweaks.items.ItemTarBall;
import com.breakinblocks.bbtweaks.util.TextHelper;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static Logger logger = LogManager.getLogger(ModInformation.NAME);
	public static Item TABLET;

	public static Item inertcore;
	public static Item awakenedcore;
	public static Item purifiedwill;
	public static Item breakbitworldbreaker;
	public static Item breakbitinvar;
	public static Item breakbitelectrum;
	public static Item breakbitenderium;
	public static Item tarBall;

	public static void init() {

		inertcore = new ItemInertCore();
		register(inertcore, "inertcore");

		awakenedcore = new ItemAwakenedCore();
		register(awakenedcore, "awakenedcore");

		purifiedwill = new ItemPurifiedWill();
		register(purifiedwill, "purifiedwill");

		breakbitworldbreaker = new ItemBreakBitWorldBreaker();
		register(breakbitworldbreaker, "breakbitworldbreaker");
		breakbitinvar = new ItemBreakBitInvar();
		register(breakbitinvar, "breakbitinvar");
		breakbitelectrum = new ItemBreakBitElectrum();
		register(breakbitelectrum, "breakbitelectrum");
		breakbitenderium = new ItemBreakBitEnderium();
		register(breakbitenderium, "breakbitenderium");

		tarBall = new ItemTarBall();
		register(tarBall, "tarball");
	}

	public static Item register(Item item, String name) {
		if (item.getRegistryName() == null)
			item.setRegistryName(name);

		logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.register: Item Name:" + name));
		// item.setUnlocalizedName(ModInformation.ID + "." + name);
		item.setUnlocalizedName(name);
		item.setCreativeTab(BBTweaks.tabBaseMod);
		GameRegistry.register(item);
		TextureHandler.itemBuffer.add(item);
		return item;
	}

}
