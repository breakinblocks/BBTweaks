package rocks.boltsandnuts.mindmeld.items;

/*
 * Base item class for getting standard things done with quickly.
 * Extend this for pretty much every item you make.
 */

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import rocks.boltsandnuts.mindmeld.MindMeld;
import rocks.boltsandnuts.mindmeld.ModInformation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class ItemBase extends Item {


    protected String setColor="";
    protected String setInfo="";

    // If you aren't setting multiple textures for your item. IE: Non-Metadata items.
    public ItemBase(String unlocName, String textureName) {
        super();

        setUnlocalizedName(ModInformation.ID + "." + unlocName);
        setTextureName(ModInformation.ID + ":" + textureName);
        setCreativeTab(MindMeld.tabBaseMod);
        
    }

    // If you are setting multiple textures for your item. IE: Metadata items.
    public ItemBase(String unlocName) {
        super();

        setUnlocalizedName(ModInformation.ID + "." + unlocName);
        setCreativeTab(MindMeld.tabBaseMod);
    }

    @SideOnly(Side.CLIENT)
    protected String setToolTipData(ItemStack IStack){
        return this.setColor + this.setInfo;
    }
    
}

