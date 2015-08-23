package rocks.boltsandnuts.mindmeld.blocks;

/*
 * General place to do all your block related recipe things'n'stuff.
 */

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

@SuppressWarnings("unused")
public class BlockRecipeRegistry {

    //Self explanatory. Continue these how you wish. EG: registerPulverizerRecipes
    private static void registerShapedRecipes() {
/*
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.gold_ore), "XXX", "X X", "XXX", 'X', "ingotGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.quisqueLapisBlock), "XXX", "XXX", "XXX", 'X', "dustQuisqueLapis"));
*/
    }

    private static void registerShapelessRecipes() {

    }

    public static void registerBlockRecipes() {
        registerShapedRecipes();
        registerShapelessRecipes();
    }
}
