package com.breakinblocks.bbtweaks.util;

import org.apache.logging.log4j.Level;

import com.breakinblocks.bbtweaks.BBTweaks;
import com.breakinblocks.bbtweaks.ConfigHandler;
import com.breakinblocks.bbtweaks.ModInformation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.GuiOpenEvent;

/*
 * Class for most of your events to be registered in.
 * Remember that there are two different registries for Events. This one will not work for everything.
 */

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGuiOpen(GuiOpenEvent event) {
        if(event.getGui() instanceof GuiMainMenu && !BBTweaks.played) {
            BBTweaks.played = true;
            if(BBTweaks.playOn == 1 || BBTweaks.playOn == 3) {
            	SoundEvent sound = (SoundEvent)SoundEvent.REGISTRY.getObject(new ResourceLocation(BBTweaks.name));
                if(sound.getSoundName() != null) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sound, (float)BBTweaks.pitch));
                } else {
                    FMLLog.log("BBTweaks", Level.WARN, "Could not find sound: %s", new Object[]{new ResourceLocation(BBTweaks.name)});
                }
            }
        }

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onConnectToServer(ClientConnectedToServerEvent event) {
        BBTweaks.playWorld = true;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onWorldTick(WorldTickEvent event) {
        if(BBTweaks.playWorld && event.phase == Phase.END && Minecraft.getMinecraft().player != null && (Minecraft.getMinecraft().player.ticksExisted > 20 || Minecraft.getMinecraft().isGamePaused())) {
        	BBTweaks.playWorld = false;
            if(BBTweaks.playOn == 2 || BBTweaks.playOn == 3) {
                SoundEvent sound = (SoundEvent)SoundEvent.REGISTRY.getObject(new ResourceLocation(BBTweaks.nameWorld));
                if(sound != null) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sound, (float)BBTweaks.pitchWorld));
                } else {
                    FMLLog.log("BBTweaks", Level.WARN, "Could not find sound: %s", new Object[]{new ResourceLocation(BBTweaks.nameWorld)});
                }
            }
        }

    }
	
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.getModID().equals(ModInformation.ID)) {
            ConfigHandler.syncConfig();
            BBTweaks.logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.config.refresh"));
        }
    }
}
