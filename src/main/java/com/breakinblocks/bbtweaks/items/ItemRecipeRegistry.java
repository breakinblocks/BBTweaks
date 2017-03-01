package com.breakinblocks.bbtweaks.items;

import com.breakinblocks.bbtweaks.blocks.BlockRegistry;
import com.breakinblocks.bbtweaks.common.registry.ModItems;

/*
 * General place to do all your item related recipe things'n'stuff.
 */

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;


    

@SuppressWarnings("unused")
public class ItemRecipeRegistry {

    // Self explanatory. Continue these how you wish. EG: registerSmeltingRecipes
    private static void registerShapedRecipes() {
    	ItemStack invar = new ItemStack(ModItems.breakbit_invar,1);
    	ItemStack electrum = new ItemStack(ModItems.breakbit_electrum,1);
    	ItemStack enderium = new ItemStack(ModItems.breakbit_enderium,1);
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(electrum, new Object[]{"XXX", "XXX", "XXX", 'X', invar}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(enderium, new Object[]{"XXX", "XXX", "XXX", 'X', electrum}));

    
    }

    private static void registerShapelessRecipes() {
    	ItemStack invar = new ItemStack(ModItems.breakbit_invar,1);
    	ItemStack electrum = new ItemStack(ModItems.breakbit_electrum,1);
    	ItemStack enderium = new ItemStack(ModItems.breakbit_enderium,1);
    	ItemStack breakers = new ItemStack(ModItems.breakbit_worldbreaker, 32);
    	ItemStack invars = new ItemStack(ModItems.breakbit_invar, 9);
    	ItemStack electrums = new ItemStack(ModItems.breakbit_electrum, 9);
    	
    	GameRegistry.addShapelessRecipe(breakers, invar );
        GameRegistry.addShapelessRecipe(invars, electrum);
        GameRegistry.addShapelessRecipe(electrums, enderium);

    
        

    }
    public static void registerItemRecipes() {
        registerShapedRecipes();
        registerShapelessRecipes();
    }
    
    

    private static void registerArcaneShapeless(){
        
    }
    
}


