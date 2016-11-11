package com.breakinblocks.bbtweaks;

/*
 * Check all the classes for (hopefully) detailed descriptions of what it does. There will also be tidbits of comments throughout the codebase.
 * If you wish to add a description to a class, or extend/change an existing one, submit a PR with your changes.
 */

import WayofTime.alchemicalWizardry.api.rituals.Rituals;
import WayofTime.alchemicalWizardry.common.renderer.AlchemyCircleRenderer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.breakinblocks.bbtweaks.blocks.BlockRecipeRegistry;
import com.breakinblocks.bbtweaks.blocks.BlockRegistry;
import com.breakinblocks.bbtweaks.client.gui.CreativeTabBaseMod;
import com.breakinblocks.bbtweaks.client.gui.GuiHandler;
import com.breakinblocks.bbtweaks.command.CommandBB;
import com.breakinblocks.bbtweaks.command.CommandSayLocation;
import com.breakinblocks.bbtweaks.items.ItemRecipeRegistry;
import com.breakinblocks.bbtweaks.items.ItemRegistry;
import com.breakinblocks.bbtweaks.proxies.CommonProxy;
import com.breakinblocks.bbtweaks.rituals.RitualEffectCulling;
import com.breakinblocks.bbtweaks.rituals.RitualEffectDev;
import com.breakinblocks.bbtweaks.rituals.RitualEffectNatureLeech;
import com.breakinblocks.bbtweaks.util.EventHandler;
import com.breakinblocks.bbtweaks.util.OreDictHandler;
import com.breakinblocks.bbtweaks.util.TextHelper;

@SuppressWarnings("unused")
@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.DEPEND, guiFactory = ModInformation.GUIFACTORY)
public class BBTweaks {
	 
	public static       boolean isDevEnv     = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	 
    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabBaseMod = new CreativeTabBaseMod(ModInformation.ID + ".creativeTab");
    public static Logger logger = LogManager.getLogger(ModInformation.NAME);

    @Mod.Instance
    public static BBTweaks instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.preInit"));

        ConfigHandler.init(event.getSuggestedConfigurationFile());

        ItemRegistry.registerItems();
        BlockRegistry.registerBlocks();

        OreDictHandler.registerOreDict();
        FMLCommonHandler.instance().bus().register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.init"));

        ItemRecipeRegistry.registerItemRecipes();
        BlockRecipeRegistry.registerBlockRecipes();
        
        if (isDevEnv)
        Rituals.registerRitual("ritualDev", 1, 1, new RitualEffectDev(), StatCollector.translateToLocal("ritual.bbtweaks.dev"));
        Rituals.registerRitual("ritualLeech", 1, 15000, new RitualEffectNatureLeech(), StatCollector.translateToLocal("ritual.bbtweaks.leech"), new AlchemyCircleRenderer(new ResourceLocation("alchemicalwizardry:textures/models/SimpleTransCircle.png"), 0, 0, 0, 255, 0, 0.501, 0.501, 0, 1.5, false));
        Rituals.registerRitual("ritualCulling", 2, 50000, new RitualEffectCulling(), StatCollector.translateToLocal("ritual.bbtweaks.culling"), new AlchemyCircleRenderer(new ResourceLocation("alchemicalwizardry:textures/models/SimpleTransCircle.png"), 0, 0, 0, 255, 0, 0.501, 0.501, 0, 1.5, false));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.postInit"));
        
        	 
    }
    
	@Mod.EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event)
	{
		
		event.registerServerCommand(new CommandSayLocation());
		event.registerServerCommand(new CommandBB());
	
	}
    
}
