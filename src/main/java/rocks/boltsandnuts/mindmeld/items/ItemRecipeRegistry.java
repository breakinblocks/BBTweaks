package rocks.boltsandnuts.mindmeld.items;

/*
 * General place to do all your item related recipe things'n'stuff.
 */

import cpw.mods.fml.common.registry.GameRegistry;
import rocks.boltsandnuts.mindmeld.blocks.BlockRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;


    

@SuppressWarnings("unused")
public class ItemRecipeRegistry {

    // Self explanatory. Continue these how you wish. EG: registerSmeltingRecipes
    private static void registerShapedRecipes() {
/*
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.blaze_rod), new Object[]{"X  ", " X ", "  X", 'X', "powderBlaze"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.blaze_rod), new Object[]{"  X", " X ", "X  ", 'X', "powderBlaze"}));
*/
    }

    private static void registerShapelessRecipes() {
/*
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.quisqueLapisBlock), new ItemStack(ItemRegistry.quisqueLapis, 9));
*/
    }

    public static void registerItemRecipes() {
        registerShapedRecipes();
        registerShapelessRecipes();
    }
    
    

    private static void registerArcaneShapeless(){
        
    }
    
}


