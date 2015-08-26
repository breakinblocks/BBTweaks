package rocks.boltsandnuts.bbtweaks.items;

import java.awt.Color;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;


public class ItemPurifiedWill extends ItemBase {

	
	public ItemPurifiedWill() {
		super("purifiedwill", "purifiedWill");
	    setMaxStackSize(16);;
		}
		
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
			return Color.HSBtoRGB((System.currentTimeMillis() /8) % 360 / 360F, 0.4F, 1F);
			
		}
		
	
}
