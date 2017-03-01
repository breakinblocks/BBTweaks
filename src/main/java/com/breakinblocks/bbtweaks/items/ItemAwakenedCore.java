/**
 * 
 * This class is sorta ripped from Vazkii. Just giving her credit :3
 * 
 * I took her math and a function
 * 
 * I also stole most of wayoftime's render code since transparent. >:3 evil
 *  ~cube1234567890
 */
package com.breakinblocks.bbtweaks.items;

import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;

public class ItemAwakenedCore extends ItemBase {

	public ItemAwakenedCore() {
		super("awakenedcore", "inertcore");
		setMaxStackSize(16);
	}

	@net.minecraftforge.fml.relauncher.SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return Color.HSBtoRGB((System.currentTimeMillis() / 10) % 360 / 360F, 0.25F, 1F);

	}

}