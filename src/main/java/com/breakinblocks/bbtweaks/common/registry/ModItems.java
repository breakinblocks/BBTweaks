package com.breakinblocks.bbtweaks.common.registry;

import com.breakinblocks.bbtweaks.BBTweaks;
import com.breakinblocks.bbtweaks.client.TextureHandler;
import com.breakinblocks.bbtweaks.items.ItemAwakenedCore;
import com.breakinblocks.bbtweaks.items.ItemBreakBitElectrum;
import com.breakinblocks.bbtweaks.items.ItemBreakBitEnderium;
import com.breakinblocks.bbtweaks.items.ItemBreakBitInvar;
import com.breakinblocks.bbtweaks.items.ItemBreakBitWorldBreaker;
import com.breakinblocks.bbtweaks.items.ItemInertCore;
import com.breakinblocks.bbtweaks.items.ItemPurifiedWill;
import com.breakinblocks.bbtweaks.items.ItemTarBall;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static Item TABLET;

    public static Item inertCore;
    public static Item awakenedCore;
    public static Item purifiedWill;
    public static Item breakbit_worldbreaker;
    public static Item breakbit_invar;
    public static Item breakbit_electrum;
    public static Item breakbit_enderium;
	public static Item tarBall;



	public static void init() {
		

        inertCore = new ItemInertCore();
        register(inertCore, "ItemInertcore");

        awakenedCore = new ItemAwakenedCore();
        register(awakenedCore, "ItemAwakenedCore");
        
        purifiedWill = new ItemPurifiedWill();
        register(purifiedWill, "ItemPurifiedWill");
       
        breakbit_worldbreaker = new ItemBreakBitWorldBreaker();
        register(breakbit_worldbreaker, "ItemBreakBitWorldBreaker");
        breakbit_invar = new ItemBreakBitInvar();
        register(breakbit_invar, "ItemBreakBitInvar");
        breakbit_electrum = new ItemBreakBitElectrum();
        register(breakbit_electrum, "ItemBreakBitElectrum");
        breakbit_enderium = new ItemBreakBitEnderium();
        register(breakbit_enderium, "ItemBreakBitEnderium");
		
		tarBall= new ItemTarBall();
		register(tarBall, "ItemTarBall");
	}
	
	public static Item register(Item item, String name) {
		if (item.getRegistryName() == null)
			item.setRegistryName(name);
		item.setUnlocalizedName(name);
		item.setCreativeTab(BBTweaks.tabBaseMod);
		GameRegistry.register(item);
		TextureHandler.itemBuffer.add(item);
		return item;
	}

}
