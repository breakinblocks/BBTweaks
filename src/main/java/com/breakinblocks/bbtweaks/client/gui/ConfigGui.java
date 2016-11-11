package com.breakinblocks.bbtweaks.client.gui;

/*
 * Creates a config GUI for your mod. This requires an mcmod.info file with the correct modid. These are dummy sections that don't do anything.
 */

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;


import java.util.ArrayList;
import java.util.List;

import com.breakinblocks.bbtweaks.ModInformation;
import com.breakinblocks.bbtweaks.util.TextHelper;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ModInformation.ID, false, false, TextHelper.localize("gui." + ModInformation.ID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        // adds sections declared in ConfigHandler. toLowerCase() is used because the configuration class automatically does this, so must we.
        //list.add(new ConfigElement<ConfigCategory>(config.getCategory(generation.toLowerCase())));

        return list;
    }
}