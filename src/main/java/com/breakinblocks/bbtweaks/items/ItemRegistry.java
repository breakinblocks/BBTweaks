package com.breakinblocks.bbtweaks.items;

/*
 * Class to register your blocks in.
 * The order that you list them here is the order they are registered.
 * Keep that in mind if you like nicely organized creative tabs.
 */

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemRegistry {

    //items
    public static Item meldingManuscript;
    public static Item inertCore;
    public static Item awakenedCore;
    public static Item purifiedWill;
    public static Item breakbit_worldbreaker;
    public static Item breakbit_invar;
    public static Item breakbit_electrum;
    public static Item breakbit_enderium;
	public static Item tarBall;
    public static Item fragileActivator;
    
    public static void registerItems() {


        inertCore = new ItemInertCore();
        GameRegistry.registerItem(inertCore, "ItemInertCore");

        awakenedCore = new ItemAwakenedCore();
        GameRegistry.registerItem(awakenedCore, "ItemAwakenedCore");
        
        purifiedWill = new ItemPurifiedWill();
        GameRegistry.registerItem(purifiedWill, "ItemPurifiedWill");
       
        breakbit_worldbreaker = new ItemBreakBitWorldBreaker();
        GameRegistry.registerItem(breakbit_worldbreaker, "ItemBreakBitWorldBreaker");
        breakbit_invar = new ItemBreakBitInvar();
        GameRegistry.registerItem(breakbit_invar, "ItemBreakBitInvar");
        breakbit_electrum = new ItemBreakBitElectrum();
        GameRegistry.registerItem(breakbit_electrum, "ItemBreakBitElectrum");
        breakbit_enderium = new ItemBreakBitEnderium();
        GameRegistry.registerItem(breakbit_enderium, "ItemBreakBitEnderium");
		
		tarBall= new ItemTarBall();
		GameRegistry.registerItem(tarBall, "ItemTarBall");
    }
    
    
}
