package rocks.boltsandnuts.bbtweaks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.BookBuilder;
import amerifrance.guideapi.categories.CategoryItemStack;
import amerifrance.guideapi.entries.EntryText;
import amerifrance.guideapi.pages.PageUnlocText;

public class BBTweaksGuide {

	public static Book myBook;
	
	public static void addPage(String title, List<EntryAbstract> category, Object... extra) {
	    ArrayList<IPage> pages = new ArrayList<IPage>();
	    for (Object obj : extra) {
	        if (obj instanceof IPage) {
	            pages.add((IPage) obj);
	        } else if (obj instanceof String) {
	            pages.add(new PageUnlocText("book.bbtweaks." + (String) obj));
	        }
	    }
	    category.add(new EntryText(pages, title));
	}
	
	public static void buildGuide() {
	    List<EntryAbstract> botania = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> mekanism   = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> bloodmagic  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> agricraft  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> rftools  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> buildcraft  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> extrautilities  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> oregen  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> commands  = new ArrayList<EntryAbstract>();
	    List<EntryAbstract> other  = new ArrayList<EntryAbstract>();
	    
	    addPage("Relics", botania, "botania.relics.body");
	    addPage("Manasteel", botania, "botania.manasteel.body");
	    addPage("Manapetals", botania, "botania.manapetals.body");
	    addPage("Flower Generation", botania, "botania.flowers.body");
	    addPage("Manatablet", botania, "botania.manatablet.body");
	    addPage("Manapools", botania, "botania.manapools.body");
	    addPage("The Endoflame", botania, "botania.endoflame.body");
	    
	    addPage("Universal Cables", mekanism, "mekanism.universalcables.body");
	    addPage("Compressed Materials", mekanism, "mekanism.compressedmaterials.body");
	    addPage("Digital Miner", mekanism, "mekanism.digitalminer.body");
	    addPage("Power Generation", mekanism, "mekanism.powergeneration.body");
	    addPage("Salt Production", mekanism, "mekanism.saltproduction.body");
	    
	    addPage("Blood Automation", bloodmagic, "bloodmagic.bloodautomation.body");
	    addPage("NOVA", bloodmagic, "bloodmagic.nova.body");
	    addPage("Ritual of Nature Leech", bloodmagic, "bloodmagic.natureleech.body");
	    addPage("Ritual of Culling", bloodmagic, "bloodmagic.culling.body");
	    
	    
	     addPage("Crop changes", agricraft, "agricraft.cropchanges.body");
	    
	    addPage("Disabled Items & Blocks", rftools, "rftools.disabled.body");
	    addPage("Blacklisted Dimlets", rftools, "rftools.dimlets.body");
	    addPage("Recipe Tweaks", rftools, "rftools.recipes.body");
	    
	    addPage("Quarry", buildcraft, "buildcraft.quarry.body");

	    addPage("Ender Quarry", extrautilities, "extrautilities.enderquarry.body");
	    addPage("The Legendary Kikoku", extrautilities, "extrautilities.kikoku.body");

	    addPage("Overworld", oregen, "oregen.overworld.body");
	    addPage("Nether", oregen, "oregen.nether.body");
	    addPage("End", oregen, "oregen.end.body");
	    addPage("InterGalactic", oregen, "oregen.intergalactic.body");
	    
	    addPage("/bb", commands, "commands.bb.body");
	    addPage("/nab", commands, "commands.nab.body");
	    addPage("/saylocation", commands, "commands.saylocation.body");
	    
	    
	    addPage("IndustrialCraft", other, "other.industrialcraft.body");
	    addPage("Immersive Engineering", other, "other.immersiveengineering.body");
	    addPage("Tinker's Construct", other, "other.tinkersconstruct.body");
	    addPage("Big Reactors", other, "other.bigreactors.body");
	    
	    ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(new CategoryItemStack(commands, "Commands", new ItemStack(Items.book)));
	    categories.add(new CategoryItemStack(botania, "Botania", new ItemStack(Items.flower_pot)));
        categories.add(new CategoryItemStack(mekanism, "Mekanism", new ItemStack(Items.iron_ingot)));
        categories.add(new CategoryItemStack(bloodmagic, "Blood Magic", new ItemStack(Items.spider_eye)));
        categories.add(new CategoryItemStack(agricraft, "Agricraft", new ItemStack(Items.ender_eye)));
        categories.add(new CategoryItemStack(rftools, "RF Tools", new ItemStack(Items.shears)));
        categories.add(new CategoryItemStack(buildcraft, "Buildcraft", new ItemStack(Items.hopper_minecart)));
        categories.add(new CategoryItemStack(extrautilities, "Extrautilities", new ItemStack(Items.glowstone_dust)));
        categories.add(new CategoryItemStack(oregen, "Oregen", new ItemStack(Items.stone_pickaxe)));
        categories.add(new CategoryItemStack(other, "Other", new ItemStack(Items.quartz)));
        
	    BookBuilder builder = new BookBuilder();
	    builder.setCategories(categories);
	    builder.setUnlocBookTitle("book.bbtweaks.title");
	    builder.setUnlocWelcomeMessage("book.bbtweaks.welcome");
	    builder.setUnlocDisplayName("book.bbtweaks.displayname");
	    builder.setBookColor(Color.BLUE);
	    builder.setSpawnWithBook(true);
	    myBook = builder.build();
	    GuideRegistry.registerBook(myBook);
	}
	

}
