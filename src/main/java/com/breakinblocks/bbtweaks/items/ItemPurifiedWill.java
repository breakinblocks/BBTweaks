package com.breakinblocks.bbtweaks.items;

import java.awt.Color;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
