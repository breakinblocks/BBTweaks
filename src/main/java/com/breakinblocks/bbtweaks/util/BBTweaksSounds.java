package com.breakinblocks.bbtweaks.util;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BBTweaksSounds {
	public static void init() {
		String[] sounds = {
				"bbtweaks:iwrench"
		};

		for (String s : sounds) {
			ResourceLocation location = new ResourceLocation(s);
			GameRegistry.register(new SoundEvent(location), location);
		}
	}

	private BBTweaksSounds() {
	}
}