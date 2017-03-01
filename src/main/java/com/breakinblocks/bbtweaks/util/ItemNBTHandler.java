package com.breakinblocks.bbtweaks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class ItemNBTHandler {
	public static void InsertNBT(ItemStack stack, NBTTagCompound NBTData) {
		stack.setTagCompound(NBTData);
	}

	public static void setString(ItemStack stack, String tagPlayer, String playerName) {
		// TODO Auto-generated method stub
		GetNBTData(stack).setString(tagPlayer, playerName);

	}

	public static String getString(ItemStack stack, String tag, String StrDefault) {
		return CheckExist(stack, tag) ? GetNBTData(stack).getString(tag) : StrDefault;
	}

	private static boolean CheckExist(ItemStack stack, String tag) {
		// TODO Auto-generated method stub
		return GetNBTData(stack).hasKey(tag);
	}

	public static NBTTagCompound GetNBTData(ItemStack stack) {

		initNBT(stack);
		return stack.getTagCompound();

	}

	private static void initNBT(ItemStack stack) {
		// TODO Auto-generated method stub
		if (!detectNBT(stack))
			writeNBT(stack, new NBTTagCompound());
	}

	private static void writeNBT(ItemStack stack, NBTTagCompound nbtTagCompound) {
		// TODO Auto-generated method stub
		stack.setTagCompound(nbtTagCompound);
	}

	private static boolean detectNBT(ItemStack stack) {
		// TODO Auto-generated method stub
		return stack.hasTagCompound();
	}

}
