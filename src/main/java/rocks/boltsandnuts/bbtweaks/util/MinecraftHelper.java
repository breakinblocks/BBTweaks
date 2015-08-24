package rocks.boltsandnuts.bbtweaks.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class MinecraftHelper {

	public MinecraftHelper(){
		return;
	}
	
	public static boolean isClientSide() {
		// TODO Auto-generated method stub
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
	}
	
}
