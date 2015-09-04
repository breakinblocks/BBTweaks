package rocks.boltsandnuts.bbtweaks;

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
import rocks.boltsandnuts.bbtweaks.blocks.BlockRecipeRegistry;
import rocks.boltsandnuts.bbtweaks.blocks.BlockRegistry;
import rocks.boltsandnuts.bbtweaks.client.gui.CreativeTabBaseMod;
import rocks.boltsandnuts.bbtweaks.client.gui.GuiHandler;
import rocks.boltsandnuts.bbtweaks.command.CommandBB;
import rocks.boltsandnuts.bbtweaks.command.CommandNab;
import rocks.boltsandnuts.bbtweaks.command.CommandSayLocation;
import rocks.boltsandnuts.bbtweaks.items.ItemRecipeRegistry;
import rocks.boltsandnuts.bbtweaks.items.ItemRegistry;
import rocks.boltsandnuts.bbtweaks.proxies.CommonProxy;
import rocks.boltsandnuts.bbtweaks.rituals.RitualEffectCulling;
import rocks.boltsandnuts.bbtweaks.rituals.RitualEffectDev;
import rocks.boltsandnuts.bbtweaks.rituals.RitualEffectNatureLeech;
import rocks.boltsandnuts.bbtweaks.util.EventHandler;
import rocks.boltsandnuts.bbtweaks.util.OreDictHandler;
import rocks.boltsandnuts.bbtweaks.util.TextHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        BBTweaksGuide.buildGuide(); //Register GuideBook
        if (isDevEnv)
        	
        Rituals.registerRitual("ritualDev", 1, 1, new RitualEffectDev(), StatCollector.translateToLocal("ritual.bbtweaks.dev"));
        Rituals.registerRitual("ritualLeech", 1, 15000, new RitualEffectNatureLeech(), StatCollector.translateToLocal("ritual.bbtweaks.leech"), new AlchemyCircleRenderer(new ResourceLocation("alchemicalwizardry:textures/models/SimpleTransCircle.png"), 0, 0, 0, 255, 0, 0.501, 0.501, 0, 1.5, false));
        Rituals.registerRitual("ritualCulling", 1, 50000, new RitualEffectCulling(), StatCollector.translateToLocal("ritual.bbtweaks.culling"), new AlchemyCircleRenderer(new ResourceLocation("alchemicalwizardry:textures/models/SimpleTransCircle.png"), 0, 0, 0, 255, 0, 0.501, 0.501, 0, 1.5, false));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.postInit"));
        
        	 
    }
    
	@Mod.EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandNab());
		event.registerServerCommand(new CommandSayLocation());
		event.registerServerCommand(new CommandBB());
	
	}
    
}
