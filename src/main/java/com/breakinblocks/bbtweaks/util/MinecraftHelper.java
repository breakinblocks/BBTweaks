package com.breakinblocks.bbtweaks.util;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class MinecraftHelper {

	public MinecraftHelper() {
		return;
	}

	public static boolean isClientSide() {
		// TODO Auto-generated method stub
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
	}

}
