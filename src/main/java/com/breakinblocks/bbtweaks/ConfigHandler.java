package com.breakinblocks.bbtweaks;

/*
 * Creation and usage of the config file.
 */

import com.breakinblocks.bbtweaks.util.EventHandler;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

import com.breakinblocks.bbtweaks.command.CommandBB;

public class ConfigHandler {

	public static Configuration config;

	// Sections to add to the config
	public static String exampleSection = "Main Section";
	// public static String generation = "Generation";

	// Options in the config
	public static boolean exampleOption;
	// public static boolean enableGeneration;

	public static void init(File file) {
		config = new Configuration(file);
		syncConfig();
	}

	public static void syncConfig() {
		config.addCustomCategoryComment(exampleSection, "Reserved for Future use");
		// config.addCustomCategoryComment(generation, "This section contains
		// all settings regarding ore generation.");
		CommandBB.cooldown = config.get(exampleSection, "BreakbitCooldown", 1000 * 60 * 60 * 24).getInt();
		CommandBB.maxBreakbits = config
				.get(exampleSection, "BreakbitMaximum", 32, "[1,64] Threshold for breakbit reset.").getInt();
		exampleOption = config.get(exampleSection, "notReallyAnOption", true,
				"if you choose not to decide, you still have made a choice.").getBoolean(exampleOption);
		EventHandler.fluidTemperatureTooltipEnabled = config.get(exampleSection, "FluidTemperatureTooltip", true,
				"Show fluid temperature on tooltips.").getBoolean();
		config.save();
	}
}
